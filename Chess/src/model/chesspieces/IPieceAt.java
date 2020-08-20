package model.chesspieces;

import java.awt.*;

public interface IPieceAt {
    boolean pieceAt(Point pos);

    boolean isWhiteAt(Point pos);
}
