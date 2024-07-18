package com.example;

import java.io.IOException;

public class Pokemon {
    private String name;
    private int health;
    private int attackPower;
    private int maxHealth;
    private String type;
    private TypeEffectiveness typeEffectiveness;

    public Pokemon(String name, int health, int attackPower, String type, TypeEffectiveness typeEffectiveness) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.maxHealth = health;
        this.type = type;
        this.typeEffectiveness = typeEffectiveness;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public String getType() {
        return type;
    }
    public void damage(int amount) {
        this.health -= amount;
        if (this.health < 0) this.health = 0;
    }
    public void attack(Pokemon target) throws InterruptedException {
        double effectiveness = typeEffectiveness.getEffectiveness(this.type, target.getType());
        int damage = (int) (this.attackPower * effectiveness);
        
        System.out.println(this.name + " attacks " + target.getName() + " for " + damage + " damage!");
    
        if (effectiveness > 1.0) {
            System.out.println("It's super effective!");
        } else if (effectiveness < 1.0 && effectiveness > 0.0) {
            System.out.println("It's not very effective...");
        } else if (effectiveness == 0.0) {
            System.out.println("It doesn't affect " + target.getName() + "...");
        }
    
        target.animatedHealthChange(-damage);
        System.out.println();
    }
    

    public void heal(int amount) throws InterruptedException {
        if (this.health == this.maxHealth) {
            System.out.println(this.name + " is already at full health and cannot be healed.");
            return;
        }
        int healedAmount = amount;
        if (this.health + amount > this.maxHealth) {
            healedAmount = this.maxHealth - this.health;
            this.health = this.maxHealth;
        } else {
            this.health += amount;
        }
        System.out.println(this.name + " healed by " + healedAmount + " hp points!");
        animatedHealthChange(healedAmount);
    }

    public boolean hasFainted() {
        return this.health <= 0;
    }

    public boolean isAtMaxHealth() {
        return this.health == this.maxHealth;
    }

    public void displayHealthBar() {
        int totalSegments = 20;
        int filledSegments = (health * totalSegments) / maxHealth;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < totalSegments; i++) {
            if (i < filledSegments) {
                bar.append("#");
            } else {
                bar.append(" ");
            }
        }
        bar.append("] ").append(health).append("/").append(maxHealth);
        System.out.print("\r" + bar.toString());
    }

    public void animatedHealthChange(int healthChange) throws InterruptedException {
        int newHealth = this.health + healthChange;
        if (newHealth > maxHealth) newHealth = maxHealth;
        if (newHealth < 0) newHealth = 0;

        int steps = 20;
        int healthStep = healthChange / steps;

        for (int i = 0; i < steps; i++) {
            this.health += healthStep;
            if (this.health > maxHealth) this.health = maxHealth;
            if (this.health < 0) this.health = 0;
            displayHealthBar();
            Thread.sleep(50);
        }
        this.health = newHealth;
        displayHealthBar();
        System.out.println();
    }

    public String pokedex() {
        return name + " [Current Health Level: " + health + ", Current Power Level: " + attackPower + "]";
    }

}

   