package model.invertedboardstate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NormalBoard implements InvertedBoardState{
    @Override
    public List<Point> getPos(List<Point> points) {
        List<Point> returnList = new ArrayList<>();
        for(Point p : points){
            returnList.add(new Point(p.x, p.y));
        }
        return returnList;
    }

    @Override
    public Point getPos(Point point) {
        return new Point(point.x, point.y);
    }

    @Override
    public InvertedBoardState invertState() {
        return new InvertedBoard();
    }
}
