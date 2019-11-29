package view;

import model.*;
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

import java.awt.*;
import java.util.ArrayList;


public class ChessGUI extends Application {

    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;

    private Assets assets;

    private Chess chess;

    private double mouseX;
    private double mouseY;

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
        mouseX = mouse.getX();
        mouseY = mouse.getY();
    }


    private void render(){
        fg.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                char notation = chess.board.getBoard()[x][y];
                if(notation != '-' && notation != 'E'){
                    Piece p = Board.getPieceType(notation);
                    double realX = WINDOW_WIDTH * (x / 8.0);
                    double realY = WINDOW_HEIGHT * ((7 - y) / 8.0);
                    drawImage(p, realX, realY, WINDOW_WIDTH / 8.0, WINDOW_HEIGHT / 8.0);
                }
            }
        }
        if(chess.getActivePieceNotation() != '-' && chess.getActivePieceNotation() != 'E'){
            Piece p = Board.getPieceType(chess.getActivePieceNotation());
            double xPos = mouseX - WINDOW_WIDTH / 16.0;
            double yPos = mouseY - WINDOW_HEIGHT / 16.0;
            drawImage(p, xPos, yPos, WINDOW_WIDTH / 8.0, WINDOW_HEIGHT / 8.0);
        }
    }

    private void drawImage(Piece p, double x, double y, double width, double height){
        Image pieceImage = assets.get(p, p.isWhite());
        fg.drawImage(pieceImage, x, y, width, height);
    }

}
