package app.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Converter {

    public void DecToBin() {
    }

    public void BinToDec() {
    }

    public void DecToHex() {
    }

    public void HexToDec() {
    }

    public void DecToRom() {
    }

    public void RomToDec() {
    }

    public Float ImcResult(String poids, String taille) {

        float p = Float.parseFloat(poids);
        float t = Float.parseFloat(taille);

        float imcF = p/(t*t);

        return imcF;

    }
}
