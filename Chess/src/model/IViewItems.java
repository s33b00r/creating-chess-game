package model;

import pathhandling.PiecePathsHandler;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface IViewItems {
    Map<Boolean, Map<Object, String>> createMap(PiecePathsHandler paths);
    List<Object> getObjects();
    List<Point> getPos();
    List<Boolean> getIsWhite();
}
