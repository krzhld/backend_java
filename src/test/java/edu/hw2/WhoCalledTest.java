package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.hw2.who_called.CallingInfo;
import static edu.hw2.who_called.WhoCalled.callingInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class WhoCalledTest {
    @Test
    @DisplayName("Вызов функции из текущей директории")
    void testCallingInfo() {
        CallingInfo info = callingInfo();
        String result = info.className() + " " + info.methodName();

        String expectedResult = "edu.hw2.WhoCalledTest" + " " + "testCallingInfo";
        assertThat(result).isEqualTo(expectedResult);
    }
}
