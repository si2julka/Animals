package com.ggit.simulation;

import java.util.Random;

public enum MapDirection {
    NORTH(0, 1), SOUTH(0, -1), WEST(-1, 0), EAST(1, 0);
    private Vector2D unitVector;

    MapDirection(int x, int y) {
        unitVector = new Vector2D(x, y);
    }

    public Vector2D getUnitVector() {
        return unitVector;
    }

    public static MapDirection getRandomDirection() {
        MapDirection[] directions = MapDirection.values();
        return directions[(new Random()).nextInt(directions.length)];
    }
}
