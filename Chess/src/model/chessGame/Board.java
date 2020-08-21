package model.chessGame;

import model.chesspieces.IPieceAt;
import model.chesspieces.Piece;
import model.chesspieces.PieceOrganizer;
import org.jetbrains.annotations.Nullable;
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
    private Piece lastPiece = null;

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
       if (Character.toLowerCase(activePiece.getNotation()) == 'k') {
           if (p.x == 6 || p.x == 2)
               moveRookInCastling(p);
       }
       if (Character.toLowerCase(activePiece.getNotation()) == 'p') {
           if (p.y == 5 || p.y == 2)
               removeEnPassantPawn(p);
       }
       lastPos = activePiece.getPos();
       lastPiece = activePiece;
       activePiece.move(p);
       activePiece = null;
   }

    private void removeEnPassantPawn(Point p) {
        Piece pieceToRemove = getPieceAt(p);
        if (pieceToRemove == null) {
            int y = p.y == 5 ? 4 : 3;
            pieceToRemove = getPieceAt(new Point(p.x, y));
            allAlivePieces.remove(pieceToRemove);
        }
    }

    private void moveRookInCastling(Point p) {
        if (p.x == 6) {
            Piece rook = getPieceAt(new Point(7, p.y));
            if (rook != null) {
                rook.move(new Point(5, p.y));
            }
        } else {
            Piece rook = getPieceAt(new Point(0, p.y));
            if (rook != null) {
                rook.move(new Point(3, p.y));
            }
        }
    }

    void setActivePiece(Point p) {
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

    @Nullable
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

    @Override
    public boolean rightRookHasMoved(boolean isWhite) {
        char notation = isWhite ? 'R' : 'r';
        for (Piece p : allAlivePieces) {
            if (p.getNotation() == notation) {
                if (p.getPos().x == 7 && p.hasMoved())
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean leftRookHasMoved(boolean isWhite) {
        char notation = isWhite ? 'R' : 'r';
        for (Piece p : allAlivePieces) {
            if (p.getNotation() == notation) {
                if (p.getPos().x == 0 && p.hasMoved())
                    return false;
            }
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Point p, boolean isWhite) {
        for (Piece piece : allAlivePieces) {
            if (piece.isWhite() == isWhite) {
                if (piece.canMove(p))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean canEnPassant(Point p) {
        if (Character.toLowerCase(lastPiece.getNotation()) == 'p') {
            if (p.y == 5) {
                return lastPos.y == 6 && lastPiece.getPos().y == 4;
            } else if (p.y == 2) {
                return lastPos.y == 1 && lastPiece.getPos().y == 3;
            }
        }
        return false;
    }
}
