package ru.nsu.stolyarov;

public class Link extends Text{
    protected String url;
    public Link(){
        super();
        this.url = "";
    }

    public String toString(){
        StringBuilder temp = new StringBuilder();
        if(text == ""){
            temp.append("<");
            temp.append(url);
            temp.append(">");
        }
        else{
            if(bold){
                temp.append("**");
            }
            if(italic){
                temp.append("*");
            }
            temp.append("[");
            temp.append(text);
            temp.append("]");
            temp.append("(");
            temp.append(url);
            temp.append(")");
            if(italic){
                temp.append("*");
            }
            if(bold){
                temp.append("**");
            }
        }
        return temp.toString();
    }

    public static class Builder implements ElementBuilder{
        private Link building;
        public Link build(){
            return building;
        }
        public Builder(){
            building = new Link();
        }
        public Link.Builder setUrl(String url){
            building.url = url;
            return this;
        }

        public Link.Builder setTextObj(Text text){
            building.textCpy(text);
            return this;
        }
        public Link.Builder setText(String text){
            building.text = text;
            return this;
        }
        public Link.Builder setBold(boolean val){
            building.bold = val;
            return this;
        }
        public Link.Builder setItalic(boolean val){
            building.italic = val;
            return this;
        }
        public Link.Builder setObject(Link obj){
            building = obj;
            return this;
        }
    }
}
