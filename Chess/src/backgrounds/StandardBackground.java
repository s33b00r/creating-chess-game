package backgrounds;

import javax.swing.*;
import java.awt.*;

public class StandardBackground extends JPanel {

    private int width;
    private int height;

    public StandardBackground(int windowXPos, int windowYPos, int width, int height){
        this.width = width;
        this.height = height;

        this.setBounds(windowXPos, windowYPos, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int sWidth = width / 8;
        int sHeight = height / 8;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color curColor = (i + j) % 2 == 0? Color.WHITE : Color.BLACK;
                g.setColor(curColor);
                g.fillRect(sWidth * i, sHeight * j, sWidth, sHeight);
            }
        }
    }
}
