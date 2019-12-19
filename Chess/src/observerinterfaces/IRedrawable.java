package observerinterfaces;

import java.awt.*;

public interface IRedrawable {
    void redrawWithActivePiece(boolean isWhite, Point pos, Object piece);
}
