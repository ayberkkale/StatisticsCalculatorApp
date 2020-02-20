//Mustafa Ayberk Kale eexmk22 

package mystatisticscalculatorapp;

import javafx.scene.canvas.GraphicsContext;


public abstract class MyStatisticsCalculatorAppShapes {
    
    // enum for different type of shapes 
    public static enum shapeStyle{LineShape,RectangleShape,CircleShape};
    
    private int id; // to tag the shape 
    
    // get and sets
    public int getID(){return id;}
    public final void setID(int _id){ 
        id = _id;
    }
    
    
    // constructor
    public MyStatisticsCalculatorAppShapes(int _id){
        setID(_id);
    }
    
    
    // abstract method 
    // YOU CAN'T IMPLEMENT THIS METHOD HERE
    // IT MUST BE OVERRIDE BY CHILD
 
    public abstract void draw(GraphicsContext gc); // to draw the shape

}
