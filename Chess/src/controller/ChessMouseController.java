package controller;


import observerinterfaces.IMouseClickListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessMouseController implements MouseListener {

    private List<IMouseClickListener> mouseClickListeners = new ArrayList<>();

    private int yOffset = 25;

    public ChessMouseController() {

    }

    public ChessMouseController(int yOffset) {
        this.yOffset = yOffset;
    }

    public void addListeners(IMouseClickListener... listeners) {
        mouseClickListeners.addAll(Arrays.asList(listeners));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (IMouseClickListener listener : mouseClickListeners) {
            listener.clicked(e.getX(), e.getY() - 25);
        }
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
