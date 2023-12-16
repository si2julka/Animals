package com.ggit.simulation;

import java.util.*;
import java.util.stream.Collectors;

public class WorldMap extends AbstractWorldMap {
    private Map<Vector2D, Plant> plants;
    private AnimalsMapping animals;
    private int dayNumber = 1;
    private static final int noOfPlants = 5;
    private static final int noOfAnimals = 15;
    private static final int animalEnergy = 20;
    private static final int plantEnergy = 10;
    private static final int dayEnergy = 5;
    private static final Random random = new Random();

    public WorldMap(int width, int height) {
        super(width, height);
        plants = new HashMap<>();
        for (int i = 0; i < noOfPlants; i++) addPlant();
        animals = new AnimalsMapping();
    }

    private void addPlant() {
        if (width * height == plants.size()) return;

        Vector2D position = getRandomPosition();
        while (isOccupiedByPlant(position)) {
            position = getRandomPosition();
        }
        plants.put(position, new Plant(position));
    }

    private boolean isOccupiedByPlant(Vector2D position) {
        return plants.containsKey(position);
    }

    private Vector2D getRandomPosition() {
        return new Vector2D(random.nextInt(width), random.nextInt(height));
    }

    @Override
    public void startDay() {
        System.out.println("Mamy nowy dzień nr: " + dayNumber);
    }

    @Override
    public void run() {
        animals.mapping.clear();
        animals.list.forEach(animal -> {
            animal.moveBasedOnGenome();
            animals.placeAnimalOnMap(animal);
        });
    }

    @Override
    public void eat() {
        animals.mapping.forEach((pos, animals) -> {
            if (isOccupiedByPlant(pos)) {
                animals.stream().max(Animal::compareTo).ifPresent(animal -> {
                    System.out.println("Zwierzę " + animal.getId() + " zjadło roślinę");
                    animal.withChangedEnergy(animal.getEnergy() + plantEnergy);
                    plants.remove(animal.getPosition());
                    addPlant();
                });
            }
        });
    }

    @Override
    public void reproduce() {
        List<Animal> children = new LinkedList<>();
        animals.mapping.forEach((position, animalsList) -> {
            List<Animal> parents = animalsList.stream()
                    .filter(animal -> animal.getEnergy() > animalEnergy / 2)
                    .sorted(Comparator.reverseOrder())
                    .limit(2)
                    .toList();
            if (parents.size() == 2) {
                Animal child = new Animal(parents.get(0), parents.get(1));
                children.add(child);
                System.out.printf("Urodziło się nowe zwierzę: %d. Rodzice to %d i %d",
                        child.getId(), parents.get(0).getId(), parents.get(1).getId());
            }
        });
        children.forEach(animal -> animals.addAnimal(animal));
    }

    @Override
    public SimulationStatistics getStatistics() {
        int animalsCount = animals.list.size();
        if (animalsCount == 0) return new SimulationStatistics(0, 0, 0, 0, 0);
        return new SimulationStatistics(
                dayNumber,
                animals.list.stream().mapToInt(Animal::getAge).sum() / animalsCount,
                animals.list.stream().mapToInt(Animal::getEnergy).sum() / animalsCount,
                animals.list.stream().mapToInt(Animal::getNumberOfChildren).sum() / animalsCount,
                animalsCount
        );
    }

    @Override
    public Map<Vector2D, List<Animal>> getAnimals() {
        return animals.mapping;
    }

    @Override
    public Set<Vector2D> getPlantsLocations() {
        return plants.keySet();
    }

    @Override
    public void endDay() {
        dayNumber++;
        int oldAnimalsCount = animals.list.size();
        animals.updateAnimals(animals.list.stream()
                .map(animal -> animal.withChangedEnergy(animal.getEnergy() - dayEnergy))
                .filter(animal -> animal.getEnergy() >= 0)
                .map(Animal::dayOlder)
                .collect(Collectors.toList()));
        System.out.printf("Zwierząt było %d, pozostało %d\n", oldAnimalsCount, animals.list.size());
    }

    private class AnimalsMapping {
        List<Animal> list;
        Map<Vector2D, List<Animal>> mapping;

        AnimalsMapping() {
            mapping = new HashMap<>();
            list = new LinkedList<>();
            for (int i = 0; i < noOfAnimals; i++) {
                addAnimal(new Animal(getRandomPosition(), animalEnergy));
            }
        }

        void addAnimal(Animal animal) {
            placeAnimalOnMap(animal);
            list.add(animal);
        }

        void placeAnimalOnMap(Animal animal) {
            mapping.computeIfAbsent(animal.getPosition(), pos -> new LinkedList<>()).add(animal);
        }

        void updateAnimals(List<Animal> newAnimals) {
            list = newAnimals;
        }
    }
}
