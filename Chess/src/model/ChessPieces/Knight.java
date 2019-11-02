package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Knight extends Piece {

    public Knight(boolean isWhite){
        super(isWhite);
    }

    public static boolean canMove(int curX, int curY, int xPos, int yPos, char[][] board) {
        if(!isOnBoard(xPos, yPos)){
            return false;
        }
        if(attackingFriendly(curX, curY, xPos, yPos, board)){
            return false;
        }
        int dx = Math.abs(curX - xPos);
        int dy = Math.abs(curY - yPos);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
