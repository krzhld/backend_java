package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task7 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task7() {
    }

    private final static int TEN = 10;
    private final static int TWO = 2;

    private static int binaryToDecimal(long decimalNumber) {
        long curDecimalNumber = decimalNumber;
        int binaryNumber = 0;
        int i = 0;
        while (curDecimalNumber != 0) {
            binaryNumber += (int) (Math.pow(TWO, i) * (curDecimalNumber % TEN));
            curDecimalNumber /= TEN;
            ++i;
        }
        return binaryNumber;
    }

    static int rotateRight(int n, int shift) {
        String strBinary = Integer.toBinaryString(n);
        int moduleShift = shift % strBinary.length();

        String suffix = strBinary.substring(strBinary.length() - moduleShift);
        String prefix = strBinary.substring(0, strBinary.length() - moduleShift);

        return binaryToDecimal(Long.parseLong(suffix + prefix));
    }

    static int rotateLeft(int n, int shift) {
        String strBinary = Integer.toBinaryString(n);
        shift = shift % strBinary.length();

        String suffix = strBinary.substring(shift);
        String prefix = strBinary.substring(0, shift);

        return binaryToDecimal(Long.parseLong(suffix + prefix));
    }

    public static void main(String[] args) {
    }
}
