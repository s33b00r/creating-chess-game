package Model;

import java.util.List;

abstract public class Piece {
    //Position on the board (goes from 0 - 7)
    private int xPos;
    private int yPos;
    //Position on the window screen
    private int realXPos;
    private int realYPos;

    private boolean isWhite;

    private String notation;

    public Piece(int xPos, int yPos, boolean isWhite, String notation){
        this.xPos = xPos;
        this.yPos = yPos;
        this.isWhite = isWhite;
        this.notation = notation;
    }

    public abstract boolean canMove(int xPos, int yPos, List<Piece> allPieces);

    public void move(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    protected boolean isOnBoard(int x, int y){
        return x >= 0 && x < 8 &&
                y >= 0 && y < 8;
    }
}
