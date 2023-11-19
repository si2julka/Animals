package com.ggit;

public abstract class AbstractWorldMap implements IWorldMap {
    protected int width;
    protected int height;

    protected AbstractWorldMap(int width, int height) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
