//Mustafa Ayberk Kale eexmk22 

package mystatisticscalculatorapp;



import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;


public class IOOperation {
        /**
     * To perform Open file action
     * @param fileDestination
     * @param gc
     * @param whichContainer
     * @param BinFormat
     */
    private static String fileName=null;
    
    /**
     *
     * @param fileDestination
     * @throws IOException
     * OpenMeasurementDataFile(fileDestination) function gets the location of the file by absolute path saves it into a 
     * private string value and check if it is a text file or not other wise throws and exception. 
     * fileName = fileDestination.getAbsolutePath(); 
     */
    public static void OpenMeasurementDataFile( File fileDestination) throws IOException  {
        
          fileName = fileDestination.getAbsolutePath();
                  
        
        int indexOfExt = fileName.lastIndexOf('.')+1;

        String fileExtension = fileName.substring(indexOfExt);
        
    
        
        if( fileExtension.equalsIgnoreCase("txt")!=true ) {
            
              System.out.println("File is not a txt");
              throw new IOException("File is not a txt");
        
           
        }
        
      
       
        
    }
    
    /**
     *
     * @param gc the value of gc
     * @param whichContainer the value of whichContainer
     * @param BinFormat the value of BinFormat
     * @param ValueInfoArea the value of ValueInfoArea
     * @param GaussianFittingArea the value of GaussianFittingArea
     * function checks if file is chosen or not throws an exception if not available. 
     * Reads the values into an double ArrayList container. 
     * And sends the necessary values to histogram data calculator and plot function 
     */
    public static void executeOpenMeasurementDataTxtFiletoHistogram(GraphicsContext gc, MyStatisticsCalculatorAppShapeContainer whichContainer, String BinFormat, TextArea ValueInfoArea, TextArea GaussianFittingArea) throws IOException{
        
        System.out.println("fileName Loc: "+fileName);
        
        if(fileName == null){
             throw new IOException("File is not choosen");
        }
        
            ArrayList<Double>  inFileNumbers = new ArrayList<>(); // Create an ArrayList object
       
        
           try{
        // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // go through inputFileLine by inputFileLine
            String inputFileLine ;
         
                 double numberCount;
           
          
           while((inputFileLine = bufferedReader.readLine())!=null) {
                
           numberCount = Double.parseDouble(inputFileLine);
          
           inFileNumbers.add(numberCount);
            
            
            }

                     // Always close files.
            bufferedReader.close(); 
            
           
            
            
        }
        
        catch(IOException ex) {
            System.out.println("Error reading file \n");                  
        }
        
          
          // send the values to histogram data calculator and plot function
           Histogram.CalculateandPlot(gc, whichContainer, BinFormat, ValueInfoArea, GaussianFittingArea, inFileNumbers);
        
        
        
          
        
    }

    
        /**
     * To perform file saving action
     * @param content
     * @param fileDestination
     * @throws java.io.IOException
     * function  opens a FileWriter and converts a writable image  
     * to a bitmap file using SwingFXUtils and  ImageIO class’ functions. 
     */
    public static void SaveToFile(Object content, File fileDestination) throws IOException{
        
        // get the extension 
        String fileName = fileDestination.getName();
        
        int indexOfExt = fileName.lastIndexOf('.')+1;

        String fileExtension = fileName.substring(indexOfExt);
        
        
        // if the content is a text area means 
        // save the text content
        if( content instanceof TextArea ) {
            String StringContent = ((TextArea) content).getText();
            
            FileWriter fileWriter = new FileWriter(fileDestination);
             
            fileWriter.write(StringContent);
            fileWriter.close();

        }      
        
        // if the content is a canvas and extension is png  
        // save the image
        if( (content instanceof Canvas) && fileExtension.equalsIgnoreCase("png") ){
            
            Canvas tempCanvas = (Canvas) content; 
            
            WritableImage writableImage = new WritableImage((int) tempCanvas.getWidth(), (int) tempCanvas.getHeight());
            tempCanvas.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            
            ImageIO.write(renderedImage, "png", fileDestination);
            
        }
        
        
    }
    
    
     

    
    
    
}
