package ru.nsu.stolyarov;

/**
 * Класс, описывающий изображеня.
 */
public class Image extends Element implements Listable, Tableable {
    private String text;
    private String url;
    public Image() {
        text = new String();
        url = new String();
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append("!");
        temp.append("[");
        temp.append(text);
        temp.append("]");
        temp.append("(");
        temp.append(url);
        temp.append(")");
        return temp.toString();
    }

    /**
     * Builder.
     */
    public static class Builder implements ElementBuilder {
        private Image building;

        public Image build(){
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
