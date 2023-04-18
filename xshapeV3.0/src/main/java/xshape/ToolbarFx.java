package xshape;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import xshape.model.Rectangle;
import xshape.model.ShapeFactory;

import java.io.File;
import java.io.InputStream;

public class ToolbarFx extends ElementAbstract implements xshape.Toolbar{

    public Rectangle rectangle;

    private ToolbarStyle style;

    private ShapeFactory factory;

    public ToolbarFx(ShapeFactory fx, ToolbarStyle style){
        this.factory = fx;
        this.style = style;
    }
    @Override
    public Object draw() {
        if(style == ToolbarStyle.VERTICAL){
            ToolBar toolbar = new ToolBar();
            toolbar.setOrientation(Orientation.VERTICAL);
            return toolbar;
        } else {
            ToolBar toolbar = new ToolBar();
            toolbar.setOrientation(Orientation.HORIZONTAL);
//            ImageView iv = new ImageView();
//            File binFile = new File(getClass().getClassLoader().getResource("bin.png").getFile());
//            Image bin = new Image(binFile.getAbsolutePath());
//            iv.setImage(bin);
//            iv.setFitWidth(40);
//            iv.setFitHeight(40);
//            iv.setId("bin");
//            toolbar.getItems().add(iv);
            return toolbar;
        }
    }

}
