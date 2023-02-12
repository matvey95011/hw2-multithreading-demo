package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.digitalhabbits.homework2.impl.AppConstants.COUNT_THREADS;

public class LetterCounterTask extends RecursiveTask<Map<Character, Long>> {

    private static List<String> list;
    private static long capacity;

    private final int startIndex;
    private final int endIndex;
    private final LetterCountMerger merger;
    private final LetterCounter counter;
    private Map<Character, Long> map;

    public LetterCounterTask(int startIndex, int endIndex, List<String> listString) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.map = new HashMap<>();
        this.merger = new LetterCountMergerImpl();
        this.counter = new LetterCounterImpl();
        init(listString);
    }

    private LetterCounterTask(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.map = new HashMap<>();
        this.merger = new LetterCountMergerImpl();
        this.counter = new LetterCounterImpl();
    }

    private void init(List<String> listString) {
        list = listString;
        if(!list.isEmpty()) {
            capacity = list.size() / COUNT_THREADS;
        }
    }

    /**
     * Делит исходный массив данных {@link list} между потоками и выполняет подсчет символов
     *
     * @return {@link HashMap}. Коллекция, где key - символ, value - колличество символов в массиве данных {@link list}
     */
    @Override
    protected Map<Character, Long> compute() {
        if (list.isEmpty()) {
            return new HashMap<>();
        }

        if (endIndex > list.size() || startIndex < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if ((endIndex - startIndex) <= capacity) {
            return countCharacters();
        } else {
            return divideStream();
        }
    }


    private Map<Character, Long> countCharacters() {
        IntStream.range(startIndex, endIndex)
                .forEach(it -> map = merger.merge(map, counter.count(list.get(it))));

//        list.stream()
//                .skip(startIndex)
//                .limit(endIndex - startIndex)
//                .forEach(str -> map = merger.merge(map, counter.count(str)));
        return map;
    }

    private Map<Character, Long> divideStream() {
        int middle = (endIndex + startIndex) / 2;

        var firstHalf = new LetterCounterTask(startIndex, middle);
        firstHalf.fork();

        var secondHalf = new LetterCounterTask(middle, endIndex);
        secondHalf.fork();

        return merger.merge(firstHalf.join(), secondHalf.join());
    }

}
