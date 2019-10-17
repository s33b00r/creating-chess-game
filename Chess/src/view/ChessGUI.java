package view;

import model.Board;
import model.Chess;
import model.Piece;
import view.theme.Standard;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;



public class ChessGUI extends Application {

    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;

    private Assets assets;

    private Chess chess;


    public static void main(String[] args) {
        launch(args);
    }

    private GraphicsContext fg;

    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {

        Board board = new Board();
        chess = new Chess(board);

        assets = new Standard();

        BorderPane root = new BorderPane();

        Canvas foreground = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        fg = foreground.getGraphicsContext2D();

        Pane pane = new Pane(assets.background, foreground);
        root.setCenter(pane);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //Updates methods here
                //TODO
                render();
            }
        };

        Scene scene = new Scene(root);
        scene.setOnMouseClicked(this::mouseClicked);
        scene.setOnMouseMoved(this::mouseMoved);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess");


        primaryStage.show();
        timer.start();
    }

    private void mouseClicked(MouseEvent mouse) {
       chess.mouseClickHandler(mouse.getX(), mouse.getY());
    }

    private void mouseMoved(MouseEvent mouse){
        if(chess.getActivePiece() != null){
            chess.mouseMovementHandler(mouse.getX(), mouse.getY());
        }
    }


    private void render(){
        fg.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        for(Piece p : chess.board.getAllPieces()){
            if(p != chess.getActivePiece()){
                drawImage(p, p.getRealXPos(), p.getRealYPos(), WINDOW_WIDTH / 8, WINDOW_HEIGHT / 8);
            }
        }
        if(chess.getActivePiece() != null){
            double width = WINDOW_WIDTH * 1.2 / 8;
            double height = WINDOW_HEIGHT * 1.2 / 8;
            double xPos = chess.getActivePiece().getRealXPos() - width / 2;
            double yPos = chess.getActivePiece().getRealYPos() - height / 2;
            drawImage(chess.getActivePiece(), xPos, yPos, width, height);
        }
    }

    private void drawImage(Piece p, double x, double y, double width, double height){
        Image pieceImage = assets.get(p, p.getIsWhite());
        fg.drawImage(pieceImage, x, y, width, height);
    }

}
