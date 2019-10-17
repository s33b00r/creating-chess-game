package model;

import static view.ChessGUI.WINDOW_WIDTH;

public class Chess {

    public Board board;
    private boolean whitesTurn = true;
    private Piece activePiece = null;

    public Chess(Board board){
        this.board = board;
    }

    public void mouseClickHandler(double mouseX, double mouseY){
        if(activePiece != null){
            int nextXPos = Board.convertRealX(mouseX);
            int nextYPos = Board.convertRealY(mouseY);
            if(activePiece.canMove(nextXPos, nextYPos, board.getAllPieces())){
                activePiece.move(Board.convertRealX(mouseX), Board.convertRealY(mouseY));
                whitesTurn = !whitesTurn;
            }else{
                activePiece.setRealXPos(activePiece.calculateRealXPos());
                activePiece.setRealYPos(activePiece.calculateRealYPos());
            }
            activePiece = null;
        }else{
            Piece check = board.getPieceAt(mouseX, mouseY);
            if(check != null && check.getIsWhite() == whitesTurn){
                activePiece = board.getPieceAt(mouseX, mouseY);
                moveToMouse(activePiece, mouseX, mouseY);
            }
        }
    }

    public void mouseMovementHandler(double mouseX, double mouseY){
        moveToMouse(activePiece, mouseX, mouseY);
    }


    private void moveToMouse(Piece p, double mouseX, double mouseY){
        p.setRealXPos((int) mouseX);
        p.setRealYPos((int) mouseY);
    }

    public Piece getActivePiece() {
        return activePiece;
    }
}
