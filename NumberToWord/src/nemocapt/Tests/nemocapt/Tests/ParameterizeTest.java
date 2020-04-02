package nemocapt.Tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static nemocapt.numbertoword.NumberToWord.numberToWord;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(Parameterized.class)
public class ParameterizeTest {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizeTest.class);
    private String initialValue;
    private String expected;


    public ParameterizeTest(String initialValue, String expected) {
        this.initialValue = initialValue;
        this.expected = expected;
    }

    @Parameterized.Parameters()
    public static Iterable<Object[]> dataForTest() throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("tests.txt")));
        Map<String, String> testValues = new HashMap<>();

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            logger.info(str);
            testValues.put(str.substring(0, str.indexOf(" ")), str.substring(str.indexOf(" ") + 1));
        }
        Object[][] objectTestValues = new Object[testValues.size()][];
        int i=0;
        for (Map.Entry<String, String> entry : testValues.entrySet()) {
            objectTestValues[i]=new Object[2];
            objectTestValues[i][0]=entry.getKey();
            objectTestValues[i][1]=entry.getValue();
            i++;
        }
        return Arrays.asList(objectTestValues);
    }

    @Test
    public void paramTest() throws FileNotFoundException {
        assertEquals(expected, numberToWord(initialValue));
    }
}
