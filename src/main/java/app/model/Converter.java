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

    public String DecToRom(String text) {
        return text;
    }

    public int RomToDec(String strRomain) {
        // Find a way to patch the error if the user enter anything else than those leters.
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

                if (currentValue > previousValue) {
                    converted += currentValue - (2 * previousValue);
                } else {
                    converted += currentValue;
                }

            } else {
                converted += currentValue;
            }
        }
        return converted;
    }

    public void ImcResult() {
    }
}
