package model;

import model.ChessPieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static view.ChessGUI.WINDOW_HEIGHT;
import static view.ChessGUI.WINDOW_WIDTH;

public class Board {

    public Board(){
        allPieces = newStandardSetup();
    }

    private List<Piece> allPieces;

    public void reset(){
        allPieces = newStandardSetup();
    }

    public Piece getPieceAt(double x, double y){
        int xSquare = convertRealX(x);
        int ySquare = convertRealY(y);
        for(Piece p : allPieces){
            if(p.isPointingInside(xSquare, ySquare)){
                return p;
            }
        }
        return null;
    }

    public static int convertRealX(double x){
        return (int) Math.floor(x * 8 / WINDOW_WIDTH);
    }
    public static int convertRealY(double y){
        return 7 - (int) Math.floor(y * 8 / WINDOW_HEIGHT);
    }

    private List<Piece> newStandardSetup(){

        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new King(4, 0, true));
        pieces.add(new King(4, 7, false));
        pieces.add(new Queen(3, 0, true));
        pieces.add(new Queen(3, 7, false));
        pieces.add(new Rook(0, 0, true));
        pieces.add(new Rook(7, 0, true));
        pieces.add(new Rook(0, 7, false));
        pieces.add(new Rook(7, 7, false));
        pieces.add(new Bishop(2, 0, true));
        pieces.add(new Bishop(5, 0, true));
        pieces.add(new Bishop(2, 7, false));
        pieces.add(new Bishop(5, 7, false));
        pieces.add(new Knight(1, 0, true));
        pieces.add(new Knight(6, 0, true));
        pieces.add(new Knight(1, 7, false));
        pieces.add(new Knight(6, 7, false));

        boolean isWhite = false;
        for (int i = 0; i < 2; i++) {
            isWhite = !isWhite;
            for (int j = 0; j < 8; j++) {
                pieces.add(new Pawn(j, 1 + 5*i, isWhite));
            }
        }
        return pieces;
    }

    public List<Piece> getAllPieces() {
        return allPieces;
    }

    public void removePiece(Piece takenPiece) {
        allPieces = allPieces.stream().filter(p -> p != takenPiece).collect(Collectors.toList());
    }
}
