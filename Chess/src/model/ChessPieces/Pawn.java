package model.ChessPieces;

import model.Chess;
import model.Piece;

import java.util.List;

public class Pawn extends Piece {

    private boolean hasMoved = false;

    public Pawn(int x, int y, boolean isWhite){
        super(x, y, isWhite, "p");
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

        int dx = xPos - getXPos();
        int dy = yPos - getYPos();

        if(Math.abs(dx) > 1 || Math.abs(dy) > 2){
            return false;
        }

        if(getIsWhite()){
            if(dy > 0){
                if(dy == 2 && dx == 0){
                    return canMoveTwoSteps(allPieces, yPos);
                }
                if(dy == 1 && Math.abs(dx) == 1){
                    return canTakeDiagonal(allPieces, xPos, yPos);
                }
                if(dy == 1 && dx == 0){
                    return canMoveUp(allPieces, yPos);
                }
            return false;
            }
        }else{
            if(dy < 0){
                if(dy == -2 && dx == 0){
                    return canMoveTwoSteps(allPieces, yPos);
                }
                if(dy == -1 && Math.abs(dx) == 1){
                    return canTakeDiagonal(allPieces, xPos, yPos);
                }
                if(dy == -1 && dx == 0){
                    return canMoveUp(allPieces, yPos);
                }
            }
            return false;
        }
        return false;
    }

    private boolean canMoveTwoSteps(List<Piece> allPieces, int newY){
        return !goingThroughAPieceVertically(getXPos(), newY, allPieces) && !hasMoved;
    }

    private boolean canTakeDiagonal(List<Piece> allPieces, int newX, int newY){
        Piece attackedPiece = getPieceAt(newX, newY, allPieces);
        if(attackedPiece != null){
            return attackedPiece.getIsWhite() == getIsWhite();
        }

        //En passant rules
        Piece lastMovedPiece = Chess.lastMoveList.peek();
        if(lastMovedPiece instanceof Pawn){
            int temp = Chess.lastPos.pop();
            int dy = lastMovedPiece.getYPos() - Chess.lastPos.peek();
            Chess.lastPos.push(temp);

            if(Math.abs(dy) == 2){
                if(newX == lastMovedPiece.getXPos()){
                    return newY == lastMovedPiece.getYPos() - dy / Math.abs(dy);
                }
            }
        }
        return false;
    }

    private boolean canMoveUp(List<Piece> allPieces, int newY){
        Piece p = getPieceAt(getXPos(), newY, allPieces);
        return p == null;
    }
}
