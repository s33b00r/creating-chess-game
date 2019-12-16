package Backgrounds;

import javax.swing.*;
import java.awt.*;

public class StandardBackground extends JPanel {

    private int width;
    private int height;
    private int marginLeftRight;
    private int marginUpDown;

    public StandardBackground(int width, int height, int marginLeftRight, int marginUpDown){
        this.width = width;
        this.height = height;
        this.marginLeftRight = marginLeftRight;
        this.marginUpDown = marginUpDown;

        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int sWidth = (width - marginLeftRight * 2) / 8;
        int sHeight = (height - marginUpDown * 2) / 8;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color curColor = (i + j) % 2 == 0? Color.WHITE : Color.BLACK;
                g.setColor(curColor);
                g.fillRect(marginLeftRight + sWidth * i, marginUpDown + sHeight * j, sWidth, sHeight);
            }
        }
    }
}
