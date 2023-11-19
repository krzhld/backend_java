package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiskMap {
    private final static Logger LOGGER = LogManager.getLogger();

    public DiskMap() throws IOException {
        this.diskMap = new HashMap<String, String>();
        initFile();
    }

    private Map<String, String> diskMap;
    private final String FILE_DISK_MAP = "diskMap.txt";

    private void initFile() throws IOException {
        File file = new File("diskMap.txt");
        try {
            if (file.createNewFile()) {
                LOGGER.info("File diskMap.txt is created!");
            } else {
                LOGGER.info("File already exists! Rewriting.");
                Files.newBufferedWriter(Path.of(FILE_DISK_MAP), StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveData(String key, String data) throws IOException {
        if (diskMap.containsKey(key)) {
            throw new RuntimeException("diskMap already contains this key. Try another!");
        } else {
            diskMap.put(key, data);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_DISK_MAP, true);
                StringBuilder stringBuilder = new StringBuilder(key);
                stringBuilder.append(":").append(data).append("\n");
                fileOutputStream.write(stringBuilder.toString().getBytes());
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("Cant create fileOutputStream!");
            } catch (IOException e) {
                throw new IOException("Cant write string to file!");
            }
        }
    }

    public String loadData(String key) throws IOException {
        if (!diskMap.containsKey(key)) {
            return "";
        } else {
            try {
                File file = new File(FILE_DISK_MAP);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                Pattern pattern = Pattern.compile(key + ":");
                Matcher matcher = pattern.matcher(line);
                while (line != null) {
                    if (matcher.find()) {
                        String[] result = line.split(":");
                        return result[1];
                    } else {
                        line = bufferedReader.readLine();
                        matcher = pattern.matcher(line);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("Cant create fileReader!");
            } catch (IOException e) {
                throw new IOException("Cant write string to file!");
            }
        }
        return "";
    }
}
