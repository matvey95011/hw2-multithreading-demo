package ru.digitalhabbits.homework2.impl;

public final class AppConstants {

    private AppConstants() {}
    
    /**
     * Количество вычислительных ядер, доступных JVM
     */
    public final static int COUNT_THREADS = Runtime.getRuntime().availableProcessors();

}
