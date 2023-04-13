package xshape.model;

import xshape.Element;

import java.awt.geom.Point2D;

public interface Shape extends Element {
	Shape translate(Point2D vec);
	double rotation();
	void rotation(double angle);
	Point2D rotationCenter();
	void rotationCenter(Point2D.Double centerOfRotation);

	void setPosition(Point2D.Double position);

	public Shape clone();
}
