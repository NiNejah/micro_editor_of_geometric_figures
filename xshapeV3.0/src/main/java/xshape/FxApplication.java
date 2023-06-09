package xshape;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApplication extends Application {
    public static Group _root = new Group ();

    private FxApp fxapp;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("XShape JavaFx Rendering");
        Scene _scene = new Scene(_root, 500, 500);
        primaryStage.setScene(_scene);
        primaryStage.show();
        fxapp = new FxApp(primaryStage);
        fxapp.run();
    }

    public static void main(String[] args) {
        launch();
    }
}