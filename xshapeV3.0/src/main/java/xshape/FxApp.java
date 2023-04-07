package xshape;

public class FxApp extends XShape {

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(FxApplication._root);
    }
    @Override
    void run() {
        draw();
        FxApplication.launch(FxApplication.class);
    }
}