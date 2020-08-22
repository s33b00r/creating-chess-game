package model.chessgame;

import pathhandling.PiecePathsHandler;

import java.util.List;
import java.util.Map;

public interface IViewItems {
    Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths);
    List<PieceObjectData> getAllPiecesData();
    PieceObjectData getActivePieceData();
}
