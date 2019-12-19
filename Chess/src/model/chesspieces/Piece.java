package model.chesspieces;

import java.awt.*;

public abstract class Piece {

    private final boolean isWhite;
    private int xPos;
    private int yPos;

    public Piece(int xPos, int yPos, boolean isWhite){
        this.isWhite = isWhite;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Point getPos(){
        return new Point(xPos, yPos);
    }

    public boolean isWhite() {
        return isWhite;
    }

    //TODO make abstract and implement in other subclasses
    public boolean canMove(Point p){
        return true;
    }

    public void move(Point p){
        xPos = p.x;
        yPos = p.y;
    }


}
