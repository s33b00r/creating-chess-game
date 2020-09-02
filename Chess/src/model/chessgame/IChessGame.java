package model.chessgame;

import observerinterfaces.IMouseClickListener;
import observerinterfaces.IRedrawable;

public interface IChessGame extends IMouseClickListener {
    void addRedrawableObserver(IRedrawable redrawable);
}
