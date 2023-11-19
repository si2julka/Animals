package com.ggit;

import java.util.Random;

public class World {
    private static Random random = new Random();

    public static void main(String[] args) {
        Animal animal = new Animal(new Vector2D(random.nextInt(100), random.nextInt(100)));
        System.out.println("Zwierzę znajduje się na pozycji: " + animal.getPosition());

        for (MapDirection direction : MapDirection.values()) {
            animal.move(direction);
        }
    }
}
