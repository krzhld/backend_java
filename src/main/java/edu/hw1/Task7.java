package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task7 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task7() {
    }

    private static int binaryToDecimal(long decimalNumber) {
        int binaryNumber = 0;
        int i = 0;
        while (decimalNumber != 0) {
            binaryNumber += (int) (Math.pow(2, i) * (decimalNumber % 10));
            decimalNumber /= 10;
            ++i;
        }
        return binaryNumber;
    }

    static int rotateRight(int n, int shift) {
        String strBinary = Integer.toBinaryString(n);
        shift = shift % strBinary.length();

        String suffix = strBinary.substring(strBinary.length() - shift);
        String prefix = strBinary.substring(0, strBinary.length() - shift);

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
        LOGGER.info(rotateRight(1111, 0));
        //LOGGER.info(rotateLeft(16, 1));
        //LOGGER.info(rotateLeft(17, 2));
    }
}
