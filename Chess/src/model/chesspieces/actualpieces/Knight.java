package model.chesspieces.actualpieces;

import model.chesspieces.Piece;
import model.chesspieces.PieceData;
import model.chesspieces.cascades.IPieceAt;

import java.awt.*;

public class Knight extends Piece {

    public Knight(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap, PieceData.KNIGHT);
    }

    @Override
    public boolean canMove(Point p) {
        int dx = p.x - getPos().x;
        int dy = p.y - getPos().y;

        if (Math.abs(dx) != 2 || Math.abs(dy) != 1) {
            if (Math.abs(dx) != 1 || Math.abs(dy) != 2) {
                return false;
            }
        }

        return !pieceMap.pieceAt(p) || pieceMap.isWhiteAt(p) != isWhite;

    }

}
