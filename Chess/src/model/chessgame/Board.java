package model.chessgame;

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

    IPromotionHandler promotionHandler;

    Board(IPromotionHandler promotionHandler) {
        this.promotionHandler = promotionHandler;
    }

    private List<Piece> allAlivePieces;

    private GameStatus status = GameStatus.RUNNING;
    private Piece activePiece = null;
    private Point lastPos = null;
    private Piece lastPiece = null;

    void setup() {
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
                removeEnPassantPawn(p, activePiece.isWhite());
            if (p.y == 7 || p.y == 0) {
                promotionMove(p);
            }
        }
        lastPos = activePiece.getPos();
        lastPiece = activePiece;
        activePiece.move(p);
        activePiece = null;
    }

    private void promotionMove(Point p) {
        activePiece.move(p);
        Piece temp = activePiece;
        activePiece = null;
        while (true) {
            Object pieceToBe = promotionHandler.getPromotionPiece(temp.isWhite());
            if (pieceToBe != null) {
                allAlivePieces.remove(temp);
                Piece promoted = PieceOrganizer.convertToRealPiece(pieceToBe, p, temp.isWhite(), this);
                allAlivePieces.add(promoted);
                activePiece = promoted;
                break;
            }
        }
    }

    private void removeEnPassantPawn(Point p, boolean isWhite) {
        Piece pieceToRemove = getPieceAt(p);
        if (pieceToRemove == null) {
            int y = p.y == 5 ? 4 : 3;
            pieceToRemove = getPieceAt(new Point(p.x, y));
            if (pieceToRemove != null && isWhite != pieceToRemove.isWhite())
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
        for (Piece p : allAlivePieces) {
            returnList.add(new PieceObjectData(p, p.getPos(), p.isWhite()));
        }
        return returnList;
    }

    PieceObjectData getActivePieceData() {
        if (hasActivePiece()) {
            return new PieceObjectData(activePiece, activePiece.getPos(), activePiece.isWhite());
        }
        return null;
    }

    @Nullable
    private Piece getPieceAt(Point p) {
        for (Piece piece : allAlivePieces) {
            if (piece.getPos().equals(p)) {
                return piece;
            }
        }
        return null;
    }


    void removePieceAt(Point p) {
        allAlivePieces.remove(getPieceAt(p));
    }

    boolean canMoveActivePiece(Point p) {
        if (hasActivePiece()) {
            if (activePiece.canMove(p)) {
                return !isCheck(activePiece, p);
            }
        }
        return false;
    }

    private boolean isCheck(Piece pieceToMove, Point p) {
        Piece pieceToRemove = getPieceAt(p);
        Point tempPos = pieceToMove.getPos();
        pieceToMove.move(p);
        List<Piece> tempList;
        if(pieceToRemove != null){
            tempList = new ArrayList<>(allAlivePieces);
            tempList.remove(pieceToRemove);
        } else {
            tempList = allAlivePieces;
        }
        boolean isCheck = findIfPositionHasCheck(tempList, pieceToMove.isWhite());
        pieceToMove.move(tempPos);
        return isCheck;
    }

    private boolean findIfPositionHasCheck(List<Piece> pieceList, boolean isWhite) {
        Piece king = null;
        for (Piece p : pieceList) {
            if ((p.getNotation() == PieceData.MOVED_KING || p.getNotation() == PieceData.KING) && p.isWhite() == isWhite) {
                king = p;
                break;
            }
        }

        for(Piece p : pieceList){
            if(p.isWhite() != isWhite){
                if(p.canMove(king.getPos()))
                    return true;
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

    GameStatus getStatus() {
        return status;
    }

    void checkForEndOfGame(boolean whitesTurn) {
        //Checking if a piece can move
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                for (Piece p : allAlivePieces) {
                    if (canMoveTo(p, whitesTurn, x, y)) {
                        status = GameStatus.RUNNING;
                        return;
                    }
                }
            }
        }
        //Checking if the king is in check, if so it is checkmate
        Piece king = null;
        for (Piece p : allAlivePieces) {
            if ((p.getNotation() == PieceData.KING || p.getNotation() == PieceData.MOVED_KING) && whitesTurn == p.isWhite()) {
                king = p;
                break;
            }
        }
        if (canMoveTo(king.getPos(), !whitesTurn)) {
            status = !whitesTurn ? GameStatus.WHITE_WON : GameStatus.BLACK_WON;
            return;
        }
        status = GameStatus.STALEMATE;
    }

    private boolean canMoveTo(Piece p, boolean whitesTurn, int x, int y) {
        if (p.isWhite() == whitesTurn) {
            if (p.canMove(new Point(x, y))) {
                return !isCheck(p, new Point(x, y));
            }
        }
        return false;
    }

    public void clearActivePiece() {
        activePiece = null;
    }
}
