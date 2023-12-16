package com.ggit.simulation;

public class Animal implements Comparable<Animal> {
    private Vector2D position;
    private final int id = counter++;
    private int energy;
    private int age = 1;
    private final Genome genome;
    private int numberOfChildren = 0;
    private static int counter = 0;

    public Animal(Vector2D position, int initialEnergy) {
        this.position = position;
        energy = initialEnergy;
        genome = new Genome();
    }

    public Animal(Animal mother, Animal father) {
        position = pbc(mother.getPosition().add(MapDirection.getRandomDirection().getUnitVector()));
        energy = (mother.energy + father.energy) / 4;
        genome = new Genome(mother.getGenome(), father.getGenome());
        mother.increaseNumberOfChildren();
        father.increaseNumberOfChildren();
        mother.withChangedEnergy(mother.energy * 3 / 4);
        father.withChangedEnergy(father.energy * 3 / 4);
    }

    public Genome getGenome() {
        return genome;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void increaseNumberOfChildren() {
        numberOfChildren++;
    }

    public int getAge() {
        return age;
    }

    public Animal dayOlder() {
        age++;
        return this;
    }

    public int getEnergy() {
        return energy;
    }

    public Animal withChangedEnergy(int energy) {
        this.energy = energy;
        return this;
    }

    public int getId() {
        return id;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void move(MapDirection direction) {
        position = pbc(position.add(direction.getUnitVector()));
        System.out.println("Zwierzę przesunęło się na pozycję: " + position);
    }

    public void moveBasedOnGenome() {
        move(genome.getRandomDirection());
    }

    private Vector2D pbc(Vector2D position) {
        int width = Simulation.getWidth();
        int height = Simulation.getHeight();
        if (position.getX() < 0) return position.add(new Vector2D(width, 0));
        if (position.getX() >= width) return position.subtrack(new Vector2D(width, 0));
        if (position.getY() < 0) return position.add(new Vector2D(0, height));
        if (position.getY() >= height) return position.subtrack(new Vector2D(0, height));
        return position;
    }

    @Override
    public int compareTo(Animal o) {
        return energy == o.energy ? id - o.id : energy - o.energy;
    }
}
