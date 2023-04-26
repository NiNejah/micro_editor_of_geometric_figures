package xshape;

public class UIBuilder {
    private ElementFactory factory;
    private Toolbar toolbarH;
    private Toolbar toolbarV;



    public UIBuilder(ElementFactory factory){
        this.factory = factory;
    }

    public Object build(){
        this.toolbarH = factory.createToolbar(0, 0, 50,600 , ToolbarStyle.HORIZONTAL);
        this.toolbarV = factory.createToolbar(0, 32, 550,50, ToolbarStyle.VERTICAL);
        return factory.createUI(toolbarH, toolbarV);
    }

}
