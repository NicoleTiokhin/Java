package com.example;

public class Pokemon {
    private String name;
    private int health;
    private int attackPower;
    private int maxHealth;


    public Pokemon(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.maxHealth = health;

    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void attack(Pokemon target) {
        System.out.println(this.name + " attacks " + target.getName() + " for " + this.attackPower + " damage!");
        target.setHealth(target.getHealth() - this.attackPower);
    }
    public void heal(int amount) {
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
    }
    

    public boolean hasFainted() {
        if (this.health <= 0) {
            return true;
        }
        return false;
    }

    public boolean isAtMaxHealth() {
        return this.health == this.maxHealth;
    }

    public String pokedex() {
        return name + " [Current Health Level: " + health + ", Current Power Level: " + attackPower + "]";
    }
}
