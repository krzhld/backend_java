package edu.hw6;

import static edu.hw6.task2.ClonerFile.cloneFile;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;

public class TestTask2 {
    @Test
    void test1() throws IOException {
        Path path = Path.of("uwu - копия.txt");
        cloneFile(path);
        cloneFile(path);
        cloneFile(path);
        cloneFile(path);
        cloneFile(path);
    }
}
