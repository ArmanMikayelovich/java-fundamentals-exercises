package com.bobocode.se;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    private final Map<Character, Integer> charCounts;

    private FileStats(Map<Character, Integer> statistics) {
        this.charCounts = statistics;
    }

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        Map<Character, Integer> charCounts = new HashMap<>();
        String content = readWholeFile(fileName);
        content.chars().filter(c -> !Character.isWhitespace(c)).forEach(c -> charCounts.merge((char) c, 1, Integer::sum));
        return new FileStats(charCounts);
    }

    public static String readWholeFile(String fileName) {
        try (InputStream resourceAsStream = FileStats.class.getClassLoader().getResourceAsStream(fileName)) {
            if (resourceAsStream == null) {
                throw new FileNotFoundException("File not found: " + fileName);
            }

            try (Scanner scanner = new Scanner(resourceAsStream, StandardCharsets.UTF_8)) {
                StringBuilder stringBuilder = new StringBuilder();
                boolean isFirstLine = true;
                while (scanner.hasNextLine()) {
                    if (!isFirstLine) {
                        stringBuilder.append('\n');
                    }
                    isFirstLine = false;
                    stringBuilder.append(scanner.nextLine());
                }
                return stringBuilder.toString();
            }
        } catch (IOException e) {
            throw new FileStatsException("");
        }
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return charCounts.getOrDefault(character, 0);
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        return charCounts.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElseThrow(() -> new RuntimeException("No characters found"));
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return charCounts.containsKey(character);
    }
}

