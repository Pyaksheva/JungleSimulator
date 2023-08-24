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
            } else if (eventNumber >= 60 && eventNumber < 66) {
                eatBoarEvent(tiger);
            } else if (eventNumber >= 66 && eventNumber < 72) {
                eatDeerEvent(tiger);
            } else if (eventNumber >= 72 && eventNumber < 78) {
                huntShareDeerEvent(tiger);
            } else if (eventNumber >= 78 && eventNumber < 81) {
                growlEvent(tiger);
            } else if (eventNumber >= 81 && eventNumber < 88) {
                defendTerritoryEvent(tiger);
            } else if (eventNumber >= 88 && eventNumber < 90) {
                lickWoolEvent(tiger);
            } else if (eventNumber >= 90 && eventNumber < 96) {
                reproduceEvent(tiger);
            } else if (eventNumber >= 96 && eventNumber <= 100) {
                hunterAttackEvent(tiger);
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
        System.out.println("Тигр поспал! " + " + " + (energy - tiger.getEnergy()) + " энергии. Текущая энергия: " + energy);
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

    private void eatBoarEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        int health = tiger.getHealth();
        energy = energy - 12;
        energy = normalizeEnergy(energy);
        health = health + (int) (tiger.getPreySizeRate() * 4);
        health = normalizeHealth(health);
        tiger.setEnergy(energy);
        tiger.setHealth(health);
        checkEnergy(tiger);
        System.out.println("Тигр съел дикого кабана! Текущая энергия: " + tiger.getEnergy() + " Текущее здоровье: " + tiger.getHealth());
    }

    private void eatDeerEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        int health = tiger.getHealth();
        energy = energy - 15;
        energy = normalizeEnergy(energy);
        health = health + (int) (tiger.getPreySizeRate() * 6);
        health = normalizeHealth(health);
        tiger.setEnergy(energy);
        tiger.setHealth(health);
        checkEnergy(tiger);
        System.out.println("Тигр съел оленя! Текущая энергия: " + tiger.getEnergy() + " Текущее здоровье: " + tiger.getHealth());
    }

    private void huntShareDeerEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        int health = tiger.getHealth();
        energy = energy - 15;
        energy = normalizeEnergy(energy);
        health = health + (int) ((tiger.getPreySizeRate() / 2) * 6);
        health = normalizeHealth(health);
        checkEnergy(tiger);
        tiger.setEnergy(energy);
        tiger.setHealth(health);
        System.out.println("Тигр поймал оленя и любезно разделил добычу с тигрицей! Текущий уровень энергии: " + tiger.getEnergy() + " Текущее здоровье: " + tiger.getHealth());
    }

    private void hunterAttackEvent(Tiger tiger) {
        int health = tiger.getHealth();
        health = health - 30;
        health = normalizeHealth(health);
        tiger.setHealth(health);
        checkEnergy(tiger);
        System.out.println("На тигра напал браконьер! Текущее здоровье: " + tiger.getHealth());
    }

    private void growlEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        energy = energy - 5;
        energy = normalizeEnergy(energy);
        tiger.setEnergy(energy);
        checkEnergy(tiger);
        System.out.println("Тигр рычит!!! Текущая энергия: " + tiger.getEnergy());
    }

    private void defendTerritoryEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        int health = tiger.getHealth();
        energy = energy - 30;
        health = health - 15;
        energy = normalizeEnergy(energy);
        health = normalizeHealth(health);
        tiger.setEnergy(energy);
        tiger.setHealth(health);
        checkEnergy(tiger);
        System.out.println("Тигр защищает свою территорию от чужака - другого тигра! Текущая энергия: " + tiger.getEnergy() + " Текущее здоровье: " + tiger.getHealth());
    }

    private void lickWoolEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        int health = tiger.getHealth();
        energy = energy - 3;
        health = health + 5;
        energy = normalizeEnergy(energy);
        health = normalizeHealth(health);
        tiger.setEnergy(energy);
        tiger.setHealth(health);
        checkEnergy(tiger);
        System.out.println("Тигр вылизывает шерсть! Текущая энергия: " + tiger.getEnergy() + " Текущее здоровье: " + tiger.getHealth());
    }

    private void reproduceEvent(Tiger tiger) {
        int energy = tiger.getEnergy();
        energy = energy - 7;
        energy = normalizeEnergy(energy);
        tiger.setEnergy(energy);
        checkEnergy(tiger);
        System.out.println("Тигр спаривается с тигрицей! Текущая энергия: " + tiger.getEnergy());
    }

    private boolean checkStatus(Tiger tiger) {
        System.out.println("hp: " + tiger.getHealth() + " energy: " + tiger.getEnergy());
        return tiger.getHealth() > 0;
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
