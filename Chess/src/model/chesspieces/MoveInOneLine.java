package model.chesspieces;

import java.awt.*;

public interface MoveInOneLine {
    boolean canMove(IPieceAt pieceMap, boolean isWhite, Point currentPos, Point wantedPos);
}
