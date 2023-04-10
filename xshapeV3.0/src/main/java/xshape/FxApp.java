package xshape;

public class FxApp extends XShape {

    @Override
    protected ElementFactory createFactory() {
        return new ElementFactoryFx(FxApplication._root);
    }
    @Override
    void run() {
        draw();
        FxApplication.launch(FxApplication.class);
    }
}