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
    private static final int TOP_BORDER_SIZE = 30;


    private static final String NAME = "Chess";

    private ChessMouseController mouseController;
    private IChessGame cGame;
    private GameView full;

    private static int xBoardOffset = 10;
    private static int yBoardOffset = 10;

    public static void main(String[] args) {

        //Window
        Application app = new Application();
        app.setName(NAME);
        app.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        app.getContentPane().setBackground(Color.blue);

        //Initialization of objects
        app.cGame = ChessGameFactory.createChessGame(app);
        app.full = new GameView(xBoardOffset, yBoardOffset, WINDOW_WIDTH - 50, WINDOW_HEIGHT - 50,
                ChessGameFactory.getPathHandler(), ChessGameFactory.getItemHandler(), app);
        app.mouseController = new ChessMouseController(app.cGame, xBoardOffset, yBoardOffset + TOP_BORDER_SIZE,
                WINDOW_WIDTH - 50, WINDOW_HEIGHT - 50 + TOP_BORDER_SIZE + yBoardOffset);

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
        if(getMousePosition() != null){
            Point returnPoint = getMousePosition();
            returnPoint.x -= xBoardOffset;
            returnPoint.y -= yBoardOffset;
            return returnPoint;
        }
        return null;
    }
}
