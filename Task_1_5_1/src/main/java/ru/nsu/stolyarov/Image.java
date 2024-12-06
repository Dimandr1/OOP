package ru.nsu.stolyarov;

/**
 * Класс, описывающий изображеня.
 */
public class Image extends Link {
    public Image() {
        super();
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        if (bold) {
            temp.append("**");
        }
        if (italic) {
            temp.append("*");
        }
        if (strikethrough) {
            temp.append("~~");
        }
        if (code) {
            temp.append("`");
        }
        temp.append("!");
        temp.append("[");
        temp.append(text);
        temp.append("]");
        temp.append("(");
        temp.append(url);
        temp.append(")");
        if (code) {
            temp.append("`");
        }
        if (strikethrough) {
            temp.append("~~");
        }
        if (italic) {
            temp.append("*");
        }
        if (bold) {
            temp.append("**");
        }
        return temp.toString();
    }

    /**
     * Builder.
     */
    public static class Builder implements ElementBuilder {
        private Image building;

        public Image build() {
            return building;
        }

        /**
         * Инициализация.
         */
        public Builder() {
            building = new Image();
        }

        /**
         * Задать новый адрес изображения.
         *
         * @param url новый адрес
         * @return self
         */
        public Image.Builder setUrl(String url) {
            building.url = url;
            return this;
        }

        /**
         * Скопировать поля из Текст-объекта.
         *
         * @param text объект для копирования
         * @return self
         */
        public Image.Builder setTextObj(Text text) {
            building.textCpy(text);
            return this;
        }

        /**
         * Задать текст для отображения.
         *
         * @param text текст
         * @return self
         */
        public Image.Builder setText(String text) {
            building.text = text;
            return this;
        }

        /**
         * Сделать текст жирным-нежирным.
         *
         * @param val новое значение жирности
         * @return self
         */
        public Image.Builder setBold(boolean val) {
            building.bold = val;
            return this;
        }

        /**
         * Сделать текст курсивным-некурсивным.
         *
         * @param val новое значение курсивности
         * @return self
         */
        public Image.Builder setItalic(boolean val) {
            building.italic = val;
            return this;
        }

        /**
         * Сделать текст зачеркнутым/незачеркнутым.
         *
         * @param val новое значение
         * @return self
         */
        public Image.Builder setStrike(boolean val) {
            building.strikethrough = val;
            return this;
        }

        /**
         * Сделать текст кодом/не кодом.
         *
         * @param val новое значение
         * @return self
         */
        public Image.Builder setCode(boolean val) {
            building.code = val;
            return this;
        }

        /**
         * Заместить строящийся объект другой ссылкой.
         *
         * @param obj новая ссылка
         * @return self
         */
        public Image.Builder setObject(Image obj) {
            building = obj;
            return this;
        }
    }
}
