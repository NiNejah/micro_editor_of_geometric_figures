package xshape;

import java.awt.geom.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class RectangleFx extends Rectangle {

	javafx.scene.shape.Rectangle _adapted = new javafx.scene.shape.Rectangle();
    Group _grp = null;

	public RectangleFx(double posX, double posY, double height, double width, Group grp) {
		position(new Point2D.Double(posX, posY));
		size(new Point2D.Double(width, height));
		_grp = grp;
		_grp.getChildren().add(_adapted);
	}

	@Override
	public void draw() {
		Point2D pos = position();
		Point2D size = size();
		_adapted.setX(pos.getX()- size.getX()/2);
		_adapted.setY(pos.getY()- size.getY()/2);
		_adapted.setWidth(size.getX());
		_adapted.setHeight(size.getY());
		_adapted.setFill(Color.BLUE);
	}
}
