//Mustafa Ayberk Kale eexmk22 

/*
 * Container for shapes of MyStatisticsCalculatorApp
 */
package mystatisticscalculatorapp;


import java.util.ArrayList; // import the ArrayList class
import javafx.scene.canvas.GraphicsContext;


/**
 * 
 * @author ayberkkale
 */
public class MyStatisticsCalculatorAppShapeContainer {
    
    // a static member which will contain all the member
    // use of abstract class MyStatisticsCalculatorAppShapes makes this possible
    private ArrayList<MyStatisticsCalculatorAppShapes> myShapeList;
    
    // to get the number of shape stored in the container
    public int getNoShapes(){
        return myShapeList.size() ;
    }

    
    // to clear the container as myShapeList is private
    public void clearContainer(){
        myShapeList.clear();
    }
    
    // to remove the last shape -- for undo button
    public void removeLastShape(){
        myShapeList.remove(getNoShapes()-1);
    }
    
    // just to wrap the draw method as myShapeList is private
    public void drawWrapper(int indexInContainer, 
                                   GraphicsContext gc){
        myShapeList.get(indexInContainer).draw(gc);
    }
    
    // constructor
    public MyStatisticsCalculatorAppShapeContainer(){
         myShapeList = new ArrayList<>();
    }
    
    /**
     * 
     * @param shapeStyle
     * @param userInputData
     * @param gc 
     */

    // to make, save it in the container and draw a new shape
    public void make(String shapeStyle,
                            String userInputData,
                            GraphicsContext gc)   {
 
        
        // convert UserInputData from string to array
        double[] userInputDataDouble = DrawUtils.readStringInfo(userInputData);
        
         // add the ID to the input array
        double[] userInputDataDoublewithID = new double[userInputDataDouble.length+1];
        
        userInputDataDoublewithID[0] = myShapeList.size() + 1;

        // copy the content of the each array to the new array
        for(int i=0;i<userInputDataDouble.length;i++){
            userInputDataDoublewithID[i+1] = userInputDataDouble[i];
        }
        
        // if line shape
        if(shapeStyle.equals(MyStatisticsCalculatorAppShapes.shapeStyle.LineShape.toString())){
            // use UPCATING to make and draw the new line shape
            myShapeList.add(new LineShape(userInputDataDoublewithID));
            myShapeList.get(myShapeList.size()-1).draw(gc);
        }
        
        // if rectangle shape
        if(shapeStyle.equals(MyStatisticsCalculatorAppShapes.shapeStyle.RectangleShape.toString())){
            // use UPCATING to make and draw the new line shape
            myShapeList.add(new RectangleShape(userInputDataDoublewithID));
            myShapeList.get(myShapeList.size()-1).draw(gc); 
        }
        
     
        // if circle shape
        if(shapeStyle.equals(MyStatisticsCalculatorAppShapes.shapeStyle.CircleShape.toString())){
            // use UPCATING to make and draw the new line shape
            myShapeList.add(new CircleShape(userInputDataDoublewithID));
            myShapeList.get(myShapeList.size()-1).draw(gc); 
        }
        // For future development
        
        
        //Course Work
 
    }
    
}
