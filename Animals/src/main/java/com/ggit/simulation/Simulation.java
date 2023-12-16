package com.ggit.simulation;

public class Simulation {
    private static final int height = 10;
    private static final int width = 10;
    private static IWorldMap worldMap = new WorldMap(width, height);

    public static IWorldMap getWorldMap() {
        return worldMap;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void simulateDay() {
        worldMap.startDay();
        worldMap.run();
        worldMap.eat();
        worldMap.reproduce();
        worldMap.endDay();
    }
}
