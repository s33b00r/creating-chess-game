package model;

import model.ChessPieces.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniMaxAI implements Runnable{

    private boolean isWhite;
    private int maxSteps;

    private NextMove bestMove;

    public Board activeBoard;

    private String threadName = "calculateMoves";
    private Thread t;

    public MiniMaxAI(boolean isWhite, int maxSteps){
        this.isWhite = isWhite;
        this.maxSteps = maxSteps;
    }

    public void calculateWithThread(){
        t = new Thread(this, threadName);
        t.start();
    }

    public void run(){
        calculateMoves(activeBoard);
        move(activeBoard.getBoard());

    }


    public void calculateMoves(Board board){
        int amountOfMovesAvailable = 0;
        int curXBest = -1;
        int curYBest = -1;
        int bestX = -1;
        int bestY = -1;
        double maxValue = -Double.MAX_VALUE;
        List<NextMove> possibleMoves = getAllMoves(board.getBoard(), isWhite);

        double alpha = -Double.MAX_VALUE;
        double beta = Double.MAX_VALUE;

        for(NextMove nextMove : possibleMoves){
            amountOfMovesAvailable++;
            char[][] newBoard = copyBoard(board.getBoard());
            move(nextMove, newBoard);
            double value = miniMax(newBoard, maxSteps - 1, alpha, beta, false);
            if(maxValue < value){
                maxValue = value;
                curXBest = nextMove.currentX;
                curYBest = nextMove.currentY;
                bestX = nextMove.nextX;
                bestY = nextMove.nextY;
            }
            alpha = maxValue;
        }
        bestMove = new NextMove(curXBest, curYBest, bestX, bestY);
        System.out.println(amountOfMovesAvailable);
        System.out.println("Best move is to " + bestX + ", " + bestY + " from " + curXBest + ", " + curYBest +
                " with the value of " + maxValue);

    }

    private double miniMax(char[][] board, int depth, double alpha, double beta, boolean maximizingPlayer){
        //TODO: Make all the evaluation methods here
        if(depth == 0){
            return calculateBoardValue(board);
        }



       /* if(isCheckMate(allPieces)){
            double points = Double.MAX_VALUE;
            return maximizingPlayer ? points : -points;
        }

        if(isDraw(allPieces)){
            return 0;
        }*/

        List<NextMove> allMoves = getAllMoves(board, isWhite == maximizingPlayer);


        double maxEval = -Double.MAX_VALUE;
        double minEval = Double.MAX_VALUE;
        for(NextMove nm : allMoves){

            char[][] newBoard = copyBoard(board);
            move(nm, newBoard);

            double eval = miniMax(newBoard, depth - 1, alpha, beta, !maximizingPlayer);

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


    private class NextMove{
        public int nextX;
        public int nextY;
        public int currentX, currentY;

        public NextMove(int currentX, int currentY, int nextX, int nextY) {
            this.currentX = currentX;
            this.currentY = currentY;
            this.nextX = nextX;
            this.nextY = nextY;
        }

        @Override
        public String toString() {
            return "(" + currentX + ", " + currentY + ") -> (" + nextX + ", " + nextY + ")";
        }
    }

    private List<NextMove> getAllMoves(char[][] board, boolean isWhite){
       List<NextMove> returnList = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                char currentPiece = board[x][y];
               if(currentPiece != '-'){
                   if(Character.isLowerCase(currentPiece) == !isWhite){
                        switch (Character.toLowerCase(currentPiece)){
                            case 'k':
                                returnList.addAll(getAllKingMoves(x, y, board));
                                break;
                            case 'q':
                                returnList.addAll(getAllQueenMoves(x, y, board));
                                break;
                            case 'b':
                                returnList.addAll(getAllBishopMoves(x, y, board));
                                break;
                            case 'r':
                                returnList.addAll(getAllRookMoves(x, y, board));
                                break;
                            case 'n':
                                returnList.addAll(getAllKnightMoves(x, y, board));
                                break;
                            case 'p':
                                returnList.addAll(getAllPawnMoves(x, y, board));
                                break;
                            default:
                                break;
                        }
                   }
               }
            }
        }
        return returnList;
    }

    private List<NextMove> getAllKingMoves(int curX, int curY, char[][] board){
        List<NextMove> returnList = new ArrayList<>();
        //normal movement
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(!(i == 0 && j == 0)){
                    if(King.canMove(curX, curY, curX + i, curY + j, board)){
                        NextMove nextMove = new NextMove(curX, curY, curX + i, curY + j);
                        returnList.add(nextMove);
                    }
                }
            }
        }
        //TODO: castling movement

        return returnList;
    }
    private List<NextMove> getAllQueenMoves(int curX, int curY, char[][] board){
        List<NextMove> returnList = new ArrayList<>();
        int testX = curX + 1;
        int testY = curY;
        while (Queen.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX += 1;
        }

        testX = curX - 1;
        testY = curY;
        while (Queen.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX -= 1;
        }

        testX = curX;
        testY = curY + 1;
        while (Queen.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testY += 1;
        }

        testX = curX;
        testY = curY - 1;
        while (Queen.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testY -= 1;
        }

        testX = curX + 1;
        testY = curY + 1;
        while (Queen.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX += 1;
            testY += 1;
        }

        testX = curX - 1;
        testY = curY + 1;
        while (Queen.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX -= 1;
            testY += 1;
        }

        testX = curX + 1;
        testY = curY - 1;
        while (Queen.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX += 1;
            testY -= 1;
        }

        testX = curX - 1;
        testY = curY - 1;
        while (Queen.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX -= 1;
            testY -= 1;
        }

        return returnList;
    }
    private List<NextMove> getAllBishopMoves(int curX, int curY, char[][] board){
       List<NextMove> returnList = new ArrayList<>();

        int testX = curX + 1;
        int testY = curY + 1;
        while (Bishop.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX += 1;
            testY += 1;
        }

        testX = curX - 1;
        testY = curY + 1;
        while (Bishop.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX -= 1;
            testY += 1;
        }

        testX = curX + 1;
        testY = curY - 1;
        while (Bishop.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX += 1;
            testY -= 1;
        }

        testX = curX - 1;
        testY = curY - 1;
        while (Bishop.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX -= 1;
            testY -= 1;
        }

        return returnList;
    }
    private List<NextMove> getAllRookMoves(int curX, int curY, char[][] board){

        List<NextMove> returnList = new ArrayList<>();
        int testX = curX + 1;
        int testY = curY;
        while (Rook.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX += 1;
        }

        testX = curX - 1;
        testY = curY;
        while (Rook.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testX -= 1;
        }

        testX = curX;
        testY = curY + 1;
        while (Rook.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testY += 1;
        }

        testX = curX;
        testY = curY - 1;
        while (Rook.canMove(curX, curY, testX, testY, board)){
            NextMove nextMove = new NextMove(curX, curY, testX, testY);
            returnList.add(nextMove);
            testY -= 1;
        }
        return returnList;
    }
    private List<NextMove> getAllKnightMoves(int curX, int curY, char[][] board){
        List<NextMove> returnList = new ArrayList<>();
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                if((Math.abs(i) == 1 && Math.abs(j) == 2) || (Math.abs(i) == 2 && Math.abs(j) == 1)){
                    if(Knight.canMove(curX, curY, curX + i, curY + j, board)){
                        NextMove nextMove = new NextMove(curX, curY, curX + i, curY + j);
                        returnList.add(nextMove);
                    }
                }
            }
        }
        return returnList;
    }
    private List<NextMove> getAllPawnMoves(int curX, int curY, char[][] board){
        List<NextMove> returnList = new ArrayList<>();
        char notation = board[curX][curY];

        int direction = Character.isLowerCase(notation) ? -1 : 1;

        for (int i = 1; i < 3; i++) {
            if(Pawn.canMove(notation, curX, curY, curX, curY + direction * i, board)){
                int nextY = curY + direction * i;
                returnList.add(new NextMove(curX, curY, curX, nextY));
            }
        }
        for (int i = -1; i < 2; i += 2) {
            if(Pawn.canMove(notation, curX, curY, curX + i, curY + direction, board)){
                returnList.add(new NextMove(curX, curY, curX + i, curY + direction));
            }
        }
        return returnList;
    }

    private double calculateBoardValue(char[][] board){
        double totalPoints = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != '-'){
                    double multiplier = !Character.isLowerCase(board[i][j]) == isWhite ? 1 : -1;
                    totalPoints += multiplier * getValueOfNotation(board[i][j]);
                    totalPoints += multiplier * getValueOfSquare(i, j);
                }
            }
        }
        return totalPoints;
    }

    public void move(char[][] board){
        board[bestMove.nextX][bestMove.nextY] = board[bestMove.currentX][bestMove.currentY];
        board[bestMove.currentX][bestMove.currentY] = '-';
    }

    private void move(NextMove nm, char[][] board){
        board[nm.nextX][nm.nextY] = board[nm.currentX][nm.currentY];
        board[nm.currentX][nm.currentY] = '-';
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
    private double getValueOfNotation(char not){
        switch (Character.toLowerCase(not)){
            case 'k':
                return 100;
            case 'q':
                return 9;
            case 'r':
                return 5;
            case 'b':
            case 'n':
                return 3;
            case 'p':
                return 1;
            default:
                return 0;
        }
    }


    private char[][] copyBoard(char[][] toCopy){
        char[][] returnValue = new char[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(toCopy[i], 0, returnValue[i], 0, 8);
        }
        return returnValue;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Thread getT() {
        return t;
    }
}
