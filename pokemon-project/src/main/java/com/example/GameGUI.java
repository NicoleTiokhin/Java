package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.json.JSONObject;

public class GameGUI extends JFrame implements KeyListener {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel introPanel;
    private JPanel gamePanel;
    private JLabel character;
    private int characterX = 100;
    private int characterY = 100;
    private Pokemon starterPokemon;

    public GameGUI() {
        setTitle("Pokemon main Story Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Introduction Panel
        introPanel = new JPanel();
        introPanel.setLayout(new BorderLayout());
        JLabel introLabel = new JLabel("Choose your starter Pokémon: Squirtle, Charmander, or Bulbasaur", JLabel.CENTER);
        introPanel.add(introLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton squirtleButton = new JButton("Squirtle");
        JButton charmanderButton = new JButton("Charmander");
        JButton bulbasaurButton = new JButton("Bulbasaur");

        squirtleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starterPokemon = createPokemon("squirtle");
                startGame();
            }
        });

        charmanderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starterPokemon = createPokemon("charmander");
                startGame();
            }
        });

        bulbasaurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starterPokemon = createPokemon("bulbasaur");
                startGame();
            }
        });

        buttonPanel.add(squirtleButton);
        buttonPanel.add(charmanderButton);
        buttonPanel.add(bulbasaurButton);
        introPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Game Panel
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(characterX, characterY, 50, 50);
            }
        };
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);

        mainPanel.add(introPanel, "Intro");
        mainPanel.add(gamePanel, "Game");

        add(mainPanel);
    }

    private void startGame() {
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            characterX -= 10;
        } else if (key == KeyEvent.VK_RIGHT) {
            characterX += 10;
        } else if (key == KeyEvent.VK_UP) {
            characterY -= 10;
        } else if (key == KeyEvent.VK_DOWN) {
            characterY += 10;
        }

        gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameGUI game = new GameGUI();
                game.setVisible(true);
            }
        });
    }

    public Pokemon createPokemon(String pokemonName) {
        try {
            JSONObject data = PokemonAPI.getPokemonData(pokemonName.trim().toLowerCase());
            String name = data.getString("name");
            int health = data.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
            int attackPower = data.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
            String type = data.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name").toUpperCase();
            TypeEffectiveness typeEffectiveness = PokemonAPI.getTypeEffectiveness(type);
            return new Pokemon(name, health, attackPower, type, typeEffectiveness);
        } catch (Exception e) {
            System.out.println("Error fetching data for " + pokemonName + ".");
            return null;
        }
    }
}
