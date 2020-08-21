package model.chesspieces;


import java.awt.*;

public abstract class Piece {

    protected final boolean isWhite;
    private int xPos;
    private int yPos;
    protected IPieceAt pieceMap;
    protected char notation;
    private boolean hasMoved = false;

    public Piece(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap, char notation) {
        this.isWhite = isWhite;
        this.xPos = xPos;
        this.yPos = yPos;
        this.pieceMap = pieceMap;
        this.notation = isWhite ? notation : Character.toLowerCase(notation);
    }

    public Point getPos(){
        return new Point(xPos, yPos);
    }

    public boolean isWhite() {
        return isWhite;
    }

    //TODO make abstract and implement in other subclasses
    public abstract boolean canMove(Point p);

    public char getNotation() {
        return notation;
    }

    public void move(Point p){
        hasMoved = true;
        xPos = p.x;
        yPos = p.y;
    }

    public boolean hasMoved() {
        return hasMoved;
    }


}
