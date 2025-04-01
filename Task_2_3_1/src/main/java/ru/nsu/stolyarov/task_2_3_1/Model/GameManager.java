package ru.nsu.stolyarov.task_2_3_1.Model;

public class GameManager {
    private MapManager map;
    private Snake player;
    private int score;

    public void startGame(Settings settings){
        score = 0;
        map = new MapManager(settings);
        player = new Snake(settings.mapWidth()/2, settings.mapHeight()/2,
                map, settings, 1000, this);
        for(int i = 0; i < settings.foodAmount(); i++){
            map.placeObjectRandom(new Apple(this, map));
        }
        Thread playerThread = new Thread(() -> player.start());
        playerThread.start();
    }
    public void increaseScore(){
        score++;
    }
    public void endGame(boolean won){

    }
}
