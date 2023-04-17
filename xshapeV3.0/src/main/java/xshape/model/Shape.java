package xshape.model;

import xshape.Element;

import java.awt.geom.Point2D;
import java.util.List;

public interface Shape extends Element {
	Shape translate(Point2D vec);
	double rotation();
	void rotation(double angle);
	Point2D rotationCenter();
	void rotationCenter(Point2D.Double centerOfRotation);

	Point2D offset(Point2D pos);

	void setPosition(Point2D.Double position);

	public Shape clone();

	public void update();

	public double[] getRGB();

	public void setColor(double r, double g, double b);
	public List<String> editableParameters();
}
