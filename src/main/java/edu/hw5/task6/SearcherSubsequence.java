package edu.hw5.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearcherSubsequence {
    private SearcherSubsequence() {

    }

    public static boolean isThereSubsequenceInSequence(String subSequence, String sequence) {
        StringBuilder regex = new StringBuilder(".*");
        for (int i = 0; i < subSequence.length(); i++) {
            regex.append(subSequence.charAt(i)).append(".*");
        }
        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(sequence);
        return matcher.find();
    }
}
