package com.example.p2pfilesharing.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Utility class for file operations.
 */
public class FileUtils {
    private static final Logger logger = LogManager.getLogger(FileUtils.class);

    private FileUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Reads a file into a byte array.
     *
     * @param file the file to read
     * @return the file contents as a byte array
     * @throws IOException if an I/O error occurs
     */
    public static byte[] readFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            logger.info("File read successfully: {}", file.getName());
            return bos.toByteArray();
        } catch (IOException e) {
            logger.error("Error reading file: {}", file.getName(), e);
            throw e;
        }
    }

    /**
     * Writes a byte array to a file.
     *
     * @param file the file to write to
     * @param data the byte array to write
     * @throws IOException if an I/O error occurs
     */
    public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
            logger.info("File written successfully: {}", file.getName());
        } catch (IOException e) {
            logger.error("Error writing file: {}", file.getName(), e);
            throw e;
        }
    }

    /**
     * Checks if a file exists and is readable.
     *
     * @param file the file to check
     * @return true if the file exists and is readable, false otherwise
     */
    public static boolean isFileReadable(File file) {
        return file.exists() && file.canRead();
    }

    /**
     * Checks if a file can be written to.
     *
     * @param file the file to check
     * @return true if the file can be written to, false otherwise
     */
    public static boolean isFileWritable(File file) {
        if (file.exists()) {
            return file.canWrite();
        } else {
            File parent = file.getParentFile();
            return parent != null && parent.canWrite();
        }
    }
}