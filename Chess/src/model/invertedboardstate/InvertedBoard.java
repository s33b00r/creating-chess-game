package model.invertedboardstate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InvertedBoard implements InvertedBoardState{
    @Override
    public List<Point> getPos(List<Point> points) {
        List<Point> returnList = new ArrayList<>();
        for(Point p : points){
            returnList.add(new Point(p.x, 7 - p.y));
        }
        return returnList;
    }

    @Override
    public Point getMouseBoardPos(Point point) {
        return new Point(point.x, 7 - point.y);
    }

    @Override
    public InvertedBoardState invertState() {
        return new NormalBoard();
    }
}
