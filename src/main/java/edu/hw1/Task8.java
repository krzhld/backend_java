package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task8 {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static int[][] desk;

    private static boolean checkDirection(int col, int row, TypeMoves movement) {
        int shiftHorizontal = movement.getHorizontalShift();
        if ((col + shiftHorizontal) < 0 | (col + shiftHorizontal) >= COLS) {
            return false;
        }

        int shiftVertical = movement.getVerticalShift();
        if ((row + shiftVertical) < 0 | (row + shiftVertical) >= ROWS) {
            return false;
        }

        return desk[row + shiftVertical][col + shiftHorizontal] == ONE;
    }

    private enum TypeMoves {
        UP_RIGHT(ONE, -TWO),
        UP_LEFT(-ONE, -TWO),
        DOWN_RIGHT(ONE, TWO),
        DOWN_LEFT(-ONE, TWO),
        RIGHT_UP(TWO, -ONE),
        RIGHT_DOWN(TWO, ONE),
        LEFT_UP(-TWO, -ONE),
        LEFT_DOWN(-TWO, ONE);
        private final int horizontalShift;
        private final int verticalShift;

        TypeMoves(int horizontalShift, int verticalShift) {
            this.horizontalShift = horizontalShift;
            this.verticalShift = verticalShift;
        }

        public int getHorizontalShift() {
            return horizontalShift;
        }

        public int getVerticalShift() {
            return verticalShift;
        }
    }

    private Task8() {
    }

    private static boolean checkCorners() {
        boolean isHit = false;

        int curColumn = ZERO;
        int curRow = ZERO;
        if (desk[curColumn][curRow] == ONE) {
            isHit |= checkAllDirections(curColumn, curRow);
        }

        curColumn = ZERO;
        curRow = ROWS - ONE;
        if (desk[curColumn][curRow] == ONE) {
            isHit |= checkAllDirections(curColumn, curRow);
        }

        curColumn = COLS - ONE;
        curRow = ROWS - ONE;
        if (desk[curColumn][curRow] == ONE) {
            isHit |= checkAllDirections(curColumn, curRow);
        }

        curColumn = COLS - ONE;
        curRow = ZERO;
        if (desk[curColumn][curRow] == ONE) {
            isHit |= checkAllDirections(curColumn, curRow);
        }
        return isHit;
    }

    private static boolean checkAllDirections(int colIndex, int rowIndex) {
        return (checkDirection(colIndex, rowIndex, TypeMoves.UP_RIGHT)
            | checkDirection(colIndex, rowIndex, TypeMoves.UP_LEFT)
            | checkDirection(colIndex, rowIndex, TypeMoves.DOWN_RIGHT)
            | checkDirection(colIndex, rowIndex, TypeMoves.DOWN_LEFT)
            | checkDirection(colIndex, rowIndex, TypeMoves.RIGHT_UP)
            | checkDirection(colIndex, rowIndex, TypeMoves.LEFT_UP)
            | checkDirection(colIndex, rowIndex, TypeMoves.RIGHT_DOWN)
            | checkDirection(colIndex, rowIndex, TypeMoves.LEFT_DOWN)
        );
    }

    static boolean knightBoardCapture(int[][] inputDesk) {
        desk = inputDesk;
        if (checkCorners()) {
            return false;
        }
        for (int curColumn = TWO; curColumn < COLS - TWO; ++curColumn) {
            for (int curRow = TWO; curRow < ROWS - TWO; ++curRow) {
                if (desk[curColumn][curRow] == ONE) {
                    if (checkAllDirections(curColumn, curRow)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
