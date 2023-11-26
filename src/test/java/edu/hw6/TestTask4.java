package edu.hw6;

import edu.hw6.task4.OutputStreamPipeline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask4 {
    @Test
    public void testOutputStreamComposition(@TempDir Path tempDir) {
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        Path path = Paths.get(tempDir.toString(), "test");
        String filePath = path.toString();

        OutputStreamPipeline.writeToFile(filePath, text);

        try (Stream<String> lines = Files.lines(path)) {
            String result = lines.collect(Collectors.joining());
            assertThat(result).isEqualTo(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
