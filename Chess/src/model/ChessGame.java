package model;


class ChessGame implements IChessGame{

    public Board board;
    private boolean whitesTurn = true;



    @Override
    public void mouseClick(double mouseX, double mouseY) {
        /*if(!board.hasActivePiece()){
            board.pickupPiece();
        }*/
    }
}
