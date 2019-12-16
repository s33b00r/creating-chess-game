package model;

public class ChessGameFactory {
    public static IChessGame createChessGame(){
        return new ChessGame();
    }
}
