package controller;


import model.IChessGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChessMouseController implements MouseListener, MouseMotionListener {

    IChessGame chessGame;

    private int squareWidth;
    private int squareHeight;
    private int marginLeft;
    private int marginUp;

    public ChessMouseController(IChessGame chessGame, int xStart, int yStart, int xEnd, int yEnd){
        this.chessGame = chessGame;
        squareWidth = (xEnd - xStart) / 8;
        squareHeight = (yEnd - yStart) / 8;
        marginLeft = xStart;
        marginUp = yStart;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (e.getX() - marginLeft) / squareWidth;
        int y = (e.getY() - marginUp) / squareHeight;
        chessGame.mouseClick(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        chessGame.mouseMoved(e.getX(), e.getY());
    }
}
