import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener, ItemListener {

    private GameModel model;
    private GameView view;
    private boolean solutionMode;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     */
    public GameController(int width, int height) {

        model = new GameModel(width, height);
        view = new GameView(this.model, this);
    }


    /**
     * Callback used when the user clicks a button (reset, 
     * random or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Reset":
                model.reset();
                break;
            case "Random":
                model.randomize();
                break;
            case "Quit":
                System.exit(0);
                break;
            default:
                if (e.getActionCommand().charAt(0) == '#') { //simple verification

                    int[] pos = obtainCoordinates(e.getActionCommand());
                    model.click(pos[0], pos[1]);

                } else {
 
                    System.err.println("Unknown Command: " + e.getActionCommand());
                    System.exit(0);
                }
                break;
        }

        view.update();
    }

    /**
     * Callback used when the user select/unselects
     * a checkbox
     *
     * @param e
     *            the ItemEvent
     */

    public void itemStateChanged(ItemEvent e){

        //solutionMode = (e.getStateChange() == 1) ? true : false;
        solutionMode = view.solutionShown();
        view.update();
    }

    //used to extract 2 integers from button actioncommand
    private int[] obtainCoordinates(String str) {

        int pos[] = {0, 0};

        for (int i = 1; i < str.length(); i++) {

            if (str.charAt(i) == ':') {

                String row = str.substring(1, i);
                String col = str.substring(i + 1, str.length());
                pos[0] = Integer.parseInt(row);
                pos[1] = Integer.parseInt(col);
                break;
            }
        }

        return pos;
    }

}
