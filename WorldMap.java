package com.ggit;

import java.util.Random;

public class WorldMap extends AbstractWorldMap {
    private Animal animal;
    private static final Random random = new Random();
    public WorldMap(int width, int height) {
        super(width, height);
        animal = new Animal(new Vector2D(random.nextInt(width), random.nextInt(height)));
    }

    @Override
    public void run() {
        MapDirection[] directions = MapDirection.values();
        animal.move(directions[random.nextInt(directions.length)]);
    }
}
