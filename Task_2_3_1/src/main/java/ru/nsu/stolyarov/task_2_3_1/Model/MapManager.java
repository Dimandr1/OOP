package ru.nsu.stolyarov.task_2_3_1.Model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MapManager {
    private ArrayList<ArrayList<MapObject>> map;
    private Settings settings;
    public MapManager(Settings settings){
        this.settings = settings;
        map = new ArrayList<>();
        for(int i = 0; i < settings.mapHeight(); i++){
            ArrayList<MapObject> line = new ArrayList<>();
            for(int j = 0; j < settings.mapWidth(); j++){
                line.add(new EmptyCell());
            }
        }
    }

    public void placeObjectRandom(MapObject obj){
        int cellNum = new Random().nextInt(map.size() * map.getLast().size());
        int x = cellNum % map.getLast().size();
        int y = cellNum / map.getLast().size();
        boolean first = true;
        while(map.get(y).get(x).getClass() != EmptyCell.class){
            if(!first && x == cellNum % map.getLast().size()
                    && y == cellNum / map.getLast().size()){
                throw new RuntimeException();
            }
            first = false;
            if(x == map.getLast().size() - 1){
                x = 0;
                if(y == map.size() - 1){
                    y = 0;
                }
                else{
                    y++;
                }
            }
            else{
                x++;
            }
        }
        map.get(y).set(x, obj);
    }

    public void placeObject(MapObject obj, int x, int y){
        map.get(y).set(x, obj);
    }
    public void deleteCellObject(int x, int y){
        map.get(y).set(x, new EmptyCell());
    }
    public MapObject getCellObject(int x, int y){
        return map.get(y).get(x);
    }
}
