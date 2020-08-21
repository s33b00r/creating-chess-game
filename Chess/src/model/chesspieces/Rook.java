package model.chesspieces;

import java.awt.*;

public class Rook extends LineMovingPiece {

    public Rook(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap, 'R');
        movementList.add(MoveVertically.getInstance());
        movementList.add(MoveHorizontally.getInstance());
    }


    @Override
    public void move(Point p) {
        notation = isWhite ? 'H' : 'h';
        super.move(p);
    }
}
