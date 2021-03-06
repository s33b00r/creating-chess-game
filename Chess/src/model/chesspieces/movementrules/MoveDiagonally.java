package model.chesspieces.movementrules;

import model.chesspieces.cascades.IPieceAt;

import java.awt.*;

public class MoveDiagonally implements MoveInOneLine {

    private static MoveDiagonally moveDiagonally;

    public static MoveDiagonally getInstance() {
        if (moveDiagonally == null)
            moveDiagonally = new MoveDiagonally();
        return moveDiagonally;
    }

    @Override
    public boolean canMove(IPieceAt pieceMap, boolean isWhite, Point currentPos, Point wantedPos) {
        int dx = wantedPos.x - currentPos.x;
        int dy = wantedPos.y - currentPos.y;
        if (Math.abs(dx) != Math.abs(dy) || dx == 0) {
            return false;
        }
        int stepDirX = dx > 0 ? 1 : -1;
        int stepDirY = dy > 0 ? 1 : -1;

        int currX = currentPos.x + stepDirX;
        int currY = currentPos.y + stepDirY;

        while (currX != wantedPos.x) {
            if (pieceMap.pieceAt(new Point(currX, currY))) {
                return false;
            }
            currX += stepDirX;
            currY += stepDirY;
            if (Math.abs(currX) > 100)
                System.out.println("DIAGONAL");
        }

        return !pieceMap.pieceAt(wantedPos) || pieceMap.isWhiteAt(wantedPos) != isWhite;

    }
}
