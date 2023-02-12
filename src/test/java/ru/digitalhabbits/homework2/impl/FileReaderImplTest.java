package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.util.stream.Stream;

import static com.google.common.io.Resources.getResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderImplTest {

    private final FileReader reader = new FileReaderImpl();
    private final File file = getFile("test.txt");

    @Test()
    @DisplayName("Чтение файла test.txt и проверка количества прочитанных строк")
    void read_file_by_line() {
        Stream<String> stringStream = reader.readLines(file);
        assertEquals(stringStream.count(), 1000);
    }


    private File getFile(String name) {
        return new File(getResource(name).getPath());
    }

}