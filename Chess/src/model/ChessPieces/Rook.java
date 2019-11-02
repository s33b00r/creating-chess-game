package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Rook extends Piece {

    private boolean hasMoved = false;

    public Rook(boolean isWhite) {
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

        if(dx != 0 && dy == 0){
            return !goingThroughAPieceHorizontally(curX, curY, xPos, board);
        }
        if(dy != 0 && dx == 0){
            return !goingThroughAPieceVertically(curX, curY, yPos, board);
        }

        return false;
    }

}
