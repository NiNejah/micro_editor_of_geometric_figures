package xshape;


import javafx.application.Application;
import javafx.stage.Stage;

public class App {

    public static void main(String[] args) {
        XShape appAwt = new AwtApp();
        appAwt.run();
//        FxApplication.main(null);
    }
}
