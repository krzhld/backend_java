package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class TreeAndNullTest {
    @Test
    void testComparatorWithNull() {
        Comparator<String> comparator = new ComparatorWithNull<>();
        TreeMap<String, String> tree = new TreeMap<>(comparator);
        tree.put(null, "something");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
