package ru.nsu.stolyarov;

import javax.management.InvalidAttributeValueException;

/**
 * Класс, описывающий заголовки.
 */
public class Header extends Element {
    private int size;
    private String text;

    /**
     * Инициализация пустого хедера с размером 1.
     */
    public Header() {
        size = 1;
        text = "";
    }


    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < size; i++) {
            temp.append("#");
        }
        temp.append(" ");
        temp.append(text);
        return temp.toString();
    }

    /**
     * Builder.
     */
    public static class Builder implements ElementBuilder {
        Header building;

        public Header build() {
            return building;
        }

        /**
         * Инициализация.
         */
        public Builder() {
            building = new Header();
        }

        /**
         * Задание текста хедера.
         *
         * @param text новый текст
         * @return self
         */
        public Header.Builder setText(String text) {
            building.text = text;
            return this;
        }

        /**
         * Задание размера хедера.
         *
         * @param size новый размер
         * @return self
         * @throws InvalidAttributeValueException возникает при аргументе вне [1;6]
         */
        public Header.Builder setSize(int size) throws InvalidAttributeValueException {
            if (size < 1 || size > 6) {
                throw new InvalidAttributeValueException("Header size out of bounds [1; 6]");
            }
            building.size = size;
            return this;
        }

        /**
         * Замещение строящегося хедера другим готовым.
         *
         * @param obj другой хедер
         * @return self
         */
        public Header.Builder setObject(Header obj) {
            building = obj;
            return this;
        }
    }
}
