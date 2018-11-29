package com.example.jbao8.encryptionmp6;

import java.util.Scanner;

/**
 * A class that "encrypts" data through a simple transformation.
 * <p>
 * The provided code is incomplete. Modify it so that it works properly and passes the unit tests in
 * <code>EncryptTest.java</code>.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/1/">MP1 Documentation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Caesar_cipher">Caesar Cipher Documentation</a>
 */
public class Encrypt {
    /** Sets the minimum shift that the encrypter and decrypter need to handle.
     *
     */
    public static final int MIN_SHIFT = -1024;

    /** Sets the maximum shift that the encrypter and decrypter need to handle.
     *
     */
    public static final int MAX_SHIFT = 1024;

    /** Transformation start. */
    public static final int TRANSFORM_START = (int) ' ';

    /** Transformation end. */
    public static final int TRANSFORM_END = (int) '~';

    /** Modulo to use for our transformation. */
    public static final int TRANSFORM_MODULUS = TRANSFORM_END - TRANSFORM_START + 1;

    /** Value for finding charachter when shift causes looping. */
    public static final int VALUE_RIGNT_BEFORE_START_OF_ASCII = 31;


    /**
     * Encrypt a single line of text using a rotate-N transformation.
     * <p>
     * The printable range of ASCII characters starts at decimal value 32 (' ') and ends at 126
     * ('~'). You should shift characters within this range by the shift value provided. For
     * example, ' ' (32) shift 1 becomes '!' (33), while '~' (126) shift 1 wraps around and becomes
     * ' ' (32). You may want to explore modular arithmetic to simplify the transformation.
     * <p>
     * Your function should return a new character array, not modify the one that it is passed.
     * <p>
     * Both encrypter and decrypter may receive invalid inputs. If the character array contains invalid
     * characters (outside of the range defined above), or if the shift value is outside the range
     * defined above (e.g., larger than MAX_SHIFT), you should return null.
     * <p>
     * <strong>Your solution must match the expected output exactly, otherwise you will not receive
     * credit.</strong>
     * <p>
     * Complete the Javadoc comment for this function and write it.
     * @param line array of characters to decrypt.
     * @param shift amount to shift each character.
     * @return encryptedArray Array of encrypted characters.
     * @see <a href="http://www.asciitable.com/">ASCII Character Table</a>
     */
    public static char[] encrypter(final char[] line, final int shift) {
        char[] encryptedArray = new char[line.length];
        int minValueForPositiveShift = 0;
        int numberOfNewLetter = TRANSFORM_START;
        int loopingValue = 0;
        int zeroRemainder = 0;
        if (shift > MAX_SHIFT) {
            return null;
        } // return null if shift too big.
        if (shift < MIN_SHIFT) {
            return null;
        } // return null if shift too small.
        for (int positionInArray = 0; positionInArray < line.length; positionInArray++) {
            // loop through original array.
            if (line[positionInArray] < TRANSFORM_START || line[positionInArray] > TRANSFORM_END) {
                return null;
            } // return null if there is a bad value in the original array.
            if (shift >= minValueForPositiveShift) {
                if (line[positionInArray] + (shift % TRANSFORM_MODULUS) <= TRANSFORM_END) {
                    numberOfNewLetter = (int) line[positionInArray] + (shift % TRANSFORM_MODULUS);
                    encryptedArray[positionInArray] = (char) numberOfNewLetter;
                } else { // add shift directly to original letter if shift modulus 95 does not take over 126.
                    loopingValue = (int) line[positionInArray] + shift - VALUE_RIGNT_BEFORE_START_OF_ASCII;
                    int finalShiftInLoopIfShiftPos = loopingValue % TRANSFORM_MODULUS;
                    numberOfNewLetter = VALUE_RIGNT_BEFORE_START_OF_ASCII + finalShiftInLoopIfShiftPos;
                    encryptedArray[positionInArray] = (char) numberOfNewLetter;
                } //if positive shift takes you over 126.
            } else {
                if (shift % TRANSFORM_MODULUS == zeroRemainder) {
                    numberOfNewLetter = (int) line[positionInArray];
                    encryptedArray[positionInArray] = (char) numberOfNewLetter;
                    // if negative shift is multiple of 95, makes new value equal to old.
                } else if (line[positionInArray] + (shift % TRANSFORM_MODULUS) >= TRANSFORM_START) {
                    int negativelyLoopingValue = shift % TRANSFORM_MODULUS;
                    numberOfNewLetter = line[positionInArray] + negativelyLoopingValue;
                    encryptedArray[positionInArray] = (char) numberOfNewLetter;
                    // if negative shift after accounting for multiples of 95 does not take you under 32.
                } else {
                    loopingValue = shift % TRANSFORM_MODULUS;
                    int originalMinusRealShift = (int) line[positionInArray] + loopingValue;
                    int fromMaxPossAscii = originalMinusRealShift - VALUE_RIGNT_BEFORE_START_OF_ASCII;
                    numberOfNewLetter = TRANSFORM_END + fromMaxPossAscii;
                    encryptedArray[positionInArray] = (char) numberOfNewLetter;
                }
            }
        }
        return encryptedArray;
    }

