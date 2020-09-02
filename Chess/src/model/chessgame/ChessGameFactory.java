package model.chessgame;

public class ChessGameFactory {

    private static ChessGame chessGame;

    public static IChessGame createChessGame(int xPos, int yPos, int width, int height) {
        if (chessGame == null)
            chessGame = new ChessGame(xPos, yPos, width, height);
        return chessGame;
    }

    public static IViewPathHandler getPathHandler() {
        return chessGame;
    }

    public static IViewItemHandler getItemHandler() {
        return chessGame;
    }

}
