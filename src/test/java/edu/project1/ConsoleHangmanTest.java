package edu.project1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ConsoleHangmanTest {

    private static ConsoleHangman game;
    private static Dictionary dictionary;

    @BeforeAll
    public static void setUp() {
        StringBuilder output = new StringBuilder();
        dictionary = new Dictionary();
        game = new ConsoleHangman(dictionary, output);
    }

    @BeforeEach
    public void clearDict() {
        dictionary.clear();
    }

    @Test
    void incorrectWordLength() {
        String incorrectWord = "";

        assertThatThrownBy(() -> dictionary.addWord(incorrectWord));
    }

    @Test
    void tooLongWord() {
        String incorrectWord = "a".repeat(40);

        dictionary.addWord(incorrectWord);
        game.start();
        String result = game.getCurrentState();

        assertThat(result).isEqualTo("Cannot start a game: too long word!\n");
        game.end();
    }

    @Test
    void loseBeforeAttemptsLeft() {
        String word = "java";
        String input = "b";
        int attempts = 1;

        dictionary.addWord(word);
        game.start();
        game.getCurSession().setMaxAttempts(attempts);
        game.makeMove(input);
        String[] result = game.getCurrentState().split("\n");

        //then
        String[] expectedResult =
            {"Guess a letter:", "Missed, mistake 1 out of 1.", "", "The word: ****", "", "You lose!"};
        assertThat(result).containsExactly(expectedResult);
    }

    @Test
    void correctStateChangeWhenWin() {
        String word = "java";
        String[] moves = {"j", "a", "v"};
        int attempts = 1;

        dictionary.addWord(word);
        game.start();
        game.getCurSession().setMaxAttempts(attempts);
        for (int i = 0; i < moves.length; i++) {
            game.makeMove(moves[i]);
        }
        String[] result = game.getCurrentState().split("\n");

        String[] expectedResult = {"Guess a letter:", "Hit!", "", "The word: j***", "",
            "Guess a letter:", "Hit!", "", "The word: ja*a", "",
            "Guess a letter:", "Hit!", "", "The word: java", "",
            "You won!"};
        assertThat(result).containsExactly(expectedResult);
    }

    @Test
    void correctStateChangeWhenLose() {
        String word = "java";
        String[] moves = {"b", "c", "h"};
        int attempts = 3;

        dictionary.addWord(word);
        game.start();
        game.getCurSession().setMaxAttempts(attempts);
        for (int i = 0; i < moves.length; i++) {
            game.makeMove(moves[i]);
        }
        String[] result = game.getCurrentState().split("\n");

        String[] expectedResult = {
            "Guess a letter:", "Missed, mistake 1 out of 3.", "", "The word: ****", "",
            "Guess a letter:", "Missed, mistake 2 out of 3.", "", "The word: ****", "",
            "Guess a letter:", "Missed, mistake 3 out of 3.", "", "The word: ****", "",
            "You lose!"};
        assertThat(result).containsExactly(expectedResult);
    }

    @Test
    void inputIsNotChar() {
        String word = "x";
        String[] moves = {"aa", "bbbb", "cccccc", "x"};
        int attempts = 1;

        dictionary.addWord(word);
        game.start();
        game.getCurSession().setMaxAttempts(attempts);
        for (int i = 0; i < moves.length; i++) {
            game.makeMove(moves[i]);
        }
        String[] result = game.getCurrentState().split("\n");

        String[] expectedResult = {"Guess a letter:",
            "Guess a letter:",
            "Guess a letter:",
            "Guess a letter:",
            "Hit!", "", "The word: x", "", "You won!"};
        assertThat(result).containsExactly(expectedResult);
    }

    @Test
    void earlyGameEnd() {
        String word = "java";
        String[] moves = {"x", "exit", "t"};
        int attempts = 3;

        dictionary.addWord(word);
        game.start();
        game.getCurSession().setMaxAttempts(attempts);
        for (int i = 0; i < moves.length; i++) {
            game.makeMove(moves[i]);
        }
        String[] result = game.getCurrentState().split("\n");

        //then
        String[] expectedResult = {
            "Guess a letter:", "Missed, mistake 1 out of 3.", "", "The word: ****", "",
            "Guess a letter:", "Game is finished.", "Game is not started yet!"};
        assertThat(result).containsExactly(expectedResult);
    }
}
