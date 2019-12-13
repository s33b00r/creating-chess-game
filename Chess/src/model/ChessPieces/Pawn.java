package model.ChessPieces;

import model.Piece;

public class Pawn extends Piece {

    public Pawn(boolean isWhite){
        super(isWhite);
    }

    public static boolean canMove(char curNot, int curX, int curY, int xPos, int yPos, char[][] board) {
        if (isNotOnBoard(xPos, yPos)) {
            return false;
        }

        int dx = xPos - curX;
        int dy = yPos - curY;
        boolean isWhite = !Character.isLowerCase(curNot);

        return pawnCanMove(curX, curY, board, isWhite, dx, dy);
    }

  private static boolean pawnCanMove(int curX, int curY, char [][] board, boolean isWhite, int dx, int dy) {
      if ((isWhite && dy > 0) || (!isWhite && dy < 0)) {
          return twoStepFirstMove(curX, curY, dx, dy, board)
                  || attackingAnother(curX, curY, dx, dy, board, isWhite)
                  || normalMove(curX, curY, dx, dy, board);
      }
      return false;
  }

  private static boolean normalMove(int curX, int curY, int dx, int dy, char[][] board){
        return (board[curX + dx][curY + dy] == '-' || board[curX + dx][curY + dy] == 'E') //No piece there
                && dx == 0  //No side move
                && Math.abs(dy) == 1; //Only one step up the board
  }

  private static boolean attackingAnother(int curX, int curY, int dx, int dy, char[][] board, boolean isWhite){
        int dxPos = Math.abs(dx);
        int dyPos = Math.abs(dy);

        return dxPos == 1 //The pawn is moving sideways
                && dyPos == 1 //The pawn is moving up the board
                && board[curX + dx][curY + dy] != '-'
                && (Character.isLowerCase(board[curX + dx][curY + dy]) == isWhite //Is attacking enemy piece
                || enPassantAttack(curX, curY, dx, dy, board)); //or is En Passant
  }

  private static boolean enPassantAttack(int curX, int curY, int dx, int dy, char[][] board){
        return board[curX + dx][curY + dy] == 'E';
  }

  private static boolean twoStepFirstMove(int curX, int curY, int dx, int dy, char[][] board){
        return Math.abs(dy) == 2 //Two steps up
                && (curY == 1 || curY == 6) //Must be on start position
                && dx == 0 //Have not moved sideways
                && notGoingThroughAPieceVertically(curX, curY, curY + dy, board) //Have not moved through a piece
                && board[curX + dx][curY + dy] == '-'; //Did not attack another piece
  }

}
