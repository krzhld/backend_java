package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw3.ClusteringBrackets.clusterize;
import java.util.List;

public class ClusteringBracketsTest {
    @Test
    void testClustering1() {
        String input = "()()()";
        List<String> expectedResult = List.of("()", "()", "()");
        assertThat(clusterize(input)).isEqualTo(expectedResult);
    }

    @Test
    void testClustering2() {
        String input = "((()))";
        List<String> expectedResult = List.of("((()))");
        assertThat(clusterize(input)).isEqualTo(expectedResult);
    }

    @Test
    void testClustering3() {
        String input = "((()))(())()()(()())";
        List<String> expectedResult = List.of("((()))", "(())", "()", "()", "(()())");
        assertThat(clusterize(input)).isEqualTo(expectedResult);
    }

    @Test
    void testClustering4() {
        String input = "((())())(()(()()))";
        List<String> expectedResult = List.of("((())())", "(()(()()))");
        assertThat(clusterize(input)).isEqualTo(expectedResult);
    }

    @Test
    void testClusteringWrongBracketStructure() {
        String input = "(((";
        assertThatIllegalStateException().isThrownBy(() -> clusterize(input));
    }

    @Test
    void testClusteringNullableString() {
        String input = null;
        assertThatNullPointerException().isThrownBy(() -> clusterize(input));
    }
}
