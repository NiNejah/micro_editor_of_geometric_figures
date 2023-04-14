package xshape;

import javafx.scene.Group;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import xshape.model.Rectangle;
import xshape.model.RectangleFx;
import xshape.model.ShapeFactoryFx;

import java.awt.geom.Point2D;

public class ElementFactoryFx implements ElementFactory {
    public ElementFactoryFx() {
    }

    @Override
    public Element createButton(double posX, double posY, double height, double width, String label , String icon) {
        return new ButtonFx(posX,posY,height,width,label,icon);
    }

    @Override
    public Toolbar createToolbar(double posX, double posY, double height, double width, ToolbarStyle style) {
        return new ToolbarFx(new ShapeFactoryFx(), style);
    }

    public Object createUI(Toolbar toolbarH, Toolbar toolbarV){
        BorderPane pane = new BorderPane();
        pane.setCenter(FxApplication._root);
        pane.setLeft((ToolBar)(toolbarV.draw()));
        pane.setTop((ToolBar)(toolbarH.draw()));
        return pane;
    }

}
