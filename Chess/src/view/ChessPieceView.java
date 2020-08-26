package view;

import model.chessgame.IViewItemHandler;
import model.chessgame.PieceObjectData;
import observerinterfaces.IMousePositionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ChessPieceView extends JPanel {

    private int boardWidth;
    private int boardHeight;
    private IMousePositionListener mouseHandler;
    private IImageHandler imageHandler;
    private IViewItemHandler itemHandler;

    public ChessPieceView(IMousePositionListener mouseHandler, IImageHandler imageHandler,
                          IViewItemHandler itemHandler, int boardWidth, int boardHeight) {
        this.itemHandler = itemHandler;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.mouseHandler = mouseHandler;
        this.imageHandler = imageHandler;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<PieceObjectData> allPiecesData = itemHandler.getAllPiecesData();
        PieceObjectData activePiece = itemHandler.getActivePieceData();

        int pWidth = boardWidth / 8;
        int pHeight = boardHeight / 8;


        for(PieceObjectData data : allPiecesData){
            if(activePiece != null && data.piece.equals(activePiece.piece))
                continue; //Skips drawing out the active piece until later
            BufferedImage image = imageHandler.getImage(data.piece.getClass(), data.isWhite);
            g.drawImage(image, pWidth * data.pos.x, pHeight * data.pos.y, pWidth, pHeight, null);
        }

        //Draws out the active piece if there is one
        if(activePiece != null){
            BufferedImage img = imageHandler.getImage(activePiece.piece.getClass(), activePiece.isWhite);
            g.drawImage(img, mouseHandler.getLocalMousePosition().x - pWidth / 2,
                    mouseHandler.getLocalMousePosition().y - pHeight,
                    pWidth, pHeight, null);
        }
    }


}
