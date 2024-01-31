package edu.hw7;

import edu.hw7.task4.ApproximatorPiMultiThreads;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/*
кол-во потоков | кол-во симуляций |  результат   |  время (мсек)
      1        |      1000000     |  3,139516    |  55.1843
      1        |      10000000    |  3,1413      |  211.9001
      1        |      100000000   |  3,14185496  |  1784.1633
      2        |      1000000     |  3,143956    |  9.5995
      2        |      10000000    |  3,141868    |  81.5067
      2        |      100000000   |  3,14171116  |  891.7068
      3        |      1000000     |  3,141119142 |  6.8987
      3        |      10000000    |  3,141811515 |  63.0938
      3        |      100000000   |  3,141918392 |  618.1513
      4        |      1000000     |  3,141116    |  6.568
      4        |      10000000    |  3,141566    |  51.7634
      4        |      100000000   |  3,14167792  |  493.1709
*/

public class TestTask4 {
    @Test
    public void testApproximatorPiMultiThreads() {

        int n = 10000;
        int threadsAmount = 3;

        ApproximatorPiMultiThreads appMulti = new ApproximatorPiMultiThreads();
        double result = appMulti.approximate(n, threadsAmount);

        assertThat(result).isBetween(3.13, 3.15);
    }
}
