package game;

import java.util.*;
import game.GameOfLifeAlusta;

public class OmaAlusta extends GameOfLifeAlusta {

	private boolean[][] playArea;
    private boolean[][] altArea;

	public OmaAlusta(int w, int h) {
		super(w, h);

        //initialize playArea and altArea
		playArea = super.getArea();
        altArea = super.getArea();
		for (int y = 0; y < h; y++) { //all cells dead at first
			for (int x = 0; x < w; x++) {
				playArea[x][y] = false;
                altArea[x][y] = false;
			}
		}
	}

    public void updatePlayArea() {
        for(int y = 0; y < super.getHeight(); y++) {
            for(int x = 0; x < super.getWidth(); x++) {
                //if below is commented out, there should be no changes
                playArea[x][y] = altArea[x][y];
            }
        }
    }

    //update altArea with isAlive(x,y) info from playArea
	public void makeAlive(int x, int y) {
		altArea[x][y] = true;
	}
    //update altArea with isAlive(x,y) info from playArea
	public void makeDead(int x, int y) {
        altArea[x][y] = false;
	}
    
    //check isAlive from playArea
	public boolean isAlive(int x, int y) {
        boolean is = playArea[x][y];
		return is;
	}

    //give alive/dead status to random cells in playArea and altArea
	public void makeRandomPoints(double chanceAlive) {
		Random rng = new Random();

		//iterate through playArea and give cells alive/dead status
		for (int y = 0; y < super.getHeight(); y++) {
			for (int x = 0; x < super.getWidth(); x++) {
				double chance = rng.nextDouble(); //random in [0,1]
				if (chance <= chanceAlive) {
					playArea[x][y] = true; //alive
                    altArea[x][y] = true;
		        } //else dead
        	}
		}
	}

    //check alive/dead status of cell's neighbors (3x3 grid, ignore self)
	public int getAliveNeighbors(int x, int y) {
		int amount = 0;
		for (int iy = y - 1; iy <= y + 1; iy++) { //1 row below to above
			for (int ix = x - 1; ix <= x + 1; ix++) { //1 col left to right
				try { //try if looking for x,y outside of playArea
					if (isAlive(ix, iy)) {
                        if (ix == x && iy == y) { //doesnt count itself
                            //not sure why cant if(ix!=x && iy!=y)
                            //bug here? nope check below
                    //TODO why playArea is updated on every cell iteration
                        } else {
                            amount++;
                        }
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					//do nothing
				}
			}
		}
		return amount;
	}

    //changes altArea cells and after round updates playArea
	public void playCell(int x, int y, int aliveNeighbors) {
		if (isAlive(x, y)) { //check playArea cell
            //if cell is alive
			if (aliveNeighbors <= 1) {
				makeDead(x, y);
			} else if (aliveNeighbors >= 4) {
                makeDead(x, y);
            }

		} else {
            //if cell is dead
			if (aliveNeighbors == 3) {
				makeAlive(x, y);
			}
		}
	}
}
