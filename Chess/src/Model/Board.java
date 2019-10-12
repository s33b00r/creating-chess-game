package Model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {

    private int width;
    private int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
    }

    public GridPane drawBoard(){
        GridPane grid = new GridPane();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(width / 8);
                rec.setHeight(height / 8);
                Color c = (i + j) % 2 == 0 ?
                        new Color(1,1, 1, 1) :
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
