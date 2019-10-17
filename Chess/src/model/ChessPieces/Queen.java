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

        int dx = getXPos() - xPos;
        int dy = getYPos() - yPos;

        if(dx == 0 && dy != 0){
            return !goingThroughAPieceVertically(xPos, yPos, allPieces);
        }else if(dx != 0 && dy == 0){
            return !goingThroughAPieceHorizontally(xPos, yPos, allPieces);
        }else if (Math.abs(dx) == Math.abs(dy) && dx != 0){
            return !goingThroughAPieceDiagonally(xPos, yPos, allPieces);
        }else {
            return false;
        }
    }
}
