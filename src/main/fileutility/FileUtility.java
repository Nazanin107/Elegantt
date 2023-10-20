package main.fileutility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static main.codeparty.Filetest.processLine;

public class FileUtility {
    public static void readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            bufferedReader.lines()
                    .filter(l -> !l.trim().equals(""))
                    .forEach(line -> System.out.println(processLine(line)));

        } catch (IOException e) {
            System.err.println("File Not Found");
            System.exit(1);
        }
    }

}
