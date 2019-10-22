package model;

import model.ChessPieces.Rook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MiniMaxAI {

    private boolean isWhite;
    private int maxSteps;

    public MiniMaxAI(boolean isWhite, int maxSteps){
        this.isWhite = isWhite;
        this.maxSteps = maxSteps;
    }

    public void calculateMoves(List<Piece> allPieces){
        Piece bestPiece = null;
        int bestX = -1;
        int bestY = -1;
        double maxValue = -Double.MAX_VALUE;
        List<NextMove> possibleMoves = getAllMoves(allPieces, isWhite);
        for(NextMove nextMove : possibleMoves){
            List<Piece> newSet = newSet(allPieces, nextMove);
            double value = miniMax(newSet, maxSteps - 1, -Double.MAX_VALUE, Double.MAX_VALUE, true);
            if(maxValue < value){
                maxValue = value;
                bestPiece = nextMove.pieceToMove;
                bestX = nextMove.nextX;
                bestY = nextMove.nextY;
            }
        }

        System.out.println("Best move is to " + bestX + ", " + bestY + " with the piece " +
                bestPiece.getClass().getSimpleName() + " with the value of " + maxValue);

    }

    private double miniMax(List<Piece> allPieces, int depth, double alpha, double beta, boolean maximizingPlayer){
        //TODO: Make all the evaluation methods here
        if(depth == 0){
            return calculateBoardValue(allPieces);
        }



        /*if(isCheckMate(allPieces)){
            double points = Double.MAX_VALUE;
            return maximizingPlayer ? points : -points;
        }

        if(isDraw(allPieces)){
            return 0;
        }*/

        List<NextMove> allMoves = getAllMoves(allPieces, maximizingPlayer);


        double maxEval = -Double.MAX_VALUE;
        double minEval = Double.MAX_VALUE;
        for(NextMove nm : allMoves){
            List<Piece> newSet = newSet(allPieces, nm);
            double eval = miniMax(newSet, depth - 1, alpha, beta, false);

            if(maximizingPlayer){
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
            }else {
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
            }

            if(beta <= alpha){
                break;
            }
        }



        if(maximizingPlayer){
            return maxEval;
        }else {
            return minEval;
        }
    }

    private List<Piece> newSet(List<Piece> allPieces, NextMove nextMove){
        List<Piece> newSet = allPieces.stream()
                .map(Piece::copy)
                .collect(Collectors.toList());
        removePiece(newSet, nextMove.nextX, nextMove.nextY);
        newSet = newSet.stream().peek(piece -> {
            if(piece.getXPos() == nextMove.pieceToMove.getXPos() && piece.getYPos() == nextMove.pieceToMove.getYPos()){
                piece.move(nextMove.nextX, nextMove.nextY);
            }
        })
                .collect(Collectors.toList());
        return newSet;
    }

    private class NextMove{
        public int nextX;
        public int nextY;
        public Piece pieceToMove;

        public NextMove(int nextX, int nextY, Piece pieceToMove) {
            this.nextX = nextX;
            this.nextY = nextY;
            this.pieceToMove = pieceToMove;
        }
    }

    private List<NextMove> getAllMoves(List<Piece> allPieces, boolean isWhite){
        List<NextMove> returnList = new ArrayList<>();
        for (int i = 0; i < allPieces.size(); i++) {
            Piece currentPiece = allPieces.get(i);
            if(currentPiece.getIsWhite() == isWhite){
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        if(currentPiece.canMove(x, y, allPieces)){
                            returnList.add(new NextMove(x, y, currentPiece));
                        }
                    }
                }
            }
        }
        return returnList;
    }

    private double calculateBoardValue(List<Piece> allPieces){
        double totalValue = 0;
        for(Piece p : allPieces){
            totalValue += p.getIsWhite() == isWhite ? p.getValue() : -p.getValue();
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if(p.canMove(x, y, allPieces)){
                        double valueOfSquare = getValueOfSquare(x, y);
                        totalValue += p.getIsWhite() == isWhite ? valueOfSquare : -valueOfSquare;
                    }
                }
            }
        }
        return totalValue;
    }

    private void removePiece(List<Piece> allPieces, int x, int y){
        for (int i = 0; i < allPieces.size(); i++) {
            if(x == allPieces.get(i).getXPos() && y == allPieces.get(i).getYPos()){
                allPieces.remove(i);
                return;
            }
        }
    }

    private double getValueOfSquare(int x, int y){
        double value = 0;
        switch (x){
            case 0:
            case 7:
                value += 0.03;
                break;
            case 1:
            case 6:
                value += 0.05;
                break;
            case 2:
            case 5:
                value += 0.08;
                break;
            case 3:
            case 4:
                value += 0.12;
                break;
        }

        switch (y){
            case 0:
            case 7:
                value += 0.03;
                break;
            case 1:
            case 6:
                value += 0.05;
                break;
            case 2:
            case 5:
                value += 0.08;
                break;
            case 3:
            case 4:
                value += 0.12;
                break;
        }
        return value;
    }

}
