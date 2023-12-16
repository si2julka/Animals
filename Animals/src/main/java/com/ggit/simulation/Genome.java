package com.ggit.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Genome {
    private List<MapDirection> genome;
    private static final int genomeLength = 32;
    private static final int minSplit = 1;
    private static final int maxSplit = 30;
    private static final Random random = new Random();


    public Genome() {
        genome = Stream.generate(MapDirection::getRandomDirection)
                .limit(genomeLength)
                .toList();
    }

    public Genome(Genome mother, Genome father) {
        int split = random.nextInt(maxSplit - minSplit) + minSplit;
        genome = new ArrayList<>();
        genome.addAll(mother.genome.subList(0, split));
        genome.addAll(genome.size(), father.genome.subList(split, genomeLength));
    }

    public MapDirection getRandomDirection() {
        return genome.get(random.nextInt(genomeLength));
    }
}
