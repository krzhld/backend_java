package edu.hw3;

public class AtbashCode {
    private AtbashCode() {
    }

    public static String atbash(String input) {
        StringBuilder answer = new StringBuilder();
        char curChar;
        for (int i = 0; i < input.length(); ++i) {
            curChar = input.charAt(i);
            if (isLatinLetter(curChar)) {
                if (Character.isLowerCase(curChar)) {
                    answer.append((char) ('a' + 'z' - curChar));
                } else {
                    answer.append((char) ('A' + 'Z' - curChar));
                }
            } else {
                answer.append(curChar);
            }
        }
        return answer.toString();

    }

    private static boolean isLatinLetter(char curChar) {
        return (('a' <= curChar && curChar <= 'z') || ('A' <= curChar && curChar <= 'Z'));
    }
}
