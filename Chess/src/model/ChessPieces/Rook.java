package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Rook extends Piece {

    public Rook(int x, int y, boolean isWhite){
        super(x, y, isWhite, "R");
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        return false;
    }
}
