package ru.nsu.stolyarov;

/**
 * Класс, описывающий ссылки.
 */
public class Link extends Text {
    protected String url;

    /**
     * Инициализация пустой ссылки без эффектов.
     */
    public Link() {
        super();
        this.url = "";
    }

    public String toString() {
        StringBuilder temp = new StringBuilder();
        if (text == "") {
            temp.append("<");
            temp.append(url);
            temp.append(">");
        } else {
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
        }
        return temp.toString();
    }

    /**
     * Builder.
     */
    public static class Builder implements ElementBuilder {
        private Link building;

        public Link build() {
            return building;
        }

        /**
         * Инициализация.
         */
        public Builder() {
            building = new Link();
        }

        /**
         * Задать новый адрес ссылки.
         *
         * @param url адрес в виде текста
         * @return self
         */
        public Link.Builder setUrl(String url) {
            building.url = url;
            return this;
        }

        /**
         * Скопировать поля из Текст-объекта.
         *
         * @param text объект для копирования
         * @return self
         */
        public Link.Builder setTextObj(Text text) {
            building.textCpy(text);
            return this;
        }

        /**
         * Задать текст для отображения.
         *
         * @param text текст
         * @return self
         */
        public Link.Builder setText(String text) {
            building.text = text;
            return this;
        }

        /**
         * Сделать текст жирным-нежирным
         *
         * @param val новое значение жирности
         * @return self
         */
        public Link.Builder setBold(boolean val) {
            building.bold = val;
            return this;
        }

        /**
         * Сделать текст курсивным-некурсивным
         *
         * @param val новое значение курсивности
         * @return self
         */
        public Link.Builder setItalic(boolean val) {
            building.italic = val;
            return this;
        }

        /**
         * Сделать текст зачеркнутым/незачеркнутым.
         *
         * @param val новое значение
         * @return self
         */
        public Link.Builder setStrike(boolean val) {
            building.strikethrough = val;
            return this;
        }

        /**
         * Сделать текст кодом/не кодом.
         *
         * @param val новое значение
         * @return self
         */
        public Link.Builder setCode(boolean val) {
            building.code = val;
            return this;
        }

        /**
         * Заместить строящийся объект другой ссылкой.
         *
         * @param obj новая ссылка
         * @return self
         */
        public Link.Builder setObject(Link obj) {
            building = obj;
            return this;
        }
    }
}
