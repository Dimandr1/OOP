package ru.nsu.stolyarov.task_2_3_1.Model;

public class SnakeSegment implements MapObject{
    @Override
    public void onCollision(Snake snake) {
        snake.selfDestruct();
    }
}
