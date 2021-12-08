package app.model;

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
        //It's a temporary brute force method.
        int strLen = strRomain.length();
        int converted = 0;
        for(int nbChar = 0; nbChar < strLen; nbChar++) {
            if(strRomain.charAt(nbChar) != strRomain.charAt(strLen - 1)) {
                String specialCase = "" + strRomain.charAt(nbChar) + strRomain.charAt(nbChar + 1);

                switch (specialCase) {
                    case "IV" -> {
                        converted += 4;
                        nbChar++;
                        continue;
                    }
                    case "IX" -> {
                        converted += 9;
                        nbChar++;
                        continue;
                    }
                    case "XL" -> {
                        converted += 40;
                        nbChar++;
                        continue;
                    }
                    case "IL" -> {
                        converted += 49;
                        nbChar++;
                        continue;
                    }
                    case "XC" -> {
                        converted += 90;
                        nbChar++;
                        continue;
                    }
                    case "IC" -> {
                        converted += 99;
                        nbChar++;
                        continue;
                    }
                    case "CD" -> {
                        converted += 400;
                        nbChar++;
                        continue;
                    }
                    case "XD" -> {
                        converted += 490;
                        nbChar++;
                        continue;
                    }
                    case "ID" -> {
                        converted += 499;
                        nbChar++;
                        continue;
                    }
                    case "CM" -> {
                        converted += 900;
                        nbChar++;
                        continue;
                    }
                    case "XM" -> {
                        converted += 990;
                        nbChar++;
                        continue;
                    }
                    case "IM" -> {
                        converted += 999;
                        nbChar++;
                        continue;
                    }
                }
            }

            switch(strRomain.charAt(nbChar)) {
                case 'I' -> converted += 1;
                case 'V' -> converted += 5;
                case 'X' -> converted += 10;
                case 'L' -> converted += 50;
                case 'C' -> converted += 100;
                case 'D' -> converted += 500;
                case 'M' -> converted += 1000;
            }
        }

        return converted;
    }

    public void ImcResult() {
    }
}
