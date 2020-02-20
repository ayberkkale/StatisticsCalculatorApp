//Mustafa Ayberk Kale eexmk22 

/**
 * This is the 'MyStatisticsCalculatorAppGUI.fxml' Controller Class
 */

package mystatisticscalculatorapp;


import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;







public class MyStatisticsCalculatorAppGUIController {
    
    // =========================================================================
    /*
    private variables needed for our calculator app
    StatisticCalculatorDrawing will serve as container for the My Statistics Calculator App
    
  
    */
    // =========================================================================
    
    
    

    private MyStatisticsCalculatorAppShapeContainer StatisticCalculatorDrawing;
    
    

    
    // =========================================================================
    /*
    Define the alias of our GUI elements
    */
    // =========================================================================

    
    
    @FXML // fx:id="StatisticCalculatorTab"
    private Tab StatisticCalculatorTab; // Value injected by FXMLLoader
    
    @FXML // fx:id="squareRootChoiceButton" //left side radio button 
    private RadioButton squareRootChoiceButton; // Value injected by FXMLLoader
     
    @FXML // fx:id="sturgesButton" //left side radio button 
    private RadioButton sturgesButton; // Value injected by FXMLLoader
     
    @FXML // fx:id="riceButton"//left side radio button 
    private RadioButton riceButton; // Value injected by FXMLLoader
     
    @FXML // fx:id="binMethodToggleGroup" // toggle group for radio buttons
    private ToggleGroup binMethodToggleGroup; // Value injected by FXMLLoader
    
    @FXML // fx:id="canvasStatistics" // canvas for the GUI
    private Canvas canvasStatistics; // Value injected by FXMLLoader
    
    @FXML // fx:id="ChooseFile_SC"
    private Button ChooseFile_SC; // Value injected by FXMLLoader

    @FXML // fx:id="NormaliseandPlot_SC"
    private Button NormaliseandPlot_SC; // Value injected by FXMLLoader

    @FXML // fx:id="SavePlot_SC"
    private Button SavePlot_SC; // Value injected by FXMLLoader

    @FXML // fx:id="ClearAll_SC"
    private Button ClearAll_SC; // Value injected by FXMLLoader
    
    @FXML // fx:id="ValueInfoArea" // raw data info and statistical info box
    private TextArea ValueInfoArea; // Value injected by FXMLLoader
    
    @FXML // fx:id="shapeInfoArea"// gaussian fitting data info and statistical info box
    private TextArea GaussianFittingArea; // Value injected by FXMLLoader
    
    
   

    // =========================================================================
    /*
    This is the constructor of our calculator app... 
    constructor here to initialised to use the member variables of the class
   
    */
    // =========================================================================
    public MyStatisticsCalculatorAppGUIController(){
       
        StatisticCalculatorDrawing =  new MyStatisticsCalculatorAppShapeContainer(); 
       
    }
    
    // =========================================================================
    /*
    The initialize() method is called to initialise our GUI... 
   
    */
    // =========================================================================
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
     
        
        assert StatisticCalculatorTab != null : "fx:id=\"StatisticCalculatorTab\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        assert canvasStatistics != null : "fx:id=\"canvasStatistics\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        assert binMethodToggleGroup != null : "fx:id=\"binMethodToggleGroup\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        
        assert squareRootChoiceButton != null : "fx:id=\"squareRootChoiceButton\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        assert sturgesButton != null : "fx:id=\"sturgesButton\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        assert riceButton != null : "fx:id=\"riceButton\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        
        assert ChooseFile_SC != null : "fx:id=\"ChooseFile_SC\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        assert NormaliseandPlot_SC != null : "fx:id=\"NormaliseandPlot_SC\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        assert SavePlot_SC != null : "fx:id=\"SavePlot_SC\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        assert ClearAll_SC != null : "fx:id=\"ClearAll_SC\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        
        assert ValueInfoArea != null : "fx:id=\"ValueInfoArea\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        assert GaussianFittingArea != null : "fx:id=\"GaussianFittingArea\" was not injected: check your FXML file 'MyStatisticsCalculatorAppGUI.fxml'.";
        
        
        GraphicsContext canvasStatistics_gc = canvasStatistics.getGraphicsContext2D();
        makeBorderOnCanvas(canvasStatistics_gc);
       
        
       // FOR StatCalc bin method set the value of radio button
       
       squareRootChoiceButton.setUserData(0);
     
        sturgesButton.setUserData(1);
     
