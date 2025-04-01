package ru.nsu.stolyarov.task_2_3_1.Model;

public class Apple implements MapObject {
    private GameManager gameManager;
    private MapManager mapManager;
    public Apple(GameManager gameManager, MapManager mapManager){
        this.gameManager = gameManager;
        this.mapManager = mapManager;
    }
    @Override
    public void onCollision(Snake snake) {
        gameManager.increaseScore();
        snake.growTail();
        mapManager.placeObjectRandom(new Apple(gameManager, mapManager));
    }
}
