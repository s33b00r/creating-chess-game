package model.ChessPieces;

import model.Piece;

import java.util.List;

public class King extends Piece {

    private boolean hasMoved = false;

    public King(boolean isWhite){
        super(isWhite);
    }

    public static boolean canMove(int curX, int curY, int xPos, int yPos, char[][] board) {

        if(!isOnBoard(xPos, yPos)){
             return false;
        }
        if(Piece.attackingFriendly(curX, curY, xPos, yPos, board)){
            return false;
        }

        //TODO: Implement castling rules

        //Standard rules
        return Math.abs(curX - xPos) <= 1 &&
                Math.abs(curY - yPos) <= 1;
    }

}
