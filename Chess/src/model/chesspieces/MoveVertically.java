package model.chesspieces;

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
        if (currentPos.x != wantedPos.x)
            return false;
        int dy = wantedPos.y - currentPos.y;
        int stepDirY = dy > 0 ? 1 : -1;

        int curX = currentPos.x;
        int curY = stepDirY + currentPos.y;
        while (curY != wantedPos.y) {
            if (pieceMap.pieceAt(new Point(curX, curY)))
                return false;
            curY += stepDirY;
        }

        return !pieceMap.pieceAt(wantedPos) || pieceMap.isWhiteAt(wantedPos) != isWhite;
    }
}
