package uebung9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransformerThingy {
    
    public static void main(String... args) throws IOException {
        List<String> lines = new ArrayList<>();
        FileInputStream inputFile = new FileInputStream(args[0]);
        FileOutputStream outputFile = new FileOutputStream(args[1]);
        boolean sortLines = Boolean.valueOf(args[2]);
        boolean countWords = Boolean.valueOf(args[3]);
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        
        if (sortLines) {
            sortLines(lines);
        }
        
        Map<String, Integer> wordCountMap = new HashMap<>();
        if (countWords) {
            countWords(lines, wordCountMap);
        }
        
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for (String outputLine : lines) {
                writer.println(outputLine);
            }
        }
        
        if (countWords) {
            printWordCount(wordCountMap);
        }
    }
    
    private static void sortLines(List<String> lines) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < lines.size() - 1; i++) {
                if (lines.get(i).compareTo(lines.get(i + 1)) > 0) {
                    swap(lines, i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
    }
    
    private static void swap(List<String> list, int index1, int index2) {
        String temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
    
    private static void countWords(List<String> lines, Map<String, Integer> wordCountMap) {
        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }
    }
    
    private static void printWordCount(Map<String, Integer> wordCountMap) {
        for (String word : wordCountMap.keySet()) {
            System.out.println(word + " " + wordCountMap.get(word));
        }
    }
}
