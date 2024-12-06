package ru.nsu.stolyarov;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends MdList{
    private ArrayList<Boolean> done;

    public TaskList(){
        super();
        done = new ArrayList<>();
    }

    @Override
    public String toString(StringBuilder str, int indent){
        for(int i = 0; i < elements.size(); i++){
            for(int j = 0; j < indent; j++){
                str.append("\t");
            }
            str.append("- [");
            if(done.get(i)){
                str.append("x");
            }
            else{
                str.append(" ");
            }
            str.append("] ");
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
        private TaskList building;
        public TaskList build(){
            return building;
        }

        public Builder(){
            building = new TaskList();
        }

        public TaskList.Builder addElement(Listable ... els){
            for(Listable el : els) {
                building.elements.add(el);
                building.done.add(false);
            }
            return this;
        }
        public TaskList.Builder addElement(int ind, Listable ... els){
            for(int i = 0; i < els.length; i++) {
                building.elements.add(ind+i, els[ind]);
                building.done.add(false);
            }
            return this;
        }
        public TaskList.Builder addElement(String ... els){
            for(String el : els) {
                building.elements.add(new Text(el));
                building.done.add(false);
            }
            return this;
        }
        public TaskList.Builder addElement(int ind, String ... els){
            for(int i = 0; i < els.length; i++) {
                building.elements.add(ind+i, new Text(els[i]));
                building.done.add(false);
            }
            return this;
        }

        public TaskList.Builder delElement(int ind){
            building.elements.remove(ind);
            building.done.remove(ind);
            return this;
        }

        public TaskList.Builder delElement(){
            building.elements.remove(building.elements.size()-1);
            building.done.remove(building.done.size()-1);
            return this;
        }
        public TaskList.Builder setTask(int ind, String text, boolean val){
            building.done.set(ind, val);
            building.elements.set(ind, new Text(text));
            return this;
        }
        public TaskList.Builder setTask(int ind, Listable el, boolean val){
            building.done.set(ind, val);
            building.elements.set(ind, el);
            return this;
        }
        public TaskList.Builder setTask(int ind, String text){
            building.elements.set(ind, new Text(text));
            return this;
        }
        public TaskList.Builder setTask(int ind, Listable el){
            building.elements.set(ind, el);
            return this;
        }
        public TaskList.Builder setTask(int ind, boolean val){
            building.done.set(ind, val);
            return this;
        }

    }
}
