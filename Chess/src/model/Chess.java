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

    /**
     * When this method is called, change the active piece notation
     * @param mouseX xPos on mouse
     * @param mouseY yPos on mouse
     */
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
                Board.clearEnPassantNot(board.getBoard());
                board.getBoard()[nextXPos][nextYPos] = activePieceNotation;

                changeIfCastle(nextXPos, nextYPos);
                changeIfTwoStepsPawn(nextYPos);

                whitesTurn = !whitesTurn;

                if(whitesTurn == miniMaxAI.isWhite()){
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

    private void changeIfCastle(int nextXPos, int nextYPos){
        if(Board.didCastleShort(activePieceNotation, activePieceX, activePieceY, nextXPos, nextYPos)){
            board.getBoard()[5][activePieceY] = whitesTurn ? 'H' : 'h';
            board.getBoard()[7][activePieceY] = '-';
        }else if(Board.didCastleLong(activePieceNotation, activePieceX, activePieceY, nextXPos, nextYPos)){
            board.getBoard()[3][activePieceY] = whitesTurn ? 'H' : 'h';
            board.getBoard()[0][activePieceY] = '-';
        }
    }

    private void changeIfTwoStepsPawn(int nextYPos){

        if(Character.toLowerCase(activePieceNotation) == 'p'){
            if(Math.abs(nextYPos - activePieceY) == 2){
                board.getBoard()[activePieceX][nextYPos + (activePieceY - nextYPos) / 2] = 'E';
            }
        }

    }

    public char getActivePieceNotation() {
        return activePieceNotation;
    }

}
