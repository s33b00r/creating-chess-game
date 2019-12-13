package model;

import model.ChessPieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static view.ChessGUI.WINDOW_HEIGHT;
import static view.ChessGUI.WINDOW_WIDTH;

/**
 * King = 'K' or 'M' if has moved
 * Queen = 'Q'
 * Rook = 'R' or 'H' if has moved
 * Bishop = 'B'
 * Knight = 'N'
 * Pawn = 'P'
 * Nothing = '-' or 'E' if can do En passant to that square
 */

public class Board {

    public Board(){
        board = newStandardSetup();
    }

    private char[][] board;

    public void reset(){
        board = newStandardSetup();
    }

    public static int convertRealX(double x){
        return (int) Math.floor(x * 8 / WINDOW_WIDTH);
    }
    public static int convertRealY(double y){
        return 7 - (int) Math.floor(y * 8 / WINDOW_HEIGHT);
    }

    private char[][] newStandardSetup() {
        char[][] board = new char[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = '-';
            }
        }

        board[0][0] = 'R';
        board[1][0] = 'N';
        board[2][0] = 'B';
        board[3][0] = 'Q';
        board[4][0] = 'K';
        board[5][0] = 'B';
        board[6][0] = 'N';
        board[7][0] = 'R';

        board[0][7] = 'r';
        board[1][7] = 'n';
        board[2][7] = 'b';
        board[3][7] = 'q';
        board[4][7] = 'k';
        board[5][7] = 'b';
        board[6][7] = 'n';
        board[7][7] = 'r';

        for (int j = 0; j < 8; j++) {
            board[j][1] = 'P';
        }
        for (int j = 0; j < 8; j++) {
            board[j][6] = 'p';
        }

        return board;
    }

    public static Piece getPieceType(char pieceNotation){
        boolean isWhite = !Character.isLowerCase(pieceNotation);
        switch (Character.toLowerCase(pieceNotation)){
            case 'k':
            case 'm':
                return new King(isWhite);
            case 'q':
                return new Queen(isWhite);
            case 'b':
                return new Bishop(isWhite);
            case 'r':
            case 'h':
                return new Rook(isWhite);
            case 'n':
                return new Knight(isWhite);
            case 'p':
                return new Pawn(isWhite);
            default:
                return null;
        }
    }

    public char[][] getBoard(){
        return board;
    }

    /*public static void move(char[][] board, ){

    }*/

    public static boolean didCastleShort(char not, int beX, int beY, int curX, int curY){
        return Character.toLowerCase(not) == 'k' && curX - beX == 2 && beY == curY;
    }
    public static boolean didCastleLong(char not, int beX, int beY, int curX, int curY){
        return Character.toLowerCase(not) == 'k' && beX - curX == 2 && beY == curY;
    }

    public static void clearEnPassantNot(char[][] board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == 'E') {
                    board[i][j] = '-';
                }
            }
        }
    }

}
