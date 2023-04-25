package xshape.model;

import java.awt.geom.Point2D;
import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class RectangleFx extends Rectangle implements Serializable {

	javafx.scene.shape.Rectangle _adapted = new javafx.scene.shape.Rectangle();

	public RectangleFx(double width, double height, double arcWidth, double arcHeight,
					   Point2D.Double pos, double rot, double red, double green, double blue) {
		super(width, height, arcWidth, arcHeight, pos, rot, red, green, blue);
	}

	public RectangleFx(Rectangle r){
		super(r);
	}

	@Override
	public Object draw() {
		Point2D pos = position();
		_adapted.setX(pos.getX()- width/2);
		_adapted.setY(pos.getY()- height/2);
		_adapted.setWidth(width);
		_adapted.setHeight(height);
		_adapted.setFill(Color.color(colorR, colorG, colorB));
		_adapted.getTransforms().clear();
		_adapted.getTransforms().add(new Rotate(rotation(), rotationCenter().getX(), rotationCenter().getY()));
		return _adapted;
	}

	@Override
	public Shape clone() {
		return new RectangleFx(this);
	}

	@Override
	public void update(){
		_adapted.setX(position().getX()- width/2);
		_adapted.setY(position().getY()- height/2);
		_adapted.setWidth(width);
		_adapted.setHeight(height);
		_adapted.setFill(Color.color(colorR, colorG, colorB));
		_adapted.getTransforms().clear();
		_adapted.getTransforms().add(new Rotate(rotation(), rotationCenter().getX(), rotationCenter().getY()));
	}

}
