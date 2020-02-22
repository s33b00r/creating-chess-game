package model;

import observerinterfaces.IMousePositionListener;

public class ChessGameFactory {
    public static IChessGame createChessGame(IMousePositionListener mousePositionListener){
        return new ChessGame(mousePositionListener);
    }

    public static IViewItems convertChessGameForView(IChessGame chessGame){
        return (IViewItems)chessGame;
    }

}
