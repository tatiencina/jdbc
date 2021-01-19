package db.inicial.util;

import java.util.Scanner;

public class InputUtil {
    private static InputUtil referenceToSingleInputObject = null;

    private Scanner scannerKeyboard;

    private InputUtil() { scannerKeyboard = new Scanner(System.in); }
    public static InputUtil getInstance() {
        if (referenceToSingleInputObject == null)
            referenceToSingleInputObject = new InputUtil();
        return referenceToSingleInputObject;
    }
    /**
     * Presents a message to the user and retrieves an int value.
     * @param sMessage reference to a String object whose contents will be displayed to the user as a prompt.
     * @return int value input from keyboard
     */
    public int getInt(String sMessage) {
        System.out.print(sMessage);
        while ( ! scannerKeyboard.hasNextInt()) { // peek into keyboard buffer to see if next token is a legitimate int
            System.out.println("Number is required input.");
            System.out.print(sMessage);
            scannerKeyboard.nextLine(); // clear bad input data from the keyboard
        }
        return scannerKeyboard.nextInt();
    }
    /**
     * Presents a prompt to the user and retrieves an <i>int</i> value which is within the range of <i>nLow</i> to <i>nHigh</i> (inclusive).
     * @param sMessage reference to a <i>String</i> object whose contents will be displayed to the user as a prompt.
     * @param nLow lower boundary on the range of legitimate values
     * @param nHigh upper boundary on the range of legitimate values
     * @return <i>int</i> value input from keyboard
     */
    public int getInt(String sMessage, int nLow, int nHigh) {
        int nInput;
        do {
            System.out.printf("%s (%d-%d): ", sMessage, nLow, nHigh);
            while ( ! scannerKeyboard.hasNextInt()) { // peek into keyboard buffer to see if next token is a legitimate int
                System.out.println("Number is required input.");
                System.out.print(sMessage);
                scannerKeyboard.nextLine(); // retrieves input to the next \r\n (line separator) and we choose to ignore the String that is created and returned
            }
            nInput = scannerKeyboard.nextInt();
            if (nInput >= nLow && nInput <= nHigh) // int value is within range, thus it is valid . . . time to break out of loop
                break;
            System.out.println("Value out of range. Try again.");
        } while (true);
        return nInput;
    } // end int getInt(String sMessage, int nLow, int nHigh)
    /**
     * Presents a prompt to the user and retrieves a <i>reference-to-String</i>.
     * @param sMessage reference to a <i>String</i> object whose contents will be displayed to the user as a prompt.
     * @return <i>reference-to-String</i> object created by keyboard input
     */
    public String getString(String sMessage) {
        System.out.print(sMessage);
        scannerKeyboard.useDelimiter("[\r\n/]"); // Setting this delimiter ensures that we capture everything up to the <Enter> key. Without this, input stops at the next whitespace (space, tab, newline etc.).
        String sInput = scannerKeyboard.next();
       scannerKeyboard.reset(); // The preceding use of useDelimiter() changed the state of the Scanner object. reset() re-establishes the original state.
        return sInput;
    } // end String getString(String sPrompt)
    /**
     * Presents a prompt to the user and retrieves a <i>boolean</i> value.
     * @param sMessage reference to a <i>String</i> object whose contents will be displayed to the user as a prompt.
     * @return <i>boolean</i> value input from keyboard
     */
    public boolean getBoolean(String sMessage) {
        System.out.print(sMessage);
        return scannerKeyboard.nextBoolean();
    }
}
