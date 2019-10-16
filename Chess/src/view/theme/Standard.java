package view.theme;

import model.ChessPieces.*;
import view.Assets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static view.ChessGUI.WINDOW_HEIGHT;
import static view.ChessGUI.WINDOW_WIDTH;


public class Standard extends Assets {
    {
        bind(King.class.getSimpleName(), true, "White_King.png");
        bind(King.class.getSimpleName(), false, "Black_King.png");
        bind(Queen.class.getSimpleName(), true, "White_Queen.png");
        bind(Queen.class.getSimpleName(), false, "Black_Queen.png");
        bind(Bishop.class.getSimpleName(), true, "White_Bishop.png");
        bind(Bishop.class.getSimpleName(), false, "Black_Bishop.png");
        bind(Rook.class.getSimpleName(), true, "White_Rook.png");
        bind(Rook.class.getSimpleName(), false, "Black_Rook.png");
        bind(Knight.class.getSimpleName(), true, "White_Knight.png");
        bind(Knight.class.getSimpleName(), false, "Black_Knight.png");
        bind(Pawn.class.getSimpleName(), true, "White_Pawn.png");
        bind(Pawn.class.getSimpleName(), false, "Black_Pawn.png");

        background = drawBoard();
    }


    private GridPane drawBoard(){
        GridPane grid = new GridPane();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                javafx.scene.shape.Rectangle rec = new Rectangle();
                rec.setWidth(WINDOW_WIDTH / 8);
                rec.setHeight(WINDOW_HEIGHT / 8);
                javafx.scene.paint.Color c = (i + j) % 2 == 0 ?
                        new javafx.scene.paint.Color(1,1, 1, 1) :
                        new Color(0, 0, 0, 1);
                rec.setFill(c);
                GridPane.setRowIndex(rec, i);
                GridPane.setColumnIndex(rec, j);
                grid.getChildren().addAll(rec);
            }
        }
        return grid;
    }

}
