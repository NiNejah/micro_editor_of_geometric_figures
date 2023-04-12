package xshape;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxApp extends XShape {

    private Stage stage;

    private ToolBar toolbar;

    public FxApp(Stage stage){
        this.stage = stage;
    }
    @Override
    protected ElementFactory createFactory() {
        return new ElementFactoryFx(FxApplication._root);
    }
    @Override
    void run(){
        createUI();
        draw();
    }

    void createUI(){
        ToolbarFx toolbar = new ToolbarFx(createFactory());
        ToolBar tbFX = (ToolBar) toolbar.draw();
        this.toolbar = tbFX;
        ObservableList<Node> nodes = tbFX.getItems();
        for(Node n: nodes){
            n.getOnMouseDragEntered()
        }
        FxApplication.pane.setLeft(tbFX);
        FxApplication.pane.setCenter(FxApplication._root);
    }

    @Override
    public void draw(){
        if (_element == null) {
            _factory = createFactory();
            createScene();
        }

        for (Element s : _element){
            FxApplication._root.getChildren().add((Node) s.draw());
        }
    }
}