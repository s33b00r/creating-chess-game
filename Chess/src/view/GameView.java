package view;

import model.chessgame.IViewItemHandler;
import model.chessgame.IViewPathHandler;
import model.chesspieces.actualpieces.Bishop;
import model.chesspieces.actualpieces.Knight;
import model.chesspieces.actualpieces.Queen;
import model.chesspieces.actualpieces.Rook;
import observerinterfaces.IMousePositionListener;
import observerinterfaces.IRedrawable;
import view.backgrounds.StandardBackground;
import view.paths.StandardPiecePaths;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameView extends JLayeredPane implements IRedrawable, IImageHandler {

    private StandardBackground standardBackground;
    private ChessPieceView chessPieceView;
    private PromotionView promotionView;
    private boolean showPromotionView = true;
    private IViewPathHandler pathHandler;

    private Map<Boolean, Map<Object, BufferedImage>> imageMap;


    public GameView(int windowXPos, int windowYPos, int boardWidth, int boardHeight, IViewPathHandler pathHandler,
                    IViewItemHandler itemHandler, IMousePositionListener mouseHandler) {

        this.pathHandler = pathHandler;

        standardBackground = new StandardBackground(windowXPos, windowYPos, boardWidth, boardHeight);
        chessPieceView = new ChessPieceView(mouseHandler, this, itemHandler, boardWidth, boardHeight);

        //TODO: abstract this
        ArrayList<Object> temp = new ArrayList<>();
        temp.add(Queen.class);
        temp.add(Rook.class);
        temp.add(Knight.class);
        temp.add(Bishop.class);

        promotionView = new PromotionView(this, temp);

        chessPieceView.setOpaque(false);
        promotionView.setOpaque(false);

        chessPieceView.setBounds(windowXPos, windowYPos, boardWidth, boardHeight);
        promotionView.setBounds(windowXPos, windowYPos, boardWidth, boardHeight);

        setupImageMap();

        add(standardBackground, 0, 0);
        add(chessPieceView, 1, 0);
        add(promotionView, 2, 0);
    }

    @Override
    public void repaint() {
        super.repaint();
        chessPieceView.repaint();
        promotionView.repaint(showPromotionView);
    }

    @Override
    public void showPromotionUI(boolean show) {
        showPromotionView = show;
    }

    @Override
    public BufferedImage getImage(Object o, boolean isWhite) {
        Map<Object, BufferedImage> colorMap = imageMap.get(isWhite);
        return colorMap.get(o);
    }

    private void setupImageMap() {
        imageMap = new HashMap<>();

        Map<Boolean, Map<Object, String>> piecePathMap = pathHandler.createMap(new StandardPiecePaths());

        Map<Object, BufferedImage> whiteImgMap = new HashMap<>();
        Map<Object, BufferedImage> blackImgMap = new HashMap<>();

        imageMap.put(true, whiteImgMap);
        setupOneColorMaps(piecePathMap, whiteImgMap, true);
        imageMap.put(false, blackImgMap);
        setupOneColorMaps(piecePathMap, blackImgMap, false);
    }

    private void setupOneColorMaps(Map<Boolean, Map<Object, String>> pathMap,
                                   Map<Object, BufferedImage> oneColorMap, boolean isWhite) {
        Map<Object, String> currPathMap = pathMap.get(isWhite);
        currPathMap.forEach((k, v) -> {
            BufferedImage img = getImageFromPath(v);
            oneColorMap.put(k, img);
        });
    }

    private BufferedImage getImageFromPath(String s) {
        try {
            return ImageIO.read(new File(s));
        } catch (IOException e) {
            return null;
        }
    }
}
