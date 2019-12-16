package model.ChessPieces;

import model.ChessPieces.*;

public abstract class Piece {

    private final boolean isWhite;
    private int xPos;
    private int yPos;

    public Piece(int xPos, int yPos, boolean isWhite){
        this.isWhite = isWhite;
        this.xPos = xPos;
        this.yPos = yPos;
    }


}
