package app.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {

    public String DecToBin(String strNumber) {
        if(!strNumber.matches(".*[0-9]*")) return "InvalidCharacter";

        int number;
        try {
            number = Integer.parseInt(strNumber);
        }catch (NumberFormatException ignore) {
            if(!strNumber.matches("\s\s+")) return "InvalidCharacter";
            else return "InvalidNumber";
        }

        StringBuilder converted = new StringBuilder();

        List<Integer> binBaseAll = new ArrayList<>() {{
            int baseNum = 1;
            add(baseNum);
            for(int base = 0; base < 30; base++) {
                baseNum = baseNum * 2;
                add(baseNum);
            }
            add(0);
        }};
        List<Integer> binBase = new ArrayList<>(binBaseAll);

        for(int index = binBase.size() - 2; index >= 0; index--) {
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

    public String BinToDec(String strBinary) {
        if(!strBinary.matches("[0-9]*")) return "InvalidCharacter";
        if(!strBinary.matches("[01]*")) return "InvalidBinary";
        if(strBinary.length() > 31) return "InvalidNumber";

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
        }
        return String.valueOf(converted);
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

    public Float ImcResult(String poids, String taille) {

        String t = taille;
        float p = Float.parseFloat(poids);
        float ta = Float.parseFloat(taille);

        /* definir si la taille est de type xxx ou x.xx */
        if (taille.contains(".")){

            float imcF = p/(ta*ta);

            return imcF;
        }
        else {
            float imcF = (p/(ta*ta))*10000;

            return imcF;
        }
    }
}
