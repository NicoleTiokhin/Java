package com.example;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

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

    public void attack(Pokemon target, Terminal terminal) throws IOException, InterruptedException {
        System.out.println(this.name + " attacks " + target.getName() + " for " + this.attackPower + " damage!");
        target.animatedHealthChange(-this.attackPower, terminal);
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
        return this.health <= 0;
    }

    public boolean isAtMaxHealth() {
        return this.health == this.maxHealth;
    }

    public void displayHealthBar(Terminal terminal) throws IOException {
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

        terminal.puts(InfoCmp.Capability.carriage_return);
        terminal.writer().print(bar.toString());
        terminal.flush();
    }

    public void animatedHealthChange(int healthChange, Terminal terminal) throws IOException, InterruptedException {
        int newHealth = this.health + healthChange;
        if (newHealth > maxHealth) newHealth = maxHealth;
        if (newHealth < 0) newHealth = 0;

        int steps = 20;
        int healthStep = healthChange / steps;

        for (int i = 0; i < steps; i++) {
            this.health += healthStep;
            if (this.health > maxHealth) this.health = maxHealth;
            if (this.health < 0) this.health = 0;
            displayHealthBar(terminal);
            Thread.sleep(50);
        }
        this.health = newHealth;
        displayHealthBar(terminal);
    }

    public String pokedex() {
        return name + " [Current Health Level: " + health + ", Current Power Level: " + attackPower + "]";
    }
}
