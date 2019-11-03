package model;

import model.ChessPieces.*;

import java.util.List;

abstract public class Piece {

    private boolean isWhite;

    public Piece(boolean isWhite){
        this.isWhite = isWhite;
    }

    public static boolean canMove(char curNot, int curX, int curY, int xPos, int yPos, char[][] board){
        switch (Character.toLowerCase(curNot)){
            case 'k':
                return King.canMove(curX, curY, xPos, yPos, board);
            case 'q':
                return Queen.canMove(curX, curY, xPos, yPos, board);
            case 'b':
                return Bishop.canMove(curX, curY, xPos, yPos, board);
            case 'r':
                return Rook.canMove(curX, curY, xPos, yPos, board);
            case 'n':
                return Knight.canMove(curX, curY, xPos, yPos, board);
            case 'p':
                return Pawn.canMove(curNot, curX, curY, xPos, yPos, board);
            default:
                return false;
        }
    }

    //######################################################################################
    //Piece movement logic for rules
    //######################################################################################
    protected static boolean isOnBoard(int x, int y) {
        return x >= 0 && x < 8 &&
                y >= 0 && y < 8;
    }

    protected static boolean attackingFriendly(int curX, int curY, int newX, int newY, char[][] board) {
        if(board[newX][newY] == '-'){
            return false;
        }
        return Character.isLowerCase(board[newX][newY]) == Character.isLowerCase(board[curX][curY]);
    }

    //Expects that the piece is moving in that specific direction for these methods
    protected static boolean goingThroughAPieceDiagonally(int curX, int curY, int newX, int newY, char[][] board) {
        int dx = newX - curX;
        int dy = newY - curY;

        int stepDirX = dx / Math.abs(dx);
        int stepDirY = dy / Math.abs(dy);

        int currentXTest = stepDirX + curX;
        int currentYTest = stepDirY + curY;

        while (currentXTest != newX){
            if(board[currentXTest][currentYTest] != '-'){
                return true;
            }
            currentXTest += stepDirX;
            currentYTest += stepDirY;
        }

        return false;
    }
    protected static boolean goingThroughAPieceHorizontally(int curX, int curY, int newX, char[][] board) {
        int stepDirectionX = (newX - curX) / Math.abs(newX - curX);

        int currentXTest = curX + stepDirectionX;
        while (currentXTest != newX){
            if(board[currentXTest][curY] != '-'){
                return true;
            }
            currentXTest += stepDirectionX;
        }
        return false;
    }
    protected static boolean goingThroughAPieceVertically(int curX, int curY, int newY, char[][] board) {
        int stepDirectionY = (newY - curY) / Math.abs(newY - curY);

        int currentYTest = curY + stepDirectionY;
        while (currentYTest != newY){
            if(board[curX][currentYTest] != '-'){
                return true;
            }
            currentYTest += stepDirectionY;
        }
        return false;
    }


    public boolean isWhite() {
        return isWhite;
    }
}
