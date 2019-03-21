import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>GridButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

    // your variables here
    private GameModel model;
    private GridButton[][] buttonMatrix;
    private int h;
    private int w;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {

        this.w = gameModel.getWidth();
        this.h = gameModel.getHeight();
        this.model = gameModel;
        this.buttonMatrix = new GridButton[h][w];

        //Reset button
        JButton reset = new JButton("Reset");
        reset.addActionListener(gameController);
        add(reset);

        //Random button
        JButton random = new JButton("Random");
        random.addActionListener(gameController);
        add(random);

        //Quit button
        JButton quit = new JButton("Quit");
        quit.addActionListener(gameController);
        add(quit);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

            }
        }

        //setup
        setLayout(new GridLayout(w, h));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);

        //display the window
        setVisible(true);
    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){

        // YOUR CODE HERE

    }

    /**
     * returns true if the ``solution'' checkbox
     * is checked
     *
     * @return the status of the ``solution'' checkbox
     */

    public boolean solutionShown(){

        // YOUR CODE HERE
        return true;

    }

}
