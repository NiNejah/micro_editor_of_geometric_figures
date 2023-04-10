package xshape;

import java.awt.geom.Point2D;

public interface Element {
    void draw();
    Point2D size();
    Element size(Point2D vec);
    Point2D position();
    Element position(Point2D position);
}
