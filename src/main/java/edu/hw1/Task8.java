package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task8 {
    private final static Logger LOGGER = LogManager.getLogger();

    final private static int ROWS = 8;
    final private static int COLS = 8;
    final private static int ZERO = 0;
    final private static int ONE = 1;
    final private static int TWO = 2;
    private static int[][] desk;

    private static boolean checkDirection(int col, int row, TypeMoves movement) {
        int shiftHorizontal = movement.getHorizontalShift();
        int shiftVertical = movement.getVerticalShift();
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
        int curColumn = ZERO;
        int curRow = ZERO;
        boolean isHit = false;
        if (desk[curColumn][curRow] == ONE) {
            isHit |= checkDirection(curColumn, curRow, TypeMoves.DOWN_RIGHT)
                | checkDirection(curColumn, curRow, TypeMoves.RIGHT_DOWN);
        }

        curColumn = ZERO;
        curRow = ROWS - ONE;
        if (desk[curColumn][curRow] == ONE) {
            isHit |= checkDirection(curColumn, curRow, TypeMoves.UP_RIGHT)
                | checkDirection(curColumn, curRow, TypeMoves.RIGHT_UP);
        }

        curColumn = COLS - ONE;
        curRow = ROWS - ONE;
        if (desk[curColumn][curRow] == ONE) {
            isHit |= checkDirection(curColumn, curRow, TypeMoves.UP_LEFT)
                | checkDirection(curColumn, curRow, TypeMoves.LEFT_UP);
        }

        curColumn = COLS - ONE;
        curRow = ZERO;
        if (desk[curColumn][curRow] == ONE) {
            isHit |= checkDirection(curColumn, curRow, TypeMoves.DOWN_LEFT)
                | checkDirection(curColumn, curRow, TypeMoves.LEFT_DOWN);
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

    public static void main(String[] args) {
        int[][] desk1 = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        LOGGER.info(knightBoardCapture(desk1));

        int[][] desk2 = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };
        LOGGER.info(knightBoardCapture(desk2));

        int[][] desk3 = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };
        LOGGER.info(knightBoardCapture(desk3));
    }
}
