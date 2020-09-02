import controller.ChessMouseController;
import model.chessgame.ChessGameFactory;
import model.chessgame.IChessGame;
import observerinterfaces.IMousePositionListener;
import view.GameView;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame implements IMousePositionListener {
    private static int WINDOW_WIDTH = 800;
    private static int WINDOW_HEIGHT = 800;

    private static int BOARD_WIDTH = 700;
    private static int BOARD_HEIGHT = 700;
    private static int BOARD_X = (WINDOW_WIDTH - BOARD_WIDTH) / 2;
    private static int BOARD_Y = (WINDOW_HEIGHT - BOARD_HEIGHT) / 2;

    private static final int TOP_BORDER_SIZE = 30;


    private static final String NAME = "Chess";

    private ChessMouseController mouseController;
    private IChessGame cGame;
    private GameView full;


    public static void main(String[] args) {

        //Window
        Application app = new Application();
        app.setName(NAME);
        app.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        app.getContentPane().setBackground(Color.blue);

        //Initialization of objects
        app.cGame = ChessGameFactory.createChessGame(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT);
        app.full = new GameView(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT,
                ChessGameFactory.getPathHandler(), ChessGameFactory.getItemHandler(), app);
        app.mouseController = new ChessMouseController();
        app.mouseController.addListeners(app.cGame, app.full);

        app.cGame.addRedrawableObserver(app.full);


        //Extra Configuration for application and objects
        app.addMouseListener(app.mouseController);
        app.add(app.full, BorderLayout.CENTER);
        app.full.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //Extra
        app.pack();
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public Point getLocalMousePosition() {
        if(getMousePosition() != null) {
            Point returnPoint = getMousePosition();
            returnPoint.x -= BOARD_X;
            returnPoint.y -= BOARD_Y;
            return returnPoint;
        }
        return null;
    }
}
