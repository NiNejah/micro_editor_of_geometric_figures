package xshape;

public class UIBuilder {
    private ElementFactory factory;
    private Toolbar toolbarH;
    private Toolbar toolbarV;



    public UIBuilder(ElementFactory factory){
        this.factory = factory;
    }

    public Object build(){
        this.toolbarH = factory.createToolbar(0, 0, 0,0 , ToolbarStyle.HORIZONTAL);
        this.toolbarV = factory.createToolbar(0, 0, 0,0, ToolbarStyle.VERTICAL);
        return factory.createUI(toolbarH, toolbarV);
    }

}
