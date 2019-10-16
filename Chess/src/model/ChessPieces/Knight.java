package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Knight extends Piece {

    public Knight(int x, int y, boolean isWhite){
        super(x, y, isWhite, "N");
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        return false;
    }
}
