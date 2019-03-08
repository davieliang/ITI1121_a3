// UPDATE THIS FILE AS REQUIRED

/**
 * The class <b>Solution</b> is used
 * to store a (partial) solution to the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class Solution {

    private GameModel model;
    
    /**
     * how far along have we constructed that solution.
     * values range between 0 and height*width-1
     */
    private int currentIndex;


    /**
     * Constructor. Creates an instance of Solution 
     * for a board of size <b>widthxheight</b>. That 
     * solution does not have any board position
     * value explicitly specified yet.
     *
     * @param width
     *  the width of the board
     * @param height
     *  the height of the board
     */
    public Solution(int width, int height) {

        model = new GameModel(width, height);
        currentIndex = 0;
    }

   /**
     * Constructor. Creates an instance of Solution 
     * wich is a deep copy of the instance received
     * as parameter. 
     *
     * @param other
     *  Instance of solution to deep-copy
     */
     public Solution(Solution other) {

        this.model = new GameModel(other.model.getWidth(), other.model.getHeight());
        this.currentIndex = other.currentIndex;

        int width = this.model.getWidth();

        for(int i = 0; i < currentIndex; i++)
            model.set(i%width, i/width, other.model.isON(i/width, i%width));

    }


    /**
     * returns <b>true</b> if and only the parameter 
     * <b>other</b> is referencing an instance of a 
     * Solution which is the ``same'' as  this 
     * instance of Solution (its board as the same
     * values and it is completed to the same degree)
     *
     * @param other
     *  referenced object to compare
     */

    public boolean equals(Object other){

        if(other == null) {
            return false;
        }
        if(this.getClass() != other.getClass()) {
            return false;
        }

        Solution otherSolution = (Solution) other;

        if(model.getWidth() != otherSolution.model.getWidth() ||
            model.getHeight() != otherSolution.model.getHeight() ||
            currentIndex != otherSolution.currentIndex) {
            return false;
        }

        for(int i = 0; i < model.getHeight() ; i++){
            for(int j = 0; j < model.getWidth(); j++) {
                if(model.isON(i, j) != otherSolution.model.isON(i, j))
                    return false;
            }
        }

        return true;

    }


    /** 
    * returns <b>true</b> if the solution 
    * has been entirely specified
    *
    * @return
    * true if the solution is fully specified
    */
    public boolean isReady(){
        return currentIndex == model.getWidth() * model.getHeight();
    }

    /** 
    * specifies the ``next'' value of the 
    * solution. 
    * The first call to setNext specifies 
    * the value of the board location (1,1), 
    * the second call specifies the value
    *  of the board location (1,2) etc. 
    *
    * If <b>setNext</b> is called more times 
    * than there are positions on the board, 
    * an error message is printed out and the 
    * call is ignored.
    *
    * @param nextValue
    *  the boolean value of the next position
    *  of the solution
    */
    public void setNext(boolean nextValue) {

        if(currentIndex >= model.getWidth() * model.getHeight()) {
            System.out.println("Board already full");
            return;
        }
        model.set(currentIndex % model.getWidth(), currentIndex++ / model.getWidth(), nextValue);
    }
    
    /**
    * returns <b>true</b> if the solution is completely 
    * specified and is indeed working, that is, if it 
    * will bring a board of the specified dimensions 
    * from being  entirely ``off'' to being  entirely 
    * ``on''.
    *
    * @return
    *  true if the solution is completely specified
    * and works
    */
    public boolean isSuccessful(){

        if(currentIndex < model.getWidth() * model.getHeight()) {
            System.out.println("Board not finished");
            return false;
        }

        for(int i = 0; i < model.getHeight(); i++){
            for(int j = 0; j < model.getWidth(); j++) {
                if(!oddNeighborhood(i,j)){
                    return false;
                }
            }
        }
        return true;
    }


   /**
    * this method ensure that add <b>nextValue</b> at the
    * currentIndex does not make the current solution
    * impossible. It assumes that the Solution was
    * built with a series of setNext on which 
    * stillPossible was always true.
    * @param nextValue
    *         The boolean value to add at currentIndex
    * @return true if the board is not known to be
    * impossible (which does not mean that the board
    * is possible!)
    */
    public boolean stillPossible(boolean nextValue) {

        if(currentIndex >= model.getWidth()*model.getHeight()) {
            System.out.println("Board already full");
            return false;
        }

        int i = currentIndex/model.getWidth();
        int j = currentIndex%model.getWidth();
        boolean before = model.isON(i, j);
        boolean possible = true;

        model.set(j, i, nextValue);
        
        if((i > 0) && (!oddNeighborhood(i-1,j))){
            possible = false;
        }
        if(possible && (i == (model.getHeight()-1))) {
            if((j > 0) && (!oddNeighborhood(i,j-1))){
                possible = false;
            }
            if(possible && (j == (model.getWidth()-1))&& (!oddNeighborhood(i,j))){
                possible = false;            
            }
        }
        model.set(j, i, before);
        return possible;
    }


    /**
    * this method attempts to finish the board. 
    * It assumes that the Solution was
    * built with a series of setNext on which 
    * stillPossible was always true. It cannot
    * be called if the board can be extended 
    * with both true and false and still be 
    * possible.
    *
    * @return true if the board can be finished.
    * the board is also completed
    */
    public boolean finish(){


        int i = currentIndex/model.getWidth();
        int j = currentIndex%model.getWidth();
        
/*
        if(i == 0 && height > 1) {
            System.out.println("First line incomplete, can't proceed");
            return false;
        }
*/

        while(currentIndex < model.getHeight()*model.getWidth()) {
            if(i < model.getHeight() - 1 ) {
                setNext(!oddNeighborhood(i-1,j));
                i = currentIndex/model.getWidth();
                j = currentIndex%model.getWidth();
            } else { //last raw
                if(j == 0){
                    setNext(!oddNeighborhood(i-1,j));
                } else {
                   if((model.getHeight() > 1) && oddNeighborhood(i-1,j) != oddNeighborhood(i,j-1)){
                     return false;
                   }
                   setNext(!oddNeighborhood(i,j-1));
                } 
                i = currentIndex/model.getWidth();
                j = currentIndex%model.getWidth();
            }
        }
        if(!oddNeighborhood(model.getHeight()-1,model.getWidth()-1)){
            return false;
        }
        // here we should return true because we could
        // successfully finish the board. However, as a
        // precaution, if someone called the method on
        // a board that was unfinishable before calling
        // the method, we do a complete check
        
        if(!isSuccessful()) {
            System.out.println("Warning, method called incorrectly");
            return false;
        }
       
        return true;

    }

    public int getSize() {

        int solutionCounter = 0;

        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++)
                solutionCounter += model.isON(i, j) ? 1 : 0;
        }

        return solutionCounter;
    }

    /**
     * checks if board[i][j] and its neighborhood
     * have an odd number of values ``true''
     */

    private boolean oddNeighborhood(int i, int j) {

        int width = model.getWidth();
        int height = model.getHeight();
        
        if(i < 0 || i > height - 1 || j < 0 || j > width - 1) {
            return false;
        }

        int total = 0;
        if(model.isON(i, j)){
            total++;
        }
        if((i > 0) && (model.isON(i - 1, j))) {
            total++;
        }
        if((i < height -1 ) && (model.isON(i + 1, j))) {
            total++;
        }
        if((j > 0) && (model.isON(i, j - 1))) {
            total++;
        }
        if((j < (width - 1)) && (model.isON(i, j + 1))) {
            total++;
        }
        return (total%2)== 1 ;                
    }

    /**
     * returns a string representation of the solution
     *
     * @return
     *      the string representation
     */
    public String toString() {

        int width = model.getWidth();
        int height = model.getHeight();
        StringBuffer out = new StringBuffer();

        out.append("[");
        for(int i = 0; i < height; i++){
            out.append("[");
            for(int j = 0; j < width ; j++) {
                if (j>0) {
                    out.append(",");
                }
                out.append(model.isON(i, j));
            }
            out.append("]"+(i < height -1 ? ",\n" :""));
        }
        out.append("]");
        return out.toString();
    }

}

