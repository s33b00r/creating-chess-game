package model;

import model.chesspieces.*;
import pathhandling.PiecePathsHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private Piece activePiece = null;
    private Point lastPos = null;

    void setup(){
        allAlivePieces = PieceOrganizer.standardSetup();
    }

   /* public static char[][] boardByColor(){
        char[][] board = new char[8][8];


    }*/

   List<Point> getPos(){
       List<Point> returnList = new ArrayList<>();
       for (Piece p : allAlivePieces){
           returnList.add(p.getPos());
       }
       return returnList;
   }

   List<Object> getPiecesAsObj(){
       return new ArrayList<>(allAlivePieces);
   }

   List<Boolean> getIsWhiteList(){
       List<Boolean> returnList = new ArrayList<>();
       for(Piece p : allAlivePieces){
           returnList.add(p.isWhite());
       }
       return returnList;
   }

   Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths){
      return PieceOrganizer.createMap(paths);
   }

   boolean hasActivePiece(){
       return activePiece != null;
   }

   void placeActivePiece(Point p){
       if(activePiece.canMove(p)){
          activePiece.move(p);
       }
       activePiece = null;
   }

   void setActivePiece(Point p){
       for (Piece piece : allAlivePieces){
           if(piece.getPos().equals(p)){
               activePiece = piece;
               System.out.println("Got one");
               break;
           }
       }
   }

   boolean activePieceGetIsWhite(){
       return activePiece.isWhite();
   }

   Object getActivePieceAsObject(){
       return activePiece;
   }

}
