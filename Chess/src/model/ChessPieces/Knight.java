package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Knight extends Piece {

    public Knight(int x, int y, boolean isWhite){
        super(x, y, isWhite, "N");
        value = 3;
    }

    @Override
    public Knight copy() {
        return new Knight(getXPos(), getYPos(), getIsWhite());
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        if(!isOnBoard(xPos, yPos)){
            return false;
        }
        if(attackingFriendly(xPos, yPos, allPieces)){
            return false;
        }
        int dx = Math.abs(getXPos() - xPos);
        int dy = Math.abs(getYPos() - yPos);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
