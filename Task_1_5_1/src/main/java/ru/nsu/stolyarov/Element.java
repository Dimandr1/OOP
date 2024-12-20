package ru.nsu.stolyarov;

/**
 * Обобщающий класс для всех MD-элементов.
 */
public abstract class Element {
    /**
     * Преобразование MD-элемента в текст формата markdown.
     *
     * @return - элемент в виде текста
     */
    public abstract String toString();

    /**
     * Сравнение этого элемента с другим.
     *
     * @param obj - другой MD-элемент
     * @return истина, если элементы равны, ложь иначе
     */
    public boolean equals(Element obj) {
        if (obj.getClass() == this.getClass() && obj.toString().equals(this.toString())) {
            return true;
        }
        return false;
    }
}