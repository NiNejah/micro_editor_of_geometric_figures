package xshape.model;

import xshape.AwtContext;
import xshape.model.Rectangle;

import java.awt.geom.Point2D;
import java.awt.*;

public class RectangleAwt extends Rectangle {
	private double _rotation;
	private Point2D _centerOfRotation = new Point2D.Double(position().getX() + (size().getX()/2), position().getY() + (size().getY()/2));
	public RectangleAwt(double width, double height, double arcWidth, double arcHeight,
						Point2D.Double pos, double rot, int red, int green, int blue) {
		super(width, height, arcWidth, arcHeight, pos, rot, red, green, blue);
	}

	@Override
	public Object draw() {
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
		// TODO
		return null;
	}
	@Override
	public double rotation(){return _rotation;};
	@Override
	public void rotation(double angle){_rotation = angle;};
	public Point2D rotationCenter(){return _centerOfRotation;}
	public void rotationCenter(Point2D centerOfRotation){ _centerOfRotation = centerOfRotation;}
}
