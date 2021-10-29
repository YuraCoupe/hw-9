package ua.goit.javaCore4.homework9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class WordsFrequencyCounter {
    private static File inputFile = new File("E:\\GoIt\\Java Core 4\\HW9\\src\\main\\resources\\words.txt");

    public static void main(String[] args) {
        List<String> words = fileToString(inputFile);
        wordsCounter(words);
    }

    private static List<String> fileToString(File file) {
        List<String> words = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader((new FileReader(file)))) {
            words.addAll(new ArrayList<String>(Arrays.asList(bufferedReader.readLine().split("\s+"))));
            String number = new String();
            while ((number = bufferedReader.readLine()) != null) {
                words.addAll(new ArrayList<String>(Arrays.asList(number.split("\s+"))));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Collections.sort(words);

        return words;
    }

    private static void wordsCounter (List<String> words) {
        Map<String, Integer> elementCountMap = new LinkedHashMap<>();
        for (int i = 0; i < words.size(); i++)
        {
            if (elementCountMap.containsKey(words.get(i)))
            {
                elementCountMap.put(words.get(i), elementCountMap.get(words.get(i))+1);
            }
            else
            {
                elementCountMap.put(words.get(i), 1);
            }
        }
        ArrayList<Entry<String, Integer>> listOfEntry = new ArrayList<>(elementCountMap.entrySet());
        Collections.sort(listOfEntry, new Comparator<Entry<String, Integer>>()
                {
                    @Override
                    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
                    {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                }
        );
        for (Entry<String, Integer> entry: listOfEntry) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
