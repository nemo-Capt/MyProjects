package nemocapt.Tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberToWordTest {
    private static final Logger logger = LoggerFactory.getLogger(NumberToWordTest.class);
    @org.junit.jupiter.api.Test
    void testNumberToWord() throws FileNotFoundException {
        String testFile = "tests.txt";
        Scanner sc = new Scanner(new BufferedReader(new FileReader(testFile)));
        Map<String, String> testValues = new HashMap<>();

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            logger.info(str);
            testValues.put(str.substring(0, str.indexOf(" ")), str.substring(str.indexOf(" ") + 1));
        }

        for (Map.Entry<String, String> entry : testValues.entrySet()) {
            assertEquals(entry.getValue(), nemocapt.numbertoword.NumberToWord.numberToWord(entry.getKey()));
        }
    }

    @org.junit.jupiter.api.Test
    void createNumberTest() throws FileNotFoundException {
        assertEquals("Одна Тысяча Двести Пятьдесят Четыре ", nemocapt.numbertoword.NumberToWord.createNumber("1254", "", 0).replaceAll("\\s{2,}", " "));
    }

    @org.junit.jupiter.api.Test
    void eachDigitTest() throws FileNotFoundException {
        String dictionaryPath = "dictionary.txt";
        Scanner sc = new Scanner(new BufferedReader(new FileReader(dictionaryPath)));
        List<List<String>> digit1000 = new ArrayList<>();
        digit1000.add(new ArrayList<>());
        digit1000.add(new ArrayList<>());
        digit1000.add(new ArrayList<>());
        while (sc.hasNextLine()) {
            for (List<String> strings : digit1000) {
                String[] line = sc.nextLine().trim().split(",");
                Collections.addAll(strings, line);
            }
        }
        int[] arr = {1, 2, 3, 4, 5, 6};
        assertEquals(" Тысячи", nemocapt.numbertoword.NumberToWord.inclineEachDigitName(arr, digit1000).replaceAll("\\s{2,}", " "));
    }

}
