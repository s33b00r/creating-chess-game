package view;

import model.chessGame.IViewItems;
import model.chessGame.PieceObjectData;
import observerinterfaces.IMousePositionListener;
import observerinterfaces.IRedrawable;
import view.paths.StandardPiecePaths;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessPieceView extends JPanel implements IRedrawable {

    private IViewItems itemHandler;
    private Map<Boolean, Map<Object, String>> pathMap;
    private Map<Boolean, Map<Object, BufferedImage>> imageMap = new HashMap<>();
    private int boardWidth;
    private int boardHeight;
    private  IMousePositionListener mouseHandler;

    private PromotionView promotionView;
    private boolean showPromotionScreen = false;

    public ChessPieceView(IViewItems itemHandler, IMousePositionListener mouseHandler, int windowXPos, int windowYPos, int boardWidth, int boardHeight){
        this.itemHandler = itemHandler;
        this.pathMap = itemHandler.createMap(new StandardPiecePaths());
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.setBounds(windowXPos, windowYPos, boardWidth, boardHeight);
        this.mouseHandler = mouseHandler;
        promotionView = new PromotionView();
        this.add(promotionView);
        showPromotionScreen(true);
    }

    @Override
    public void showPromotionScreen(boolean show) {
        showPromotionScreen = show;
        promotionView.setVisible(show);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<PieceObjectData> allPiecesData = itemHandler.getAllPiecesData();
        PieceObjectData activePiece = itemHandler.getActivePieceData();

        int pWidth = boardWidth / 8;
        int pHeight = boardHeight / 8;

        if(imageMap.isEmpty()){
            setupImageMap();
        }

        for(PieceObjectData data : allPiecesData){
            if(activePiece != null && data.piece.equals(activePiece.piece))
                continue; //Skips drawing out the active piece until later
            Map<Object, BufferedImage> colorMap = imageMap.get(data.isWhite); //Get the right color of the piece
            BufferedImage image = colorMap.get(data.piece.getClass()); //Gets the right image for the piece
            g.drawImage(image, pWidth * data.pos.x, pHeight * data.pos.y, pWidth, pHeight, null);
        }

        //Draws out the active piece if there is one
        if(activePiece != null){
            Map<Object, BufferedImage> temp = imageMap.get(activePiece.isWhite);
            BufferedImage img = temp.get(activePiece.piece.getClass());
            g.drawImage(img, mouseHandler.getLocalMousePosition().x - pWidth / 2,
                    mouseHandler.getLocalMousePosition().y - pHeight,
                    pWidth, pHeight, null);
        }

        if (showPromotionScreen) {
            promotionView.repaint();
        }

    }

    private void setupImageMap(){
        Map<Object, BufferedImage> whiteImgMap = new HashMap<>();
        Map<Object, BufferedImage> blackImgMap = new HashMap<>();

        imageMap.put(true, whiteImgMap);
        setupOneColorMaps(whiteImgMap, true);
        imageMap.put(false, blackImgMap);
        setupOneColorMaps(blackImgMap, false);
    }

    private void setupOneColorMaps(Map<Object, BufferedImage> oneColorMap, boolean isWhite){
        Map<Object, String> currPathMap = pathMap.get(isWhite);
        currPathMap.forEach((k,v)->{
            BufferedImage img = getImageFromPath(v);
            oneColorMap.put(k, img);
        });
    }

    private BufferedImage getImageFromPath(String path){
        try{
            return ImageIO.read(new File(path));
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
