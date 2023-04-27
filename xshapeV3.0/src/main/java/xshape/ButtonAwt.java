package xshape;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import java.net.URL;
public class ButtonAwt extends ElementAbstract implements xshape.Button {
    private JButton _adapted ;
    private String _label  ;
    private String _icon ;
    public ButtonAwt (double posX, double posY, double height, double width ,String label,String icon ) {
        super.position(new Point2D.Double(posX, posY));
        super.size(new Point2D.Double(width, height));
        label(label);
        icon(icon);
        _adapted = new JButton();
    }
    @Override
    public void label(String label) {
        _label = label ;
//        _adapted.setText(_label); // Update the text of the JButton
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
        //String resourcePath = "/images/" + icon;
        String resourcePath = icon;
        URL resourceUrl = getClass().getResource(resourcePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        ImageIcon imageIcon = new ImageIcon(resourceUrl);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance((int) size().getX(), (int) size().getY(),Image.SCALE_SMOOTH));
        _adapted.setIcon(imageIcon);
    }

    public JButton get_adapted() {
        return _adapted;
    }

    @Override
    public Object draw() {
        Point2D pos = position();
        Point2D size = size();
        addIcon(icon());
        _adapted.setBounds((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY());
        _adapted.setForeground(Color.cyan);
        _adapted.setBackground(Color.lightGray);
        _adapted.setBorder(BorderFactory.createEtchedBorder());
        return _adapted;
    }
}
