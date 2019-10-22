package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(int x, int y, boolean isWhite){
        super(x, y, isWhite, "B");
        value = 3;
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        if(!isOnBoard(xPos, yPos)){
            return false;
        }
        if(attackingFriendly(xPos, yPos, allPieces)){
            return false;
        }

        int dx = getXPos() - xPos;
        int dy = getYPos() - yPos;
        if(Math.abs(dx) == Math.abs(dy) && dx != 0){
            return !goingThroughAPieceDiagonally(xPos, yPos, allPieces);
        }
        return false;
    }

    @Override
    public Bishop copy(){
        return new Bishop(getXPos(), getYPos(), getIsWhite());
    }
}
