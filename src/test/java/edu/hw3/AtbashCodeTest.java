package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static edu.hw3.AtbashCode.atbash;

public class AtbashCodeTest {
    @Test
    void testAtbashEnglish() {
        String input = "Hello world!";
        assertThat(atbash(input)).isEqualTo("Svool dliow!");
    }

    @Test
    void testAtbashRussian() {
        String input = "Привет, мир!";
        assertThat(atbash(input)).isEqualTo("Привет, мир!");
    }

    @Test
    void testAtbashEnglishAndRussian() {
        String input = "Привет, world!11";
        assertThat(atbash(input)).isEqualTo("Привет, dliow!11");
    }

    @Test
    void testAtbashNumerals() {
        String input = "123456";
        assertThat(atbash(input)).isEqualTo(input);
    }
}
