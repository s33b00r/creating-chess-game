package model.invertedboardstate;


import java.awt.Point;
import java.util.List;

public interface InvertedBoardState {
    List<Point> getPos(List<Point> points);
    Point getPos(Point point);
    InvertedBoardState invertState();
}
