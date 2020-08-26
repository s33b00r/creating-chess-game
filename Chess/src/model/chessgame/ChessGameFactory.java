package model.chessgame;

import observerinterfaces.IMousePositionListener;

public class ChessGameFactory {

    private static ChessGame chessGame;

    public static IChessGame createChessGame(IMousePositionListener mousePositionListener) {
        if (chessGame == null)
            chessGame = new ChessGame(mousePositionListener);
        return chessGame;
    }

    public static IViewPathHandler getPathHandler() {
        return chessGame;
    }

    public static IViewItemHandler getItemHandler() {
        return chessGame;
    }

}
