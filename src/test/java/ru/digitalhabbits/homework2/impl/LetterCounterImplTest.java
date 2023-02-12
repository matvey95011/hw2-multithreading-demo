package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LetterCounterImplTest {

    private final LetterCounter counter = new LetterCounterImpl();

    @Test
    @DisplayName("Подсчет символов в строке 'cdccfdbfeadebaee'")
    void count_of_characters_in_a_string_1() {
        String str1 = "cdccfdbfeadebaee";
        Map<Character, Long> count = counter.count(str1);

        assertThat(count).containsOnly(
                entry('a', 2L),
                entry('b', 2L),
                entry('c', 3L),
                entry('d', 3L),
                entry('e', 4L),
                entry('f', 2L)
        );
    }

    @Test
    @DisplayName("Подсчет символов в строке 'fffcaacffffdedcf'")
    void count_of_characters_in_a_string_2() {
        String str2 = "fffcaacffffdedcf";
        Map<Character, Long> count = counter.count(str2);

        assertThat(count).containsOnly(
                entry('a', 2L),
                entry('c', 3L),
                entry('d', 2L),
                entry('e', 1L),
                entry('f', 8L)
        );
    }

    @Test
    @DisplayName("Проверка на пустую строку")
    void count_of_characters_in_the_empty_string() {
        Map<Character, Long> count = counter.count("");

        assertThat(count).isEmpty();
    }

    @Test
    @DisplayName("Проверка на null")
    void count_of_characters_in_the_null() {
        Map<Character, Long> count = counter.count(null);

        assertThat(count).isEmpty();
    }
}