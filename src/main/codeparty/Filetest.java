package main.codeparty;

import main.service.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.fileutility.FileUtility.readFile;

public class Filetest {
    public static void main(String[] args) {
        Filetest fileTest = new Filetest();
        var fileName = fileTest.getClass().getClassLoader().getResource("main/codeparty/test.txt");

        if (fileName == null || fileName.getPath() == null) {
            System.err.println("file not found");
            System.exit(1);
        }

        readFile(fileName.getPath());
    }

    public static double processLine(String line) {
        Pattern operatorPattern = Pattern.compile("[\\+\\-\\*\\/]");
        Pattern numbersPattern = Pattern.compile("[0-9\\.0-9]+");

        Matcher operatorMather = operatorPattern.matcher(line);
        Matcher numberMather = numbersPattern.matcher(line);
        List<Integer> numbers = new ArrayList<>();
        while (numberMather.find()) {
            numbers.add(Integer.parseInt(numberMather.group()));
        }

        int i = 0;
        double result = 0;
        while (operatorMather.find()) {
            if (i == 0) {
                result = Service.calculate(operatorMather.group(), numbers.get(i), numbers.get(++i));
            } else {
                result = Service.calculate(operatorMather.group(), result, numbers.get(++i));
            }
        }

        return result;
    }



}