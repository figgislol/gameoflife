package game;

public class Main {

	public static void main(String[] args) {

		OmaAlusta alusta = new OmaAlusta(10, 10);
        alusta.makeRandomPoints(0.5);
        GameOfLifeTestaaja gom = new GameOfLifeTestaaja(alusta);
        gom.pelaa();
	}
}
