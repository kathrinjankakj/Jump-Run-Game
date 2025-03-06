package display;

import java.util.concurrent.TimeUnit;

import lumenaer.LumenaerConstants;
import com.fazecast.jSerialComm.SerialPort;

/**
 * This class contains methods to establish the
 * connection between computer and hardware screen.
 * Also the actual picture is sent by this class.
 *
 * @author Joachim Sommer
 */
public class HardwareMatrixCommunicator {

	private LumenaerConstants v_constants;

	// //////////////////////////////////////////
	// Variablen zur Umrechnung des Screens
	// //////////////////////////////////////////

	private int[][][] actualFrame;
	private int[][][] matrixColumn;

	private int translatedPixels = 0;
	private int screenCount = 0;
	private int rowCount = 0;
	private boolean reversedRow = false;
	private int screensFilled = 0;
	long startTime;
	int frame = 0;
	long averageTime = 0;
	int counter;


	// ////////////////////////////////////////////////////
	// Variablen fuer die Kommunikation mit dem Screen
	// ////////////////////////////////////////////////////

	private int baud = 115200;

	private SerialPort[] screenPorts;
	private int pureData;
	private int messageSize = pureData + 3;
	private byte startCommand = 0x03;
	private byte endCommand = 0x04;
	private byte[] test = { 0x0a };
	private byte[] showCommand = { 0x06 };
	private byte[] shutDownCommand = { 0x09 };
	private boolean commEst = false;

	// //////////////////////////
	// Konstruktor
	// //////////////////////////

	/**
	 * Creates an Object of the Type Screen and establishes the displayPixelMatrix.
	 */
	public HardwareMatrixCommunicator(LumenaerConstants p_constants) {
		v_constants = p_constants;
		pureData = v_constants.MESSAGESIZE * 3;
		messageSize = pureData + 3;
		actualFrame = new int[v_constants.SCREENCOUNT][v_constants.PIXELCOUNT / v_constants.SCREENCOUNT][3];
		matrixColumn = new int[v_constants.NUMBEROFSCREENS_X][v_constants.PIXELCOUNT / v_constants.SCREENCOUNT
				* v_constants.NUMBEROFSCREENS_Y][3];


		screenPorts = new SerialPort[v_constants.NUMBEROFSCREENS_X];
		SerialPort[] ports = SerialPort.getCommPorts();
		portCheck(ports);
	}

	/**
	 * Translates the actual picture to the Screen and sends it to the Teensy.
	 *
	 */

	public void translateToSingleScreen(int[] pixels) {
		startTime = System.currentTimeMillis();

		for (int pixel : pixels) {
			actualFrame[screenCount + screensFilled][translatedPixels][0] = (byte) ((pixel >> 16) & 0xff);
			actualFrame[screenCount + screensFilled][translatedPixels][1] = (byte) ((pixel >> 8) & 0xff);
			actualFrame[screenCount + screensFilled][translatedPixels][2] = (byte) (pixel & 0xff);

			if (reversedRow) {
				translatedPixels--;
				if (translatedPixels < v_constants.SINGLESCREENWIDTH + v_constants.SINGLESCREENWIDTH * (rowCount - 1)) {
					screenCount++;
					if (screenCount >= v_constants.NUMBEROFSCREENS_X) {
						screenCount = 0;
						rowCount++;
						reversedRow = false;
						translatedPixels += v_constants.SINGLESCREENWIDTH + 1;
						if (rowCount >= v_constants.SINGLESCREENHEIGHT) {
							screensFilled += v_constants.NUMBEROFSCREENS_X;
							translatedPixels = 0;
							rowCount = 0;
						}
					} else {
						translatedPixels += v_constants.SINGLESCREENWIDTH;
					}
				}

			} else {
				translatedPixels++;
				if (translatedPixels >= v_constants.SINGLESCREENWIDTH + v_constants.SINGLESCREENWIDTH * rowCount) {
					screenCount++;

					if (screenCount >= v_constants.NUMBEROFSCREENS_X) {
						screenCount = 0;
						rowCount++;
						reversedRow = true;
						translatedPixels += v_constants.SINGLESCREENWIDTH - 1;
					} else {
						translatedPixels -= v_constants.SINGLESCREENWIDTH;
					}
				}
			}

		}
		translatedPixels = 0;
		screenCount = 0;
		rowCount = 0;
		reversedRow = false;
		screensFilled = 0;

		translateToMatrixColumns();
	}

