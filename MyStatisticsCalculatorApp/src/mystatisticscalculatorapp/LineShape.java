//Mustafa Ayberk Kale eexmk22 
package mystatisticscalculatorapp;

import javafx.scene.canvas.GraphicsContext;



public class LineShape extends MyStatisticsCalculatorAppShapes {
    
    private double x1,x2,y1,y2;
    
    private double canvasXoffset =112;
    
    private double canvasYoffset =35;
    
    
    // constructor
    public LineShape(double[] userInputs){
        
        // the input must be ordered the following way
        super((int)userInputs[0]);
        
        x1 = userInputs[1];
        y1 = userInputs[2];
        x2 = userInputs[3];
        y2 = userInputs[4];
    }
    
    // 
    @Override
    public void draw(GraphicsContext gc){
        
       
        double y1Temp = (gc.getCanvas().getHeight()) - y1-canvasYoffset;
        double y2Temp = (gc.getCanvas().getHeight()) - y2-canvasYoffset;
        
        gc.strokeLine(x1+canvasXoffset, y1Temp, x2+canvasXoffset, y2Temp);
    }
    

}
