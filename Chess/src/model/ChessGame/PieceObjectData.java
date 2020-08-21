package model.ChessGame;

import java.awt.*;

public class PieceObjectData {
    public Object piece;
    public Point pos;
    public boolean isWhite;

    public PieceObjectData(){
    }

    public PieceObjectData(Object piece, Point pos, boolean isWhite){
        this.piece = piece;
        this.pos = new Point(pos);
        this.isWhite = isWhite;
    }
}
