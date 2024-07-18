package com.example;

import java.util.Random;

public class Weather {
    private String condition;

    public Weather(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void applyWeatherEffects(Pokemon attacker, Pokemon defender) {
        switch (condition.toLowerCase()) {
            case "sunny":
                if (attacker.getType().equals("FIRE")) {
                    attacker.setAttackPower((int) (attacker.getAttackPower() * 1.5));
                }
                break;
            case "rain":
                if (attacker.getType().equals("WATER")) {
                    attacker.setAttackPower((int) (attacker.getAttackPower() * 1.5));
                }
                break;
            case "hail":
                defender.damage(10);
                break;
            case "sandstorm":
                attacker.damage(10);
                defender.damage(10);
                break;
        }
    }

    public static Weather generateRandomWeather() {
        String[] weatherConditions = { "sunny", "rain", "hail", "sandstorm" };
        Random random = new Random();
        int index = random.nextInt(weatherConditions.length);
        return new Weather(weatherConditions[index]);
    }
}
