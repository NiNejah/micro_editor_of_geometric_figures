package xshape;

import javafx.stage.Stage;

public class FxApp extends XShape {

    private Stage stage;

    public FxApp(Stage stage){
        this.stage = stage;
    }
    @Override
    protected ElementFactory createFactory() {
        return new ElementFactoryFx(FxApplication._root);
    }
    @Override
    void run(){
        //FxApplication.launch(FxApplication.class);
        draw();
        System.out.println("test");
    }
}