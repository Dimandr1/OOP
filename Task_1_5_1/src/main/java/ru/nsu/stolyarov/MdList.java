package ru.nsu.stolyarov;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;

public class MdList extends Element implements Listable{
    private int listType;
    protected ArrayList<Listable> elements;
    public static final int TYPE_POINTS = 1;
    public static final int TYPE_DEFS = 2;
    public static final int TYPE_NUMS = 3;
    public MdList(){
        listType = '-';
        elements = new ArrayList<>();
    }

    public String toString(){
        StringBuilder temp = new StringBuilder();
        toString(temp, 1);
        return temp.toString();
    }
    public String toString(StringBuilder str, int indent) throws IllegalArgumentException{
        if(indent < 1){
            throw new IllegalArgumentException("Indent can't be less than 1");
        }
        for(int i = 0; i < elements.size(); i++){
            for(int j = 0; j < indent; j++){
                str.append("\t");
            }
            if(listType == TYPE_NUMS){
                str.append(i+1);
                str.append(". ");
            }
            else if(listType == TYPE_POINTS){
                str.append("* ");
            }
            else{
                str.append("- ");
            }
            if(elements.get(i).getClass() == MdList.class
                    || elements.get(i).getClass() == TaskList.class) {
                str.append("\n");
                ((MdList) elements.get(i)).toString(str, indent + 1);
            }
            else{
                str.append(elements.get(i));
                str.append("\n");
            }
        }
        return str.toString();
    }

    public static class Builder implements ElementBuilder{
        private MdList building;
        public MdList build(){
            return building;
        }

        public Builder(){
            building = new MdList();
        }
        public MdList.Builder setType(int type) throws InvalidAttributeValueException{
            if(type < 1 || type > 3){
                throw new InvalidAttributeValueException("Неверный тип списка");
            }
            building.listType = type;
            return this;
        }

        public MdList.Builder addElement(Listable ... els){
            for(Listable el : els) {
                building.elements.add(el);
            }
            return this;
        }
        public MdList.Builder addElement(int ind, Listable ... els){
            for(int i = 0; i < els.length; i++) {
                building.elements.add(ind+i, els[ind]);
            }
            return this;
        }
        public MdList.Builder addElement(String ... els){
            for(String el : els) {
                building.elements.add(new Text(el));
            }
            return this;
        }
        public MdList.Builder addElement(int ind, String ... els){
            for(int i = 0; i < els.length; i++) {
                building.elements.add(ind+i, new Text(els[i]));
            }
            return this;
        }
        public MdList.Builder setElement(int ind, Listable el){
            building.elements.set(ind, el);
            return this;
        }
        public MdList.Builder setElement(int ind, String el){
            building.elements.set(ind, new Text(el));
            return this;
        }
        public MdList.Builder removeElement(int ind){
            building.elements.remove(ind);
            return this;
        }
        public MdList.Builder removeElement(){
            building.elements.remove(building.elements.size()-1);
            return this;
        }
    }
}
