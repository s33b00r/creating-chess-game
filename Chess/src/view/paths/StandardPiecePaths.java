package view.paths;

import pathhandling.PiecePathsHandler;

public class StandardPiecePaths implements PiecePathsHandler {
    private static final String WHITE_BISHOP = "Chess/assets/chessPieceImages/White_Bishop.png";
    private static final String BLACK_BISHOP = "Chess/assets/chessPieceImages/Black_Bishop.png";
    private static final String WHITE_KING = "Chess/assets/chessPieceImages/White_King.png";
    private static final String BLACK_KING = "Chess/assets/chessPieceImages/Black_King.png";
    private static final String WHITE_KNIGHT = "Chess/assets/chessPieceImages/White_Knight.png";
    private static final String BLACK_KNIGHT = "Chess/assets/chessPieceImages/Black_Knight.png";
    private static final String WHITE_PAWN = "Chess/assets/chessPieceImages/White_Pawn.png";
    private static final String BLACK_PAWN = "Chess/assets/chessPieceImages/Black_Pawn.png";
    private static final String WHITE_QUEEN = "Chess/assets/chessPieceImages/White_Queen.png";
    private static final String BLACK_QUEEN = "Chess/assets/chessPieceImages/Black_Queen.png";
    private static final String WHITE_ROOK = "Chess/assets/chessPieceImages/White_Rook.png";
    private static final String BLACK_ROOK = "Chess/assets/chessPieceImages/Black_Rook.png";


    @Override
    public String getWhiteKing() {
        return WHITE_KING;
    }

    @Override
    public String getBlackKing() {
        return BLACK_KING;
    }

    @Override
    public String getWhiteQueen() {
        return WHITE_QUEEN;
    }

    @Override
    public String getBlackQueen() {
        return BLACK_QUEEN;
    }

    @Override
    public String getWhiteRook() {
        return WHITE_ROOK;
    }

    @Override
    public String getBlackRook() {
        return BLACK_ROOK;
    }

    @Override
    public String getWhiteBishop() {
        return WHITE_BISHOP;
    }

    @Override
    public String getBlackBishop() {
        return BLACK_BISHOP;
    }

    @Override
    public String getWhiteKnight() {
        return WHITE_KNIGHT;
    }

    @Override
    public String getBlackKnight() {
        return BLACK_KNIGHT;
    }

    @Override
    public String getWhitePawn() {
        return WHITE_PAWN;
    }

    @Override
    public String getBlackPawn() {
        return BLACK_PAWN;
    }
}
