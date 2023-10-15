package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.util.Arrays.sort;

public final class Task6 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task6() {
    }

    private static final int KAPREKAR = 6174;
    private static final int FOUR = 4;
    private static int kaprekarIterations = 0;
    private static final int TEN = 10;
    private static final int THOUSAND = 1000;
    private static final int TEN_THOUSAND = 10000;

    private static int doKaprekarProcedure(int number) {
        int curNumber = number;
        int[] arrayNumber = new int[FOUR];
        for (int i = 1; i <= FOUR; ++i) {
            arrayNumber[FOUR - i] = curNumber % TEN;
            curNumber /= TEN;
        }
        sort(arrayNumber);
        int max = 0;
        int min = 0;
        for (int i = 0; i < FOUR; ++i) {
            min = TEN * min + arrayNumber[i];
            max = TEN * max + arrayNumber[FOUR - 1 - i];
        }
        return max - min;
    }

    static int countK(int number) {
        if (number <= THOUSAND | number >= TEN_THOUSAND) {
            return -1;
        }
        if (number == KAPREKAR) {
            int answer = kaprekarIterations;
            kaprekarIterations = 0;
            return answer;
        }
        ++kaprekarIterations;
        return countK(doKaprekarProcedure(number));
    }
}
