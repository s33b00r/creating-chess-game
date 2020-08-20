package model.chesspieces;

import java.awt.*;

class King extends Piece {

    private boolean hasMoved = false;

    public King(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap);
    }

    @Override
    public boolean canMove(Point p) {
        //TODO: Castling
        int dx = p.x - getPos().x;
        int dy = p.y - getPos().y;

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1)
            return false;

        return !pieceMap.pieceAt(p) || pieceMap.isWhiteAt(p) != isWhite;
    }

    @Override
    public void move(Point p) {
        hasMoved = true;
        super.move(p);
    }
}
