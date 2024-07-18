package com.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Fight {
    private ArrayList<Pokemon> team1;
    private ArrayList<Pokemon> team2;
    private String team1Name;
    private String team2Name;
    private Potion team1Potion;
    private Potion team2Potion;
    private int totalRounds;
    private int currentRound;
    private int team1TotalHealthLost;
    private int team2TotalHealthLost;
    private Weather weather;

    public Fight(ArrayList<Pokemon> team1, ArrayList<Pokemon> team2, String team1Name, String team2Name, Potion team1Potion, Potion team2Potion, int totalRounds, Weather weather) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.team1Potion = team1Potion;
        this.team2Potion = team2Potion;
        this.totalRounds = totalRounds;
        this.currentRound = 1;
        this.team1TotalHealthLost = 0;
        this.team2TotalHealthLost = 0;
        this.weather = weather; 
    }

    public void start() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (currentRound <= totalRounds) {
            if (countActivePokemon(team1) > 0 && countActivePokemon(team2) > 0) {
                System.out.println("\nRound " + currentRound + " of " + totalRounds);
                fight();
                trackHealth();
                restoreHealth();
                currentRound++;
            } else {
                break;
            }
        }
        determineWinnerByHealth();
        scanner.close();
    }

    private void fight() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        Pokemon currentAttacker = team1.get(0);
        Pokemon currentDefender = team2.get(0);
        ArrayList<Pokemon> currentAttackerTeam = team1;
        ArrayList<Pokemon> currentDefenderTeam = team2;
        Potion currentPotion = team1Potion;

        while (countActivePokemon(team1) > 0 && countActivePokemon(team2) > 0) {
            System.out.println(getTeam1Status(team1, team1Name));
            System.out.println(getTeam2Status(team2, team2Name));

            boolean validChoice = false;
            while (!validChoice) {
                System.out.println(currentAttacker.getName() + "'s turn.");
                System.out.println("1. Attack");
                if (currentPotion != null && !currentAttacker.isAtMaxHealth()) {
                    System.out.println("2. Use Potion (heals " + currentPotion.getHealingPower() + " HP)");
                }
                if (countActivePokemon(currentAttackerTeam) > 1) {
                    System.out.println("3. Switch Pokémon");
                }

                int choice = getIntInput(scanner);

                if (choice == 1) {
                    if (weather != null) {
                        weather.applyWeatherEffects(currentAttacker, currentDefender); 
                    }
                    currentAttacker.attack(currentDefender); 
                    validChoice = true;
                } else if (choice == 2 && currentPotion != null && !currentAttacker.isAtMaxHealth()) {
                    currentAttacker.heal(currentPotion.getHealingPower());
                    validChoice = true;
                } else if (choice == 3 && countActivePokemon(currentAttackerTeam) > 1) {
                    System.out.println("What Pokémon do you want to switch to?");
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
                        (currentPotion != null && !currentAttacker.isAtMaxHealth() ? " or 2 to Use Healing Potion" : "") +
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

                currentPotion = (currentPotion == team1Potion) ? team2Potion : team1Potion;
            }

            System.out.println("Please press Enter to continue...");
            scanner.nextLine();
        }
    }

    
    private void determineWinnerByHealth() {
        System.out.println("Total health lost by " + team1Name + ": " + team1TotalHealthLost);
        System.out.println("Total health lost by " + team2Name + ": " + team2TotalHealthLost);
    
        if (team1TotalHealthLost < team2TotalHealthLost) {
            System.out.println(team1Name + " wins by losing less health!");
        } else if (team2TotalHealthLost < team1TotalHealthLost) {
            System.out.println(team2Name + " wins by losing less health!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private int calculateTotalHealthLost(ArrayList<Pokemon> team) {
        int totalHealthLost = 0;
        for (Pokemon p : team) {
            totalHealthLost += (p.getMaxHealth() - p.getHealth());
        }
        return totalHealthLost;
    }

    private void trackHealth() {
        team1TotalHealthLost += healthLostInRound(team1);
        team2TotalHealthLost += healthLostInRound(team2);
    }

    private int healthLostInRound(ArrayList<Pokemon> team) {
        int healthLost = 0;
        for (Pokemon p : team) {
            healthLost += (p.getMaxHealth() - p.getHealth());
        }
        return healthLost;
    }

    private void restoreHealth() {
        restoreTeamHealth(team1);
        restoreTeamHealth(team2);
    }

    private void restoreTeamHealth(ArrayList<Pokemon> team) {
        for (Pokemon p : team) {
            p.setHealth(p.getMaxHealth());
        }
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
 