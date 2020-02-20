//Mustafa Ayberk Kale eexmk22 

package mystatisticscalculatorapp;



import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import org.apache.commons.math3.fitting.*;


/**
 *
 * @author ayberkkale
 */
public class Histogram {
    
    private static ArrayList<Double> inFileNumbers;
    private  static ArrayList<Double> _HistogramBinPointsContainer;
    private  static ArrayList<Double> _UnNormHistogramFrequency;
    private  static ArrayList<Double>  _normalisedHistogramFrequency;
    private  static ArrayList<Double> _MarquardtFittingNormalPDFs;
    /**
     *
     * @param gc
     * @param whichContainer
     * @param BinFormat
     * @param ValueInfoArea
     * @param GaussianFittingArea
     * @param inFileNumbersReceived
     */
    public static void CalculateandPlot(GraphicsContext gc, MyStatisticsCalculatorAppShapeContainer whichContainer, String BinFormat, TextArea ValueInfoArea, TextArea GaussianFittingArea, ArrayList <Double> inFileNumbersReceived){
        
         inFileNumbers =inFileNumbersReceived;
         
         
           /*******************************************************************************************
            * 
            * 
            * Mean , Variance and Standard Deviation Calculation
            * Min Value , Max Value Get
            * Append to ValueInfoArea Box in GUI
            * 
            ********************************************************************************************/
           
           // mean average
        double mean = 0.0;
        for (int i = 0; i < inFileNumbers.size(); i++) {
         mean += inFileNumbers.get(i);
        }
        mean /= inFileNumbers.size();
         System.out.print("mean µm: ");
        System.out.println(mean*Math.pow(10, 6));
        
        // The variance
        
        double variance=0.0;
        for (int i = 0; i < inFileNumbers.size(); i++) {
            variance += (inFileNumbers.get(i) - mean) * (inFileNumbers.get(i) - mean);
        }
        variance /= inFileNumbers.size();
        System.out.print("variance nm: ");
         System.out.println(variance*Math.pow(10, 9));
        
        // Standard Deviation
        double std = Math.sqrt(variance);
        
        System.out.print("std µm: ");
          
        System.out.println(std*Math.pow(10, 6));
          
  
           
         inFileNumbers.sort(null);// sort the input numbers' list from min to max
       
         double minimumValue =  inFileNumbers.get(0);
            
         double maxValue =  inFileNumbers.get(inFileNumbers.size()-1); 
         
         
         ValueInfoArea.appendText("Raw Data\n");
         ValueInfoArea.appendText("Number of Data: "+inFileNumbers.size()+"\n");
         ValueInfoArea.appendText("Minimum Value(µm): "+minimumValue*Math.pow(10, 6)+"\n");
         ValueInfoArea.appendText("Maximum Value(µm): "+maxValue*Math.pow(10, 6)+" \n");
         
         ValueInfoArea.appendText("\n\nStatistical Analysis\n");
          ValueInfoArea.appendText("Mean value(µm): "+mean*Math.pow(10, 6)+" \n");
         ValueInfoArea.appendText("Variance Value(nm): "+variance*Math.pow(10, 9)+" \n");
         ValueInfoArea.appendText("Standard Deviation Value(µm): "+std*Math.pow(10, 6)+" \n");
         
         
            /*******************************************************************************************
            * 
            * 
            * BinContainer Count Calculation
            * Bin Width Calculation
            *  Bin's points calculation by rationing real coordinate points added to min value with bin width 
            * 
            ********************************************************************************************/
         
         double BinCount=giveBinCount(BinFormat);
         
        
        double realHistBinWidth =(maxValue-minimumValue)/(double)Math.round(BinCount);
        
        
        
         ArrayList<Double> realHistogramBinPointsContainer = new ArrayList<>();// graph bin points
        
        realHistogramBinPointsContainer.ensureCapacity((int)BinCount);
        
        realHistogramBinPointsContainer.add(minimumValue);// first point
        
         for(int i=1;i< BinCount ;i++){
                
                 realHistogramBinPointsContainer.add((minimumValue + i*(realHistBinWidth)));//other points
                 
             }      
         
             _HistogramBinPointsContainer = realHistogramBinPointsContainer;
    
         
             /*******************************************************************************************
            * 
            * 
            * Unnormalised histogram frequency of each data range calculation
            * 
            * maxbinheight and GUI canvas to graph heigh calculation
            *  To leave space at the bottom and top of canvas.
            * 
            ********************************************************************************************/
         
         
         
         double graphCanvasHeight=(gc.getCanvas().getHeight()-35);// graph height with a factor
         double maxBinHeight = graphCanvasHeight-40; //maximum height of bin in GUI
         
         ArrayList<Double> realUnNormHistogramFrequency = new ArrayList<>();
  
        unNormHistFreqCalculate(realUnNormHistogramFrequency,realHistogramBinPointsContainer);
        
        
        _UnNormHistogramFrequency=realUnNormHistogramFrequency;
    
            /*******************************************************************************************
            * 
            * 
            * Normalised histogram frequency of each data range calculation
            * maxHeightofNormalisedHistogram
            * binRatioofGraph real frequency to graph max height ratio
            * 
            ********************************************************************************************/
        
        
        
        
        
        ArrayList<Double> normalisedHistogramFrequency = new ArrayList<>();
        
        
        calculateNormalizedHistFreq(realUnNormHistogramFrequency, realHistBinWidth,normalisedHistogramFrequency);
        
        double maxHeightofNormalisedHistogram=Collections.max(normalisedHistogramFrequency);
        double binRatioofGraph=maxBinHeight/maxHeightofNormalisedHistogram;
               //calculates the maximum of the normalised data to ratio it to graph. 
               
        System.out.println("maxHeightofNormalisedHistogram: "+maxHeightofNormalisedHistogram);
        System.out.println("binRatioofGraph: "+binRatioofGraph);
        
        
        _normalisedHistogramFrequency=normalisedHistogramFrequency;

        /*******************************************************************************************
        * 
        * 
        * MarquardFitting
        * PDFs
        * 
        * 
        ********************************************************************************************/


        ArrayList<Double> MarquardtFittingNormalPDFs = new ArrayList<>();
        
         MarquardtFittingNormal(realHistogramBinPointsContainer,realUnNormHistogramFrequency,MarquardtFittingNormalPDFs,GaussianFittingArea);
         
         double binRatioofCircles= maxBinHeight/Collections.max(MarquardtFittingNormalPDFs);
         
         
         _MarquardtFittingNormalPDFs=MarquardtFittingNormalPDFs;
             
        /*******************************************************************************************
        * 
        * 
        * Write axis names X and Y values to canvas
        * 
        * 
        * 
        ********************************************************************************************/


         
         WriteAxisTypes(gc);  

          WritetoYAxis(gc,binRatioofGraph,normalisedHistogramFrequency,graphCanvasHeight);
          
          WritetoXAxis(gc,realHistogramBinPointsContainer,BinCount);
          
          
        /*******************************************************************************************
        * 
        * 
        * Draw Rectangles and Circles in Graph "canvas" 
        * By using MyStatisticsCalculatorAppShapeContainer class inherited classes
        * 
        * CircleShape and RectangleShape
        * 
        * Keep circle y points to draw the PDF lines
        ********************************************************************************************/

          
          
          
         ArrayList<Double> LineContainer = new ArrayList<>();
         
         for(int i=0;i<BinCount-1;i++){
           
             
                double w=((gc.getCanvas().getWidth())-180)/BinCount;

             
               double h=binRatioofGraph*normalisedHistogramFrequency.get(i);
               double y1=0;
                
                double x1=((i)*w*11/10);
                 String userShapeStyleInput = "RectangleShape";
                 String userShapeInput = x1+";"+y1+";"+w+";"+h+";255;0;0;1;";

                 whichContainer.make(userShapeStyleInput,userShapeInput,gc);
                 userShapeStyleInput = "CircleShape";
                 double circleDiameter=4;
                 double ycirc=binRatioofCircles*MarquardtFittingNormalPDFs.get(i);
                 
                 userShapeInput = x1+";"+ycirc+";"+circleDiameter+";0;0;255;1;";
                 whichContainer.make(userShapeStyleInput,userShapeInput,gc);
                 LineContainer.add(ycirc);
                 
               
        }
        
         
        /*******************************************************************************************
        * 
        * 
        * Draw the PDF lines
        * By using MyStatisticsCalculatorAppShapeContainer class inherited classes
        * 
        * LineShape
        * 
        * 
        ********************************************************************************************/

          
         
          
         for(int i=1;i<BinCount-1;i++){
           
             
                double w=((gc.getCanvas().getWidth())-180)/BinCount;

                double x1=((i-1)*w*11/10);
                double x2=((i)*w*11/10);
                 String userShapeStyleInput = "LineShape";
                 String userShapeInput = x1+";"+LineContainer.get(i-1)+";"+x2+";"+LineContainer.get(i)+";";
                  whichContainer.make(userShapeStyleInput,userShapeInput,gc);

                
        
        }
         
         
        
        
    }
    
