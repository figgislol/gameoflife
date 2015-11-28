package game;

public abstract class GameOfLifeAlusta {

	private int w, h;
	private boolean[][] playArea;

	public GameOfLifeAlusta(int w, int h) {
		this.w = w;
		this.h = h;
		playArea = new boolean[w][h];
	}

	public boolean[][] getArea() {
		return playArea;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	public void playOnce() {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				playCell(x, y, getAliveNeighbors(x, y));
			}
		}
        //after all cells are updated, update playArea
        updatePlayArea();
	}

	//abstract methods//
	public abstract void makeAlive(int x, int y);
	public abstract void makeDead(int x, int y);
	public abstract boolean isAlive(int x, int y);

	//cell is alive if rng says so
	public abstract void makeRandomPoints(double chanceAlive);
	public abstract int getAliveNeighbors(int x, int y);

	//execute rules for cell
	public abstract void playCell(int x, int y, int aliveNeighbors);

    //own methods
    public abstract void updatePlayArea();
}
