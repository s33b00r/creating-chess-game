package model.chessGame;

import model.chesspieces.Piece;
import model.chesspieces.PieceData;
import model.chesspieces.PieceOrganizer;
import model.chesspieces.cascades.IKingInformation;
import model.chesspieces.cascades.IPawnInformation;
import org.jetbrains.annotations.Nullable;
import pathhandling.PiecePathsHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


class Board implements IKingInformation, IPawnInformation {

    private List<Piece> allAlivePieces;

    private Piece activePiece = null;
    private Point lastPos = null;
    private Piece lastPiece = null;

    void setup(){
        allAlivePieces = PieceOrganizer.standardSetup(this);
    }

    Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths) {
      return PieceOrganizer.createMap(paths);
   }

    boolean hasActivePiece() {
       return activePiece != null;
   }

    void placeActivePiece(Point p) {
        if (activePiece.getNotation() == PieceData.KING) {
           if (p.x == 6 || p.x == 2)
               moveRookInCastling(p);
       }
        if (activePiece.getNotation() == PieceData.PAWN) {
           if (p.y == 5 || p.y == 2)
               removeEnPassantPawn(p);
       }
       lastPos = activePiece.getPos();
       lastPiece = activePiece;
       activePiece.move(p);
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

    boolean correctColorPiece(Point p, boolean isWhite) {
       return getPieceAt(p) != null && getPieceAt(p).isWhite() == isWhite;
   }

    List<PieceObjectData> getAllPiecesData() {
        List<PieceObjectData> returnList = new ArrayList<>();
        for(Piece p : allAlivePieces){
            returnList.add(new PieceObjectData(p, p.getPos(), p.isWhite()));
        }
        return returnList;
   }

    PieceObjectData getActivePieceData() {
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
            if (activePiece.canMove(p)) {
                return !isCheck(p);
            }
        }
        return false;
    }

    private boolean isCheck(Point p) {
        Piece tempPiece = getPieceAt(p);
        Point tempPos = activePiece.getPos();
        removePieceAt(p);
        activePiece.move(p);
        boolean isCheck = findIfPositionHasCheck(activePiece.isWhite());
        activePiece.move(tempPos);
        if (tempPiece != null)
            allAlivePieces.add(tempPiece);
        return isCheck;
    }

    private boolean findIfPositionHasCheck(boolean isWhite) {
        for (Piece p : allAlivePieces) {
            if ((p.getNotation() == PieceData.MOVED_KING || p.getNotation() == PieceData.KING) && p.isWhite() == isWhite) {
                return canMoveTo(p.getPos(), !isWhite);
            }
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
    public boolean leftRookHasMoved(boolean isWhite) {
        return rookHasMoved(isWhite, 0);
    }

    @Override
    public boolean rightRookHasMoved(boolean isWhite) {
        return rookHasMoved(isWhite, 7);
    }

    private boolean rookHasMoved(boolean isWhite, int startXPos) {
        for (Piece p : allAlivePieces) {
            if ((p.getNotation() == PieceData.ROOK || p.getNotation() == PieceData.MOVED_ROOK) && isWhite == p.isWhite()) {
                if (p.getPos().x == startXPos && p.hasMoved())
                    return false;
            }
        }
        return true;
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
        if (lastPiece.getNotation() == PieceData.PAWN) {
            if (p.y == 5) {
                return lastPos.y == 6 && lastPiece.getPos().y == 4;
            } else if (p.y == 2) {
                return lastPos.y == 1 && lastPiece.getPos().y == 3;
            }
        }
        return false;
    }
}
