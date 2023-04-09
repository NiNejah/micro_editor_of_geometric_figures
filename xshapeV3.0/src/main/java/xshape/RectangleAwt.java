package xshape;

import java.awt.geom.Point2D;
import java.awt.*;

public class RectangleAwt extends Rectangle {
	private double _rotation;
	private Point2D _centerOfRotation = new Point2D.Double(position().getX() + (size().getX()/2), position().getY() + (size().getY()/2));
	public RectangleAwt(double posX, double posY, double height, double width) {
		super.position(new Point2D.Double(posX, posY));
		super.size(new Point2D.Double(width, height));
	}

	@Override
	public void draw() {
        Graphics g = AwtContext.instance().graphics();
        Color c = g.getColor();
		Point2D pos = position();
		Point2D size = size();
        g.setColor(Color.BLUE);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        g.setColor(c);
	}
	@Override
	public double rotation(){return _rotation;};
	@Override
	public void rotation(double angle){_rotation = angle;};
	public Point2D rotationCenter(){return _centerOfRotation;}
	public void rotationCenter(Point2D centerOfRotation){ _centerOfRotation = centerOfRotation;}
}
