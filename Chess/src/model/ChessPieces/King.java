package model.ChessPieces;

import model.Piece;

public class King extends Piece {

    private boolean hasMoved = false;

    public King(boolean isWhite){
        super(isWhite);
    }

    public static boolean canMove(int curX, int curY, int xPos, int yPos, char[][] board, char not) {

        if(isNotOnBoard(xPos, yPos)){
             return false;
        }
        if(Piece.attackingFriendly(curX, curY, xPos, yPos, board)){
            return false;
        }
        if(curX != xPos && Piece.goingThroughAPieceHorizontally(curX, curY, xPos, board)){
            return false;
        }

        if(curX == xPos && curY == yPos){
            return false;
        }

        //TODO: Implement castling rules
        //m for moved (only for king)
        if(Character.toLowerCase(not) != 'm'){
            if(curY == yPos && Piece.cannotAttack(curX, curY, board, Character.isLowerCase(not))){
                if(curX - xPos == 2){
                    if(Character.toLowerCase(board[0][curY]) == 'r'){
                        if(Piece.cannotAttack(3, curY, board, Character.isLowerCase(not)) &&
                                Piece.cannotAttack(2, curY, board, Character.isLowerCase(not))){
                            return true;
                        }
                    }
                }else if(curX - xPos == -2){
                    if(Character.toLowerCase(board[7][curY]) == 'r'){
                        if(Piece.cannotAttack(5, curY, board, Character.isLowerCase(not)) &&
                                Piece.cannotAttack(6, curY, board, Character.isLowerCase(not))){
                            return true;
                        }
                    }
                }
            }
        }
        //Standard rules
        return Math.abs(curX - xPos) <= 1 &&
                Math.abs(curY - yPos) <= 1;
    }

}
