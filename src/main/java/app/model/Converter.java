package app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {

    public String DecToBin(String strNumber) {
        int number = Integer.parseInt(strNumber);
        StringBuilder converted = new StringBuilder();

        List<Integer> binBaseAll = new ArrayList<>() {{
            int baseNum = 1;
            add(baseNum);
            for(int base = 0; base < 30; base++) {
                baseNum = baseNum * 2;
                add(baseNum);
            }
        }};
        List<Integer> binBase = new ArrayList<>(binBaseAll);

        for(int index = binBase.size() - 1; index >= 0; index--) {
            if(number >= binBase.get(index) && number > 1) {
                binBase.subList(binBase.indexOf(binBase.get(index + 1)), binBase.indexOf(binBase.get(binBase.size() - 1))).clear();
                binBase.remove(binBase.size() - 1);
                break;
            } else if (number == 1) {
                binBase.subList(1, binBase.indexOf(binBase.get(binBase.size() - 1))).clear();
                binBase.remove(binBase.size() - 1);
                break;
            }
        }

        while(number != 0) {
            for(int index = (binBase.size()) > 7 ? binBase.size() - 1 : 7 ; index >= 0; index--) {
                if(number >= binBaseAll.get(index)) {
                    number -= binBaseAll.get(index);
                    converted.append(1);
                } else {
                    converted.append(0);
                }
            }
        }
        return converted.toString();
    }

    public int BinToDec(String strBinary) {
        int len = strBinary.length() - 1;
        int converted = 0;
        List<Integer> binBaseAll = new ArrayList<>() {{
            int baseNum = 1;
            add(baseNum);
            for(int base = 0; base < len; base++) {
                baseNum = baseNum * 2;
                add(baseNum);
            }
        }};

        for(int index = 0; index <= len; index++) {
            if(Integer.parseInt(String.valueOf(strBinary.charAt(index))) == 1) {
                converted += Math.pow(2,binBaseAll.size() - 1);
            }
            binBaseAll.remove(binBaseAll.size() - 1);
            System.out.println(converted);
        }
        return converted;
    }

    public void DecToHex() {
    }

    public void HexToDec() {
    }

    public String DecToRom(String strNumber) {
        // Find a way to patch the error if the user enter anything else than a number.
        // Find a better way to patch the lasting II when we clean the TextField.

        System.out.println(strNumber);
        int number = Integer.parseInt(strNumber);
        StringBuilder converted = new StringBuilder();
        String[] chars = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        Integer[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

        List<String> romanChar = Arrays.asList(chars);
        List<Integer> romanValue = Arrays.asList(values);

        while(number != 0) {
            for(int index = romanValue.size() - 1; index >= 0; index--) {
                if(number >= romanValue.get(index)) {
                    number -= romanValue.get(index);
                    converted.append(romanChar.get(index));
                    break; // Useful to avoid XIXI on 20 for example.
                }
            }
        }
        return converted.toString();
    }

    public int RomToDec(String strRomain) {
        // Find a way to patch the error if the user enter anything else than those letters.
        // Find a better way to patch the wrong roman value.

        int converted = 0;
        Character[] chars = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        Integer[] values = {1, 5, 10, 50, 100, 500, 1000};

        List<Character> romanChar = Arrays.asList(chars);
        List<Integer> romanValue = Arrays.asList(values);

        for(int index = 0; index < strRomain.length(); index++) {
            char currentChar = strRomain.charAt(index);
            int currentValue = romanValue.get(romanChar.indexOf(currentChar));

            if (index != 0) {
                int previousValue = romanValue.get(romanChar.indexOf(strRomain.charAt(index - 1)));

                if (currentValue > previousValue) converted += currentValue - (2 * previousValue);
                else converted += currentValue;
            } else converted += currentValue;
        }
        return converted;
    }

    public void ImcResult() {
    }
}
