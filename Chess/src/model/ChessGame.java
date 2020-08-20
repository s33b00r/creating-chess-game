package model;


import model.invertedboardstate.InvertedBoard;
import model.invertedboardstate.InvertedBoardState;
import observerinterfaces.IMousePositionListener;
import observerinterfaces.IRedrawable;
import pathhandling.PiecePathsHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ChessGame implements IChessGame, IViewItems {

    private Board board;
    private List<IRedrawable> redrawableList = new ArrayList<>();

    private boolean whitesTurn = true;

    private InvertedBoardState state = new InvertedBoard();
    private boolean lastUpdate = false;

    ChessGame(IMousePositionListener mousePositionListener){
        board = new Board();
        board.setup();

        start();
    }

    @Override
    public void addRedrawableObserver(IRedrawable redrawable){
        redrawableList.add(redrawable);
    }

    @Override
    public Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths) {
        return board.createMap(paths);
    }

    @Override
    public List<PieceObjectData> getAllPiecesData() {
        List<PieceObjectData> returnList = new ArrayList<>();
        for(PieceObjectData data : board.getAllPiecesData()){
            returnList.add(new PieceObjectData(data.piece, state.getPos(data.pos), data.isWhite));
        }
        return returnList;
    }

    @Override
    public PieceObjectData getActivePieceData() {
        return board.getActivePieceData();
    }

    @Override
    public void mouseClick(int xPos, int yPos) {

        Point rightPos = state.getPos(new Point(xPos, yPos));

        if(board.hasActivePiece()){
            if (board.canMoveActivePiece(rightPos)) {
                board.removePieceAt(rightPos);
                board.placeActivePiece(rightPos);
                whitesTurn = !whitesTurn;
            } else {
                board.removeActivePiece();
            }
        }else{
            if(board.correctColorPiece(rightPos, whitesTurn)){
                board.setActivePiece(rightPos);
            }
        }
    }

    //Starts a new main loop thread separated from the Application
    private void start(){
        Runnable runnable = () -> {
            while (true){
                try {
                    update();
                    Thread.sleep(1000 / 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void update(){
        if(board.hasActivePiece() || !lastUpdate){
            for (IRedrawable iRedrawable : redrawableList) {
                iRedrawable.repaint();
            }
            lastUpdate = !board.hasActivePiece();
        }
    }

    public void invertBoard() {
        state = state.invertState();
    }


}
