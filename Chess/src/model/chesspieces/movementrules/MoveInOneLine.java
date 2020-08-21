package model.chesspieces.movementrules;

import model.chesspieces.cascades.IPieceAt;

import java.awt.*;

public interface MoveInOneLine {
    boolean canMove(IPieceAt pieceMap, boolean isWhite, Point currentPos, Point wantedPos);
}
