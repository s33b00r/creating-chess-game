package model.chesspieces.actualpieces;

import model.chesspieces.LineMovingPiece;
import model.chesspieces.PieceData;
import model.chesspieces.cascades.IPieceAt;
import model.chesspieces.movementrules.MoveHorizontally;
import model.chesspieces.movementrules.MoveVertically;

import java.awt.*;

public class Rook extends LineMovingPiece {

    public Rook(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap, PieceData.ROOK);
        movementList.add(MoveVertically.getInstance());
        movementList.add(MoveHorizontally.getInstance());
    }


    @Override
    public void move(Point p) {
        notation = PieceData.MOVED_ROOK;
        super.move(p);
    }
}
