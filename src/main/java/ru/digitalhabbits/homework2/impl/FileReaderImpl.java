package ru.digitalhabbits.homework2.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Slf4j
public class FileReaderImpl implements FileReader {

    /**
     * Построчное чтение файла
     *
     * @param file {@link File}. Исходный файл
     * @return {@link Stream<String>}. Строки файла
     */
    @Override
    public Stream<String> readLines(File file) {
        try {
            return Files.lines(Path.of(file.getPath()));
        } catch (IOException e) {
            log.error("resource {} not found {} ", file.getName(), e.getMessage());
            return Stream.empty();
        }
    }

}
