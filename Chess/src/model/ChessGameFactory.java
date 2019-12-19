package model;

public class ChessGameFactory {
    public static IChessGame createChessGame(){
        return new ChessGame();
    }

    public static IViewItems convertChessGameForView(IChessGame chessGame){
        return (IViewItems)chessGame;
    }

}
