package xshape;

import javafx.application.Platform;
import javafx.scene.Group;

import java.awt.geom.Point2D;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ButtonFx extends ElementAbstract implements xshape.Button {
    private javafx.scene.control.Button _adapted = new javafx.scene.control.Button() ;
    private String _label  ;
    Group _grp = null;
    public ButtonFx (double posX, double posY, double height, double width, String label ,Group grp){
        position(new Point2D.Double(posX, posY));
        size(new Point2D.Double(width, height));
        label(label);
        _grp = grp;
        _grp.getChildren().add(_adapted);
//        Platform.runLater(() -> {
//            _grp.getChildren().add(_adapted);
//        });
    }
    @Override
    public void label(String label) {
        _label = label ;
    }

    @Override
    public String label() {
        return _label;
    }
    // FIXME
    @Override
    public void draw() {
        Point2D pos = position();
        Point2D size = size();
        _adapted.setLayoutX(pos.getX());
        _adapted.setLayoutY(pos.getY());
        _adapted.setText(label());
        _adapted.setPrefSize(size.getX(),size.getY());
        //_adapted.setStyle("-fx-background-color: black;");
    }

}
