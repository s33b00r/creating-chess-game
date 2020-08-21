package model.chesspieces.cascades;

import java.awt.*;

public interface IKingInformation extends IPieceAt {

    boolean leftRookHasMoved(boolean isWhite);

    boolean rightRookHasMoved(boolean isWhite);

    boolean canMoveTo(Point p, boolean isWhite);
}
