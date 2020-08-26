package model.chessgame;

import java.util.List;

public interface IViewItemHandler {
    List<PieceObjectData> getAllPiecesData();

    PieceObjectData getActivePieceData();
}
