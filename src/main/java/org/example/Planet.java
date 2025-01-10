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
                description = "Скалистая";
                mineCapacity = rand.nextFloat(10000)+50000;
                basicFood = rand.nextFloat(500);
                break;
            case GREEN:
                description = "Земного типа";
                mineCapacity = rand.nextFloat(5000)+10000;
                basicFood = rand.nextFloat(10000)+20000;
                break;
        }
    }

    enum PlanetType {
        ROCKY,
        GREEN;
    }

    @Override
    public String toString() { return name;}
}
