package ru.piaksheva.jungleapp.entity;

public class Tiger {
    private int health = 100;
    private int energy = 100;
    private double preySizeRate = 2.5;

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return this.energy;
    }

    public double getPreySizeRate() {
        return this.preySizeRate;
    }
}
