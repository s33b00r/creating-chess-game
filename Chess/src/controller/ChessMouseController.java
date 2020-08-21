package controller;


import model.chessGame.IChessGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessMouseController implements MouseListener {

    private IChessGame chessGame;

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
        int x = (e.getX() - marginLeft);
        x /= squareWidth;
        int y = (e.getY() - marginUp);
        y /= squareHeight;
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
}
