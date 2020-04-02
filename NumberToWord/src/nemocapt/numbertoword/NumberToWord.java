package nemocapt.numbertoword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class NumberToWord {
    private static final Logger logger = LoggerFactory.getLogger(NumberToWord.class);

    public static String numberToWord(String wholePart) throws FileNotFoundException {//рекурсивный метод, заполняет разряды по три подразряда:  разряды(миллиарды, миллионы, тысячи и т.п.) подразряды(сотни десятки единицы)
        String processedNumber = "", result = "", dictionaryForFractionsPath = "dictionaryForFractions.txt";
        if (wholePart.equals("0"))//для нуля
            return "Ноль";

        if (wholePart.substring(0, 1).equals("-") && !wholePart.substring(1, 2).equals("0")) {//для отрицательных
            wholePart = wholePart.substring(1);
            result += "Минус ";
        }

        int isFractional = 0, fractionPosition;
        String fractionPart = "";
        for (fractionPosition = 0; fractionPosition < wholePart.length(); fractionPosition++) {
            if (wholePart.substring(fractionPosition, fractionPosition + 1).equals(",")) {
                isFractional++;
                fractionPart = wholePart.substring(fractionPosition + 1);
                wholePart = wholePart.substring(0, fractionPosition);
            }
        }

        for (int i = 0; i < fractionPart.length(); i++) {//удаление повторяющихся нулей в конце дробной части
            if (fractionPart.substring(fractionPart.length() - 1).equals("0") && fractionPart.length() != 1)
                fractionPart = fractionPart.substring(0, fractionPart.length() - 1);
        }

        result += createNumber(wholePart, processedNumber, isFractional);//запись целой части
        if (isFractional == 1 && !fractionPart.equals("0")) {//запись дробной асти
            result += " И ";
            result += createNumber(fractionPart, processedNumber, isFractional);
            Scanner sc = new Scanner(new BufferedReader(new FileReader(dictionaryForFractionsPath)));
            List<List<String>> fractionName = new ArrayList<>();
            fractionName.add(new ArrayList<>());
            fractionName.add(new ArrayList<>());
            while (sc.hasNextLine()) {
                for (int i = 0; i < 2; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (String s : line) {
                        fractionName.get(i).add(s);
                    }
                }
            }
            int i = 0;
            boolean isEleven = false;
            if (!fractionPart.substring(fractionPart.length() - 1).equals("1"))
                i++;//для правильного склонения
            if (fractionPart.length() != 1)
                if (fractionPart.substring(fractionPart.length() - 2).equals("11")) {
                    result += " " + fractionName.get(1).get(fractionPart.length() - 1);
                    isEleven = true;
                }
            if (!isEleven)
                result += " " + fractionName.get(i).get(fractionPart.length() - 1);
        }
        if (result.substring(result.length() - 1).equals(" "))
            result = result.substring(0, result.length() - 1);
        return result.replaceAll("\\s{2,}", " ");//выводит финальный ответ, удаляет лишние пробелы
    }

    public static String createNumber(String numberPart, String processedNumber, int isFractional) throws FileNotFoundException {
        String[][] digit1 = {{"", "Один", "Два", "Три", "Четыре", "Пять", "Шесть", "Семь", "Восемь", "Девять"}, {"", "Одна", "Две", "Три", "Четыре", "Пять", "Шесть", "Семь", "Восемь", "Девять"}};
        String[] digit10 = {"Десять", "Одиннадцать", "Двенадцать", "Тринадцать", "Четырнадцать", "Пятнадцать", "Шестнадцать", "Семнадцать", "Восемнадцать", "Девятнадцать"};
        String[] digit20 = {"", "Десять", "Двадцать", "Тридцать", "Сорок", "Пятьдесят", "Шестьдесят", "Семьдесят", "Восемьдесят", "Девяносто"};
        String[] digit100 = {"", "Сто", "Двести", "Триста", "Четыреста", "Пятьсот", "Шестьсот", "Семьсот", "Восемьсот", "Девятьсот"};
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
        StringBuilder processedNumberBuilder = new StringBuilder(processedNumber);
        do {
            String[] tempStr = numberPart.split("");//Создание массива из цифр заданного числа
            int[] digits = new int[numberPart.length()];
            for (int i = 0; i < numberPart.length(); i++) {
                digits[i] = Integer.parseInt(tempStr[i]);
            }
            int count;//число подрязрядов в разряде (первый разряд может иметь 1-3 подразряда, все остальные всегда 3)
            if (numberPart.length() % 3 != 0) count = numberPart.length() % 3;
            else count = 3;

            int isThousand = 0;//для тысяч используются другие окончания у цифр 1-3
            if (numberPart.length() > 3 && numberPart.length() < 7)
                isThousand++;

            processedNumberBuilder.append(eachDigit(count, digits, digit1, digit10, digit20, digit100, isThousand, isFractional));//метод заполнения разряда
            processedNumberBuilder.append(inclineEachDigitName(digits, digit1000));//метод добавления названия разряда в правильном склонении

            numberPart = numberPart.substring(count);
            processedNumberBuilder.append(" ");
        } while (numberPart.length() > 2);
        processedNumber = processedNumberBuilder.toString();

        return processedNumber.replaceAll("\\s{2,}", " ");//выводит ответ, удаляет лишние пробелы
    }

    private static String eachDigit(int count, int[] digits, String[][] digit1, String[] digit10, String[] digit20, String[] digit100, int isThousand, int isFraction) {
        String processedDigit = "";
        if (isFraction == 1)
            isThousand = isFraction;
        switch (count) {
            case 0:
                logger.info("Введите число!");
            case 1://единицы
                processedDigit += digit1[isThousand][digits[0]];
                break;
            case 2://десятки
                if (digits[0] == 1)
                    processedDigit += digit10[digits[1]];
                else {
                    processedDigit += digit20[digits[0]];
                    if (digits[0] > 0)
                        processedDigit += " " + digit1[isThousand][digits[1]];
                    else if (isFraction == 1)
                        processedDigit += " " + digit1[isThousand][digits[1]];
                }
                break;
            case 3://сотни
                processedDigit += digit100[digits[0]];
                if (digits[1] == 1)
                    processedDigit += " " + digit10[digits[2]];
                else {
                    processedDigit += " " + digit20[digits[1]];
                    if (digits[2] > 0)
                        processedDigit += " " + digit1[isThousand][digits[2]];
                }
                break;
        }
        return processedDigit;
    }

    public static String inclineEachDigitName(int[] digits, List<List<String>> digit1000) {
        String digitName = "";
        if (digits.length > 3) {
            if (digits[1] != 1 && digits.length % 3 == 0) {//добавление названия разряда в правильном склонении
                if (digits[2] == 0)
                    if (digits[1] == 0)
                        if (digits[0] == 0)
                            return "";
                if (digits[2] == 1)
                    digitName += " " + digit1000.get(0).get((int) Math.ceil((double) digits.length / 3));
                if (digits[2] > 1 && digits[2] < 5)
                    digitName += " " + digit1000.get(1).get((int) Math.ceil((double) digits.length / 3));
                if (digits[2] > 4 || digits[2] == 0)
                    digitName += " " + digit1000.get(2).get((int) Math.ceil((double) digits.length / 3));
            } else if (digits.length % 3 == 0)
                digitName += " " + digit1000.get(2).get((int) Math.ceil((double) digits.length / 3) - 2);
            if (digits.length % 3 == 2) {//если первый разряд из 2х чисел
                if (digits[0] != 1) {
                    if (digits[1] == 0)
                        if (digits[0] == 0)
                            return "";
                    if (digits[1] == 1)
                        digitName += " " + digit1000.get(0).get((int) Math.ceil((double) digits.length / 3));
                    if (digits[1] > 1 && digits[1] < 5)
                        digitName += " " + digit1000.get(1).get((int) Math.ceil((double) digits.length / 3));
                    if (digits[1] > 4 || digits[1] == 0)
                        digitName += " " + digit1000.get(2).get((int) Math.ceil((double) digits.length / 3));
                } else
                    digitName += " " + digit1000.get(2).get((int) Math.ceil((double) digits.length / 3));
            }
            if (digits.length % 3 == 1) {//если первый разряд из 1го числа
                if (digits[0] == 1)
                    digitName += " " + digit1000.get(0).get((int) Math.ceil((double) digits.length / 3));
                if (digits[0] > 1 && digits[0] < 5)
                    digitName += " " + digit1000.get(1).get((int) Math.ceil((double) digits.length / 3));
                if (digits[0] > 4)
                    digitName += " " + digit1000.get(2).get((int) Math.ceil((double) digits.length / 3));
            }
        }
        return digitName;
    }
}
