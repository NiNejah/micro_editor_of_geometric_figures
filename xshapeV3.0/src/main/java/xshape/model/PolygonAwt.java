package xshape.model;

import java.awt.geom.Point2D;

public class PolygonAwt extends Polygon{
    public PolygonAwt(int sides, double length, Point2D.Double pos, double rot, double trX, double trY, double red, double green, double blue) {
        super(sides, length, pos, rot, trX, trY, red, green, blue);
    }

    public PolygonAwt(Polygon p) {
        super(p);
    }

    public Shape clone(){
        return new PolygonAwt(this);
    }
}
