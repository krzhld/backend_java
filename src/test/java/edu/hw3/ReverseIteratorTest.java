package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ReverseIteratorTest {
    @Test
    void testReverseIteratorListInts() {
        List<Integer> input = List.of(1, 10, 100);
        List<Integer> expectedResult = List.of(100, 10, 1);

        ReverseIterator<Integer> reverseIterator = new ReverseIterator<>(input);

        List<Integer> result = new ArrayList<>();
        while (reverseIterator.hasNext()) {
            result.add(reverseIterator.next());
        }

        assertThat(result).containsExactlyElementsOf(expectedResult);
    }

    @Test
    void testReverseIteratorListStrings() {
        List<String> input = List.of("1", "10", "100");
        List<String> expectedResult = List.of("100", "10", "1");

        ReverseIterator<String> reverseIterator = new ReverseIterator<>(input);

        List<String> result = new ArrayList<>();
        while (reverseIterator.hasNext()) {
            result.add(reverseIterator.next());
        }

        assertThat(result).containsExactlyElementsOf(expectedResult);
    }

    @Test
    void testReverseIteratorOnNullCollection() {
        List<String> input = null;

        assertThatIllegalArgumentException().isThrownBy(() -> new ReverseIterator<>(input));
    }
}
