package AI;

import model.ChessPieces.*;

import java.util.ArrayList;
import java.util.List;

class MiniMaxAI {

    private boolean isWhite;
    private int maxSteps;



    private String threadName = "calculateMoves";
    private Thread t;

    public MiniMaxAI(boolean isWhite, int maxSteps){
        this.isWhite = isWhite;
        this.maxSteps = maxSteps;
    }



}
