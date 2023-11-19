package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;
import static java.time.temporal.ChronoUnit.SECONDS;

@SuppressWarnings("MultipleStringLiterals")
public class HackerNews {
    private HackerNews() {

    }

    private static final String ENDPOINT_TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ENDPOINT_NUMBER_STORY = "https://hacker-news.firebaseio.com/v0/item/#.json";

    private final static int TIMEOUT_SEC = 10;

    public static long[] hackerNewTopStories() throws URISyntaxException, IOException, InterruptedException {
        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI(ENDPOINT_TOP_STORIES))
                .GET()
                .timeout(Duration.of(TIMEOUT_SEC, SECONDS))
                .build();

            var response = newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
            var body = response.body();

            return Arrays.stream(
                    body.substring(1, body.length() - 1)
                        .split(",")
                )
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .toArray();

        } catch (URISyntaxException e) {
            throw new URISyntaxException(ENDPOINT_TOP_STORIES, "Problems with URI-structure of endpoint!");
        } catch (InterruptedException e) {
            throw new InterruptedException("Problems with sending request!");
        } catch (IOException e) {
            throw new IOException("Problems with sending request!");
        }
    }

    public static String news(long id) throws URISyntaxException, IOException, InterruptedException {
        StringBuilder stringBuilder = new StringBuilder(ENDPOINT_NUMBER_STORY);
        int index = stringBuilder.indexOf("#");
        stringBuilder.replace(index, index + 1, String.valueOf(id));

        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI(stringBuilder.toString()))
                .GET()
                .timeout(Duration.of(TIMEOUT_SEC, SECONDS))
                .build();

            var response = newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
            var body = response.body();

            Pattern pattern = Pattern.compile("\"title\":\"([\\w\\s]*)\"");
            Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return "NO_TITLE";
            }

        } catch (URISyntaxException e) {
            throw new URISyntaxException(ENDPOINT_NUMBER_STORY, "Problems with URI-structure of endpoint!");
        } catch (InterruptedException e) {
            throw new InterruptedException("Problems with sending request!");
        } catch (IOException e) {
            throw new IOException("Problems with sending request!");
        }
    }

}
