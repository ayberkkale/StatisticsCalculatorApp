//Mustafa Ayberk Kale eexmk22 


package mystatisticscalculatorapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class RectangleShape extends TwoDimensionalShape {
    
    private double canvasXoffset =110;
    
    private double canvasYoffset =35;
    

    /**
     * 
     * @param userInputs
     * Rectangle class draws the bins with an offset to 
     * canvas to build a graph, using setFill and fillRect function of Graphics context. 
     */
    
    // constructor
    public RectangleShape(double[] userInputs){
        
        // the input must be ordered the following way
        super(userInputs);
        
    }
        // 
    @Override
    public void draw(GraphicsContext gc){
        
        // because user provide the centre ,convert to canvas of graph
        double x1Temp = super.getx1() +canvasXoffset;
        
        double y1Temp = (gc.getCanvas().getHeight()) - super.gety1()-canvasYoffset -super.getHeight();
   
        
        //
        
            gc.setFill(Color.rgb(super.getColorRed(), 
                                 super.getColorGreen(), 
                                 super.getColorBlue(), 
                                 super.getColorAlpha()));
            
            
           
            gc.fillRect(x1Temp, y1Temp, super.getWidth()*8/10, super.getHeight());
            
           
        
    }

    

}
