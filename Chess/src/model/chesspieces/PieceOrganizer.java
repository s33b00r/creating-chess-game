package model.chesspieces;

import pathhandling.PiecePathsHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceOrganizer {

    public static List<Piece> standardSetup(IPieceAt pieceMap) {
        List<Piece> returnList = new ArrayList<>();


        //Add all the pawns
        boolean isWhite = false;
        int yPos = 1 - 5;

        for (int j = 0; j < 2; j++) {
            isWhite = !isWhite;
            yPos += 5;
            for (int i = 0; i < 8; i++) {
                returnList.add(new Pawn(i, yPos, isWhite, pieceMap));
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
                returnList.add(new Rook(xPos * j, yPos, isWhite, pieceMap));
                returnList.add(new Knight(Math.abs(xPos * j - 1), yPos, isWhite, pieceMap));
                returnList.add(new Bishop(Math.abs(xPos * j - 2), yPos, isWhite, pieceMap));
            }
        }

        //Add the Queen and King
        isWhite = false;
        yPos = -7;
        for (int i = 0; i < 2; i++) {
            yPos += 7;
            isWhite = !isWhite;

            returnList.add(new Queen(3, yPos, isWhite, pieceMap));
            returnList.add(new King(4, yPos, isWhite, pieceMap));
        }

        return returnList;
    }

    public static Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths){
        Map<Boolean, Map<Object, String>> returnMap = new HashMap<>();
        Map<Object, String> whitePieceMap = new HashMap<>();
        Map<Object, String> blackPieceMap = new HashMap<>();


        whitePieceMap.put(King.class, paths.getWhiteKing());
        whitePieceMap.put(Queen.class, paths.getWhiteQueen());
        whitePieceMap.put(Rook.class, paths.getWhiteRook());
        whitePieceMap.put(Bishop.class, paths.getWhiteBishop());
        whitePieceMap.put(Knight.class, paths.getWhiteKnight());
        whitePieceMap.put(Pawn.class, paths.getWhitePawn());

        blackPieceMap.put(King.class, paths.getBlackKing());
        blackPieceMap.put(Queen.class, paths.getBlackQueen());
        blackPieceMap.put(Rook.class, paths.getBlackRook());
        blackPieceMap.put(Bishop.class, paths.getBlackBishop());
        blackPieceMap.put(Knight.class, paths.getBlackKnight());
        blackPieceMap.put(Pawn.class, paths.getBlackPawn());

        returnMap.put(true, whitePieceMap);
        returnMap.put(false, blackPieceMap);

        return returnMap;
    }
}
