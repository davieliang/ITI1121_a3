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
    private int h;
    private int w;
    private boolean solutionMode;
    private GridButton[][] buttonMatrix;
    private JLabel numOfStepsLabel;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {

        super("Lights Out");
        this.w = gameModel.getWidth();
        this.h = gameModel.getHeight();
        this.model = gameModel;
        this.solutionMode = false;
        this.buttonMatrix = new GridButton[h][w];

        //Panel
        JPanel optionPanel = new JPanel();

        //Reset button
        JButton reset = new JButton("Reset");
        reset.addActionListener(gameController);
        optionPanel.add(reset);

        //Random button
        JButton random = new JButton("Random");
        random.addActionListener(gameController);
        optionPanel.add(random);

        //Solution checkbox
        JCheckBox checkbox = new JCheckBox("Solution");
        checkbox.addItemListener(gameController);
        optionPanel.add(checkbox);

        //Quit button
        JButton quit = new JButton("Quit");
        quit.addActionListener(gameController);
        optionPanel.add(quit);

        //Grid buttons
        JPanel buttonsPanel = new JPanel();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                buttonMatrix[i][j] = new GridButton(j, i);
                buttonMatrix[i][j].addActionListener(gameController);
                buttonsPanel.add(buttonMatrix[i][j]);
            }
        }

        //Label for number of steps
        numOfStepsLabel = new JLabel("Number of steps: 0");


        //Graphical Setup
        buttonsPanel.setLayout(new GridLayout(h, w));
        optionPanel.setLayout(new GridLayout(4, 1));
        add(buttonsPanel, BorderLayout.WEST);
        add(optionPanel, BorderLayout.EAST);
        add(numOfStepsLabel, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++)
                buttonMatrix[i][j].setState(model.isON(i, j), solutionMode && model.solutionSelects(i, j));
        }

        numOfStepsLabel.setText("Number of steps: " + model.getNumberOfSteps());

        //if Game is Finished:
        if (model.isFinished()) {

            Object[] options = { "Play Again", "Quit" };

            int choice = JOptionPane.showOptionDialog(null, "Congratulations! You won in " + model.getNumberOfSteps() + " steps!\n" 
                + "Would you like to play again?", "Won", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, options, options[1]);

            if (choice == JOptionPane.YES_OPTION)
                model.reset();
            else
                System.exit(0);

            update();
        }

    }

    /**
     * returns true if the ``solution'' checkbox
     * is checked
     *
     * @return the status of the ``solution'' checkbox
     */

    public boolean solutionShown(){

        // YOUR CODE HERE
        return solutionMode;

    }

}
