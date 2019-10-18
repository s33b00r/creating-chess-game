package model.ChessPieces;

import model.Piece;

import java.util.List;

public class Rook extends Piece {

    private boolean hasMoved = false;

    public Rook(int x, int y, boolean isWhite){
        super(x, y, isWhite, "R");
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

        int dx = getXPos() - xPos;
        int dy = getYPos() - yPos;

        if(dx != 0 && dy == 0){
            return !goingThroughAPieceHorizontally(xPos, yPos, allPieces);
        }
        if(dy != 0 && dx == 0){
            return !goingThroughAPieceVertically(xPos, yPos, allPieces);
        }

        return false;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }
}
