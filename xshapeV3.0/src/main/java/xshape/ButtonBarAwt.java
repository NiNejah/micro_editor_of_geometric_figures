package xshape;

import xshape.model.RectangleAwt;
import xshape.model.Shape;
import xshape.model.ShapeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ButtonBarAwt extends RectangleAwt implements xshape.Toolbar, ActionListener {
    private List<ButtonAwt> Buttons = new ArrayList<>();

    public ButtonBarAwt(double width, double height, Point2D.Double pos, ShapeFactory shapeFactory) {
        super(width, height, 0, 0, pos, 0, 120, 100, 125);
    }

    public void addButton(ButtonAwt b) {
        b.get_adapted().addActionListener(this);
        Buttons.add(b);
    }

    @Override
    public Object draw() {
        JPanel jp = (JPanel) super.draw();
        for (ButtonAwt shape : Buttons) {
            jp.add((Component) shape.draw());
        }
        return jp;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == Buttons.get(0).get_adapted()) {
            System.out.println("save");
        } else if (actionEvent.getSource() == Buttons.get(1).get_adapted()) {
            System.out.println("redo");
        } else if (actionEvent.getSource() == Buttons.get(2).get_adapted()) {
            System.out.println("undo");
        }
    }
}
