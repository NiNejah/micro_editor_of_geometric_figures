package xshape;

import java.awt.geom.Point2D;

public interface Element {
    void draw();
    Point2D size();
    Shape size(Point2D vec);
    Point2D position();
    Shape position(Point2D position);
}
