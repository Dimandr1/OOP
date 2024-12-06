package ru.nsu.stolyarov;

public class Text extends Element implements Listable{
    protected String text;
    protected boolean bold;
    protected boolean italic;

    /**
     * Инициализация параметров.
     */
    public Text(){
        text = "";
        bold = false;
        italic = false;
    }
    public Text(String text){
        this.text = text;
        bold = false;
        italic = false;
    }
    public void textCpy(Text obj){
        this.text = obj.text;
        this.bold = obj.bold;
        this.italic = obj.italic;
    }
    public String toString(){
        StringBuilder temp = new StringBuilder();
        if(bold){
            temp.append("**");
        }
        if(italic){
            temp.append("*");
        }
        temp.append(text);
        if(italic){
            temp.append("*");
        }
        if(bold){
            temp.append("**");
        }
        return temp.toString();
    }


    public static class Builder implements ElementBuilder{
        private Text building;
        public Text build(){
            return building;
        }
        public Builder(){
            building = new Text();
        }
        /**
         * Задание текстового содержимого.
         * @param text новый текст
         * @return self
         */
        public Text.Builder setText(String text){
            building.text = text;
            return this;
        }

        /**
         * Сделать текст жирным/нежирным.
         * @param val новое значение
         * @return self
         */
        public Text.Builder setBold(boolean val){
            building.bold = val;
            return this;
        }

        /**
         * Сделать текст курсивным/некурсивным.
         * @param val новое значение
         * @return self
         */
        public Text.Builder setItalic(boolean val){
            building.italic = val;
            return this;
        }

        /**
         * Заменить строящийся объект другим готовым.
         * @param text другой Text объект
         * @return self
         */
        public Text.Builder setObject(Text text){
             building = text;
             return this;
        }
    }
}
