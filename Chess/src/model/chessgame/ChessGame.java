package model.chessgame;


import model.invertedboardstate.InvertedBoard;
import model.invertedboardstate.InvertedBoardState;
import observerinterfaces.IRedrawable;
import pathhandling.PiecePathsHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ChessGame implements IChessGame, IViewPathHandler, IViewItemHandler, IPromotionHandler {

    private Board board;
    private IRedrawable redrawable;
    private boolean hasCheckForEndOfGame = true;

    private boolean whitesTurn = true;

    private InvertedBoardState state = new InvertedBoard();
    private boolean lastUpdate = false;

    private int width;
    private int height;
    private int xPos;
    private int yPos;

    ChessGame(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;

        board = new Board(this);
        board.setup();

        start();
    }

    @Override
    public void addRedrawableObserver(IRedrawable redrawable) {
        this.redrawable = redrawable;
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

    private void onMouseClick(Point boardPos) {
        if (board.hasActivePiece()) {
            if (board.canMoveActivePiece(boardPos)) {
                board.removePieceAt(boardPos);
                board.placeActivePiece(boardPos);
                whitesTurn = !whitesTurn;
                hasCheckForEndOfGame = false;
            } else {
                board.clearActivePiece();
            }
        } else {
            if (board.correctColorPiece(boardPos, whitesTurn)) {
                board.setActivePiece(boardPos);
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

    /**
     * Visual update method that call for update on the visual if there is an active piece
     * Also checks if game has ended once every move
     */
    private void update() {
        if (/*(board.hasActivePiece() || !lastUpdate) && */ redrawable != null) { //TODO: maybe make so only update when necessary
            redrawable.repaint();
            lastUpdate = !board.hasActivePiece();
        }
        if (!hasCheckForEndOfGame) {
            board.checkForEndOfGame(whitesTurn);
            if (board.getStatus() != GameStatus.RUNNING) {
                System.out.println("Game has ended!");
                System.out.println(board.getStatus());
            }
            hasCheckForEndOfGame = true;
        }
    }

    public void invertBoard() {
        state = state.invertState();
    }


    @Override
    public Object getPromotionPiece(boolean isWhite) {
        redrawable.showPromotionUI(true);
        Object pieceTest = redrawable.getClickedOnPiece();
        if (pieceTest != null) {
            redrawable.showPromotionUI(false);
        }
        return pieceTest;
    }

    @Override
    public void clicked(int x, int y) {

        x = (x - xPos) * 8 / width;
        y = (y - yPos) * 8 / height;

        Point rightPos = state.getPos(new Point(x, y));

        Runnable runnable = () -> onMouseClick(rightPos);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
