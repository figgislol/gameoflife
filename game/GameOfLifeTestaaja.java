package game;

import game.GameOfLifeAlusta;
import java.util.Scanner;

// tämän luokan avulla voi testata koodiasi, ks. tehtävänanto
public class GameOfLifeTestaaja {

    private static final Scanner LUKIJA = new Scanner(System.in);
    private GameOfLifeAlusta alusta;

    public GameOfLifeTestaaja(GameOfLifeAlusta alusta) {
        this.alusta = alusta;
    }

    public void pelaa() {
        piirra();
        while (jatketaanKierros()) {
            try {
                alusta.playOnce();
            } catch (Exception e) {
                System.out.println("Virhe: " + e.getMessage());
            }
            piirra();
        }
    }

    private static boolean jatketaanKierros() {
        tulostaKomennot();
        if ("".equals(LUKIJA.nextLine())) {
            return true;
        }

        System.out.println("Kiitos!");

        return false;
    }

    private static void tulostaKomennot() {
        System.out.print("Paina enter jatkaaksesi, muut lopettaa: ");
    }

    public void piirra() {
        if (this.alusta == null) {
            return;
        }

        System.out.println("");

        for (int y = 0; y < alusta.getHeight(); y++) {
            for (int x = 0; x < alusta.getWidth(); x++) {
                if (alusta.isAlive(x, y)) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println("");
        }
    }
}