	private void translateToMatrixColumns() {
		int column = 0;
		int pixel = 0;
		for (int i = 0; i < v_constants.REALSCREENWIDTH * v_constants.REALSCREENHEIGHT; i++) {
			matrixColumn[column][pixel][0] = actualFrame[screenCount][translatedPixels][0] - 127;
			matrixColumn[column][pixel][1] = actualFrame[screenCount][translatedPixels][1] - 127;
			matrixColumn[column][pixel][2] = actualFrame[screenCount][translatedPixels][2] - 127;
			translatedPixels++;
			pixel++;
			if (translatedPixels >= v_constants.SINGLESCREENWIDTH * v_constants.SINGLESCREENHEIGHT) {
				translatedPixels = 0;
				screenCount += v_constants.NUMBEROFSCREENS_X;
				if (screenCount >= v_constants.NUMBEROFSCREENS_X * v_constants.NUMBEROFSCREENS_Y) {
					screenCount -= v_constants.NUMBEROFSCREENS_X * v_constants.NUMBEROFSCREENS_Y - 1;
					column++;
					pixel = 0;
				}
			}
		}

		screenCount = 0;
		translatedPixels = 0;
		sendToScreen();
	}

	private void portCheck(SerialPort[] ports) {
		try {
			commEst = openPorts(ports);
		} catch (NullPointerException ne) {
			System.err.println("No Ports available");
		}
	}

	// Etablierung der Kommunikation mit dem Screen
	private boolean openPorts(SerialPort[] ports) {
		int connectedPorts = 0;
		for (int i = 0; i < ports.length; i++) {
			try {
				if (ports[i].openPort()) {
					ports[i].setBaudRate(baud);
					ports[i].setNumDataBits(8);
					ports[i].setParity(0);
					ports[i].setNumStopBits(0);
					ports[i].setFlowControl(1);
					ports[i].openPort();
					ports[i].writeBytes(test, 1);
					Thread.sleep(1500); // give it time to respond

					if (ports[i].bytesAvailable() > 0) {
						byte[] answer = new byte[9];
						ports[i].readBytes(answer, 9);
						String concatAnswer = "";
						for (int j = 0; j < answer.length; j++) {
							concatAnswer += (char) answer[j];
						}

						System.out.println(concatAnswer);
						for (int j = 0; j < v_constants.NUMBEROFSCREENS_X; j++) {
							if (concatAnswer.equals("Lumenaer" + j)) {
								screenPorts[j] = ports[i];
								connectedPorts++;

							}

						}
					}
					if (connectedPorts >= v_constants.NUMBEROFSCREENS_X) {
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Senden des finalen Bildes
	private void sendToScreen() {
		int screenCounter = 0;
		int part = 0;
		if (commEst) {
			byte[][] message = new byte[v_constants.NUMBEROFSCREENS_X][messageSize];
			boolean sendComplete = false;
			while (!sendComplete) {
				message[screenCounter][0] = startCommand;
				for (int i = 0; i < v_constants.MESSAGESIZE; i++) {
					message[screenCounter][i * 3
							+ 1] = (byte) (matrixColumn[screenCounter][i + v_constants.MESSAGESIZE * part][0]);
					message[screenCounter][i * 3
							+ 2] = (byte) (matrixColumn[screenCounter][i + v_constants.MESSAGESIZE * part][1]);
					message[screenCounter][i * 3
							+ 3] = (byte) (matrixColumn[screenCounter][i + v_constants.MESSAGESIZE * part][2]);
				}
				message[screenCounter][pureData + 1] = (byte) part;
				message[screenCounter][pureData + 2] = endCommand;

				part++;
				if (part >= v_constants.SINGLESCREENWIDTH * v_constants.REALSCREENHEIGHT / v_constants.MESSAGESIZE) {
					screenCounter++;
					part = 0;
					if (screenCounter >= v_constants.NUMBEROFSCREENS_X) {
						sendComplete = true;
						for (int i = 0; i < screenPorts.length; i++) {
							try {
								screenPorts[i].writeBytes(message[i], messageSize);
								try {
//									Thread.sleep(1);
									TimeUnit.NANOSECONDS.sleep(1);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								screenPorts[i].writeBytes(showCommand, 1);
							} catch (NullPointerException ne) {
								System.err.println("Lost Screen Connection");
								commEst = false;
							}
						}
					}
				}
			}
		} else {
			resetScreen();
		}
	}

	/**
	 * Sends a signal to the Screen to switch off all pixels.
	 */
	// Sendet ein Signal an den Screen, aufgrund dessen der Screen abgeschaltet
	// wird.
	public void shutDown() {
		if (commEst) {
			for (int i = 0; i < screenPorts.length; i++) {
				try {
					screenPorts[i].writeBytes(shutDownCommand, 1);
				} catch (NullPointerException ne) {
					ne.printStackTrace();
				}
			}
		}

	}

	/**
	 * Reestablishes the displayPixelMatrix between Computer and Screen. This is needed
	 * if the Computer has gone to sleep or hibernate and the Teensy resetted.
	 */

	// Zuruecksetzen der Kommunikation
	public void resetScreen() {
		commEst = false;
		SerialPort[] ports = SerialPort.getCommPorts();
		portCheck(ports);

	}

}
