package com.gildedrose;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class TestUtils {

    public static File getExpectedFile(String fileName) {
        try {
            URI expectedFileUri = Objects.requireNonNull(TestUtils.class.getClassLoader().getResource(fileName)).toURI();
            return Paths.get(expectedFileUri).toFile();
        } catch (Exception e) {
            System.out.println("Error retrieving the benchmark file");
            throw new AssertionError(e);
        }
    }

    public static void writeEvolution(File file, String dayContent) {
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(dayContent);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Error while writing the monthly evolution");
            throw new AssertionError(e);
        }
    }

    public static File createResultFile(String fileName) {
        try {
            URI resourcesUri = TestUtils.class.getResource("/").toURI();
            Path parent = Paths.get(resourcesUri);
            Path evolution = Paths.get(parent.toString() + "/" + fileName);
            Files.deleteIfExists(evolution);
            Path path = Files.createFile(evolution);
            return path.toFile();
        } catch (Exception e) {
            System.out.println("Error creating the output file");
            throw new AssertionError(e);
        }
    }
}
