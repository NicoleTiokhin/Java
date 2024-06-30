package com.example;

import java.util.Scanner;

public class Fight {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Potion potion;

    public Fight(Pokemon pokemon1, Pokemon pokemon2 ,Potion potion) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.potion = potion;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Pokemon currentAttacker = pokemon1;
        Pokemon currentDefender = pokemon2;

        while (!pokemon1.hasFainted() && !pokemon2.hasFainted()) {
            System.out.println("\n" + pokemon1.pokedex());
            System.out.println(pokemon2.pokedex());

            boolean validChoice = false;
            while (!validChoice) {
                System.out.println(currentAttacker.getName() + "'s turn.");
                System.out.println("1. Attack");
                if (potion != null && !currentAttacker.isAtMaxHealth()) { 
                    System.out.println("2. Use Potion (heals " + potion.getHealingPower() + " HP)");
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    currentAttacker.attack(currentDefender);
                    validChoice = true;
                } else if (choice == 2 && potion != null && !currentAttacker.isAtMaxHealth()) { 
                    currentAttacker.heal(potion.getHealingPower());
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please type 1 to Attack" + (potion != null && !currentAttacker.isAtMaxHealth() ? " or 2 to Use Healing Potion." : "."));
                }
            }

            if (currentDefender.hasFainted()) {
                System.out.println(currentDefender.getName() + " has fainted! Please bring to Pokémon Center to recover! " + currentAttacker.getName() + " is the winner!");
                break;
            }

            Pokemon temp = currentAttacker;
            currentAttacker = currentDefender;
            currentDefender = temp;

            System.out.println("Please press Enter to continue...");
            scanner.nextLine();
        }

        scanner.close();
    }
}
