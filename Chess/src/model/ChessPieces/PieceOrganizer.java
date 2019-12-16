package model.ChessPieces;

import java.util.ArrayList;
import java.util.List;

public class PieceOrganizer {

    public static List<Piece> standardSetup(){
        List<Piece> returnList = new ArrayList<>();


        //Add all the pawns
        boolean isWhite = false;
        int yPos = 1 - 5;

        for (int j = 0; j < 2; j++) {
            isWhite = !isWhite;
            yPos += 5;
            for (int i = 0; i < 8; i++) {
                returnList.add(new Pawn(i, yPos, isWhite));
            }
        }

        //Add all Pieces (Except Queen and King)
        isWhite = false;
        int xPos = 7;
        yPos = -7;
        for (int i = 0; i < 2; i++) {
            isWhite = !isWhite;
            yPos += 7;
            for (int j = 0; j < 2; j++) {
                returnList.add(new Rook(xPos * j, yPos, isWhite));
                returnList.add(new Knight(Math.abs(xPos - 1) * j, yPos, isWhite));
                returnList.add(new Bishop(Math.abs(xPos - 2) * j, yPos, isWhite));
            }
        }

        //Add the Queen and King
        isWhite = false;
        yPos = -7;
        for (int i = 0; i < 2; i++) {
            yPos += 7;
            isWhite = !isWhite;

            returnList.add(new Queen(3, yPos, isWhite));
            returnList.add(new King(4, yPos, isWhite));
        }

        return returnList;
    }

}
