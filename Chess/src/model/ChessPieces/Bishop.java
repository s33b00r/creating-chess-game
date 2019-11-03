package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(boolean isWhite){
        super(isWhite);
    }

    public static boolean canMove(int curX, int curY, int xPos, int yPos, char[][] board) {
        if(!isOnBoard(xPos, yPos)){
            return false;
        }
        if(attackingFriendly(curX, curY, xPos, yPos, board)){
            return false;
        }

        int dx = curX - xPos;
        int dy = curY - yPos;
        if(Math.abs(dx) == Math.abs(dy) && dx != 0){
            return !goingThroughAPieceDiagonally(curX, curY, xPos, yPos, board);
        }
        return false;
    }

}
