package model.chesspieces.cascades;

import java.awt.*;

public interface IPawnInformation extends IPieceAt {
    boolean canEnPassant(Point p);
}
