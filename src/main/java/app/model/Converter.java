package app.model;

public class Converter {

    public void DecToBin() {
    }

    public void BinToDec() {
    }

    /**
     * Translate a decimal number (txt) into an Hexadecimal number
     * @param dec String from txtfield sent
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
     * * Translate aN Hexadecimal number (txt) into an Decimal number
     * @param hex String from textFiled sent
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

    public void DecToRom() {
    }

    public void RomToDec() {
    }

    public void ImcResult() {
    }
}
