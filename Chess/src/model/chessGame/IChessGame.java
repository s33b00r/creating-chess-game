package model.chessGame;

import observerinterfaces.IRedrawable;

public interface IChessGame {
    void mouseClick(int xPos, int yPos);
    void addRedrawableObserver(IRedrawable redrawable);
}
