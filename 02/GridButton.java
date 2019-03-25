import java.awt.*;
import javax.swing.*;

public class GridButton extends JButton {

    private int column;
    private int row;

    private static final String ON_NO_SOLUTION = "Icons/Light-0.png";
    private static final String OFF_NO_SOLUTION = "Icons/Light-1.png";
    private static final String ON_WITH_SOLUTION = "Icons/Light-2.png";
    private static final String OFF_WITH_SOLUTION = "Icons/Light-3.png";


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

        setActionCommand("#" + row + ":" + column + "");
        this.column = column;
        this.row = row;

        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        setContentAreaFilled(false);
        setIcon(new ImageIcon(OFF_NO_SOLUTION));
    }

   /**
    * sets the icon of the button to reflect the
    * state specified by the parameters
    * @param isOn true if that location is ON
    * @param isClicked true if that location is
    * tapped in the model's current solution
    */ 
    public void setState(boolean isOn, boolean isClicked) {

        if (!isOn && !isClicked)
            setIcon(new ImageIcon(OFF_NO_SOLUTION));
        if (isOn && !isClicked)
            setIcon(new ImageIcon(ON_NO_SOLUTION));
        if (!isOn && isClicked)
            setIcon(new ImageIcon(OFF_WITH_SOLUTION));
        if (isOn && isClicked)
            setIcon(new ImageIcon(ON_WITH_SOLUTION));
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
}
