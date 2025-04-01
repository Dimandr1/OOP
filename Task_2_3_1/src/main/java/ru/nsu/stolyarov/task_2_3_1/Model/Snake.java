package ru.nsu.stolyarov.task_2_3_1.Model;

import javafx.util.Pair;
import ru.nsu.stolyarov.task_2_3_1.SnakeDequeue;

public class Snake{
    private enum Direction{
        UP, RIGHT, DOWN, LEFT
    }
    private long moveTime;
    private Settings settings;
    private SnakeDequeue snakeBody;
    int x;
    int y;
    private MapManager manager;
    private GameManager game;
    private Direction curDirection;
    private boolean isActive;
    private boolean growing;

    public Snake(int x, int y, MapManager map, Settings settings,
                 long moveTime, GameManager game){
        this.moveTime = moveTime;
        this.settings = settings;
        this.x = x;
        this.y = y;
        this.manager = map;
        this.game = game;
        curDirection = Direction.UP;
        snakeBody = new SnakeDequeue(settings.mapHeight() * settings.mapWidth());
        snakeBody.addStart(new Pair(x, y));
        map.placeObject(new SnakeSegment(), x, y);
        isActive = true;
        growing = false;
    }

    public void start() {
        if(isActive){
            try {
                wait(moveTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            move();
        }
    }
    private void move(){
        if(!growing) {
            manager.deleteCellObject((int) snakeBody.getLast().getKey(),
                    (int) snakeBody.getLast().getValue());
            snakeBody.extractLast();
        }
        else{
            growing = false;
        }
        int newX = x;
        int newY = y;
        if(curDirection == Direction.DOWN || curDirection == Direction.UP){
            newX += (curDirection == Direction.DOWN ? -1 : 1);
        }
        else{
            newY += (curDirection == Direction.LEFT ? -1 : 1);
        }
        manager.getCellObject(newX, newY).onCollision(this);
        snakeBody.addStart(new Pair(newX, newY));
        manager.placeObject(new SnakeSegment(), newX, newY);
    }

    public void growTail(){
        growing = true;
    }

    public void selfDestruct(){
        while(snakeBody.len() > 0){
            Pair<Integer, Integer> res = snakeBody.extractLast();
            manager.deleteCellObject(res.getKey(), res.getValue());
        }
        game.endGame(false);
    }
}
