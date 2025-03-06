package lumenaer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import DegnRun.Start;
import display.HardwareMatrixCommunicator;
import display.PixelMatrixPanel;

/**
 * The lumenaer framework class for setting up games and displaying them either on
 * a real 24x24 pixel matrix or in a window for development purposes.
 */
public class Lumenaer {

    /* flag that determines whether the output shoud be on the real HW-Matrix*/
    private final boolean SENDTOMATRIX;

    /* constants encoding the physical dimensions of the HW-screen*/
    private LumenaerConstants lumenaerConstants;

    /* variables for display on a computer screen*/
    private JFrame frame;
    private JPanel drawPanel;

    /* serial communication with the HW-Matrix*/
    private HardwareMatrixCommunicator hwCommunicator;

    /* the pixel matrix representing an abstract screen */
    private PixelMatrix pixelMatrix;

    /* the game instance currently running*/
    private Game currentGame;

    /**
     * Constructs Lumenaer instance, decide where the game should be shown
     *
     * @param hwMatrixMode whether the game should run on the hardware screen
     */
    public Lumenaer(boolean hwMatrixMode) {
        SENDTOMATRIX = hwMatrixMode;
        lumenaerConstants = new LumenaerConstants();
    }

    /**
     * Run loop: runs forever, unless interrupted. Triggers the next game step
     * and displays the current pixelMatrix (depending on the operation mode)
     *
     * @throws InterruptedException
     */
    void runForever() throws InterruptedException {

        long start;

        while (true) {
            start = System.currentTimeMillis();
            currentGame.nextGameStep();
            displayPixelMatrix();
            Thread.sleep(start + LumenaerConstants.MS_PER_FRAME - System.currentTimeMillis());
        }
    }

    /**
     * Initializes everything necessary for displaying the game on either the
     * hardware matrix or the computer screen
     * Even for the hardware mode, a JFrame must be constructed in order to be
     * able to handle the Mouse events (mouseListeners have to be registered
     * somewhere...)
     */
    public void setup() {

        frame = new JFrame("Lumenaer " + LumenaerConstants.VERSIONNUMBER);

        pixelMatrix = new PixelMatrix(lumenaerConstants.REALSCREENHEIGHT, lumenaerConstants.REALSCREENWIDTH);

        // register listeners, so that the current Game can be informed about changes
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                currentGame.buzzered();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentGame.buzzerReleased();
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                currentGame.wheelRotation(e.getWheelRotation());
            }
        };
        frame.addMouseListener(mouseAdapter);
        frame.addMouseWheelListener(mouseAdapter);

        // if the came should be displayed on the lumenaer hardware matrix
        if (SENDTOMATRIX) {

            //setUp hardware displayPixelMatrix
            hwCommunicator = new HardwareMatrixCommunicator(lumenaerConstants);

            // make empty frame, position mouse on it, so that mouse events get captured
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            // the mouse pointer must be over the window so that mouse events are recognized...
            try {
                Robot initialMousePositioner = new Robot();
                initialMousePositioner.mouseMove(200, 200);
            } catch (Exception e) {
                System.out.println("Mouse could not be positioned...");
            }

        } else {
            // if the pixel matrix should be displayed on the local computer
            // create a swing panel and display pixelMatrix on the computer screen
            int scaleFactor = 30; // enlarge every pixel to 30x30 pixel
            drawPanel = new PixelMatrixPanel(pixelMatrix, scaleFactor);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(drawPanel);
            frame.pack();
        }

        //bring the panel to front
        frame.setVisible(true);
        frame.toFront();

        // later on: logic to change the current game
        // for now: insert here the class name of the game to be started
        currentGame = new Start(pixelMatrix);

        prepareExitHandler();
    }


    /**
     * Draws the actual frame or sends it to the hardware matrix, depending on the mode
     */
    public void displayPixelMatrix() {

        // Aufrufen der Send Methode und Übergabe des aktuellen Bildes
        if (SENDTOMATRIX) {
            hwCommunicator.translateToSingleScreen(pixelMatrix.getPixels());
        } else {
            drawPanel.repaint();
        }
    }


    /**
     * Shuts down the screen if window is closed
     */
    private void prepareExitHandler() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                if (SENDTOMATRIX) {
                    hwCommunicator.shutDown();
                }
            }
        }));
    }


}
