package xshape;

import java.awt.geom.Point2D;

public abstract class ElementAbstract implements Element {
    private Point2D _pos  = new Point2D.Double(0, 0);
    private Point2D _size = new Point2D.Double(1, 1);
    @Override
    public Point2D size() {
        return (Point2D) _size.clone();
    }

    @Override
    public Element size(Point2D vec) {
        _size = (Point2D) vec.clone();
        return this;
    }

    @Override
    public Point2D position() {
        return (Point2D) _pos.clone();
    }

    @Override
    public Element position(Point2D position) {
        _pos = (Point2D) position.clone();
        return this;
    }

}
