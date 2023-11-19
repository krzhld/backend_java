package edu.hw6.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClonerFile {
    private ClonerFile() {

    }

    public static void cloneFile(Path path) throws IOException {
        File file = new File(path.toUri());
        if (file.exists()) {
            String[] parsePath = path.toString().split("\\.");
            StringBuilder pathToCopy = new StringBuilder(parsePath[0]);

            File copyFile = new File(pathToCopy.append(" - копия.").append(parsePath[1]).toString());
            try {
                if (!copyFile.exists()) {
                    Files.copy(path, copyFile.toPath());
                    return;
                }
                int i = 2;
                while (true) {
                    pathToCopy = new StringBuilder(parsePath[0]);
                    copyFile = new File(pathToCopy.append(" - копия (").append(i).append(").").append(parsePath[1])
                        .toString());
                    if (!copyFile.exists()) {
                        Files.copy(path, copyFile.toPath());
                        return;
                    }
                    i += 1;
                }
            } catch (IOException e) {
                throw new IOException("Cant copy file!");
            }
        }
    }
}
