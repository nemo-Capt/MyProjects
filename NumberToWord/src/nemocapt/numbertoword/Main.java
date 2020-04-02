package nemocapt.numbertoword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        logger.info("Введите число:");
        String data = in.next();
        while (data.equals("-0") || data.length() > 1 && data.substring(0, 1).equals("0") || data.length() > 1 && data.substring(0, 2).equals("-0")) {
            logger.info("Введите существующее число:");
            data = in.next();
        }
        logger.info(NumberToWord.numberToWord(data));
    }
}
