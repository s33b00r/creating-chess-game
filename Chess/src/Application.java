import backgrounds.StandardBackground;
import controller.ChessMouseController;
import model.ChessGameFactory;
import model.IChessGame;
import view.ChessPieceView;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private static int WINDOW_WIDTH = 800;
    private static int WINDOW_HEIGHT = 800;

    private static String NAME = "Chess";

    private ChessMouseController mouseController;
    private ChessPieceView cGUI;
    private IChessGame cGame;
    private JLayeredPane full = new JLayeredPane();

    public static void main(String[] args) {

        //Window
        Application app = new Application();
        app.setName(NAME);
        app.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        app.getContentPane().setBackground(Color.blue);

        //Initialization of objects
        app.cGame = ChessGameFactory.createChessGame();
        app.mouseController = new ChessMouseController(app.cGame, 10, 10, WINDOW_WIDTH - 40, WINDOW_HEIGHT - 40);
        app.cGUI = new ChessPieceView(ChessGameFactory.convertChessGameForView(app.cGame),
                10, 10, WINDOW_WIDTH - 50, WINDOW_HEIGHT - 50);

        app.cGame.addRedrawableObserver(app.cGUI);

        JPanel bg = new StandardBackground(10, 10,
                WINDOW_WIDTH - 50, WINDOW_HEIGHT - 50);

        //Extra Configuration for application and objects
        app.addMouseListener(app.mouseController);
        app.addMouseMotionListener(app.mouseController);
        app.add(app.full, BorderLayout.CENTER);
        app.full.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        app.cGUI.setOpaque(false);
        bg.setOpaque(true);

        //Adding all widgets
        app.full.add(bg, 0, 0);
        app.full.add(app.cGUI, 1, 0);

        //Extra
        app.pack();
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
