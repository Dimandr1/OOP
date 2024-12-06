package ru.nsu.stolyarov;

public class Image extends Link{
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        if (bold) {
            temp.append("**");
        }
        if (italic) {
            temp.append("*");
        }
        temp.append("!");
        temp.append("[");
        temp.append(text);
        temp.append("]");
        temp.append("(");
        temp.append(url);
        temp.append(")");
        if (italic) {
            temp.append("*");
        }
        if (bold) {
            temp.append("**");
        }
        return temp.toString();
    }
    public static class Builder implements ElementBuilder{
        private Image building;
        public Image build(){
            return building;
        }
        public Builder(){
            building = new Image();
        }
        public Image.Builder setUrl(String url){
            building.url = url;
            return this;
        }

        public Image.Builder setTextObj(Text text){
            building.textCpy(text);
            return this;
        }
        public Image.Builder setText(String text){
            building.text = text;
            return this;
        }
        public Image.Builder setBold(boolean val){
            building.bold = val;
            return this;
        }
        public Image.Builder setItalic(boolean val){
            building.italic = val;
            return this;
        }
        public Image.Builder setObject(Image obj){
            building = obj;
            return this;
        }
    }
}
