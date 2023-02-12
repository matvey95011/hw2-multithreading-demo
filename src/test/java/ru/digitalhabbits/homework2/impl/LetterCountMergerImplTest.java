package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LetterCountMergerImplTest {

    private final LetterCountMerger merger = new LetterCountMergerImpl();
    private final Map<Character, Long> EMPTY_MAP = new HashMap<>();

    private final Map<Character, Long> FIRST_MAP = Map.of(
            'a', 1L,
            'b', 1L,
            'c', 42L,
            'd', 24L
    );

    private final Map<Character, Long> SECOND_MAP = Map.of(
            'a', 1L,
            'b', 1L,
            'e', 42L,
            'f', 24L
    );

    @Test
    @DisplayName("Проверка слияния дву коллекций")
    void merge_two_map() {
        var merge = merger.merge(FIRST_MAP, SECOND_MAP);

        assertThat(merge).containsOnly(
                entry('a', 2L),
                entry('b', 2L),
                entry('c', 42L),
                entry('d', 24L),
                entry('e', 42L),
                entry('f', 24L)
        );
    }

    @Test
    @DisplayName("Проверка слияния коллекции с пустой коллекцией")
    void merge_first_map_with_isEmpty_map() {
        var merge = merger.merge(FIRST_MAP, EMPTY_MAP);

        assertThat(merge).containsOnly(
                entry('a', 1L),
                entry('b', 1L),
                entry('c', 42L),
                entry('d', 24L)
        );
    }

    @Test
    @DisplayName("Проверка слияния пустой коллекции с не пустой")
    void merge_isEmpty_map_with_second_map() {
        var merge = merger.merge(EMPTY_MAP, SECOND_MAP);

        assertThat(merge).containsOnly(
                entry('a', 1L),
                entry('b', 1L),
                entry('e', 42L),
                entry('f', 24L)
        );
    }

    @Test
    @DisplayName("Проверка слияния двух null")
    void merge_two_null() {
        var merge = merger.merge(null, null);

        assertThat(merge).isEmpty();
    }

    @Test
    @DisplayName("Проверка слияния двух пустых коллекций")
    void merge_two_isEmpty() {
        var merge = merger.merge(EMPTY_MAP, EMPTY_MAP);

        assertThat(merge).isEmpty();
    }

    @Test
    @DisplayName("Проверка слияния пустой коллекции с null")
    void merge_isEmpty_with_null() {
        var merge = merger.merge(EMPTY_MAP, null);

        assertThat(merge).isEmpty();
    }

    @Test
    @DisplayName("Проверка слияния null с пустой коллекцией")
    void merge_null_with_isEmpty() {
        var merge = merger.merge(null, EMPTY_MAP);

        assertThat(merge).isEmpty();
    }

    @Test
    @DisplayName("Проверка слияния коллекции с null")
    void merge_first_map_with_null() {
        var merge = merger.merge(FIRST_MAP, null);

        assertThat(merge).containsOnly(
                entry('a', 1L),
                entry('b', 1L),
                entry('c', 42L),
                entry('d', 24L)
        );
    }

    @Test
    @DisplayName("Проверка слияния null c коллекцией")
    void merge_null_with_second_map() {
        var merge = merger.merge(null, SECOND_MAP);

        assertThat(merge).containsOnly(
                entry('a', 1L),
                entry('b', 1L),
                entry('e', 42L),
                entry('f', 24L)
        );
    }
}