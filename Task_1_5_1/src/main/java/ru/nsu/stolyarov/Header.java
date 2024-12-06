package ru.nsu.stolyarov;

import javax.management.InvalidAttributeValueException;

public class Header extends Element{
    private int size;
    private String text;
    public Header(){
        size = 1;
        text = "";
    }




    public String toString(){
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < size; i++){
            temp.append("#");
        }
        temp.append(" ");
        temp.append(text);
        return temp.toString();
    }
    public static class Builder implements ElementBuilder{
        Header building;
        public Header build(){
            return building;
        }
        public Builder(){
            building = new Header();
        }
        public Builder(String text){
            building = new Header();
            building.text = text;
        }

        public Header.Builder setText(String text){
            building.text = text;
            return this;
        }
        public Header.Builder setSize(int size) throws InvalidAttributeValueException{
            if(size < 1 || size > 6){
                throw new InvalidAttributeValueException("Header size out of bounds [1; 6]");
            }
            building.size = size;
            return this;
        }
        public Header.Builder setObject(Header obj){
            building = obj;
            return this;
        }
    }
}
