package model;

import model.ChessPieces.Pawn;

import java.util.ArrayDeque;
import java.util.Deque;

import static view.ChessGUI.WINDOW_HEIGHT;
import static view.ChessGUI.WINDOW_WIDTH;

public class Chess {

    public Board board;
    private boolean whitesTurn = true;
    private char activePieceNotation = '-';
    private int activePieceX = -1;
    private int activePieceY = -1;

    private MiniMaxAI miniMaxAI;

    public Chess(Board board){
        this.board = board;
        miniMaxAI = new MiniMaxAI(false, 5);
    }

    //TODO: Make it more readable and more "logic"
    public void mouseClickHandler(double mouseX, double mouseY){

        if(miniMaxAI.isWhite() == whitesTurn){

            if(miniMaxAI.getT().getState() == Thread.State.TERMINATED){
                whitesTurn = !whitesTurn;
            }
        }

        if(activePieceNotation != '-'){
            int nextXPos = Board.convertRealX(mouseX);
            int nextYPos = Board.convertRealY(mouseY);
            if(Piece.canMove(activePieceNotation, activePieceX, activePieceY, nextXPos, nextYPos, board.getBoard())){
                board.getBoard()[nextXPos][nextYPos] = activePieceNotation;
                if(Board.didCastleShort(activePieceNotation, activePieceX, activePieceY, nextXPos, nextYPos)){
                    board.getBoard()[5][activePieceY] = whitesTurn ? 'H' : 'h';
                    board.getBoard()[7][activePieceY] = '-';
                }else if(Board.didCastleLong(activePieceNotation, activePieceX, activePieceY, nextXPos, nextYPos)){
                    board.getBoard()[3][activePieceY] = whitesTurn ? 'H' : 'h';
                    board.getBoard()[0][activePieceY] = '-';
                }
                whitesTurn = !whitesTurn;
                if(!whitesTurn){
                    miniMaxAI.activeBoard = board;
                    miniMaxAI.calculateWithThread();
                }
            }else{
                board.getBoard()[activePieceX][activePieceY] = activePieceNotation;
            }
            activePieceNotation = '-';

        }else{
            char check = board.getBoard()[Board.convertRealX(mouseX)][Board.convertRealY(mouseY)];
            if(check != '-' && !Character.isLowerCase(check) == whitesTurn){
                activePieceNotation = check;
                activePieceX = Board.convertRealX(mouseX);
                activePieceY = Board.convertRealY(mouseY);
                board.getBoard()[activePieceX][activePieceY] = '-';
            }
        }
    }


    public char getActivePieceNotation() {
        return activePieceNotation;
    }

}
