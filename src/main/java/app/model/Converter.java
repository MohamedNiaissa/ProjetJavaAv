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

        String t = taille;
        float p = Float.parseFloat(poids);
        float ta = Float.parseFloat(taille);

        /* definir si la taille est de type xxx ou x.xx */
        if ((taille.contains("."))|| taille.length() == 1) {

            float imcF = p / (ta * ta);

            return imcF;
        } else
        {
            float imcF = (p / (ta * ta)) * 10000;

            return imcF;
        }
    }
}
