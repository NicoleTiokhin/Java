package com.example;

import java.util.Random;

public class Potion {
    private int healingPower;

    public Potion() {
        Random random = new Random();
        this.healingPower = random.nextInt(41) + 10;
    }

    public int getHealingPower() {
        return healingPower;
    }
}