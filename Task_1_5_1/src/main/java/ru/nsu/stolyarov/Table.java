package ru.nsu.stolyarov;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Table extends Element{
    public final Integer ALLIGN_EMPTY = 0;
    public final Integer ALLIGN_LEFT = 1;
    public final Integer ALLIGN_MID = 2;
    public final Integer ALLIGN_RIGHT = 3;
    private ArrayList<Text> colNames;
    private ArrayList<Integer> allignments;
    private ArrayList<ArrayList<Text>> table;


    public Table(){
        colNames = new ArrayList<>();
        allignments = new ArrayList<>();
        table = new ArrayList<>();
    }


    public String toString(){
        StringBuilder temp = new StringBuilder();
        for(Text el : colNames){
            temp.append("|");
            temp.append(el.toString());
        }
        temp.append("|\n");
        for(Integer el : allignments){
            temp.append("|");
            if(el == ALLIGN_EMPTY){
                temp.append("-");
            }
            else if(el == ALLIGN_LEFT){
                temp.append(":-");
            }
            else if(el == ALLIGN_MID){
                temp.append(":-:");
            }
            else if (el == ALLIGN_RIGHT){
                temp.append("-:");
            }
        }
        temp.append("|\n");
        for(ArrayList<Text> row : table){
            for(Text el : row){
                temp.append("|");
                temp.append(el.toString());
            }
            temp.append("|\n");
        }
        return temp.toString();
    }

    public static class Builder implements ElementBuilder{
        private Table building;
        public Table build(){
            return building;
        }
        public Builder(){
            building = new Table();
        }
        public Table.Builder changeObj(Table obj){
            building = obj;
            return this;
        }
        public Table.Builder addCol(int ind, String name, int allignment, String ... vals){
            while(vals.length > building.table.size()){
                addRow();
            }
            building.colNames.add(ind, new Text(name));
            building.allignments.add(ind, 0);
            for(int i = 0; i < building.table.size(); i++){
                if(i < vals.length){
                    building.table.get(i).add(ind, new Text(vals[i]));
                }
                else{
                    building.table.get(i).add(ind, new Text());
                }
            }
            return this;
        }
        public Table.Builder addCol(int ind, Text name, int allignment, Text ... vals){
            while(vals.length > building.table.size()){
                addRow();
            }
            building.colNames.add(ind,name);
            building.allignments.add(ind, 0);
            for(int i = 0; i < building.table.size(); i++){
                if(i < vals.length){
                    building.table.get(i).add(ind, vals[i]);
                }
                else{
                    building.table.get(i).add(ind, new Text());
                }
            }
            return this;
        }
        public Table.Builder addCol(){
            building.colNames.add(new Text());
            building.allignments.add(0);
            for(int i = 0; i < building.table.size(); i++){
                building.table.get(i).add(new Text());
            }
            return this;
        }
        public Table.Builder addCol(String name, int allignment, String ... vals){
            while(vals.length > building.table.size()){
                addRow();
            }
            building.colNames.add(new Text(name));
            building.allignments.add(0);
            for(int i = 0; i < building.table.size(); i++){
                if(i < vals.length){
                    building.table.get(i).add(new Text(vals[i]));
                }
                else{
                    building.table.get(i).add(new Text());
                }
            }
            return this;
        }
        public Table.Builder addCol(Text name, int allignment, Text ... vals){
            while(vals.length > building.table.size()){
                addRow();
            }
            building.colNames.add(name);
            building.allignments.add(0);
            for(int i = 0; i < building.table.size(); i++){
                if(i < vals.length){
                    building.table.get(i).add(vals[i]);
                }
                else{
                    building.table.get(i).add(new Text());
                }
            }
            return this;
        }
        public Table.Builder addRow(int ind, String ... vals){
            while(vals.length > building.colNames.size()){
                addCol();
            }
            ArrayList<Text> newList = new ArrayList<>();
            for(int i = 0; i < building.colNames.size(); i++){
                if(i < vals.length) {
                    newList.add(new Text(vals[i]));
                }
                else{
                    newList.add(new Text());
                }
            }
            building.table.add(ind, newList);
            return this;
        }
        public Table.Builder addRow(int ind, Text ... vals){
            while(vals.length > building.colNames.size()){
                addCol();
            }
            ArrayList<Text> newList = new ArrayList<>();
            for(int i = 0; i < building.colNames.size(); i++){
                if(i < vals.length) {
                    newList.add(vals[i]);
                }
                else{
                    newList.add(new Text());
                }
            }
            building.table.add(ind, newList);
            return this;
        }
        public Table.Builder addRow(String ... vals){
            while(vals.length > building.colNames.size()){
                addCol();
            }
            ArrayList<Text> newList = new ArrayList<>();
            for(int i = 0; i < building.colNames.size(); i++){
                if(i < vals.length) {
                    newList.add(new Text(vals[i]));
                }
                else{
                    newList.add(new Text());
                }
            }
            building.table.add(newList);
            return this;
        }
        public Table.Builder addRow(Text ... vals){
            while(vals.length > building.colNames.size()){
                addCol();
            }
            ArrayList<Text> newList = new ArrayList<>();
            for(int i = 0; i < building.colNames.size(); i++){
                if(i < vals.length) {
                    newList.add(vals[i]);
                }
                else{
                    newList.add(new Text());
                }
            }
            building.table.add(newList);
            return this;
        }
        public Table.Builder addRow(){
            ArrayList<Text> newList = new ArrayList<>();
            for(int i = 0; i < building.colNames.size(); i++){
                newList.add(new Text());
            }
            building.table.add(newList);
            return this;
        }


        public Table.Builder changeName(int ind, String ... vals){
            while(vals.length + ind > building.colNames.size()){
                addCol();
            }
            for(int i = 0; i < vals.length; i++){
                building.colNames.set(ind + i, new Text(vals[i]));
            }
            return this;
        }
        public Table.Builder changeName(int ind, Text ... vals){
            while(vals.length + ind> building.colNames.size()){
                addCol();
            }
            for(int i = 0; i < vals.length; i++){
                building.colNames.set(ind + i, vals[i]);
            }
            return this;
        }
        public Table.Builder changeName(String ... vals){
            while(vals.length> building.colNames.size()){
                addCol();
            }
            for(int i = 0; i < vals.length; i++){
                building.colNames.set(i, new Text(vals[i]));
            }
            return this;
        }
        public Table.Builder changeName(Text ... vals){
            while(vals.length > building.colNames.size()){
                addCol();
            }
            for(int i = 0; i < vals.length; i++){
                building.colNames.set(i, vals[i]);
            }
            return this;
        }

        public Table.Builder changeAllignment(int ind, int ... vals){
            while(vals.length + ind> building.allignments.size()){
                addCol();
            }
            for(int i = 0; i < vals.length; i++){
                building.allignments.set(ind + i, vals[i]);
            }
            return this;
        }
        public Table.Builder changeAllignment(int ... vals){
            while(vals.length > building.allignments.size()){
                addCol();
            }
            for(int i = 0; i < vals.length; i++){
                building.allignments.set(i, vals[i]);
            }
            return this;
        }


        public Table.Builder changeRow(int ind, String ... vals){
            while(vals.length + ind > building.colNames.size()){
                addCol();
            }
            for(int i = 0; i < vals.length; i++){
                building.table.get(ind).set(ind + i, new Text(vals[i]));
            }
            return this;
        }

    }
}