     /**
      * 
      * 
      * @param realHistogramFrequency
      * @param realHistogramBinPointsContainer 
      * function is a void function so desired output ArrayList has to be given as first argument and bin value container 
      * has to be given as second argument. 
      * It counts every value of sorted input is in the range or not. 
      * Then subtracts the reoccurring numbers into previous container from the current container. 
      */   
    
    
    private static void unNormHistFreqCalculate(ArrayList<Double> realHistogramFrequency,ArrayList<Double> realHistogramBinPointsContainer){
        
               ArrayList<Double> tempHistogramFrequency = new ArrayList<>();
         
         
        double counter=0;
              
        int program_counter=0;
             
        inFileNumbers.sort(null); // be sure it is sorted
             
        while(program_counter!=realHistogramBinPointsContainer.size()){
                
                 
                 
             for(int i=0;i<inFileNumbers.size();i++){
                 
                 if(inFileNumbers.get(i)<realHistogramBinPointsContainer.get(program_counter)){
                    counter++; 
                   // counts every value of sorted input is in the range or not
                   
                 }
             }
                  
                tempHistogramFrequency.add(counter);
                      
                
                 
                  program_counter++;
                  
                
                  counter=0;
                 
                 
        }
        
                realHistogramFrequency.add(tempHistogramFrequency.get(0));
           
                for(int i=1;i<tempHistogramFrequency.size();i++){
                     // substract the reoccuring numbers int previous container from the current container. 
                   realHistogramFrequency.add(tempHistogramFrequency.get(i) - tempHistogramFrequency.get(i-1));
             
                }
                
                double realtemp = realHistogramFrequency.get(realHistogramFrequency.size()-1);
                realtemp=realtemp+1;
                realHistogramFrequency.remove(realHistogramFrequency.size()-1);
                realHistogramFrequency.add(realtemp);
                
                
    }
    
