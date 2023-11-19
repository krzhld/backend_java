package edu.hw6;

import static edu.hw6.task5.HackerNews.hackerNewTopStories;
import static edu.hw6.task5.HackerNews.news;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class TestTask5 {
    @Test
    void test1() throws URISyntaxException, IOException, InterruptedException {
        long[] longs = hackerNewTopStories();
        System.out.println(Arrays.toString(longs));
    }

    @Test
    void test2() throws URISyntaxException, IOException, InterruptedException {
        System.out.println(news(37570036));
    }
}
