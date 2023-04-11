package xshape;

public interface Button extends Element {
    void label(String label);
    String label();
    void icon(String icon);
    String icon();
    void addIcon(String icon);
}
