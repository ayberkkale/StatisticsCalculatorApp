//Mustafa Ayberk Kale eexmk22 
package mystatisticscalculatorapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class CircleShape extends TwoDimensionalShape {
    
    private double canvasXoffset =110;
    
    private double canvasYoffset =35;
    
    // constructor
    public CircleShape(double[] userInputs){
        
        // user will give 
        // x0;y0;diameter;etc;
        // so it needs to be rearranged
        // so it must be updated first to have the width = heght
        // but we cannoot have any operation before super constructor call 
        // we can bypass this by calling a static method 
        
        // the input must be ordered the following way
        super(updateUserInput(userInputs));
        
    }
    /**
     * 
     * @param gc 
     * 
    *Circle class draws the gaussian fitted pdf 
    * points as a circle  with an offset to canvas to build a graph, 
    * using setFill and fillOval function of Graphics context. 
    * Oval has same height and width to build a circle.

     */
    // 
    @Override
    public void draw(GraphicsContext gc){
        
       
       // because user provide the centre ,convert to canvas of graph
        double x1Temp = super.getx1() +canvasXoffset;
        double y1Temp = (gc.getCanvas().getHeight()) - super.gety1()-canvasYoffset -super.getHeight();
        
      
            gc.setFill(Color.rgb(super.getColorRed(), 
                                 super.getColorGreen(), 
                                 super.getColorBlue(), 
                                 super.getColorAlpha()));
            
            
            
            gc.fillOval(x1Temp, y1Temp, super.getWidth(), super.getHeight());
    
       
        
    }
    
        // to allow the method call it should be static
    private static double[] updateUserInput(double[] userInputs){
        // user will give 
        // x0;y0;diameter;etc;
        // so it needs to be rearranged
        
        double[] updatedUserInput = new double[userInputs.length + 1]; 
        
        updatedUserInput[0] = userInputs[0]; // id
        updatedUserInput[1] = userInputs[1]; // x0
        updatedUserInput[2] = userInputs[2]; // y0
        updatedUserInput[3] = userInputs[3]; // diameter
        updatedUserInput[4] = userInputs[3]; // diamter
        
        if(userInputs.length == 8){
            
            updatedUserInput[5] = userInputs[4]; // red
            updatedUserInput[6] = userInputs[5]; // green
            updatedUserInput[7] = userInputs[6]; // blue
            updatedUserInput[8] = userInputs[7]; // alpha
        }
        
        return updatedUserInput;
    }
    
    

}
