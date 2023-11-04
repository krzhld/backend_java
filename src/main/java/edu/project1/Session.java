package edu.project1;

import java.util.Arrays;

public class Session {
    private String wordToGuess;
    private char[] wordChars;
    private int maxAttempts;
    private int currentAttempts;
    private static final int DEFAULT_MAX_ATTEMPTS = 5;

    public Session(String wordToGuess, int maxAttempts) {
        this.wordToGuess = wordToGuess;
        this.maxAttempts = maxAttempts;
        this.currentAttempts = 0;
    }

    public Session(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.maxAttempts = DEFAULT_MAX_ATTEMPTS;
        this.currentAttempts = 0;
    }

    public Session() {

    }

    public void incrementAttempts() {
        ++currentAttempts;
    }

    public void initWordChars() {
        wordChars = new char[wordToGuess.length()];
        Arrays.fill(wordChars, '*');
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public void setAttempts(int attempts) {
        this.currentAttempts = attempts;
    }

    public String printWord() {
        return new String(wordChars);
    }

    public int getCurrentAttempts() {
        return currentAttempts;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public char[] getWordChars() {
        return wordChars;
    }

    public char getCharOfWordChars(int index) {
        return wordChars[index];
    }

    public void setCharInWordChars(int index, char letter) {
        wordChars[index] = letter;
    }

}
