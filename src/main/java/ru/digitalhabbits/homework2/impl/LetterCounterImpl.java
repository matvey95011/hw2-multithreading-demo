package ru.digitalhabbits.homework2.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class LetterCounterImpl implements LetterCounter {

    /**
     * Подсчет символов в строке
     *
     * @param input {@link String}. Исходная строка
     * @return {@link HashMap}. Коллекция, где key - символ, value - колличество символов в исходной строке
     */
    @Override
    public Map<Character, Long> count(String input) {
        if (StringUtils.isEmpty(input)) {
            log.info("input string is null or length = 0");
            return new HashMap<>();
        }

//        return input.chars()
//                .mapToObj(e -> (char) e)
//                .collect(HashMap::new,
//                        (map, ch) -> map.put(ch, map.containsKey(ch) ? (map.get(ch) + 1) : 1), // getOrDefault
//                        HashMap::putAll);

        return input
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
}
