import Backgrounds.StandardBackground;
import Controller.ChessMouseController;
import model.ChessGameFactory;
import model.IChessGame;
import view.IChessGUI;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private static int WINDOW_WIDTH = 800;
    private static int WINDOW_HEIGHT = 800;

    private static String NAME = "Chess";

    private ChessMouseController mouseController;
    private IChessGUI cGUI;
    private IChessGame cGame;

    public static void main(String[] args) {

        //Window
        Application app = new Application();
        app.setName(NAME);
        app.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        //Initialization of objects
        app.cGame = ChessGameFactory.createChessGame();
        app.mouseController = new ChessMouseController(app.cGame);

        //Extra Configuration for application and objects
        app.addMouseListener(app.mouseController);

        //Adding all widgets
        app.add(new StandardBackground(WINDOW_WIDTH, WINDOW_HEIGHT, 50, 50));

        //Extra
        app.pack();
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
