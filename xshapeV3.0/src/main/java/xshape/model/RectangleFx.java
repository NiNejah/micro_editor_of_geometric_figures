package xshape.model;

import java.awt.geom.Point2D;

import javafx.scene.paint.Color;

public class RectangleFx extends Rectangle {

	javafx.scene.shape.Rectangle _adapted = new javafx.scene.shape.Rectangle();

	public RectangleFx(double width, double height, double arcWidth, double arcHeight,
					   Point2D.Double pos, double rot, int red, int green, int blue) {
		super(width, height, arcWidth, arcHeight, pos, rot, red, green, blue);
	}

	public RectangleFx(RectangleFx r){
		super(r);
	}

	@Override
	public Object draw() {
		Point2D pos = position();
		_adapted.setX(pos.getX()- width/2);
		_adapted.setY(pos.getY()- height/2);
		_adapted.setWidth(width);
		_adapted.setHeight(height);
		_adapted.setFill(Color.rgb(colorR, colorG, colorB));
		return _adapted;
	}
	@Override
	public double rotation(){
		return _adapted.getRotate();
	}
	@Override
	public void rotation(double angle){
		_adapted.setRotate(angle);
	}
	public Point2D rotationCenter(){
		// TODO
		return null;
	}
	public void rotationCenter(Point2D centerOfRotation){
		// TODO
	};

	@Override
	public Shape clone() {
		return new RectangleFx(this);
	}


}
