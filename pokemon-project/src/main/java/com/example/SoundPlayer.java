package com.example;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {
    public static void playSoundFromFile(String filePath) {
        if (filePath.endsWith(".wav")) {
            playWavFromFile(filePath);
        } else if (filePath.endsWith(".mp3")) {
            playMp3FromFile(filePath);
        } else {
            System.err.println("Unsupported audio file format: " + filePath);
        }
    }

    private static void playWavFromFile(String filePath) {
        try {
            System.out.println("Attempting to play WAV sound from file: " + filePath);
            InputStream audioSrc = new FileInputStream(filePath);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("WAV sound is playing...");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing WAV file: " + e.getMessage());
        }
    }

    private static void playMp3FromFile(String filePath) {
        try {
            System.out.println("Attempting to play MP3 sound from file: " + filePath);
            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player player = new Player(bis);
            player.play();
            System.out.println("MP3 sound is playing...");
        } catch (JavaLayerException | IOException e) {
            System.err.println("Error playing MP3 file: " + e.getMessage());
        }
    }
}

