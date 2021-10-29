package ua.goit.javaCore4.homework9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidNumberClass {
    private static File file = new File("E:\\GoIt\\Java Core 4\\HW9\\src\\main\\resources\\file.txt");
    private static List<String> numbers = new ArrayList<>();


    public static void main(String[] args) {
        numbers = numberReader(file);
        System.out.println("Valid numbers are:");
        for (int i = 0; i < numbers.size(); i++) {
            if (numberValidator(numbers.get(i))) {
                System.out.println(numbers.get(i));
            }
        }
    }

    private static List<String> numberReader(File file) {
        List<String> numbers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader((new FileReader(file)))) {
            numbers.add(bufferedReader.readLine());
            String number = new String();
            while ((number = bufferedReader.readLine()) != null) {
                numbers.add(number);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return numbers;
    }

    private static boolean numberValidator(String number) {
        String regex = "\\(\\d\\d\\d\\) \\d\\d\\d-\\d\\d\\d\\d|\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

}