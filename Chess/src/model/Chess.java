package model;

import model.ChessPieces.Pawn;

import java.util.ArrayDeque;
import java.util.Deque;

import static view.ChessGUI.WINDOW_HEIGHT;
import static view.ChessGUI.WINDOW_WIDTH;

public class Chess {

    public Board board;
    private boolean whitesTurn = true;
    private Piece activePiece = null;
    public static ArrayDeque<Integer> lastPos = new ArrayDeque<>();
    public static ArrayDeque<Piece> lastMoveList = new ArrayDeque<>();

    public Chess(Board board){
        this.board = board;
    }

    //TODO: Make it more readable and more "logic"
    public void mouseClickHandler(double mouseX, double mouseY){
        if(activePiece != null){
            int nextXPos = Board.convertRealX(mouseX);
            int nextYPos = Board.convertRealY(mouseY);
            if(activePiece.canMove(nextXPos, nextYPos, board.getAllPieces())){
                lastPos.push(activePiece.getYPos());
                lastPos.push(activePiece.getXPos());
                lastMoveList.push(activePiece);
                Piece takenPiece = board.getPieceAt(mouseX, mouseY);
                //Removing of taken pieces
                if(takenPiece != null){
                    board.removePiece(takenPiece);
                }else if(activePiece instanceof Pawn && activePiece.getXPos() != nextXPos){ //En passant checking as well
                    double xPos = WINDOW_WIDTH / 8.0 * nextXPos;
                    double yPos = WINDOW_HEIGHT / 8.0 * (7 - activePiece.getYPos());
                    board.removePiece(board.getPieceAt(xPos, yPos));
                }
                activePiece.move(Board.convertRealX(mouseX), Board.convertRealY(mouseY));
                whitesTurn = !whitesTurn;
                if(!whitesTurn){
                    MiniMaxAI miniMaxAI = new MiniMaxAI(false, 4);
                    miniMaxAI.calculateMoves(board.getAllPieces());
                }
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
