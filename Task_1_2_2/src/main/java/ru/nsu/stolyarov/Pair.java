package ru.nsu.stolyarov;

/**
 * Структура вида "пара ключ-значение"
 * @param <K> - тип ключа
 * @param <V> - тип значения
 */
public class Pair<K, V> {
    K first;
    V second;

    /**
     * Инициализация.
     * @param f - первый элемент
     * @param s - второй элемент
     */
    public Pair(K f, V s){
        first = f;
        second = s;
    }

}
