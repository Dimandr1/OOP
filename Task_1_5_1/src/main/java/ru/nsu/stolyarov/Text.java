package ru.nsu.stolyarov;

/**
 * Класс, описывающий текст.
 */
public class Text extends Element implements Listable {
    protected String text;
    protected boolean bold;
    protected boolean italic;
    protected boolean strikethrough;
    protected boolean code;

    /**
     * Инициализация пустого текста без эффектов.
     */
    public Text() {
        text = "";
        bold = false;
        italic = false;
    }

    /**
     * Инициализация текстового объекта с заданным содержимым (без эффектов).
     *
     * @param text заданный текст
     */
    public Text(String text) {
        this.text = text;
        bold = false;
        italic = false;
    }

    /**
     * Копирование параметров другого текстового объекта в этот.
     *
     * @param obj другой текстовый объект
     */
    public void textCpy(Text obj) {
        this.text = obj.text;
        this.bold = obj.bold;
        this.italic = obj.italic;
    }

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
        temp.append(text);
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
        private Text building;

        public Text build() {
            return building;
        }

        /**
         * Инициализация.
         */
        public Builder() {
            building = new Text();
        }

        /**
         * Задание текстового содержимого.
         *
         * @param text новый текст
         * @return self
         */
        public Text.Builder setText(String text) {
            building.text = text;
            return this;
        }

        /**
         * Сделать текст жирным/нежирным.
         *
         * @param val новое значение
         * @return self
         */
        public Text.Builder setBold(boolean val) {
            building.bold = val;
            return this;
        }

        /**
         * Сделать текст курсивным/некурсивным.
         *
         * @param val новое значение
         * @return self
         */
        public Text.Builder setItalic(boolean val) {
            building.italic = val;
            return this;
        }

        /**
         * Сделать текст зачеркнутым/незачеркнутым.
         *
         * @param val новое значение
         * @return self
         */
        public Text.Builder setStrike(boolean val) {
            building.strikethrough = val;
            return this;
        }

        /**
         * Сделать текст кодом/не кодом.
         *
         * @param val новое значение
         * @return self
         */
        public Text.Builder setCode(boolean val) {
            building.code = val;
            return this;
        }

        /**
         * Заменить строящийся объект другим готовым.
         *
         * @param text другой Text объект
         * @return self
         */
        public Text.Builder setObject(Text text) {
            building = text;
            return this;
        }
    }
}
