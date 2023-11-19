package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class TestTask1 {
    @Test
    void testDiskMap() throws IOException {
        DiskMap diskMapObj = new DiskMap();
        diskMapObj.saveData("111", "121");
        diskMapObj.saveData("112", "122");
        diskMapObj.saveData("113", "123");
        assertThat(diskMapObj.loadData("111")).isEqualTo("121");
        assertThat(diskMapObj.loadData("112")).isEqualTo("122");
        assertThat(diskMapObj.loadData("113")).isEqualTo("123");
    }

    @Test
    void testDiskMapCollision() throws IOException {
        DiskMap diskMapObj = new DiskMap();
        diskMapObj.saveData("111", "121");
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> diskMapObj.saveData("111", "122"));
    }

}
