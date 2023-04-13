package xshape;

import xshape.model.Rectangle;

public interface ElementFactory {
    Element createButton (double posX, double posY, double height, double width,String label,String icon);
    Toolbar createToolbar(double posX, double posY, double height, double width ) ;
}

