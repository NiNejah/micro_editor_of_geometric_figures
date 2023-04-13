package xshape;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import xshape.model.Rectangle;
import xshape.model.ShapeFactory;

public class ToolbarFx extends ElementAbstract implements xshape.Toolbar{

    public Rectangle rectangle;

    private ShapeFactory factory;

    public ToolbarFx(ShapeFactory fx){
        this.factory = fx;
    }
    @Override
    public Object draw() {
        ToolBar toolbar = new ToolBar();
        toolbar.setOrientation(Orientation.VERTICAL);
        rectangle = factory.createRectangle(0, 0, 50, 50);
        rectangle.setColor(0, 0, 255);
        javafx.scene.shape.Rectangle node = (javafx.scene.shape.Rectangle) rectangle.draw();
        toolbar.getItems().add(node);
        return toolbar;
    }

}
