package lumenaer;

import java.util.Arrays;
import java.util.List;

/**
 * Starter class for the lumenaer programm.
 *
 */
public class LumenaerStarter {

	/**
	 * main method to start lumenaer as an application
	 */
	public static void main(String args[]) {

		List<String> arguments = Arrays.asList(args);

		boolean hardwareMatrixMode;

		// command line arguments define the mode of operation:
		// should a computer screen or the HW-Matrix be used as display?
		if (arguments.size() > 0 && arguments.get(0).equals("true")) {
			hardwareMatrixMode = true; // display on HW-Matrix (serial communication)
		} else {
			hardwareMatrixMode = false; // "emulation mode": display on a computer screen
		}

		Lumenaer lumenaer = new Lumenaer(hardwareMatrixMode);
		lumenaer.setup();

		try {
			lumenaer.runForever();
		} catch (Exception e) {
			System.out.println("Programm aborted due to an exception!");
			e.printStackTrace();
		}
	}
		
}
