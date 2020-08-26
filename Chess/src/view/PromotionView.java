package view;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class PromotionView extends JPanel {

    private boolean isVisible = false;
    private IImageHandler imageHandler;
    private int offsetX = 200;
    private int offsetY = 300;
    private List<Object> piecesToShow;

    PromotionView(IImageHandler imageHandler, List<Object> piecesToShow) {
        this.imageHandler = imageHandler;
        this.piecesToShow = piecesToShow;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isVisible) {
            return;
        }

        //TODO: make size more "smart"
        int width = getWidth() - offsetX * 2;
        int height = getHeight() - offsetY * 2;
        int x = offsetX;
        int y = offsetY;
        int size = width / 4;

        g.setColor(Color.gray);
        g.fillRect(x, y, width, height);

        for (int i = 0; i < piecesToShow.size(); i++) {
            BufferedImage image = imageHandler.getImage(piecesToShow.get(i), true);
            g.drawImage(image, x + i * size, y + 30, size, size, null);
        }
    }

    public void repaint(boolean show) {
        this.isVisible = show;
        super.repaint();
    }
}
