package xshape;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxApplication extends Application {
    public static Group _root = new Group ();

    public static BorderPane pane = new BorderPane();

    private FxApp fxapp;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("XShape JavaFx Rendering");
        Scene _scene = new Scene(pane, 500, 500);
        pane.setCenter(_root);
        _root.setManaged(false);
        primaryStage.setScene(_scene);
        primaryStage.show();
        fxapp = new FxApp(primaryStage);
        fxapp.run();
    }

    public static void main(String[] args) {
        launch();
    }
}