package ru.nsu.stolyarov;

/**
 * Общий интерфейс для всех Builder-классов.
 */
public interface ElementBuilder {
    /**
     * Возвращает построенный класс.
     *
     * @return построенный класс
     */
    public Element build();
}
