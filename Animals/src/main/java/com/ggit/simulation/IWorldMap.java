package com.ggit.simulation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IWorldMap {
    int getWidth();

    int getHeight();

    void run();
    void eat();
    void startDay();
    void endDay();
    void reproduce();
    SimulationStatistics getStatistics();
    Map<Vector2D, List<Animal>> getAnimals();
    Set<Vector2D> getPlantsLocations();
}
