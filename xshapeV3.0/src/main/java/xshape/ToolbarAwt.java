package xshape;

import xshape.model.Rectangle;
import xshape.model.ShapeFactory;

import javax.swing.*;

public class ToolbarAwt extends ElementAbstract implements xshape.Toolbar{

    public Rectangle rectangle;

    private ToolbarStyle style;

    private ShapeFactory factory;

    public ToolbarAwt(ShapeFactory fx, ToolbarStyle style){
        this.factory = fx;
        this.style = style;
    }
    @Override
    public Object draw() {
        return null;
    }
}
