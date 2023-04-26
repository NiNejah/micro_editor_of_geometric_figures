package xshape;

public class UIBuilder {
    private ElementFactory factory;
    private Toolbar toolbarH;
    private Toolbar toolbarV;



    public UIBuilder(ElementFactory factory){
        this.factory = factory;
    }

    public Object build(){
        this.toolbarH = factory.createToolbar(0, 0, 50,500 , ToolbarStyle.HORIZONTAL);
        this.toolbarV = factory.createToolbar(0, 0, 0,100, ToolbarStyle.VERTICAL);
        return factory.createUI(toolbarH, toolbarV);
    }

}
