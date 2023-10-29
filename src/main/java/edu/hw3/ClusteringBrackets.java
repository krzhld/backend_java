package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class ClusteringBrackets {
    private ClusteringBrackets() {
    }

    public static List<String> clusterize(String input) {
        if (input == null) {
            throw new NullPointerException("Nullable string!");
        }
        if (!input.matches("[()]+")) {
            throw new IllegalStateException("Incorrect input!");
        }
        List<String> answerList = new ArrayList<>();
        StringBuilder currElem = new StringBuilder();
        int bracketCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                ++bracketCounter;
            } else { // else if c == ')'
                --bracketCounter;
            }
            currElem.append(c);
            if (bracketCounter == 0) {
                answerList.add(currElem.toString());
                currElem.setLength(0);
            } else if (bracketCounter < 0) {
                throw new IllegalStateException("Incorrect bracket structure!");
            }
        }
        if (bracketCounter != 0) {
            throw new IllegalStateException("Incorrect bracket structure!");
        }
        return answerList;
    }
}