    /**
     * 
     * 
     * 
     * 
     * @param realUnNormHistogramFrequency
     * @param realHistBinWidth
     * @param normalisedHistogramFrequency 
     * 
     * 
     * 
     * 
     * function is a void function so desired output ArrayList has to be given as third argument
     *  bin width has to be given as second argument and 
     * unnormalized histogram frequencies as first argument . 
     * 
     * It counts every value of sorted input is in the range or not. 
     *To find the normalized value each bin values is divided to binwidth*numbercount
     */
    private static void calculateNormalizedHistFreq(ArrayList<Double> realUnNormHistogramFrequency,double realHistBinWidth,ArrayList<Double> normalisedHistogramFrequency){
        
           
                
                for(int i=0;i<realUnNormHistogramFrequency.size();i++){
                   
             
                    normalisedHistogramFrequency.add(realUnNormHistogramFrequency.get(i)/(inFileNumbers.size()*realHistBinWidth));
                    
                   
                  
             
                }
                
          
        
    }
 
    
    /**
     * 
     * @param realHistogramBinPointsContainer
     * @param realUnNormHistogramFrequency
     * @param MarquardtFittingNormalPDFs
     * @param GaussianFittingArea 
     * 
     * function is a void function so 
     * desired output ArrayList has to be given as third argument 
     * bin unnormalized histogram frequencies as second argument 
     * bin container has to be given as first argument and 
     * text area to be written has to be given as fourth argument. 
     */
    private static void MarquardtFittingNormal(ArrayList<Double> realHistogramBinPointsContainer,ArrayList<Double> realUnNormHistogramFrequency,ArrayList<Double> MarquardtFittingNormalPDFs,TextArea GaussianFittingArea ){
     
        System.out.println("realHistogramBinPoints size: "+realHistogramBinPointsContainer.size());
        System.out.println("normalisedHistogramFrequency size: "+realUnNormHistogramFrequency.size());
        
        
        
        WeightedObservedPoints obs = new WeightedObservedPoints();
        //WeightedObservedPoints list are created using bin points and unnormalized frequencies. GaussianCurveFitter is 
        //fitted the curve to get normalization factor, mean and sigma. 
        //And these values are used in Normal PDF formula. 
        
        for(int i=0;i<realHistogramBinPointsContainer.size();i++){
            
       
            obs.add(realHistogramBinPointsContainer.get(i),realUnNormHistogramFrequency.get(i));
             
        }
       
        
        
         double [] parameters = GaussianCurveFitter.create().fit(obs.toList());
         
        double MarquardtFittingNormalNormFactor=parameters[0];
        double MarquardtFittingNormalMean=parameters[1];
        double MarquardtFittingNormalSigma=parameters[2];
        
        
         GaussianFittingArea.appendText("Gaussian Fitting\n");
         GaussianFittingArea.appendText("Normalization Factor: "+MarquardtFittingNormalNormFactor+"\n");
        GaussianFittingArea.appendText("Mean Value: "+MarquardtFittingNormalMean*Math.pow(10, 6)+" (µm)\n");
          GaussianFittingArea.appendText("Sigma Value: "+MarquardtFittingNormalSigma*Math.pow(10, 6)+" (µm)\n");
          
          //Normal PDF formula
        
   for(int i=0;i<realHistogramBinPointsContainer.size();i++){
       
            MarquardtFittingNormalPDFs.add(((Math.exp(-(Math.pow(((realHistogramBinPointsContainer.get(i)-MarquardtFittingNormalMean)/MarquardtFittingNormalSigma),2))/2.0))*MarquardtFittingNormalNormFactor));
        
   }
        

    }
    
    
    /**
     * 
     * @param BinFormat
     * @return 
     * 
     * uses respective formulas for square root ,sturges and rice binning methods.
     */
       private static double giveBinCount (String BinFormat){
           
           double BinCount=0;
           
           switch (BinFormat) {
            case "0":
                //square
                BinCount = Math.sqrt(inFileNumbers.size()) ;
                BinCount=Math.round(BinCount);
                System.out.println("square bin count : "+BinCount);
                break;
      
            case "1":
                //sturge
                BinCount = (3.3*Math.log(inFileNumbers.size())) + 1.0 ;// log is ln
           
                BinCount=Math.round(BinCount);
                System.out.println("sturge's bin count : "+BinCount);
                break;
            case "2":
                //rice
                
                BinCount=2*Math.pow(inFileNumbers.size(), 1.0/3.0);
                BinCount=Math.round(BinCount);
                System.out.println("rice bin count : "+BinCount);
                break;
            default:
                break;
        }
        
        
        
      
        
        return BinCount;
    }

