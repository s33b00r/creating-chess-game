package View;

import Model.Chess;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChessGUI extends Application {

    int windowWidth = 800;
    int windowHeight = 800;

    Chess chess;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        chess = new Chess(windowWidth, windowHeight);

        GridPane grid = chess.board.drawBoard();
        Scene scene = new Scene(grid, windowWidth, windowHeight);

        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
