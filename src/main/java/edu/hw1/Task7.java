package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task7 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task7() {
    }

    private static final int TEN = 10;
    private static final int TWO = 2;

    static int rotateRight(int n, int shift) {
        String strBinary = Integer.toBinaryString(n);
        int moduleShift = shift % strBinary.length();

        String suffix = strBinary.substring(strBinary.length() - moduleShift);
        String prefix = strBinary.substring(0, strBinary.length() - moduleShift);

        return Integer.parseInt(suffix + prefix, 2);
    }

    static int rotateLeft(int n, int shift) {
        String strBinary = Integer.toBinaryString(n);
        int moduleShift = shift % strBinary.length();

        String suffix = strBinary.substring(moduleShift);
        String prefix = strBinary.substring(0, moduleShift);

        return Integer.parseInt(suffix + prefix, 2);
    }
}
