public class GameModel {
	
	private int width;
	private int height;
	private boolean board[][]; 

	public GameModel(int width, int height) {
		
        this.width = width;
		this.height = height;
		board = new boolean[height][width];
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
    }

    //Column i, Row j
    public void set(int i, int j, boolean value) {
        
        board[j][i] = value;
    }

    public String toString() {

        String out = "[";

        for (int i = 0; i < height; i++) {

            out += "[";

            for (int j = 0; j < width; j++) {
                out += board[i][j];
                out += (j == width - 1) ? "" : ", ";
            }

            out += (i == height - 1) ? "]]\n" : "],\n";

        }

        return out;
    }
}
