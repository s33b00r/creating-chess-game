package model.ChessPieces;

import model.Piece;

public class Queen extends Piece {

   public Queen(boolean isWhite){
       super(isWhite);
   }

    public static boolean canMove(int curX, int curY, int xPos, int yPos, char[][] board) {
        if(isNotOnBoard(xPos, yPos)){
            return false;
        }
        if(attackingFriendly(curX, curY, xPos, yPos, board)){
            return false;
        }

        int dx = curX - xPos;
        int dy = curY - yPos;

        if(dx == 0 && dy != 0){
            return notGoingThroughAPieceVertically(curX, curY, yPos, board);
        }else if(dx != 0 && dy == 0){
            return !goingThroughAPieceHorizontally(curX, curY, xPos, board);
        }else if (Math.abs(dx) == Math.abs(dy) && dx != 0){
            return notGoingThroughAPieceDiagonally(curX, curY, xPos, yPos, board);
        }else {
            return false;
        }
    }

}