    /**
     * Decrypt a single line of text using a rotate-N transformation.
     * <p>
     * See comment for encrypter above.
     *
     * @param line array of characters to decrypt
     * @param shift amount to shift each character
     * @return line decrypted by rotating the specified amount
     * @see <a href="http://www.asciitable.com/">ASCII Character Table</a>
     */
    public static char[] decrypter(final char[] line, final int shift) {
        if (shift > MAX_SHIFT) {
            return null;
        } // return null if shift too big.
        if (shift < MIN_SHIFT) {
            return null;
        } // return null if shift too small.
        return encrypter(line, -shift);
    }

    /* ********************************************************************************************
     * You do not need to modify code below this comment.
     **********************************************************************************************/

    /**
     * Solicits a single line of text from the user, encrypts it using a random shift, and then
     * decrypts it.
     * <p>
     * You are free to review this function, but should not modify it. Note that this function is
     * not tested by the test suite, as it is purely to aid your own interactive testing.
     *
     * @param unused unused input arguments
     */
    @SuppressWarnings("resource")
    public static void main(final String[] unused) {

        String linePrompt = "Enter a line of text, or a blank line to exit:";
        String shiftPrompt = "Enter an integer to shift by:";

        /*
         * Two steps here: first get a line, then a shift integer.
         */
        Scanner lineScanner = new Scanner(System.in);
        repeat: while (true) {
            String line = null;
            Integer shift = null;

            System.out.println(linePrompt);
            //noinspection LoopStatementThatDoesntLoop
            while (lineScanner.hasNextLine()) {
                line = lineScanner.nextLine();
                if (line.equals("")) {
                    break repeat;
                } else {
                    break;
                }
            }

            System.out.println(shiftPrompt);
            while (lineScanner.hasNextLine()) {
                Scanner intScanner = new Scanner(lineScanner.nextLine());
                if (intScanner.hasNextInt()) {
                    shift = intScanner.nextInt();
                    if (intScanner.hasNext()) {
                        shift = null;
                        System.out.println("Invalid input: please enter only a single integer.");
                    }
                } else {
                    System.out.println("Invalid input: please enter an integer.");
                }
                intScanner.close();
                if (shift != null) {
                    break;
                }
            }

            if (line == null || line.equals("")) {
                throw new RuntimeException("Should have a line at this point");
            }
            if (shift == null) {
                throw new RuntimeException("Should have a shift value at this point");
            }

            char[] originalCharacterArray = line.toCharArray();
            char[] encryptedCharacterArray = encrypter(originalCharacterArray, shift);
            char[] decryptedCharacterArray = decrypter(encryptedCharacterArray, shift);

            System.out.println("Encrypted line with ROT-" + shift + ":");
            assert encryptedCharacterArray != null;
            System.out.println(String.valueOf(encryptedCharacterArray));
            System.out.println("Decrypted line:");
            System.out.println(String.valueOf(decryptedCharacterArray));
            System.out.println("Original line:");
            System.out.println(String.valueOf(originalCharacterArray));
        }
        lineScanner.close();
    }
}
