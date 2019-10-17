package model;

import java.util.List;

import static view.ChessGUI.WINDOW_HEIGHT;
import static view.ChessGUI.WINDOW_WIDTH;

abstract public class Piece {
    //Position on the board (goes from 0 - 7)
    private int xPos;
    private int yPos;
    //Position on the window screen
    private int realXPos;
    private int realYPos;

    private boolean isWhite;

    private String notation;

    public Piece(int xPos, int yPos, boolean isWhite, String notation) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isWhite = isWhite;
        this.notation = notation;
        realXPos = calculateRealXPos();
        realYPos = calculateRealYPos();
    }

    public int calculateRealXPos() {
        return WINDOW_WIDTH / 8 * this.xPos;
    }

    public abstract boolean canMove(int xPos, int yPos, List<Piece> allPieces);

    public void move(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        realXPos = calculateRealXPos();
        realYPos = calculateRealYPos();
    }

    boolean isPointingInside(int x, int y){
        return x == xPos && y == yPos;
    }

    public int calculateRealYPos() {
        return WINDOW_HEIGHT / 8 * (7 - this.yPos);
    }

    protected boolean isOnBoard(int x, int y) {
        return x >= 0 && x < 8 &&
                y >= 0 && y < 8;
    }

    protected boolean attackingFriendly(List<Piece> allPieces) {
        for (Piece p : allPieces) {
            if (p.getIsWhite() == isWhite) {
                if (p.xPos == xPos && p.yPos == yPos) {
                    return true;
                }
            }
        }
        return false;
    }

    //Expects that the piece is moving diagonally
    protected boolean isGoingThroughAPieceDiagonally(int x, int y, List<Piece> allPieces){
        int dX = xPos - x;
        int dY = yPos - y;

        if(Math.abs(dX) != Math.abs(dY)){
            System.out.println("Invalid input!");
            return true;
        }

        int stepDirectionX = dX / Math.abs(dX);
        int stepDirectionY = dY / Math.abs(dY);
        int stepsX = stepDirectionX;
        int stepsY = stepDirectionY;

        while(dX != stepsX){
            if(getPieceAt(stepsX + xPos, stepsY + yPos, allPieces) != null){
                return true;
            }
            stepsX += stepDirectionX;
            stepsY += stepDirectionY;
        }
        return false;
    }

    private Piece getPieceAt(int xPos, int yPos, List<Piece> allPieces){
        for(Piece p : allPieces){
            if(p.xPos == xPos && p.yPos == yPos){
                return p;
            }
        }
        return null;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getRealXPos() {
        return realXPos;
    }

    public int getRealYPos() {
        return realYPos;
    }

    public void setRealXPos(int realXPos) {
        this.realXPos = realXPos;
    }

    public void setRealYPos(int realYPos) {
        this.realYPos = realYPos;
    }

    public boolean getIsWhite() {
        return isWhite;
    }
}