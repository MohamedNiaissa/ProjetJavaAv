package app.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {

    /**
     * Translate a decimal number (txt) into an Binary number
     * @param strNumber String from input txtField
     * @return  a String to be dipslayed into the related txtField
     */
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

    /**
     * Translate a binary number (txt) into an Decimal number
     * @param strBinary String from input txtField
     * @return String to be displayed into the related texfield
     */
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

    /**
     * Translate a decimal number (txt) into an Hexadecimal number
     * analyses the String from bottom to top and applies power 16 to digits
     * @param dec String from input txtField
     * @return Hexadecimal number in a String to be displayed inside the textField
     */
    public static String decToHex(String dec){
        char[] seize = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String hexa = "";
        int index = 0;
        int dec2 = Integer.parseInt(dec);
        int temp = dec2;
        while(temp !=0){
            index = temp%16;
            temp = temp/16;
            hexa=seize[index]+hexa;
        }
        return hexa;
    }

    /**
     * Translate aN Hexadecimal number (txt) into an Decimal number
     * analyses the String from bottom to top and applies power 16 to interpreted 16based digit
     * @param hex String from input txtField
     * @return a String to be displayed inside the txtField as a result
     */
    public static String hexToDec(String hex){
        char[] seize = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int dec = 0;
        char[] hex2 = hex.toCharArray();
        int start = 0;
        int index = 0;
        boolean badInput = false;
        if (hex2[0] == '#') start = 1;
        for (int i = hex2.length-1; i >= start; i--) {
            for (int j = 0; j < seize.length; j++) {
                badInput = false;
                if(hex2[i]==seize[j]){
                    index = j;
                    continue;
                }
            if(badInput) return "bxad";
            }
            dec += index * Math.pow(16, hex.length() - i-1);
        }
        return Integer.toString(dec);
    }

    /**
     * Translate a decimal number (txt) into a Roman number
     * Compares each char of decimal String with a typical format Roman digits set linked to their value set and calculate the solution
     * @param strNumber String from the input txtField
     * @return a string to displayed into the related txtField
     */
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

    /**
     * Translate a Roman number (txt) into a decimal number
     * Compares chars with set of Roman digits each of them linked to a value set and calculate the solution
     * @param strRomain String from input txtField
     * @return
     */
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

    /**
     * Calcul de l'IMC en fonction du format chiffre entr?? (xxx ou x.xx)
      * @param poids - obtenu sur le textfiel poids (String)
     * @param taille - obtenu sur le textfiel taille (String)
     * @return l'IMC (float)
     */
    public Float ImcResult(String poids, String taille) {

        String t = taille;
        float p = Float.parseFloat(poids);
        float ta = Float.parseFloat(taille);

        /* definir si la taille est de type xxx ou x.xx */
        if ((taille.contains("."))|| taille.length() == 1) {

            float imcF = p / (ta * ta);

            return imcF;
        } else
        {
            /* x10000 permet la conversion*/
            float imcF = (p / (ta * ta)) * 10000;

            return imcF;
        }
    }
}

