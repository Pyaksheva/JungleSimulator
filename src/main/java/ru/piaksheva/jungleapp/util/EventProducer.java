package ru.piaksheva.jungleapp.util;

import ru.piaksheva.jungleapp.entity.Tiger;

public class EventProducer {

    public void startSimulation(Tiger tiger) {
        while (checkStatus(tiger)) {
            int eventNumber = (int) (Math.random() * 100);
            if (eventNumber >= 0 && eventNumber < 40) {
                sleepEvent(tiger);
            } else if (eventNumber >= 40 && eventNumber < 60) {
                moveEvent(tiger);
            } else if (eventNumber >= 60 && eventNumber < 80) {
                tigerEat(tiger);
            } else if (eventNumber >= 80 && eventNumber <= 100) {
                hunterAttack(tiger);
            }
        }
        System.out.println("Ооо нет! Тигр умер! The end!");
    }

    private void sleepEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        energy = energy + 10;
        if (energy > 100) {
            energy = 100;
        }
        System.out.println("Тигр поспал днем! " + " + " + (energy - tiger.getEnergy()) + " энергии. Текущая энергия: " + energy);
        tiger.setEnergy(energy);
        checkEnergy(tiger);
    }

    private void moveEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        energy = energy - 10;
        energy = normalizeEnergy(energy);
        System.out.println("Тигр побегал! " + " - " + (tiger.getEnergy() - energy) + " энергии. Текущая энергия: " + energy);
        tiger.setEnergy(energy);
        checkEnergy(tiger);
    }

    private void tigerEat(Tiger tiger) {
        int energy = tiger.getEnergy();
        int health = tiger.getHealth();
        energy = energy - 15;
        energy = normalizeEnergy(energy);
        health = health + (int) (tiger.getPreySizeRate() * 4);
        health = normalizeHealth(health);
        tiger.setEnergy(energy);
        tiger.setHealth(health);
        checkEnergy(tiger);
        System.out.println("Тигр съел дикого кабана! Текущая энергия: " + tiger.getEnergy() + "Текущее здоровье: " + tiger.getHealth());
    }

    private void hunterAttack(Tiger tiger) {
        int health = tiger.getHealth();
        health = health - 30;
        health = normalizeHealth(health);
        tiger.setHealth(health);
        checkEnergy(tiger);
        System.out.println("На тигра напал браконьер! Текущее здоровье: " + tiger.getHealth());
    }

    private boolean checkStatus(Tiger tiger) {
        System.out.println("hp: " + tiger.getHealth() + " energy: " + tiger.getEnergy());
        if (tiger.getHealth() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    private void checkEnergy(Tiger tiger) {
        if (tiger.getEnergy() <= 0) {
            int health = tiger.getHealth();
            health = health - 5;
            health = normalizeHealth(health);
            tiger.setHealth(health);
        }
    }

    public int normalizeEnergy(int energy) {
        if (energy > 100) {
            return 100;
        } else if (energy < 0) {
            return 0;
        } else {
            return energy;
        }
    }

    public int normalizeHealth(int health) {
        if (health > 100) {
            return 100;
        } else if (health < 0) {
            return 0;
        } else {
            return health;
        }
    }
}
