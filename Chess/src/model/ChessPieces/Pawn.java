package model.ChessPieces;

import model.Chess;
import model.Piece;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(boolean isWhite){
        super(isWhite);
    }

    public static boolean canMove(char curNot, int curX, int curY, int xPos, int yPos, char[][] board) {
        if (!isOnBoard(xPos, yPos)) {
            return false;
        }

        int dx = curX - xPos;
        int dy = curY - yPos;
        boolean isWhite = !Character.isLowerCase(curNot);

        if((isWhite && dy < 0) || (!isWhite && dy > 0)){
            if(Math.abs(dy) == 2 && (curY == 1 || curY == 6)){
                return dx == 0 && !goingThroughAPieceVertically(curX, curY, yPos, board) &&
                            board[xPos][yPos] == '-';
            }
            if(Math.abs(dy) == 1){
                if(Math.abs(dx) == 1){
                    if(board[xPos][yPos] == '-'){
                        //TODO: add En Passant rules
                    }else {
                        return !attackingFriendly(curX, curY, xPos, yPos, board);
                    }
                }
                return board[xPos][yPos] == '-' && dx == 0;
            }
        }
        return false;
    }

}
