package model.chesspieces;

class Queen extends LineMovingPiece {

    public Queen(int xPos, int yPos, boolean isWhite, IPieceAt pieceMap) {
        super(xPos, yPos, isWhite, pieceMap);
        movementList.add(MoveDiagonally.getInstance());
        movementList.add(MoveHorizontally.getInstance());
        movementList.add(MoveVertically.getInstance());
   }
}
