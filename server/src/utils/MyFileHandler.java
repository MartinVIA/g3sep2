package utils;

import java.io.*;

/**
 * Utility class that handles low-level file operations for both text and binary files.
 * This class provides reusable methods for writing and reading:
 * - text content (single string or array of strings)
 * - serialized objects in binary format (single object or array of objects)
 * Higher-level utility classes such as FileReader and FileWriter
 * use this class to persist and load application data.
 * @author Allan Henriksen
 */
public class MyFileHandler {

    /**
     * Writes a string to a text file.
     * If the file already exists, its content is overwritten.
     * @param fileName the name (or path) of the file to write to
     * @param str      the string to write to the file
     * @throws FileNotFoundException if the file cannot be created or opened
     */
    public static void writeToTextFile(String fileName, String str) throws FileNotFoundException {
        writeText(fileName, str, false);
    }

    /**
     * Appends a string to a text file.
     * If the file does not exist, it is created.
     * @param fileName the name (or path) of the file to append to
     * @param str      the string to append to the file
     * @throws FileNotFoundException if the file cannot be created or opened
     */
    public static void appendToTextFile(String fileName, String str) throws FileNotFoundException {
        writeText(fileName, str, true);
    }

    /**
     * Internal helper method used by writeToTextFile(String, String)}
     * and appendToTextFile(String, String)}.
     * @param fileName the file name (or path)
     * @param str      the string to write
     * @param append   true to append, false to overwrite
     * @throws FileNotFoundException if the file cannot be created or opened
     */
    private static void writeText(String fileName, String str, boolean append) throws FileNotFoundException {
      PrintWriter writeToFile = null;
      try {
        File file = new File(fileName);
        if (file.getParentFile() != null) {
          file.getParentFile().mkdirs();
        }
        FileOutputStream fileOutStream = new FileOutputStream(file, append);
        writeToFile = new PrintWriter(fileOutStream);
        writeToFile.println(str);
      } finally {
        if (writeToFile != null) {
          writeToFile.close();
        }
      }
    }

}
