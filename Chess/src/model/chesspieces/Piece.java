package model.chesspieces;


import model.chesspieces.cascades.IPieceAt;

import java.awt.*;

public abstract class Piece {

    protected final boolean isWhite;
    private int xPos;
    private int yPos;
    protected IPieceAt pieceMap;
    private boolean hasMoved = false;
    protected PieceData notation;

    public Piece(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap, PieceData notation) {
        this.isWhite = isWhite;
        this.xPos = xPos;
        this.yPos = yPos;
        this.pieceMap = pieceMap;
        this.notation = notation;
    }

    public Point getPos(){
        return new Point(xPos, yPos);
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean canMove(Point p);

    public PieceData getNotation() {
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
