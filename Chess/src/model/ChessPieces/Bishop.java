package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(int x, int y, boolean isWhite){
        super(x, y, isWhite, "B");
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        return false;
    }
}
