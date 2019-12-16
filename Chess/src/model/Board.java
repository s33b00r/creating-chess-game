package model;

import model.ChessPieces.*;

import java.util.List;

/**
 * King = 'K' or 'M' if has moved
 * Queen = 'Q'
 * Rook = 'R' or 'H' if has moved
 * Bishop = 'B'
 * Knight = 'N'
 * Pawn = 'P'
 * Nothing = '-' or 'E' if can do En passant to that square
 */

class Board {

    private List<Piece> allAlivePieces;

    public void setup(){
        allAlivePieces = PieceOrganizer.standardSetup();
    }

   /* public static char[][] boardByColor(){
        char[][] board = new char[8][8];


    }*/

}
