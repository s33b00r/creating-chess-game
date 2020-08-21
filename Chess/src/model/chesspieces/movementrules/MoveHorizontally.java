package model.chesspieces.movementrules;

import model.chesspieces.cascades.IPieceAt;

import java.awt.*;

public class MoveHorizontally implements MoveInOneLine {

    private static MoveHorizontally moveHorizontally;

    public static MoveHorizontally getInstance() {
        if (moveHorizontally == null)
            moveHorizontally = new MoveHorizontally();
        return moveHorizontally;
    }

    @Override
    public boolean canMove(IPieceAt pieceMap, boolean isWhite, Point currentPos, Point wantedPos) {
        if (currentPos.y != wantedPos.y)
            return false;
        int dx = wantedPos.x - currentPos.x;
        int stepDirX = dx > 0 ? 1 : -1;

        int curX = currentPos.x + stepDirX;
        int curY = currentPos.y;

        while (curX != wantedPos.x) {
            if (pieceMap.pieceAt(new Point(curX, curY)))
                return false;
        }
        return !pieceMap.pieceAt(wantedPos) || pieceMap.isWhiteAt(wantedPos) != isWhite;
    }
}
