package ru.digitalhabbits.homework2.impl;

import lombok.extern.slf4j.Slf4j;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class LetterCountMergerImpl implements LetterCountMerger {

    /**
     * Слияние двух коллекций {@link Map}
     *
     * @param first  {@link Map}. Первая коллекция
     * @param second {@link Map}. Вторая коллекция
     * @return {@link HashMap}. Коллекция, которая объединяет значения first и second
     */
    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        if (first == null && second == null) {
            log.info("maps is null");
            return new HashMap<>();
        }

        if (first == null || first.isEmpty()) {
            return second == null ? new HashMap<>() : second;
        }

        if (second == null || second.isEmpty()) {
            return first;
        }

        return Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
    }

//    @Override
//    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
//        if (first == null && second == null) {
//            log.info("maps is null");
//            return new HashMap<>();
//        }
//
//        if (first == null || first.isEmpty()) {
//            return second == null ? new HashMap<>() : second;
//        }
//
//        if (second == null || second.isEmpty()) {
//            return first;
//        }
//
//        second.forEach((key, value) ->
//                first.put(key, value + first.getOrDefault(key, 0L)));
//        return first;
//    }

}
