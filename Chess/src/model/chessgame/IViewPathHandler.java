package model.chessgame;

import pathhandling.PiecePathsHandler;

import java.util.Map;

public interface IViewPathHandler {
    Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths);
}
