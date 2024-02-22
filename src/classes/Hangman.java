package classes;

import processing.core.*;
import java.util.*;

public class Hangman {
    private String secretWord;
    private char[] guessedLetters;
    private ArrayList<Character> wrongLetters = new ArrayList<Character>();
    private char[] validLetters = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'å', 'a', 's', 'd', 'f', 'g', 'h',
            'j', 'k', 'l', 'æ', 'ø', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };

    int lives = 5;

    public Hangman(String word) {
        secretWord = word.toLowerCase();
        guessedLetters = new char[secretWord.length()];

        // init partial word, and set all
        // letters to '_'
        guessedLetters = new char[word.length()];
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    public char[] getGuessedLetters() {
        return guessedLetters;
    }

    public boolean guess(char letter) {
        char lowerCase = Character.toLowerCase(letter);
        boolean letterInSecretWord = false;
        boolean valid = false;
        for (int i = 0; i < validLetters.length; i++) {
            if (validLetters[i] == lowerCase) {
                valid = true;
            }
        }
        if (!valid) {
            return letterInSecretWord;
        }
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == lowerCase) {
                guessedLetters[i] = lowerCase;
                letterInSecretWord = true;
            } else {
                boolean guessed = false;
                for (int j = 0; j < wrongLetters.size(); j++) {
                    if (wrongLetters.get(j) == lowerCase) {
                        guessed = true;
                        break;
                    }
                }
                if (!guessed) {
                    wrongLetters.add(lowerCase);
                }
            }
        }

        return letterInSecretWord;
    }

    public void drawGuessedLetters(PApplet p) {
        // draw word on screen
        p.textSize(32);
        for (int i = 0; i < guessedLetters.length; i++) {
            // draw guessed letter in center of the screen
            p.text(guessedLetters[i], p.width / 2 + 20 * i -
                    guessedLetters.length * 20 / 2, p.height / 2);
        }
    }

    public boolean isAlive() {
        if (wrongLetters.size() >= 6) {
            return false;
        }
        return true;
    }
}
