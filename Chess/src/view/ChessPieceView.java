package view;

import model.IViewItems;
import observerinterfaces.IRedrawable;
import view.paths.StandardPiecePaths;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ChessPieceView extends JPanel implements IRedrawable {

    IViewItems itemHandler;
    private Map<Boolean, Map<Object, String>> pathMap;
    private int boardWidth;
    private int boardHeight;

    public ChessPieceView(IViewItems itemHandler, int windowXPos, int windowYPos, int boardWidth, int boardHeight){
        this.itemHandler = itemHandler;
        pathMap = itemHandler.createMap(new StandardPiecePaths());
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.setBounds(windowXPos, windowYPos, boardWidth, boardHeight);
    }



    @Override
    public void paintComponent(Graphics g) {
        List<Object> currObj = itemHandler.getObjects();
        List<Point> currPos = itemHandler.getPos();
        List<Boolean> isWhite = itemHandler.getIsWhite();
        int pWidth = boardWidth / 8;
        int pHeight = boardHeight / 8;

        for(int i = 0; i < currObj.size(); i++){
            Map<Object, String> currSet = pathMap.get(isWhite.get(i));
            String path = currSet.get(currObj.get(i).getClass());

            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            g.drawImage(image, currPos.get(i).x * pWidth, currPos.get(i).y * pHeight, pWidth, pHeight, this);
        }

        super.paintComponent(g);
    }

    @Override
    public void redrawWithActivePiece(boolean isWhite, Point pos, Object piece) {
        //TODO: Make so that the image works as expected (Not a trail or that the image disappear)
        Graphics g = getGraphics();
        paintComponent(g);

        Map<Object, String> currSet = pathMap.get(isWhite);
        String path = currSet.get(piece.getClass());

        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(path));
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(pos);
        g.drawImage(image, pos.x, pos.y, boardWidth / 7, boardHeight / 7, this);
    }
}
