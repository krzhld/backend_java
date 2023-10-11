package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import static java.util.Arrays.sort;

public final class Task6 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task6() {
    }

    final private static int KAPREKAR = 6174;
    final private static int FOUR = 4;
    private static int kaprekarIterations = 0;

    private static int doKaprekarProcedure(int number) {
        int[] arrayNumber = new int[FOUR];
        for (int i = 1; i <= FOUR; ++i) {
            arrayNumber[FOUR - i] = number % 10;
            number /= 10;
        }
        sort(arrayNumber);
        int max = 0;
        int min = 0;
        for (int i = 0; i < FOUR; ++i) {
            min = 10 * min + arrayNumber[i];
            max = 10 * max + arrayNumber[FOUR - 1 - i];
        }
        return max - min;
    }

    static int countK(int number) {
        if (number <= 1000 | number >= 10000)
            return -1;
        if (number == KAPREKAR) {
            int answer = kaprekarIterations;
            kaprekarIterations = 0;
            return answer;
        }
        ++kaprekarIterations;
        return countK(doKaprekarProcedure(number));
    }

    public static void main(String[] args) {
        LOGGER.info(countK(1000));
        LOGGER.info(countK(6554));
        LOGGER.info(countK(1234));
    }
}
