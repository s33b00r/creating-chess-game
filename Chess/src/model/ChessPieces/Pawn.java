package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(int x, int y, boolean isWhite){
        super(x, y, isWhite, "p");
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        return false;
    }
}