        riceButton.setUserData(2);
      
        

        
        
        

    }
    
    //==========================================================================
    /**
     * if StatCalc Choose File button   is clicked
     * StatCalcChooseFilebuttonClicked()  function opens the file chooser of the 
     * OS and sends the opened file to 
     * IOOperation class to get the location of the file 
     */
    //==========================================================================
    @FXML
    void StatCalcChooseFilebuttonClicked() throws IOException {
        
        System.out.println("StatCalcChooseFilebuttonClicked (B1)");
    
        FileChooser myfileChooser = new FileChooser();
        myfileChooser.setTitle("Open...");
        
        //
        File openFileDestination = null;
        
        int indexOfExt = 0;
        while (indexOfExt == 0 ){
            
            openFileDestination = myfileChooser.showOpenDialog(new Stage());
            
            
            
            String fileName = openFileDestination.getName();
            indexOfExt = fileName.lastIndexOf('.')+1;
         
            
            if (indexOfExt == 0 ){
                Label addExtString = new Label("FILE HAS NO EXTENSION...");
                
                StackPane secondaryLayout = new  StackPane();
                secondaryLayout.getChildren().add(addExtString);

                Scene secondScene = new Scene(secondaryLayout, 230, 100);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setScene(secondScene);

                newWindow.showAndWait();
            }

        }
        
      
        IOOperation.OpenMeasurementDataFile(openFileDestination);
        
      
        
    }
  
    //==========================================================================
    /**
     * if StatCalc Normalise and Plot button is clicked
     * 
     * StatCalcNormaliseandPlotbuttonClicked() function clears the current canvas 
     * first and  gets the BinChoice toggle value of the radio button 
     * by binMethodToggleGroup.getSelectedToggle().getUserData()
       It calls the IOOperation function to start calculation and plotting

     */
    //==========================================================================
    @FXML
    void StatCalcNormaliseandPlotbuttonClicked() throws IOException {
         // first clear everything
        StatCalcClearAllbuttonClicked(); 
        
        System.out.println("StatCalcNormaliseandPlotbuttonClicked (B2)");
        
        String BinFormat=binMethodToggleGroup.getSelectedToggle().getUserData().toString();
        
         GraphicsContext gc = canvasStatistics.getGraphicsContext2D();
         
        IOOperation.executeOpenMeasurementDataTxtFiletoHistogram(gc,StatisticCalculatorDrawing, BinFormat, ValueInfoArea, GaussianFittingArea);
        
    
        
    }
    
    /*

        //==========================================================================
    /**
     * if StatCalc Save Plot button is clicked
     * @param event
     * @throws Exception if file is not correctly chosen
     *  StatCalcSavePlotbuttonClicked() function calls IOOperation function to save the class into a bitmap file 
     */
    //==========================================================================
    @FXML
    void StatCalcSavePlotbuttonClicked(ActionEvent event) throws Exception{
        System.out.println("StatCalcSavePlotbuttonClicked (B3) ");
        FileChooser myfileChooser = new FileChooser();
        myfileChooser.setTitle("Save to");
        
        //Set extension filter
        myfileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("TXT files (*.txt)","*.txt") );
        myfileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Bitmap files (*.png)","*.png") );
        
        //
        File saveFileDestination = null;
        
        int indexOfExt = 0;
        while (indexOfExt == 0 ){
            
            saveFileDestination = myfileChooser.showSaveDialog(new Stage());
            
            String fileName = saveFileDestination.getName();
            indexOfExt = fileName.lastIndexOf('.')+1;
            
            if (indexOfExt == 0 ){
                Label addExtString = new Label("MUST HAVE AN EXTENSION...");
 
                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(addExtString);

                Scene secondScene = new Scene(secondaryLayout, 230, 100);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setScene(secondScene);

                newWindow.showAndWait();
            }

        }
        
        IOOperation.SaveToFile(canvasStatistics, saveFileDestination);
    }

    
    /*
    
        /*
    
    //==========================================================================
    /**
     * if StacCalc Clear All button is clicked
     * StatCalcClearAllbuttonClicked() function clears the canvas, the shape container and text areas. 
     */
    //==========================================================================
    @FXML
    void StatCalcClearAllbuttonClicked() {
        
        System.out.println("StatCalcClearAllbuttonClicked (B4)");
        
                // get the canvas handler 
        GraphicsContext gc = canvasStatistics.getGraphicsContext2D();
        
        // clear canvas
        clearAllAction (gc);
        
        // clear text in object list and add the initial info
        ValueInfoArea.clear();
        GaussianFittingArea.clear();
        
        
        makeBorderOnCanvas(gc);// also reborder
        makeBorderOnGraph(gc);
        
        // also have to clear the container 
        StatisticCalculatorDrawing.clearContainer();
        
        
    }
    
    
    
     //==========================================================================
    /**
     * if StatCalcTabClicked DO NOTHING AT THE MOMENT 
     */
    //==========================================================================
    @FXML
    void StatCalcTabClicked( ) {
        // DO NOTHING
    }
    
    
    //==========================================================================
    /**
     * use this method to make border on graph
       it will need graphic context argument
     * @param gc is the graphic context to a particular canvas
     */
    //==========================================================================
    void makeBorderOnGraph(GraphicsContext gc3){
       
        
        gc3.strokeLine(110, 0, gc3.getCanvas().getWidth(), 0);
        
        gc3.strokeLine(110, 0, 110, gc3.getCanvas().getHeight()-35);
        
        gc3.strokeLine(110, gc3.getCanvas().getHeight()-35, gc3.getCanvas().getWidth(), gc3.getCanvas().getHeight()-35);
        
        gc3.strokeLine(gc3.getCanvas().getWidth(), gc3.getCanvas().getHeight()-35, gc3.getCanvas().getWidth(), 0);
    }
    
    //==========================================================================
    /**
     * use this method to make border on canvas
       it will need graphic context argument
     * @param gc is the graphic context to a particular canvas
     */
    //==========================================================================
    void makeBorderOnCanvas(GraphicsContext gc){
       
        //
        gc.strokeLine(0, 0, gc.getCanvas().getWidth(), 0);
        gc.strokeLine(0, 0, 0, gc.getCanvas().getHeight());
        gc.strokeLine(0, gc.getCanvas().getHeight(), gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.strokeLine(gc.getCanvas().getWidth(), gc.getCanvas().getHeight(), gc.getCanvas().getWidth(), 0);
        
    }
    
 
            
    
    //==========================================================================
    /**
     * clear button general action 
     * @param gc this particular canvas will be cleared
     */
    //==========================================================================
    void clearAllAction (GraphicsContext gc){
        
        // clear canvas
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        
    }
    
    
    
    
}
