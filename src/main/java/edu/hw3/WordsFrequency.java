package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class WordsFrequency {
    private WordsFrequency() {
    }

    public static <T> Map<T, Integer> freqDict(T[] a) {
        Map<T, Integer> answer = new HashMap<>();
        for (T t : a) {
            answer.merge(t, 1, Integer::sum);
        }
        return answer;
    }
}
