//Mustafa Ayberk Kale eexmk22 

package mystatisticscalculatorapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//
public class MyStatisticsCalculatorApp extends Application{
    
    // start GUI function 
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MyStatisticsCalculatorAppGUI.fxml")); // load the fxml file
  
        primaryStage.setTitle("Statistics Calculator");
        
        // 
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    //    
    public static void main(String[] args) {
        launch(args);
    }
}


