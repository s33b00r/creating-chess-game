package model;

import observerinterfaces.IRedrawable;

public interface IChessGame {
    void mouseClick(int xPos, int yPos);
    void addRedrawableObserver(IRedrawable redrawable);
    void mouseMoved(int xPos, int yPos);
}
