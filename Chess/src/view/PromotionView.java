package view;

import javax.swing.*;
import java.awt.*;

public class PromotionView extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(100, 100, 100, 100);
    }
}
