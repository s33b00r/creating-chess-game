package model.chesspieces;

import java.awt.*;

class King extends Piece {

    King(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap, 'K');
    }

    @Override
    public boolean canMove(Point p) {
        int dx = p.x - getPos().x;
        int dy = p.y - getPos().y;

        //Castling

        if (Math.abs(dx) == 2 && dy == 0) {
            return canCastle(dx, p);
        }

        //Normal moving
        if (Math.abs(dx) > 1 || Math.abs(dy) > 1)
            return false;

        return !pieceMap.pieceAt(p) || pieceMap.isWhiteAt(p) != isWhite;
    }

    private boolean canCastle(int dx, Point p) {
        if (hasMoved() &&
                !pieceMap.canMoveTo(getPos(), !isWhite) &&
                !pieceMap.canMoveTo(p, !isWhite)) {

            if (dx > 0)
                return !(
                        pieceMap.canMoveTo(new Point(getPos().x + 1, getPos().y), !isWhite) ||
                                pieceMap.rightRookHasMoved(isWhite) ||
                                pieceMap.pieceAt(new Point(getPos().x + 1, getPos().y)) ||
                                pieceMap.pieceAt(new Point(getPos().x + 2, getPos().y))
                );
            else
                return !(
                        pieceMap.canMoveTo(new Point(getPos().x - 1, getPos().y), !isWhite) ||
                                pieceMap.leftRookHasMoved(isWhite) ||
                                pieceMap.pieceAt(new Point(getPos().x - 1, getPos().y)) ||
                                pieceMap.pieceAt(new Point(getPos().x - 2, getPos().y)) ||
                                pieceMap.pieceAt(new Point(getPos().x - 3, getPos().y))
                );
        }
        return false;
    }

    @Override
    public void move(Point p) {
        notation = isWhite ? 'M' : 'm';
        super.move(p);
    }
}
