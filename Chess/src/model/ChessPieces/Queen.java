package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Queen extends Piece {

    public Queen(int xPos, int yPos, boolean isWhite){
        super(xPos, yPos, isWhite, "Q");
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        if(!isOnBoard(xPos, yPos)){
            return false;
        }
        if(attackingFriendly(xPos, yPos, allPieces)){
            return false;
        }
        if(isGoingThroughAPieceDiagonally(xPos, yPos, allPieces)){
            return false;
        }
        return true;
    }
}
