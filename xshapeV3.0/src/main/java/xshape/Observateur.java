package xshape;

import xshape.model.RectangleAwt;

import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Observateur implements MouseListener {

    private List<Element> _elements = new ArrayList<>();
    static private Observateur _singleton = null;
    private JPanel _currentRect = null; // New variable to store the current RectangleAwt
    public Observateur Observateur(){
        if (_singleton == null){
            _singleton = new Observateur();
        }
        return _singleton ;
    }
    public void add(Element e){
        _elements.add(e);
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // if a RectangleAwt  pressed !
        // Store the current RectangleAwt object
        Object source = mouseEvent.getSource();
        System.out.println("1111");
        if (source instanceof JPanel) {
            System.out.println("hello");
            _currentRect = (JPanel) source;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Object source = mouseEvent.getSource();
        // i want to change RectangleAwt here !
        if (_currentRect != null) {
            // Do something with the current RectangleAwt
//            _currentRect.rotation(45.0);
            // Reset the current RectangleAwt
            _currentRect.setBounds(mouseEvent.getX(),mouseEvent.getY(),_currentRect.getPreferredSize().width,_currentRect.getPreferredSize().height);
            _currentRect = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
