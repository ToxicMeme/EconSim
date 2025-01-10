package org.example;

import java.util.Random;

public class Planet {
    public String name;
    public String description;
    public float mineCapacity;
    public float basicFood;

    public Planet (String name, PlanetType type){
        this.name = name;

        Random rand = new Random();
        switch (type){
            case ROCKY:
                description = "Минеральная планета";
                mineCapacity = rand.nextFloat(3000)+6000;
                basicFood = rand.nextFloat(3000);
                break;
            case GREEN:
                description = "Аграрная планета";
                mineCapacity = rand.nextFloat(3000);
                basicFood = rand.nextFloat(3000)+6000;
                break;
            case BALANCE:
                description = "Сбалансированная планета";
                mineCapacity = rand.nextFloat(3000)+3000;
                basicFood = rand.nextFloat(3000)+3000;
                break;
            case FACTORY:
                description = "Промышленная планета";
                mineCapacity = rand.nextFloat(3000)+3000;
                basicFood = rand.nextFloat(3000)+3000;
                break;
        }
    }

    enum PlanetType {
        ROCKY,
        GREEN,
        BALANCE,
        FACTORY
    }

    @Override
    public String toString() { return name;}
}
