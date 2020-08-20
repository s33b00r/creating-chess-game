package model;

import model.chesspieces.IPieceAt;
import model.chesspieces.Piece;
import model.chesspieces.PieceOrganizer;
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

class Board implements IPieceAt {

    private List<Piece> allAlivePieces;

    private Piece activePiece = null;
    private Point lastPos = null;

    void setup(){
        allAlivePieces = PieceOrganizer.standardSetup(this);
    }

   Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths){
      return PieceOrganizer.createMap(paths);
   }

   boolean hasActivePiece(){
       return activePiece != null;
   }

   void placeActivePiece(Point p){
       activePiece.move(p);
       activePiece = null;
   }

   void setActivePiece(Point p){
        activePiece = getPieceAt(p);
   }

   boolean correctColorPiece(Point p, boolean isWhite){
       return getPieceAt(p) != null && getPieceAt(p).isWhite() == isWhite;
   }

   List<PieceObjectData> getAllPiecesData(){
        List<PieceObjectData> returnList = new ArrayList<>();
        for(Piece p : allAlivePieces){
            returnList.add(new PieceObjectData(p, p.getPos(), p.isWhite()));
        }
        return returnList;
   }

   PieceObjectData getActivePieceData(){
        if(hasActivePiece()){
            return new PieceObjectData(activePiece, activePiece.getPos(), activePiece.isWhite());
        }
        return null;
   }

   private Piece getPieceAt(Point p){
       for (Piece piece : allAlivePieces){
           if(piece.getPos().equals(p)){
               return piece;
           }
       }
       return null;
   }

    void removeActivePiece() {
        activePiece = null;
    }

    void removePieceAt(Point p) {
        allAlivePieces.remove(getPieceAt(p));
    }

    boolean canMoveActivePiece(Point p) {
        if (hasActivePiece()) {
            return activePiece.canMove(p);
        }
        return false;
    }

    @Override
    public boolean pieceAt(Point pos) {
        return getPieceAt(pos) != null;
    }

    @Override
    public boolean isWhiteAt(Point pos) {
        Piece cur = getPieceAt(pos);
        return getPieceAt(pos).isWhite();
    }
}
