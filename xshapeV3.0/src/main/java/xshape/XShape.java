package xshape;

import javafx.scene.Scene;

import java.awt.geom.Point2D;

public abstract class XShape {
    private ElementFactory _factory = null;
    Element[] _element = null;

    // TODO : remove all of that to a Builder :
    private int BTN_SIZE = 40 ;
    private int BTN_MARGE = 10 ;

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ElementFactory createFactory();
    //Handler to start the GUI
    abstract void run();

    private void createScene() {
        Shape shape = _factory.createRectangle(100, 100, 50, 50);
        Shape shape2 = _factory.createRectangle(250, 250, 75, 20);
        shape.translate(new Point2D.Double(100, 50));
        Element saveBtn =  _factory.createButton(BTN_MARGE,20,BTN_SIZE,BTN_SIZE,"Save","save.png");
        Element doBtn =  _factory.createButton((2*BTN_MARGE)+BTN_SIZE,20,BTN_SIZE,BTN_SIZE,"do","redo.png");
        Element[] tmp = { shape, shape2 ,saveBtn,doBtn};
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
