package com.bobocode.se;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

        try (InputStream resourceAsStream = FileReaders.class.getClassLoader().getResourceAsStream(fileName);
             Scanner scanner = new Scanner(resourceAsStream)) {

            StringBuilder stringBuilder = new StringBuilder();
            String s;
            boolean isFirstLine = true;
            while (scanner.hasNext() && (s = scanner.nextLine()) != null) {
                if (!isFirstLine) {

                    stringBuilder.append('\n');
                }
                isFirstLine = false;
                stringBuilder.append(s);
            }
            return stringBuilder.toString();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
