package Controller;


import model.IChessGame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessMouseController implements MouseListener {

    IChessGame chessGame;

    public ChessMouseController(IChessGame chessGame){
        this.chessGame = chessGame;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        chessGame.mouseClick(e.getX(), e.getY());
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
