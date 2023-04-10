package xshape;

import java.awt.geom.Point2D;

public abstract class XShape {
    private ElementFactory _factory = null;
    Element[] _element = null;

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ElementFactory createFactory();
    //Handler to start the GUI
    abstract void run();

    private void createScene() {
        Shape shape = _factory.createRectangle(100, 100, 50, 50);
        Shape shape2 = _factory.createRectangle(250, 250, 75, 20);
        shape.translate(new Point2D.Double(100, 50));
        Shape[] tmp = { shape, shape2 };
        _element = tmp;
    }

    public void draw() {
        if (_element == null) {
            _factory = createFactory();
            createScene();
        }

        for (Element s : _element)
            s.draw();
    }

}
