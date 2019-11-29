package view;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

public abstract class Assets {

    private final String PIECE_TEXTURES = "file:Chess/assets/chessPieceImages/";
    private Map<String, Image> pieceTextures = new HashMap<>();

    protected GridPane background;

    protected void bind(String objectName, boolean isWhite, String objectImage){
        Image i = getImage(objectImage);
        pieceTextures.put(objectName + isWhite, i);
    }

    public Image get(Object o, boolean isWhite){
        return pieceTextures.get(o.getClass().getSimpleName() + isWhite);
    }

    private Image getImage(String fileName){
        return new Image(PIECE_TEXTURES + fileName);
    }
}
