package ru.digitalhabbits.homework2.impl;

import lombok.SneakyThrows;
import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.digitalhabbits.homework2.impl.AppConstants.COUNT_THREADS;

public class AsyncFileLetterCounter implements FileLetterCounter {
    private final FileReader reader;
    private final ExecutorService executorService;
    private final LetterCounter letterCounter;
    private final LetterCountMerger letterMerger;

    public AsyncFileLetterCounter() {
        this.reader = new FileReaderImpl();
        this.executorService = Executors.newFixedThreadPool(COUNT_THREADS);
        this.letterCounter = new LetterCounterImpl();
        this.letterMerger = new LetterCountMergerImpl();
    }

    public AsyncFileLetterCounter(
            FileReader reader,
            ExecutorService executorService,
            LetterCounter letterCounter,
            LetterCountMerger letterMerger
    ) {
        this.reader = reader;
        this.executorService = executorService;
        this.letterCounter = letterCounter;
        this.letterMerger = letterMerger;
    }

    /**
     * Подсчитывает количество символов в файле
     *
     * @param input {@link File} исходный файл
     * @return {@link Map} количество символов
     */
    @Override
    public Map<Character, Long> count(File input) {
        List<String> list = reader.readLines(input).collect(Collectors.toList());
        ForkJoinPool pool = new ForkJoinPool(COUNT_THREADS);
        return pool.invoke(new LetterCounterTask(0, list.size(), list));
    }
//    @Override
//    @SneakyThrows
//    public Map<Character, Long> count(File input) {
//        return reader.readLines(input)
//                .map(it -> executorService.submit(() -> letterCounter.count(it)))
//                .reduce((firstMap, secondMap) ->
//                        executorService.submit(() ->
//                                letterMerger.merge(firstMap.get(), secondMap.get()))).get()
//                .get();
//    }
}
