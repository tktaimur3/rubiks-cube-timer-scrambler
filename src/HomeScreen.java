import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

/**
 * @author Taimur Khan
 * Purpose: To create a Rubik's Cube scrambler and timer, allowing the user to view all their times and averages of 3, 5 and 12 of their times
 * Also stores their times into a text file and sorts them into a different text file
 * Date: 21 January 2019
 */
public class HomeScreen {
	
		//Init private variables for the GUI
        static boolean timing;
        private JFrame frame;
        private JPanel panel;
        private JLabel label;
        private JList list;
        private JLabel ao5Time;
        private JLabel ao5BestTime;
        private JLabel ao3Time;
        private JLabel ao3BestTime;
        private JLabel ao12Time;
        private JLabel ao12BestTime;
        private JLabel currentTime;
        private JLabel bestTime;
        
        static String workingDir = System.getProperty("user.dir")+"\\src\\";
        
        /**
         * Launch the application.
         */
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                //Run the window
            	public void run() {
                    //Try to launch the application
            		try {
                            HomeScreen window = new HomeScreen();
                            window.frame.setVisible(true);
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
                }
            });
        }//end Main
        
        /**
         * A method used to get the individual lines in a file
         * 
         * @param textFileName the name of the text file
         * @return returns a string array
         * @throws IOException
         */
        public static String[] getTextFileValues(String textFileName) throws IOException {
            //Directory is created with name in parameter. If it already exists, nothing happens
            File directory = new File(workingDir+"\\"+selectedCube);
            if (!directory.exists()) {
            	directory.mkdirs();
                System.out.println("New directory created: " + workingDir);
            }
            
            //File is created with the given name, it it already exists nothing happens
            File newFile = new File(workingDir+textFileName);
            if (!newFile.exists()) { 
            	newFile.createNewFile();
            	System.out.println("New file created: " + workingDir+textFileName);
            }
            //Prints what file is being accessed for debugging purposes
            System.out.println("Accessing file: "+workingDir+textFileName);
            //BufferedReader is initialised
            BufferedReader reader = new BufferedReader(new FileReader(workingDir+textFileName));
            
            //Number of readable lines
            int count = 0;
    
            //Go through the code and read the number of lines that can be read (lines that aren't null) and increase counter by one when one is found
            while (reader.readLine() != null)
                    count++;
            //Close the reader
            reader.close();
            //Re-initialise the reader to read the same file from the beginning again
            reader = new BufferedReader(new FileReader(workingDir+textFileName));

            //Create an array to store the lines, with the length being the count integer previously stated
            String[] arrayOfLines = new String[count];
            
            //Adds each line in the file to the String array
            for (int i = 0; i < count; i++) {
                    arrayOfLines[i] = reader.readLine();
            }
            
            //Closes the reader
            reader.close();
            //Returns the array
            return arrayOfLines;
        }//End getTextFileValues

        
        /**
         * Reverses an array entered
         * 
         * @param arrayToReverse the string array to reverse
         * @return a string array that is reversed
         */
        public static String[] reverseArray (String[] arrayToReverse) {
            //Init string array that will hold the reversed array
        	String[] reversedArray = new String[arrayToReverse.length];
            
        	//Reverse array from original array
            for (int i = 0; i < arrayToReverse.length; i++) {
            	reversedArray[i] = arrayToReverse[(arrayToReverse.length-1)-i];
            }
            
            //Return the reversed array
            return reversedArray;
        }//End reverseArray
        
        /**
         * Get the best time in an array
         * 
         * @param listOfTimes
         * @return the best time string
         */
        public static String getBestTime (String[] listOfTimes) {
            //Set a default string return
            String bestTime = "N/A";        
            
            //Init the minimum
            float min = 0f;
            //Try to init the minimum, if an error is thrown there is nothing in the array so return N/A
            try {
                    min = TimeConversion.stringToSec(listOfTimes[0]);
            } catch (Exception e) {
                    return bestTime;
            }
            
            //Iterate through for loop and get min time
            for (int i = 0; i < listOfTimes.length; i++     ) {
                    if (min > TimeConversion.stringToSec(listOfTimes[i])) {
                            min = TimeConversion.stringToSec(listOfTimes[i]);
                    }
                    
            }
         
            //Set the best time
            bestTime = String.valueOf(min);
            
            //Return the best time, properly formatted
            return formatTime(bestTime);
        }//End getBestTime
        
        /**
         * Get the best time in an array list
         * 
         * @param listOfTimes the array list of listOfTimes
         * @return the best time string
         */
        public static String getBestTime (ArrayList<String> listOfTimes) {
            //Set a default string return
        	String bestTime = "N/A";        
    
            //Init the minimum
            float min = 0f;
            //Try to init the minimum, if an error is thrown there is nothing in the array so return N/A
            try {
                min = TimeConversion.stringToSec(listOfTimes.get(0));
            } catch (Exception e) {
                return bestTime;
            }
            
            //Iterate through for loop and get min time
            for (int i = 0; i < listOfTimes.size(); i++) {
                if (min > TimeConversion.stringToSec(listOfTimes.get(i))) {
                        min = TimeConversion.stringToSec(listOfTimes.get(i));
                }
            }
            
            //Set the best time
            bestTime = String.valueOf(min);
            
            //Return the best time, properly formatted
            return TimeConversion.secToString(Float.parseFloat(bestTime));
        }//End getBestTime
        
        /**
         * Get the worst time in an array
         * 
         * @param listOfTimes a list of times array
         * @return the worst time string
         */
        public static String getWorstTime (String[] listOfTimes) {
        	//Set a default string return
            String worstTime = "N/A";        
    
            //Init max time float
            float max = 0f;
            //Try to init the maximum, if an error is thrown there is nothing in the array so return N/A
            try {
                    max = TimeConversion.stringToSec(listOfTimes[0]);
            } catch (Exception e) {
                    return worstTime ;
            }
            
            //Iterate through for loop and get min time
            for (int i = 0; i < listOfTimes.length; i++) {
                    if (max < TimeConversion.stringToSec(listOfTimes[i])) {
                            max = TimeConversion.stringToSec(listOfTimes[i]);
                    }
                    
            }
            //Set the worst time
            worstTime = TimeConversion.secToString(Float.parseFloat(String.valueOf(max)));
            
            //Return the worst time
            return worstTime;
        }//End getWorstTime
        
        /**
         * Get the worst time in an array list
         * 
         * @param listOfTimes the list of times in an array
         * @return the worst time string
         */
        public static String getWorstTime (ArrayList<String> listOfTimes) {
            //Set a default string return
            String worstTime = "N/A";       

            //Init max time String
            float max = 0f;
            //Try to init the maximum, if an error is thrown there is nothing in the array so return N/A
            try {
                max = TimeConversion.stringToSec(listOfTimes.get(0));
            } catch (Exception e) {
                    return worstTime;
            }

            //Iterate through for loop and get min time
            for (int i = 0; i < listOfTimes.size(); i++     ) {
                if (max < TimeConversion.stringToSec(listOfTimes.get(i))) {
                        max = TimeConversion.stringToSec(listOfTimes.get(i));
                }
            }
            
            //Set the worst time
            worstTime = TimeConversion.secToString(Float.parseFloat(String.valueOf(max)));
            
            //Return the worst time
            return worstTime;
        }//End getWorstTime
        
        
        /**
         * Calculates average of 5
         * 
         * @param listOfTimes the list of times string
         * @return the average of 5 times
         */
        public static String ao5 (ArrayList<String> list) {
            //Init a top5 array list
            ArrayList<Float> top5 = new ArrayList<Float>();
        
            //Try to add each latest time into the array list from the top 5 array, if you can't there are <5 times so you cant calculate avg
            try {
                for (int i = 0; i < 5; i++) {
                	top5.add(TimeConversion.stringToSec(list.get(i)));
                }
            } catch (Exception e) {
                    return "N/A";
            }

            //Sort array
            top5 = sortNumbers(top5);
            
            //Init the sum of times
            float sumOfTimes = 0f;
            
            //Iterate through the array and add the index's time to the sum of times if that index is not a best or worst time
            for (int i = 1; i < (top5.size()-1); i++) { 
            	sumOfTimes += top5.get(i);
            }
            
            //Format the String
            DecimalFormat df = new DecimalFormat("#.##");            
            String d = df.format(sumOfTimes/3);
            
            //Return the string
            return TimeConversion.secToString(Float.parseFloat(formatTime(d)));
        }//End ao5
        
        /**
         * Calculates average of 3
         * 
         * @param listOfTimes2
         * @return the average of 3 times
         */
        public static String ao3 (ArrayList<String> list) {
            //Create a list of top 3 array list
        	ArrayList<String> top3 = new ArrayList<String>();
            
        	//Try to add each latest time into the array list from the top 5 array, if you can't there are <3 times so you cant get the avg
            try {
                for (int i = 0; i < 3; i++) {
                        top3.add(list.get(i));
                }
            } catch (Exception e) {
                    return "N/A";
            }

            //Init sum of times
            float sumOfTimes = 0f;
            
            //Go through array list and add each index to sum of times
            for (int i = 0; i < top3.size(); i++) { 
                    sumOfTimes += TimeConversion.stringToSec(top3.get(i));
            }
            
            //Format the time to three sig digs
            DecimalFormat df = new DecimalFormat("#.##");
            
            //Return the time, formatted
            return TimeConversion.secToString(Float.parseFloat(df.format(sumOfTimes/3)));
        }//End ao3
        
        /**
         * Calculates average of 12
         * 
         * @param listOfTimes the list of times array
         * @return the average of 12
         */
        public static String ao12 (ArrayList<String> list) {
            //Create list of top 12 latest array
                ArrayList<Float> top12 = new ArrayList<Float>();
                
            //Try to add each latest time into the array list from the top 5 array, if you can't there are <12 times so you cant get the avg
            try {
                for (int i = 0; i < 12; i++) {
                        top12.add(TimeConversion.stringToSec(list.get(i)));
                }
            } catch (Exception e) {
                return "N/A";
            }

            //Sort the top12 latest
            top12 = sortNumbers(top12);
            
            //Init sum of times
            float sumOfTimes = 0f;
            
            //Iterate through the array and add the index's time to the sum of times if that index is not a best or worst time
            for (int i = 1; i < (top12.size()-1); i++) {        
                //if (!(i == worstTimeIndex || i == bestTimeIndex)) 
            	sumOfTimes += top12.get(i);
            }
            
            //Format the time to three sig digs
            DecimalFormat df = new DecimalFormat("#.##");
            
            //Return the time, formatted
            return TimeConversion.secToString((sumOfTimes/10));
        }//End ao12

        /**
         * Formats times under a minute as: XX.XX
         * 
         * @param time the string of time to format
         * @return the formatted time
         */
        public static String formatTime (String time) {
            //The part of the string that comes after the decimal
        	String ending = time.substring((time.indexOf('.')+1), time.length());
        	
        	//Check if it even has a decimal
            if (time.indexOf(".") != -1) {
            	//Switch case for the ending length
	            switch (ending.length()) {
	            //If it has no ending then add 00 to the time so it is formatted as: XX.00
	            case 0: time += "00";
	            break;
	          //If it has no ending then add 00 to the time so it is formatted as: XX.X0
	            case 1: time += "0";
	            break;
	            }
            } else {
            	//If no decimal add the decimal and trailing zeroes
            	time+=".00";
            }
	            
            //Return the formatted time
            return time;
        }//End formatTime
        
        /**
         * Get a text file and return an array list with all the times, separating its number from the file
         * 
         * @param fileName the filename to get the files
         * @return The array list of lines from the file
         * @throws IOException
         */
        public ArrayList<String> getListOfTimes(String fileName) throws IOException {
            //Init the array with using the get text file values method and reverse it so the latest time is first
        	String[] tempListOfTimes = reverseArray(getTextFileValues(fileName));
        	//Set the global number of the number of times
            String[] listOfTimes = new String[tempListOfTimes.length];
            
            //Go through the array and separate the time number from time and edit the array at that index to match
            for (int i = 0; i < listOfTimes.length; i++) {
                    String[] placeAndTime = tempListOfTimes[i].split(" ");
                    listOfTimes[i] = placeAndTime[1];
            }
            
            //Init array list
            ArrayList<String> arrayListOfTimes = new ArrayList<String>();

            //For each element in the array, add it to the array list
            for (String s : listOfTimes)
            	arrayListOfTimes.add(s);
            
            //Return the array list
            return  arrayListOfTimes;
        }//End getListOfTimes
        
        /**
         * Rewrites the file so that times can be updated 
         * 
         * @param fileToWrite the file to write to 
         * @param listOfTimes the list of times array list that will be written
         * @throws IOException
         */
        public static void rewriteFile (String fileToWrite, ArrayList<String> listOfTimes) throws IOException {
            //Init printWriter
        	PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(workingDir+fileToWrite)));
        	//Reverse the array list
        	Collections.reverse(listOfTimes);
            
        	//Run a for loop that adds all times
        	for (int i = 0; i < listOfTimes.size(); i++) {
        		writer.println((i+1) + ". " + listOfTimes.get(i));
        	}
        	
            //Close the writer 
            writer.close();
        }//End rewriteFile
        
        /**
         * Sort the times from one file and write them to a new file
         * 
         * @param fileName the file name to get the times from
         * @param toWrite the file to write the sorted times to
         * @throws IOException
         */
        public static void sortTimes (String fileName, String toWrite) throws IOException {
        	//Init values
        	ArrayList <Float> values = new ArrayList <Float>();
    		
        	//Init bufferedReader to the file
    		BufferedReader br = new BufferedReader(new FileReader(workingDir+fileName));
    		
    		//Init Line String
    		String line;
    		//While there is something in the file...
    		while( (line=br.readLine()) != null){
    			//...get the time after its number (get Y's if it is formatted as: X. YY:YY.YY
    			int index = line.indexOf(" ");
    			values.add(TimeConversion.stringToSec(line.substring(index+1, line.length())));
    		}
    		
    		//Close the bufferedReader
    		br.close();
    		
    		//Sort the values in the array list
    		values = sortNumbers(values);
    		
    		//Init PrintWriter
    		PrintWriter pw = new PrintWriter(new FileWriter(workingDir+toWrite));
    		
    		//For each value in the array list, print it to a different file
    		for(int s = 0; s < values.size(); s++){
    			pw.println((s+1) + ". " + TimeConversion.secToString(values.get(s)));
    		}
    		
    		//Close the print writer
    		pw.close();
    	}//End sortTimes
    	
    	/**
    	 * Sort the numbers in a given array list 
    	 * 
    	 * @param numbers the array list of the numbers to be sorted
    	 * @return the sorted array list
    	 */
    	public static ArrayList <Float> sortNumbers (ArrayList <Float> numbers){ 
    		//always true bool
    		boolean r = true;
    		
    		//While true
    		while(r){
    			
    			//counts how many swaps occur each repetition
    			int numSwaps = 0;
    			
    			//checks each pairing of adjacent numbers and makes sure it is in numerical order
    			for(int s = 0; s < numbers.size()-1; s++){
    				
    				if(numbers.get(s) > numbers.get(s+1)){
    					
    					float temp = numbers.get(s+1);
    					numbers.set((s+1), numbers.get(s));
    					numbers.set(s, temp);
    					numSwaps++;
    					
    				} //if statement				
    				
    			} //for loop
    			
    			//breaks the loop when the sorting is finished
    			if(numSwaps == 0){
    				r=false;
    			} //if statement
    			
    		} //while loop
    		
    		
    		return numbers;
    	}//End sortNumbers
        
        /**
         * Create the application.
         * @throws IOException 
         */
        public HomeScreen() throws IOException {
            initialize();
        }//end HomeScreen

        //Init the timer counter
        static int timerCounter = 0;
        //Init start timer booleans
        boolean start = true;
        //Timer string
        static String notEmpty = "";
        //ArrayList of the list of times for all times, ao5,3 and 12
        static ArrayList<String> listOfTimes;
        static ArrayList<String> listOfAo5;
        static ArrayList<String> listOfAo3;
        static ArrayList<String> listOfAo12;
        //The cubes available string array
        static String[] cubesAvailable = {"2x2", "3x3", "4x4", "5x5"};
        //The selected cube string
        static String selectedCube = "3x3";
        //The count for holding the space at the start
        static int holdingRedCount = 0;
        //Check for holding flags
        static boolean checkForHolding = false;
        static boolean checkForHoldingFlag = false;
        
        /**
         * Initialize the contents of the frame.
         * @throws IOException 
         */
        private void initialize() throws IOException {
                listOfTimes = getListOfTimes(selectedCube+"\\Rubik'sTimes.txt");
                
                listOfAo5 = getListOfTimes(selectedCube+"\\ao5.txt");
                listOfAo3 = getListOfTimes(selectedCube+"\\ao3.txt");
                listOfAo12 = getListOfTimes(selectedCube+"\\ao12.txt");
                
                frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(workingDir+"icon.png"));
                frame.setBounds(100, 100, 1200, 720);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setTitle("Rubik's Vibes");
                
                panel = new JPanel();
                panel.setLayout(null);
                panel.setBackground(Color.white);
                frame.setContentPane(panel);
                
                label = new JLabel("00.00");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Courier", Font.PLAIN, 65));
                label.setBounds(385, 275, 344, 100);
                panel.add(label);
                
                list = new JList(listOfTimes.toArray());
                list.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                list.setBackground(Color.white);
                list.setBackground(new Color(240, 240, 240));
                list.setBounds(0, 0, 10, 100);
                
                //Scroll Pane for list is created
                JScrollPane listScrollPane = new JScrollPane(list);
                //List is moved over to the left side and resized accordingly
                listScrollPane.setBackground(Color.white);
                listScrollPane.setBounds(950, 170, 210, 480);
                listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                listScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
                //Add scroll pane to panel
                panel.add(listScrollPane);
                
                
                //Column 1 labels
                
                int xPos1 = 950;
                
                final JLabel timeLabel = new JLabel("time");
                timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                timeLabel.setBounds(xPos1, 38, 46, 14);
                panel.add(timeLabel);
                
                JLabel ao5Label = new JLabel("ao5");
                ao5Label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao5Label.setBounds(xPos1, 98, 46, 14);
                panel.add(ao5Label);
                
                JLabel ao3Label = new JLabel("ao3");
                ao3Label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao3Label.setBounds(xPos1, 68, 46, 14);
                panel.add(ao3Label);
                
                JLabel ao12Label = new JLabel("ao12");
                ao12Label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao12Label.setBounds(xPos1, 128, 46, 14);
                panel.add(ao12Label);
                
                //Column 1 end
                
                //Column 2 labels
                
                int diff = 90;
                int xPos2 = xPos1 + diff;
                
                JLabel currentLabel = new JLabel("current");
                currentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                currentLabel.setBounds(xPos2, 11, 46, 14);
                panel.add(currentLabel);
                
                currentTime = new JLabel("N/A");
                currentTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                currentTime.setBounds(xPos2, 39, 46, 14);
                panel.add(currentTime);
                
                ao5Time = new JLabel(ao5(listOfTimes));
                ao5Time.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao5Time.setBounds(xPos2, 98, 46, 14);
                panel.add(ao5Time);
                
                ao3Time = new JLabel(ao3(listOfTimes));
                ao3Time.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao3Time.setBounds(xPos2, 68, 46, 14);
                panel.add(ao3Time);
                
                ao12Time = new JLabel(ao12(listOfTimes));
                ao12Time.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao12Time.setBounds(xPos2, 128, 46, 14);
                panel.add(ao12Time);
                
                //Column 2 end
                
                //Column 3 labels
                
                int xPos3 = xPos2 + diff;
                
                JLabel bestLabel = new JLabel("best");
                bestLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                bestLabel.setBounds(xPos3, 12, 46, 14);
                panel.add(bestLabel);
                
                bestTime = new JLabel("best");
                bestTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                bestTime.setBounds(xPos3, 38, 46, 14);
                bestTime.setText(getBestTime(listOfTimes));
                panel.add(bestTime);
                
                ao5BestTime = new JLabel(getBestTime(listOfAo5));
                ao5BestTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao5BestTime.setBounds(xPos3, 99, 46, 14);
                panel.add(ao5BestTime);
                
                ao3BestTime = new JLabel(getBestTime(listOfAo3));
                ao3BestTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao3BestTime.setBounds(xPos3, 69, 46, 14);
                panel.add(ao3BestTime);
                
                ao12BestTime = new JLabel(getBestTime(listOfAo12));
                ao12BestTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                ao12BestTime.setBounds(xPos3, 128, 46, 14);
                panel.add(ao12BestTime);
                
                //Column 3 ends
                
                final JComboBox cubeSelector = new JComboBox(cubesAvailable);
                cubeSelector.setSelectedIndex(1);
                cubeSelector.setBounds(26, 21, 121, 20);
                cubeSelector.setFocusable(false);
                panel.add(cubeSelector);
                
                final JPanel algorithmDisplay = new MyPanel(cubeSelector.getSelectedIndex()+2);
                algorithmDisplay.setBounds(0, 450, 413, 203);
                algorithmDisplay.setBackground(Color.white);
                panel.add(algorithmDisplay);
                
                final JLabel algorithm = new JLabel("<html>"+((MyPanel)algorithmDisplay).getAlgorithm()+"</html>");
                algorithm.setFont(new Font("Monospaced", Font.PLAIN, 23));
                algorithm.setBounds(180, 22, 726, 120);
                algorithm.setHorizontalAlignment(SwingConstants.CENTER);
                algorithm.setVerticalAlignment(SwingConstants.TOP);
                panel.add(algorithm);
                
                //Add an action listener to the drop down menu
				cubeSelector.addActionListener(new ActionListener() {
			                                        
					//When something happens, do what's in the method
					@Override
					public void actionPerformed(ActionEvent e) {
						//Set the selected cube
				        selectedCube = cubesAvailable[cubeSelector.getSelectedIndex()];
				        
				        //Update the times to match the chosen cubes times
				        try {
				        	listOfTimes = getListOfTimes(selectedCube+"\\Rubik'sTimes.txt");    
							listOfAo5 = getListOfTimes(selectedCube+"\\ao5.txt");
							listOfAo3 = getListOfTimes(selectedCube+"\\ao3.txt");
							listOfAo12 = getListOfTimes(selectedCube+"\\ao12.txt");
				        } catch (Exception ex) {
				        	ex.printStackTrace();
				        }
				        
				        //Set the text of all the times and bests
				        ao5Time.setText(ao5(listOfTimes));
				        ao3Time.setText(ao3(listOfTimes));
				        ao12Time.setText(ao12(listOfTimes));
					    ao5BestTime.setText(getBestTime(listOfAo5));
					    ao3BestTime.setText(getBestTime(listOfAo3));
					    ao12BestTime.setText(getBestTime(listOfAo12));
					    bestTime.setText(getBestTime(listOfTimes));
					    
					    //Set the list data to the corresponding times 
					    list.setListData(listOfTimes.toArray());
					    
					    //Update the algorithm and the cube net
					    ((MyPanel)algorithmDisplay).setCubeSize(cubeSelector.getSelectedIndex()+2);;
	                    algorithm.setText("<html>"+((MyPanel)algorithmDisplay).getAlgorithm()+"</html>");
	                    algorithmDisplay.repaint();
					}	
				});
                
                
                //Set timing to false
                timing = false;
                
                //Init new timer
                Timer t = new Timer();
                
                //Create timer task
                TimerTask task = new TimerTask() {
                        
                	//When running task run whats in method
                    @Override
                    public void run() {
                    	//Init seconds and milliseconds as 0
                        int seconds = 0;
                        int milliseconds = 0;
                        //If the timer is not already started, start it
                        if (!start) {
                        	//Set the seconds to the milliseconds counted divided by 1000
                            seconds = timerCounter/1000;
                            //Remove the tens place of the milliseconds so its displayed accurately
                            milliseconds = Math.abs((seconds*1000) - timerCounter);
                            
                            /*
                             * Some formatting that makes the numbers look correct
                             * When the milliseconds are under 100, it makes sure that the time is displayed with a zero before the decimal 
                             * Example would 1.9 being fixed to 1.09, this happens due to the milliseconds being appended onto the end of the decimal
                             * 
                             * Makes sure that everything is rounded to two decimal places
                             */
                            char lastNumber = String.valueOf(milliseconds).charAt(String.valueOf(milliseconds).length()-1);
                            milliseconds -= Integer.parseInt(String.valueOf(lastNumber));
                            String currentTime = "";
                            if (milliseconds > 100)
                                currentTime = String.format("%d.%d", seconds, milliseconds);
                            else {
                                lastNumber = String.valueOf(milliseconds).charAt(String.valueOf(milliseconds).length()-1);
                                milliseconds -= Integer.parseInt(String.valueOf(lastNumber));
                                currentTime = String.format("%d.0%d", seconds, milliseconds);
                            }

                            currentTime = formatTime(currentTime.indexOf(".") < 0 ? currentTime : currentTime.replaceAll("0*$", "").replaceAll("\\.$", ""));

                            //Set the text for the label every millisecond
                            label.setText(TimeConversion.secToString(Float.parseFloat(currentTime)));
                           
                            //Set the notEmpty string to something other than an empty string to confirm time
                            notEmpty = "notempty";
                            
                            //Increase the counter
                            timerCounter++;
                        } else {
                        	//If it has started, stop it and reset the timer
                            timerCounter = 0;
                        }
                    }
                };
                //Schedule the task to go at every milliseconds, the run method runs at every millisecond when started
                t.scheduleAtFixedRate(task, 0, 1);
                
                //Set inner time for the holding space
                Timer inner = new Timer();
                
                //Init another task
                TimerTask ts = new TimerTask() {
                                        
                	@Override
                	public void run() {
                		//If the button is being held then init the timer and count it
                        if (checkForHolding) {     
                            holdingRedCount++;
                        }
                	}
                };
                //Schedule the timer per millisecond
                inner.scheduleAtFixedRate(ts, 0, 1);
                
                //Init a keylistener
                KeyListener keyListener = new KeyListener() {

                	//While the key is being held, run keyPressed
            		/* (non-Javadoc)
            		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
            		 */
            		@Override
                    public void keyPressed(KeyEvent e) {
            			//Holding flag is set to true
                    	checkForHolding = true;
                        
                    	//If the timer hasn't started already, set the text to red and then green after a certain length of time 
                    	if (start) {
	                        if (e.getKeyCode() == e.VK_SPACE && holdingRedCount < 20) 
	                            label.setForeground(Color.RED);
	                        else if (e.getKeyCode() == e.VK_SPACE && holdingRedCount > 20) {
	                        	//If the time has turned green, then it can start the timer
	                            label.setForeground(Color.GREEN);
	                            checkForHoldingFlag = true;
	                        }
                    	}
                    }//end keyPressed
            		
            		//When a key is released run this method
                    /* (non-Javadoc)
                     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
                     */
                    @Override
                    public void keyReleased(KeyEvent e) {
                    	//Check if the space button is being held or if the timer has not already started
                    	if (checkForHoldingFlag || !start) {
                    		//Set the tripped bool to false
                    		boolean tripped = false;
                    		//Check if space is bring pressed and if the timer hasn't started
                    		 if (!start && e.getKeyCode() != e.VK_ESCAPE) {
                                 try {   
                                	 //Start the timer if it hasnt already been started
                                	 start=!start;
                                	 //If the label is not empty
                                     if (!notEmpty.equals("")) {
                                    	 //Set the string labelTime to whatever time is being displayed
                                    	 String labelTime = label.getText();
                                    	 //Add the time displayed to the start of the array
                                         listOfTimes.add(0, labelTime);
                                         //Write the file again to reflect the new time
                                         rewriteFile(selectedCube+"\\Rubik'sTimes.txt", listOfTimes);
                                         //Update the array to match the times
                                         listOfTimes = getListOfTimes(selectedCube+"\\Rubik'sTimes.txt");
                                         
                                         //Set the list data in the list of times to the new array
                                         list.setListData(listOfTimes.toArray());
                                         
                                         //Add current AO5 to the file
                                         String currentAo5 = ao5(listOfTimes);
                                         if (!currentAo5.equals("N/A")) {
                                         	listOfAo5.add(0, currentAo5);    
                                         	rewriteFile(selectedCube+"\\ao12.txt", listOfAo5);
                                         }
                                                 
                                         //Add current AO3 to the file
                                         String currentAo3 = ao3(listOfTimes);
                                         if (!currentAo3.equals("N/A")){
                                        	 listOfAo3.add(0, currentAo3);    
                                         	rewriteFile(selectedCube+"\\ao3.txt", listOfAo3);
                                         }
                                         
                                         //Add current AO12 to the file
                                         String currentAo12 = ao12(listOfTimes);
                                         if (!currentAo12.equals("N/A")){
                                        	 listOfAo12.add(0, currentAo12);    
                                         	rewriteFile(selectedCube+"\\ao12.txt", listOfAo12);
                                         }
                                         
                                         //Update the list of 5, 4 and 12 arrays to match the newly added data
                                         listOfAo5 = getListOfTimes(selectedCube+"\\ao5.txt");
                                         listOfAo3 = getListOfTimes(selectedCube+"\\ao3.txt");
                                         listOfAo12 = getListOfTimes(selectedCube+"\\ao12.txt");
                                         
                                         //Set the current time to the latest time
                                         currentTime.setText(label.getText());
                                         //Set the best time if it has been beaten
                                         bestTime.setText(TimeConversion.secToString(TimeConversion.stringToSec(getBestTime(listOfTimes))));
                                         
                                         //Set the text for the current averages
                                         ao5Time.setText(ao5(listOfTimes));
                                         ao3Time.setText(ao3(listOfTimes));
                                         ao12Time.setText(ao12(listOfTimes));
                                         
                                         //Set the text for all the best averages if they have been beaten
                                         ao5BestTime.setText(getBestTime(listOfAo5));
                                         ao3BestTime.setText(getBestTime(listOfAo3));
                                         ao12BestTime.setText(getBestTime(listOfAo12));
                                         
                                         //Create the file for sorted times for all the averages and all times
                                         //sortTimes(selectedCube+"\\Rubik'sTimes.txt", selectedCube+"\\allTimesSorted.txt");
                                         //sortTimes(selectedCube+"\\ao3.txt", selectedCube+"\\ao3Sorted.txt");
                                         //sortTimes(selectedCube+"\\ao5.txt", selectedCube+"\\ao5Sorted.txt");
                                         //sortTimes(selectedCube+"\\ao12.txt", selectedCube+"\\ao12Sorted.txt");
                                         
                                         //Create a new scramble
                                         ((MyPanel)algorithmDisplay).newScramble();
                                         algorithm.setText("<html>"+((MyPanel)algorithmDisplay).getAlgorithm()+"</html>");
                                         algorithmDisplay.repaint();
                                         
                                         //Tripped bool is set to true 
                                         tripped = true;
                                                                                  }
                                 } catch (IOException e1) {
                                     e1.printStackTrace();
                                 }
                             }
                    		
                    		 //Check if space has been released, time is already running and the tripped bool is false
	                		 if (e.getKeyCode() == e.VK_SPACE && start && !tripped) {
	                			 //If so, start the timer and set the background color to black
	                			 label.setForeground(Color.black);
	                			 start = !start;
	                		 } else if (e.getKeyCode() == e.VK_ESCAPE) { //Else if the key released is escape
	                			 //If the timer is already started
	                			 if (!start) {
	                				 //Stop it
	                				 start = !start;
	                				 //Set the text back to 0.00
	                				 label.setText("00.00");
	                                
	                				 //Create new scramble and display it
	                				 ((MyPanel)algorithmDisplay).newScramble();
	                				 algorithm.setText("<html>"+((MyPanel)algorithmDisplay).getAlgorithm()+"</html>");
	                				 algorithmDisplay.repaint();
	                			 }
	                		 }
	                		} else {
	                			//If none of these are met, that means the timer cannot be started so just turn the text back to black
	                			label.setForeground(Color.black);
	                		}
                    	
                    	//Reset all the flags and the inner timer
                    	checkForHolding = false;
                        holdingRedCount=0;
                        checkForHoldingFlag = false;
                    }//end keyReleased

                    @Override
                    public void keyTyped(KeyEvent e) {
                            
                    }//end keyTyped
                };//end keyListener
                
                //Add the key listener to the frame and list
                frame.addKeyListener(keyListener);
                list.addKeyListener(keyListener);
        }//End initialize
}//End class
