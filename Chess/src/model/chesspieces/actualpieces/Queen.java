package model.chesspieces.actualpieces;

import model.chesspieces.LineMovingPiece;
import model.chesspieces.PieceData;
import model.chesspieces.cascades.IPieceAt;
import model.chesspieces.movementrules.MoveDiagonally;
import model.chesspieces.movementrules.MoveHorizontally;
import model.chesspieces.movementrules.MoveVertically;

public class Queen extends LineMovingPiece {

    public Queen(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap, PieceData.QUEEN);
        movementList.add(MoveDiagonally.getInstance());
        movementList.add(MoveHorizontally.getInstance());
        movementList.add(MoveVertically.getInstance());
   }
}
