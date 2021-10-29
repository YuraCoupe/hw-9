package ua.goit.javaCore4.homework9;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonFileMaker {
    private static File inputFile = new File("E:\\GoIt\\Java Core 4\\HW9\\src\\main\\resources\\file2.txt");
    private static File resultFile = new File("E:\\GoIt\\Java Core 4\\HW9\\src\\main\\resources\\user.json");


    public static void main(String[] args) {
        List<String> personsStringList = new ArrayList<>();
        personsStringList = personReader(inputFile);
        List<Person> personsList= personMaker(personsStringList);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(personsList);
        fileWriter(resultFile, json);
    }

    private static List<String> personReader(File file) {
        List<String> persons = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader((new FileReader(file)))) {
            persons.add(bufferedReader.readLine());
            String number = new String();
            while ((number = bufferedReader.readLine()) != null) {
                persons.add(number);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return persons;
    }

    private static List<Person> personMaker (List<String> personsStringList) {
        List<Person> personsList = new ArrayList<>();
        for (int i = 1; i < personsStringList.size(); i++) {
            String[] personString = personsStringList.get(i).split(" ");
            personsList.add(new Person(personString[0], Integer.parseInt(personString[1])));
        }

        return personsList;
    }

    private static void fileWriter (File file, String string) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}