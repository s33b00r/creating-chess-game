package model.chesspieces;

import model.chesspieces.cascades.IPieceAt;
import model.chesspieces.movementrules.MoveInOneLine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LineMovingPiece extends Piece {
    protected List<MoveInOneLine> movementList;

    public LineMovingPiece(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap, PieceData notation) {
        super(xPos, yPos, isWhite, pieceMap, notation);
        movementList = new ArrayList<>();
    }

    @Override
    public boolean canMove(Point wantedPos) {
        for (MoveInOneLine moveDirection : movementList) {
            if (moveDirection.canMove(pieceMap, isWhite, getPos(), wantedPos)) {
                return true;
            }
        }
        return false;
    }
}
