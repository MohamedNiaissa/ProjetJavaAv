package app.model;

public class Converter {

    public void DecToBin() {
    }

    public void BinToDec() {
    }

    public static String decToHex(String dec){
        char[] seize = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String hexa = "";
        int index = 0;
        int dec2 = Integer.parseInt(dec);
        int temp = dec2;
        while(temp !=0){
            index = temp%16;
            temp = temp/16;
            System.out.println("temp "+temp+" index "+index+" Char "+seize[index]);
            hexa=seize[index]+hexa;
            System.out.println("hexa "+hexa);
        }
        System.out.println("hexa= "+hexa);
        return hexa;
    }

    public static int hexToDec(String hex){
        char[] seize = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int dec = 0;
        char[] hex2 = hex.toCharArray();
        int start = 0;
        int index = 0;
        if (hex2[0] == '#') start = 1;
        for (int i = hex2.length-1; i >= start; i--) {
            for (int j = 0; j < seize.length; j++) {
                if(hex2[i]==seize[j]){
                    index = j;
                    continue;
                }
            }
            dec += index * Math.pow(16, hex.length() - i-1);
        }
        System.out.println("dec= "+dec);
        return (int) dec;
    }

    public void DecToRom() {
    }

    public void RomToDec() {
    }

    public void ImcResult() {
    }
}
