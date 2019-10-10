package Model.ChessPieces;

import Model.Piece;

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
        if(attackingFriendly(xPos, yPos, allPieces)){
            return false;
        }
        if()
        return true;
    }
}
