package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private final List<String> dictionary;
    private final Random generator;

    public Dictionary() {
        this.dictionary = new ArrayList<>();
        this.generator = new Random();
    }

    public void addWord(String word) {
        if (word == null) {
            throw new DictionaryException("Null word!");
        }
        if (word.isEmpty()) {
            throw new DictionaryException("Empty word!");
        }
        if (!word.matches("[a-zA-Z]+")) {
            throw new DictionaryException("Incorrect word!");
        }
        word = word.trim().toLowerCase();
        dictionary.add(word);
    }

    public String getRandomWord() {
        return dictionary.get(generator.nextInt(dictionary.size()));
    }

    public void remove(String word) {
        dictionary.remove(word);
    }

    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    public void clear() {
        dictionary.clear();
    }
}
