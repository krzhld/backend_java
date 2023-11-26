package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class OutputStreamPipeline {

    private OutputStreamPipeline() {
    }

    public static void writeToFile(String filePath, String text) {
        try (OutputStream outputStream = new FileOutputStream(filePath);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
