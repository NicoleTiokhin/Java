package com.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Fight {
    private ArrayList<Pokemon> team1;
    private ArrayList<Pokemon> team2;
    private String team1Name;
    private String team2Name;
    private Potion potion;

    public Fight(ArrayList<Pokemon> team1, ArrayList<Pokemon> team2, String team1Name, String team2Name, Potion potion) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.potion = potion;
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        Pokemon currentAttacker = team1.get(0);
        Pokemon currentDefender = team2.get(0);
        ArrayList<Pokemon> currentAttackerTeam = team1;
        ArrayList<Pokemon> currentDefenderTeam = team2;

        while (countActivePokemon(team1) > 0 && countActivePokemon(team2) > 0) {
            System.out.println("\n" + getTeam1Status(team1, team1Name));
            System.out.println(getTeam2Status(team2, team2Name));

            boolean validChoice = false;
            while (!validChoice) {
                System.out.println(currentAttacker.getName() + "'s turn.");
                System.out.println("1. Attack");
                if (potion != null && !currentAttacker.isAtMaxHealth()) {
                    System.out.println("2. Use Potion (heals " + potion.getHealingPower() + " HP)");
                }
                if (countActivePokemon(currentAttackerTeam) > 1) {
                    System.out.println("3. Switch Pokémon");
                }

                int choice = getIntInput(scanner);

                if (choice == 1) {
                    currentAttacker.attack(currentDefender);
                    validChoice = true;
                } else if (choice == 2 && potion != null && !currentAttacker.isAtMaxHealth()) {
                    currentAttacker.heal(potion.getHealingPower());
                    validChoice = true;
                } else if (choice == 3 && countActivePokemon(currentAttackerTeam) > 1) {
                    System.out.println("What Pokemon do you want to switch to?");
                    printTeam(currentAttackerTeam, currentAttacker);
                    int switchChoice = getIntInput(scanner);
                    if (switchChoice > 0 && switchChoice <= currentAttackerTeam.size() && currentAttackerTeam.get(switchChoice - 1) != currentAttacker && !currentAttackerTeam.get(switchChoice - 1).hasFainted()) {
                        currentAttacker = currentAttackerTeam.get(switchChoice - 1);
                        validChoice = true;
                    } else {
                        System.out.print("Invalid choice. Please select a valid Pokémon to switch to.");
                    }
                } else {
                    System.out.println("Invalid choice. Please type 1 to Attack" +
                        (potion != null && !currentAttacker.isAtMaxHealth() ? " or 2 to Use Healing Potion" : "") +
                        (countActivePokemon(currentAttackerTeam) > 1 ? " or 3 to Switch Pokémon." : "."));
                }
            }

            if (currentDefender.hasFainted()) {
                System.out.println(currentDefender.getName() + " has fainted! Please bring to Pokémon Center to recover!");
                if (allFainted(currentDefenderTeam)) {
                    System.out.println(currentAttacker.getName() + " is the winner!");
                    break;
                } else {
                    currentDefender = switchToNext(currentDefenderTeam);
                }
            } else {
                Pokemon temp = currentAttacker;
                currentAttacker = currentDefender;
                currentDefender = temp;

                ArrayList<Pokemon> tempTeam = currentAttackerTeam;
                currentAttackerTeam = currentDefenderTeam;
                currentDefenderTeam = tempTeam;
            }

            System.out.println("Please press Enter to continue...");
            scanner.nextLine();
        }

        scanner.close();
    }

    private int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    private boolean allFainted(ArrayList<Pokemon> team) {
        for (Pokemon p : team) {
            if (!p.hasFainted()) {
                return false;
            }
        }
        return true;
    }

    private int countActivePokemon(ArrayList<Pokemon> team) {
        int count = 0;
        for (Pokemon p : team) {
            if (!p.hasFainted()) {
                count++;
            }
        }
        return count;
    }

    private String getTeam1Status(ArrayList<Pokemon> team1, String team1Name) {
        StringBuilder status = new StringBuilder(team1Name + " Team Status:\n");
        for (Pokemon p : team1) {
            status.append(p.pokedex()).append("\n");
        }
        return status.toString();
    }
    
    private String getTeam2Status(ArrayList<Pokemon> team2, String team2Name) {
        StringBuilder status = new StringBuilder(team2Name + " Team Status:\n");
        for (Pokemon p : team2) {
            status.append(p.pokedex()).append("\n");
        }
        return status.toString();
    }
    
    
    

    private void printTeam(ArrayList<Pokemon> team, Pokemon currentPokemon) {
        for (int i = 0; i < team.size(); i++) {
            if (team.get(i) != currentPokemon && !team.get(i).hasFainted()) {
                System.out.println((i + 1) + ". " + team.get(i).getName() + " (Health: " + team.get(i).getHealth() + ")");
            }
        }
    }

    private Pokemon switchToNext(ArrayList<Pokemon> team) {
        for (Pokemon p : team) {
            if (!p.hasFainted()) {
                return p;
            }
        }
        return null;
    }
}