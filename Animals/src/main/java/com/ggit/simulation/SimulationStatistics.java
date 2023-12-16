package com.ggit.simulation;

public class SimulationStatistics {
    private final int dayNumber;
    private final int meanAge;
    private final int meanEnergy;
    private final int meanNumberOfChildren;
    private final int noOfAnimals;

    public SimulationStatistics(int dayNumber, int meanAge, int meanEnergy, int meanNumberOfChildren, int noOfAnimals) {
        this.dayNumber = dayNumber;
        this.meanAge = meanAge;
        this.meanEnergy = meanEnergy;
        this.meanNumberOfChildren = meanNumberOfChildren;
        this.noOfAnimals = noOfAnimals;
    }

    @Override
    public String toString() {
        return "STATYSTYKI\n" +
                "\ndayNumber: " + dayNumber +
                "\nmeanAge: " + meanAge +
                "\nmeanEnergy: " + meanEnergy +
                "\nmeanNumberOfChildren: " + meanNumberOfChildren +
                "\nnoOfAnimals: " + noOfAnimals;
    }
}
