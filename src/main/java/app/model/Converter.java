package app.model;

import java.util.Arrays;
import java.util.List;

public class Converter {

    public void DecToBin() {
    }

    public void BinToDec() {
    }

    public void DecToHex() {
    }

    public void HexToDec() {
    }

    public String DecToRom(String strNumber) {
        if(!strNumber.matches("[0-9]*")) return "InvalidCharacter";
        if(strNumber.length() > 4) return "InvalidNumber";

        int number;
        try {
            number = Integer.parseInt(strNumber);
        }catch (NumberFormatException ignore) {
            if(!strNumber.matches("\s\s+")) return "InvalidCharacter";
            else return "InvalidNumber";
        }

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

    public String RomToDec(String strRomain) {
        if(!strRomain.matches("[IVXLCDM]*")) return "InvalidCharacter";
        if(strRomain.length() > 9) return "InvalidNumber";
        if(strRomain.matches("[Mm]{4,}")) return "0";

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
        return String.valueOf(converted);
    }

    public void ImcResult() {
    }
}
