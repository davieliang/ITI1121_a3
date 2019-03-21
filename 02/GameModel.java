import java.util.ArrayList;
import java.lang.Math;

public class GameModel {
	
	private int width;
	private int height;
	private boolean board[][]; 
	private int numOfClicks;
	private Solution solution;

	public GameModel(int width, int height) {
		
        this.width = width;
		this.height = height;
		board = new boolean[height][width];
		numOfClicks = 0;
	}

	public int getHeight() {
		
        return height;
	}

	public int getWidth() {
		
        return width;
	}

    //Column j, Row i
	public boolean isON(int i, int j) {  

        return board[i][j];
	}

    public void reset() {
        
        board = new boolean[height][width];
        numOfClicks = 0;
    }

    //Column i, Row j
    public void set(int i, int j, boolean value) {
        
        board[j][i] = value;
    }

    //Row i, column j
    public void click(int i, int j) {

    	board[i][j] = !board[i][j];
    	numOfClicks++;

  		if (i != 0)
  			board[i - 1][j] = !board[i - 1][j];
  		if (i != height - 1)
  			board[i + 1][j] = !board[i + 1][j];
  		if (j != 0)
  			board[i][j - 1] = !board[i][j - 1];
  		if (j != width - 1)
  			board[i][j + 1] = !board[i][j + 1];
    }

    public int getNumberOfSteps() {

    	return numOfClicks;
    }

    public boolean isFinished() {

    	for (int i = 0; i < height; i++) {
    		for (int j = 0; j < width; j++) {
    			if (!board[i][j])
    				return false;
    		}
    	}

    	return true;
    }

    public void randomize() {

    	solution = null;

    	do {

            numOfClicks = 0;

    		for (int i = 0; i < height; i++) {
    			for (int j = 0; j < width; j++)
    				board[i][j] = (int)(Math.random() * 2) == 1;
    		}

    		setSolution();

    	} while (solution == null);
    }

    public void setSolution() {

    	solution = LightsOut.solveShortest(this);
    }

    public boolean solutionSelects(int i, int j) {

    	return solution != null && solution.get(j, i);
    }

    public String toString() {

        StringBuffer out = new StringBuffer("[");

        for (int i = 0; i < height; i++) {

            out.append("[");

            for (int j = 0; j < width; j++) {
                out.append(board[i][j]);
                out.append((j == width - 1) ? "" : ", ");
            }

            out.append((i == height - 1) ? "]]\n" : "],\n");
        }

        return out.toString();
    }
}
