package model.chesspieces.actualpieces;

import model.chesspieces.Piece;
import model.chesspieces.PieceData;
import model.chesspieces.cascades.IPawnInformation;
import model.chesspieces.cascades.IPieceAt;

import java.awt.*;

public class Pawn extends Piece {

    public Pawn(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap, PieceData.PAWN);
    }

    @Override
    public boolean canMove(Point p) {
        IPawnInformation pieceMap = (IPawnInformation) this.pieceMap;
        int dy = p.y - getPos().y;
        int dx = p.x - getPos().x;
        int stepDirY = dy > 0 ? 1 : -1;

        //Move correct amount of steps
        if (Math.abs(dx) > 1 || Math.abs(dy) > 2 || dy == 0)
            return false;

        //Move in correct direction
        if ((dy > 0 && !isWhite) || (dy < 0 && isWhite))
            return false;

        //Is capturing
        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            if (pieceMap.pieceAt(p))
                return pieceMap.isWhiteAt(p) != isWhite;
            else if (((p.y == 5) && isWhite) || ((p.y == 2) && !isWhite))
                return pieceMap.canEnPassant(p);
        }

        //If not capturing, shouldn't go sideways
        if (Math.abs(dx) > 0)
            return false;

        //Isn't going on any other piece
        if (pieceMap.pieceAt(new Point(getPos().x, getPos().y + stepDirY)))
            return false;

        //Is making 2 step first move
        if (Math.abs(dy) == 2) {
            return getPos().y == 1 || getPos().y == 6;
        }

        return true;
    }


}