   /**
    * 
    * @param gc3  //GraphicsContext
    * @param realHistogramBinPointsContainer // bin points
    * @param BinCount // number of bins for spefic method
    */
        private static void WritetoXAxis (GraphicsContext gc3,ArrayList<Double> realHistogramBinPointsContainer,double BinCount){
         
                double w=((gc3.getCanvas().getWidth())-180)/BinCount;

         
               double y1=(gc3.getCanvas().getHeight())-15;
                
    
        gc3.setFill(Color.rgb(0, 0,0,1));
        

         
          gc3.save();

            
          gc3.translate(110, y1);// to prints values vertically
          
          gc3.rotate(-90);
          
          //print every 5th value in the bincount.
          for(int i=0;i<BinCount;i+=5){
             double  x=((i)*w*11/10);
               int textI =(int) (realHistogramBinPointsContainer.get(i)*Math.pow(10, 6));
               
              gc3.setFont(new javafx.scene.text.Font("Default", 12));
              gc3.fillText(Integer.toString(textI), 0, x, 18);
              
          }
          
  
         gc3.restore();
         
         for(int i=5;i<BinCount;i+=5){// put stripes
             double x=((i)*w*11/10)+110;
               gc3.save();
            
             gc3.setStroke(Color.FORESTGREEN.brighter());
            gc3.setGlobalAlpha(0.3);
            gc3.strokeLine(x, 40, x, gc3.getCanvas().getHeight()-35);
            
            gc3.restore();
               
          }
         
     
         
         gc3.setFill(Color.rgb(255, 0,0,1)); 
    }

        

