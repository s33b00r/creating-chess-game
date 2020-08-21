package model.chesspieces;

class Bishop extends LineMovingPiece {
    public Bishop(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap, 'B');
        movementList.add(MoveDiagonally.getInstance());
    }

}
