package xshape;

import javafx.scene.Group;
import java.awt.geom.Point2D;
import java.net.URL;
import javafx.scene.image.ImageView;

import javax.swing.ImageIcon;
import javafx.scene.image.Image;


public class ButtonFx extends ElementAbstract implements xshape.Button {
    private javafx.scene.control.Button _adapted = new javafx.scene.control.Button() ;
    private String _label  ;
    private String _icon ;
    Group _grp = null;
    public ButtonFx (double posX, double posY, double height, double width, String label , String icon ,Group grp){
        position(new Point2D.Double(posX, posY));
        size(new Point2D.Double(width, height));
        label(label);
        icon(icon) ;
        _grp = grp;
        addIcon(icon());
        _grp.getChildren().add(_adapted);
    }
    @Override
    public void label(String label) {
        _label = label ;
    }

    @Override
    public String label() {
        return _label;
    }

    @Override
    public void icon(String icon) {
        _icon = icon ;
    }

    @Override
    public String icon() {
        return _icon;
    }

    @Override
    public void addIcon(String icon) {
        String resourcePath = "/images/" + icon;
        URL resourceUrl = getClass().getResource(resourcePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        Image image = new Image(resourceUrl.toExternalForm());
        ImageView imageView = new ImageView(image);
        _adapted.setGraphic(imageView);
    }

    // FIXME
    @Override
    public void draw() {
        Point2D pos = position();
        Point2D size = size();
        _adapted.setLayoutX(pos.getX());
        _adapted.setLayoutY(pos.getY());
//        _adapted.setText(label());
        _adapted.setPrefSize(size.getX(),size.getY());
        //_adapted.setStyle("-fx-background-color: black;");
    }

}
