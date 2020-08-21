package model.chesspieces;

import java.awt.*;

public interface IPieceAt {
    boolean pieceAt(Point pos);
    boolean isWhiteAt(Point pos);

    boolean leftRookHasMoved(boolean isWhite);

    boolean rightRookHasMoved(boolean isWhite);

    boolean canMoveTo(Point p, boolean isWhite);
}
