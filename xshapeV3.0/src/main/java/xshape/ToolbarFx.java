package xshape;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class ToolbarFx extends ElementAbstract implements xshape.Toolbar{

    private ElementFactory factory;

    public ToolbarFx(ElementFactory fx){
        this.factory = fx;
    }
    @Override
    public Object draw() {
        ToolBar toolbar = new ToolBar();
        toolbar.setOrientation(Orientation.VERTICAL);
        Rectangle rectangleFx = factory.createRectangle(0, 0, 50, 50);
        javafx.scene.shape.Rectangle node = (javafx.scene.shape.Rectangle) rectangleFx.draw();
        node.getOnDragEntered()
        toolbar.getItems().add(node);
        return toolbar;
    }


}
