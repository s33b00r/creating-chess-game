package model;


import model.invertedboardstate.InvertedBoard;
import model.invertedboardstate.InvertedBoardState;
import observerinterfaces.IRedrawable;
import pathhandling.PiecePathsHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ChessGame implements IChessGame, IViewItems {

    private Board board;
    private List<IRedrawable> redrawableList = new ArrayList<>();

    private InvertedBoardState state = new InvertedBoard();

    ChessGame(){
        board = new Board();
        board.setup();

        start();
    }

    @Override
    public void addRedrawableObserver(IRedrawable redrawable){
        redrawableList.add(redrawable);
    }

    @Override
    public void mouseMoved(int xPos, int yPos) {
        if(board.hasActivePiece()){
            for (IRedrawable redrawable : redrawableList){
                redrawable.redrawWithActivePiece(board.activePieceGetIsWhite(), new Point(xPos, yPos),
                        board.getActivePieceAsObject());
            }
        }
    }

    @Override
    public Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths) {
        return board.createMap(paths);
    }

    @Override
    public List<Object> getObjects() {
        return board.getPiecesAsObj();
    }

    @Override
    public List<Point> getPos() {
        return state.getPos(board.getPos());
    }

    @Override
    public List<Boolean> getIsWhite() {
        return board.getIsWhiteList();
    }

    @Override
    public void mouseClick(int xPos, int yPos) {

        Point rightPos = state.getMouseBoardPos(new Point(xPos, yPos));

        if(board.hasActivePiece()){
            board.placeActivePiece(rightPos);
        }else{
            board.setActivePiece(rightPos);
        }
    }

    //Starts a new main loop thread separated from the Application
    private void start(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        update();
                        Thread.sleep(1000 / 30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void update(){
        if(board.hasActivePiece()){

        }
    }


    public void invertBoard() {
        state.invertState();
    }


}
