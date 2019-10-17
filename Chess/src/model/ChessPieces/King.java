package model.ChessPieces;

import model.Piece;

import java.util.List;

public class King extends Piece {

    private boolean hasMoved = false;

    public King(int xPos, int yPos, boolean isWhite){
        super(xPos, yPos, isWhite, "K");
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        if(!isOnBoard(xPos, yPos)){
             return false;
        }
        if(attackingFriendly(allPieces)){
            return false;
        }

        //Castle rules
        if(!hasMoved){
            if(xPos == 2 || xPos == 6){
                if(!canCastle()/*TODO*/){
                    return false;
                }
            }
        }

        //Standard rules
        return Math.abs(getXPos() - xPos) <= 1 &&
                Math.abs(getYPos() - yPos) <= 1;
    }

    private boolean canCastle() {
        //TODO
        return false;
    }
}
