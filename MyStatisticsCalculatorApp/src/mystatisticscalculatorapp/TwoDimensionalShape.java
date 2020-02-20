//Mustafa Ayberk Kale eexmk22 

package mystatisticscalculatorapp;

/**
 * * abstract shape class to declare the super parameters 
 */
public abstract class TwoDimensionalShape extends MyStatisticsCalculatorAppShapes {
    
    private double x1,y1,height,width; // x1,y1 is the centre
    private int colorRed, colorGreen, colorBlue; 
    private double colorAlpha;
    private boolean isFilled;
    
    
    // get set 
    public double getx1(){
        return x1;
    }
    public double gety1(){
        return y1;
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
    
    public int getColorRed(){
        return colorRed;
    }
    public int getColorGreen(){
        return colorGreen;
    }
    public int getColorBlue(){
        return colorBlue;
    }
    public double getColorAlpha(){
        return colorAlpha;
    }
    
    public boolean getisFilled(){
        return isFilled;
    }
    
    
    
    // constructor
    public TwoDimensionalShape(double[] userInputs){
        
        // the input must be ordered the following way
        super((int)userInputs[0]);

        x1 = userInputs[1];
        y1 = userInputs[2];
        width = userInputs[3];
        height = userInputs[4];
        
        isFilled = false;
        
        // if user input the colour 
        if(userInputs.length == 9){
            // the input must be ordered the following way
            
            isFilled = true;
            colorRed = (int) userInputs[5]; 
            colorGreen = (int) userInputs[6];
            colorBlue = (int) userInputs[7];
            colorAlpha  = (double) userInputs[8];
            
        }
    }
    
    
    
    /*
    Remember this class inherit the 
    draw(gc) from MyStatisticsCalculatorAppShapes
    */
    
    
    
    
    
    
}
