import javax.swing.*;

public class GridButton extends JButton {


    // YOUR VARIABLES HERE
    private int column;
    private int row;


    /**
     * Constructor used for initializing a GridButton at a specific
     * Board location.
     * 
     * @param column
     *            the column of this Cell
     * @param row
     *            the row of this Cell
     */

    public GridButton(int column, int row) {

        this.column = column;
        this.row = row;
    }

   /**
    * sets the icon of the button to reflect the
    * state specified by the parameters
    * @param isOn true if that location is ON
    * @param isClicked true if that location is
    * tapped in the model's current solution
    */ 
    public void setState(boolean isOn, boolean isClicked) {

    }

 

    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
        return row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
        return column;
    }

    // YOUR OTHER METHODS HERE
}
