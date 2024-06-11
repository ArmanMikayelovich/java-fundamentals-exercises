package com.bobocode.se;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            while ((s = scanner.nextLine()) != null) {
                stringBuilder.append(s);
            }
            return stringBuilder.toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
