package xshape;

import java.awt.geom.Point2D;

public abstract class Rectangle extends AShape {

    protected double width, height;
    protected double arcWidth, arcHeight;
    public Rectangle(double width, double height, double arcWidth, double arcHeight,
            Point2D.Double pos, double rot, int red, int green, int blue) {
        super(pos, rot, pos, 0, 0, red, green, blue);
        this.width = width; this.height = height;
        this.arcWidth = arcWidth; this.arcHeight = arcHeight;
    }

    public Rectangle(Rectangle r){
        super(r);
        this.width = r.width; this.height = r.height;
        this.arcWidth = r.arcWidth; this.arcHeight = r.arcHeight;
    }
    @Override
    public Shape translate(Point2D vec) {
        position().setLocation(position().getX() + vec.getX(),
                position().getY() + vec.getY());
        return this;
    }
}
