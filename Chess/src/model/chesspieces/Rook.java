package model.chesspieces;

import java.awt.*;

class Rook extends LineMovingPiece {

    private boolean hasMoved = false;

    public Rook(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap);
        movementList.add(MoveVertically.getInstance());
        movementList.add(MoveHorizontally.getInstance());
    }


    @Override
    public void move(Point p) {
        hasMoved = true;
        super.move(p);
    }


}
