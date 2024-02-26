import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    String file = "src/resources/fileExample.txt";
    public String readFile() throws IOException {
        DataInputStream reader = new DataInputStream(new FileInputStream(file));
        int nBytesToRead = reader.available();

        byte[] bytes = new byte[0];
        if (nBytesToRead > 0) {
            bytes = new byte[nBytesToRead];
            reader.read(bytes);
        }
        return new String(bytes).trim();
    }

    public List<String> wordsToArrays(String textToTransform) {
        String words = textToTransform.replaceAll(" ", ",");
        return Arrays.asList(words.split(","));
    }

    public HashMap<String, Integer> wordDensity(List<String> words) {
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for (String word:words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        return wordsMap;
    }

    public HashMap<String, Integer> countWordsAndCharacters(HashMap<String, Integer> map) throws IOException {
        HashMap<String, Integer> wordsAndCharacters = new HashMap<>();
        AtomicInteger wordCount = new AtomicInteger();
        int characterCount = 0;
        map.values().forEach(value -> wordCount.set(wordCount.get() + value));
        wordsAndCharacters.put("Words", wordCount.intValue());
        wordsAndCharacters.put("Characters", readFile().length());
        return wordsAndCharacters;
    }

    public <K, V extends Comparable<? super V>> Map<K, V> sortMap(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.<K, V>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String fileText = main.readFile();
        List<String> array = main.wordsToArrays(fileText);
        HashMap<String, Integer> wordDensity = main.wordDensity(array);
        Map<String, Integer> wordDensitySorted = main.sortMap(wordDensity);
        HashMap<String, Integer> wordCount = main.countWordsAndCharacters(wordDensity);

        System.out.println(wordCount.get("Words") + " words");
        System.out.println(wordCount.get("Characters") + " characters");
        wordDensitySorted.keySet().forEach(word -> System.out.println(word + ": " + wordDensitySorted.get(word)));
    }
}