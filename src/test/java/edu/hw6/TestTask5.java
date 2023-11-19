package edu.hw6;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;

public class TestTask5 {
    @Test
    public void testHackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        long[] news = HackerNews.hackerNewTopStories();

        assertThat(news).isNotEmpty();
    }

    @Test
    public void testNews() throws URISyntaxException, IOException, InterruptedException {
        long storyId = 37570037;

        String title = HackerNews.news(storyId);

        String expectedResult = "JDK 21 Release Notes";
        assertThat(title).isEqualTo(expectedResult);
    }
}
