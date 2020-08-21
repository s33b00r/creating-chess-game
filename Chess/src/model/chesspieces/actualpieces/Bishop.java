package model.chesspieces.actualpieces;

import model.chesspieces.LineMovingPiece;
import model.chesspieces.PieceData;
import model.chesspieces.cascades.IPieceAt;
import model.chesspieces.movementrules.MoveDiagonally;

public class Bishop extends LineMovingPiece {
    public Bishop(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap, PieceData.BISHOP);
        movementList.add(MoveDiagonally.getInstance());
    }

}