    /**
     * 
     * @param gc3 //GraphicsContext
     * @param binRatioofGraph // real bin height to graph bin height
     * @param normalisedHistogramFrequency // pdf points
     * @param graphCanvasHeight  // graph height desired in canvas
     * It accepts GraphicsContext as first input, binRatioofGraph as second input and 
     * normalised Histogram Frequency container as third input and graph height as last input. 
     * It prints 18 values with width increments. binRatioofGraph used to ratio actual max height to graph height.
     */
        private static void WritetoYAxis (GraphicsContext gc3,double binRatioofGraph, ArrayList<Double> normalisedHistogramFrequency,double graphCanvasHeight){
          gc3.setFill(Color.rgb(0, 0,0,1));
            double maxHeigh= Collections.max(normalisedHistogramFrequency);
         double minHistNorm= Collections.min(normalisedHistogramFrequency);
         System.out.println("maxHeigh: "+maxHeigh);
         
         
         double maxBinHeight= binRatioofGraph*maxHeigh;
         double yMax= graphCanvasHeight-maxBinHeight;
         double yMin= maxBinHeight+40;
        
         double min_value =maxHeigh/16.0;
          
         double wid=(yMax+yMin)/18.0;
         gc3.fillText(Double.toString(Math.round(minHistNorm+0*min_value)),50, yMin-0*wid, 80); 
         for(int i=1;i<17;i++){
             
            gc3.fillText(Double.toString(Math.round(minHistNorm+i*min_value)),50, yMin-i*wid, 80); 
            gc3.save();
            
             gc3.setStroke(Color.FORESTGREEN.brighter());
            gc3.setGlobalAlpha(0.3);
            gc3.strokeLine(103, yMin-i*wid, gc3.getCanvas().getWidth(), yMin-i*wid);
            
            gc3.restore();
            
         }
         
 
        
        gc3.setFill(Color.rgb(255, 0,0,1)); 
    }
    
    /**
     * 
     * @param gc3  // canvas
     * It print x axis name, title and vertical y axis name. Only canvas is needed.  
     */
    
    private static void WriteAxisTypes (GraphicsContext gc3){
        
        gc3.setFill(Color.rgb(0, 0,0,1));
         //gc3.fillText(Double.toString(i), x1Temp, y1Temp, 10);
         double mText= 110+((gc3.getCanvas().getWidth()-180)/2);
         gc3.save();
        gc3.setFont(new javafx.scene.text.Font("Default", 10));
        String title= "Normalised Statistical Analysis (Frequency vs Measurement Data / µm)";
        gc3.fillText(title, mText-title.length()-50, 10);
         gc3.fillText("Measurement Data / µm", mText, (gc3.getCanvas().getHeight())-4);
           gc3.restore();
         
         double ftext=((gc3.getCanvas().getHeight())-110)/2;
         gc3.save();
         gc3.translate(10, ftext);
    
          gc3.rotate(-90);
          gc3.setFont(new javafx.scene.text.Font("Default", 10));
         gc3.fillText("Frequency", 10, 10);
         gc3.restore();
         gc3.setFill(Color.rgb(255, 0,0,1)); 
    }
    
    //getters for containers
    
    
    public static ArrayList<Double>getnormalisedHistogramFrequencyContainer(){
        
        return _normalisedHistogramFrequency;
        
    }
    
    public static ArrayList<Double>getHistogramBinPointsContainer(){
        
        return _HistogramBinPointsContainer;
        
    }
        
    public static ArrayList<Double>getMarquardtFittingNormalPDFsContainer(){
        
        return _MarquardtFittingNormalPDFs;
        
        
    }
    
        
    public static ArrayList<Double>getUnNormHistogramFrequencyContainer(){
        
        return _UnNormHistogramFrequency;
        
    }
    
}
