package model.ChessPieces;

import model.Piece;

import java.util.List;

public class King extends Piece {

    private boolean hasMoved = false;

    public King(int xPos, int yPos, boolean isWhite){
        super(xPos, yPos, isWhite, "K");
    }

    @Override
    public void move(int xPos, int yPos) {
        super.move(xPos, yPos);
        hasMoved = true;
    }

    @Override
    public boolean canMove(int xPos, int yPos, List<Piece> allPieces) {
        if(!isOnBoard(xPos, yPos)){
             return false;
        }
        if(attackingFriendly(xPos, yPos, allPieces)){
            return false;
        }

        //Castle rules
        if(!hasMoved){
            if((xPos == 2 || xPos == 6) && yPos == getYPos()){
                return canCastle(xPos, allPieces);
            }
        }

        //Standard rules
        return Math.abs(getXPos() - xPos) <= 1 &&
                Math.abs(getYPos() - yPos) <= 1;
    }

    private boolean canCastle(int newX, List<Piece> allPieces) {
        //TODO
        if(newX == 6){
            return findCastleRook(allPieces, 7);
        }else{
            return findCastleRook(allPieces, 0);
        }
    }

    private boolean findCastleRook(List<Piece> allPieces, int rookPos){

        Piece castleingRook = null;
        for(Piece p : allPieces){
            if(p instanceof Rook){
                Rook rook = (Rook) p;
                if(!rook.isHasMoved() &&
                        rook.getIsWhite() == getIsWhite() &&
                        rook.getXPos() == rookPos){
                    castleingRook = rook;
                }
            }
        }
        return castleingRook != null;
    }

}
