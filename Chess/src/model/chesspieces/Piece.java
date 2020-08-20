package model.chesspieces;


import java.awt.*;

public abstract class Piece {

    protected final boolean isWhite;
    private int xPos;
    private int yPos;
    protected IPieceAt pieceMap;

    public Piece(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        this.isWhite = isWhite;
        this.xPos = xPos;
        this.yPos = yPos;
        this.pieceMap = pieceMap;
    }

    public Point getPos(){
        return new Point(xPos, yPos);
    }

    public boolean isWhite() {
        return isWhite;
    }

    //TODO make abstract and implement in other subclasses
    public abstract boolean canMove(Point p);

    public void move(Point p){
        xPos = p.x;
        yPos = p.y;
    }


}
