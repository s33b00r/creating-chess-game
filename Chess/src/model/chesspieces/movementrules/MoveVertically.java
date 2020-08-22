package model.chesspieces.movementrules;

import model.chesspieces.cascades.IPieceAt;

import java.awt.*;

public class MoveVertically implements MoveInOneLine {

    private static MoveVertically moveVertically;

    public static MoveVertically getInstance() {
        if (moveVertically == null)
            moveVertically = new MoveVertically();
        return moveVertically;
    }


    @Override
    public boolean canMove(IPieceAt pieceMap, boolean isWhite, Point currentPos, Point wantedPos) {
        if (currentPos.x != wantedPos.x || currentPos.y == wantedPos.y)
            return false;
        int dy = wantedPos.y - currentPos.y;
        int stepDirY = dy > 0 ? 1 : -1;

        int curX = currentPos.x;
        int curY = stepDirY + currentPos.y;
        while (curY != wantedPos.y) {
            if (pieceMap.pieceAt(new Point(curX, curY)))
                return false;
            curY += stepDirY;
            if (Math.abs(curY) > 100)
                System.out.println("VERTICAL");
        }

        return !pieceMap.pieceAt(wantedPos) || pieceMap.isWhiteAt(wantedPos) != isWhite;
    }
}
