import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author Taimur
 * Purpose: to create a panel that displays the scrambled cube for the user to use as a reference.
 * Date: 21 January 2019
 */
class MyPanel extends JPanel {
	//Init the cube to be displayed
	private static Cube cube;
	
	//Init all the variables of the cube for each size
	private static int squareX = 150;
    private static int squareY = 90;
    private static int squareW = 15;
    private static int size2x2 = 20;
    private static int size3x3 = 15;
    private static int size4x4 = 11;
    private static int size5x5 = 9;
    
    /**
     * Default constructor
     */
    public MyPanel() {
    	
    }//End MyPanel
    
    /**
     * Constructor for creating a panel with a cube size
     * 
     * @param cubeSize the size of the cube to display
     */
    public MyPanel(int cubeSize) {
    	//Switch case for the cube size
    	switch (cubeSize) {
    	//If the cube size is 2, init a 2 sized cube and set the display length for each square
    	case 2: cube = new Cube2x2();
    		squareW = size2x2;
		break;
		//If the cube size is 3, init a 3 sized cube and set the display length for each square
    	case 3: cube = new Cube3x3();
    		squareW = size3x3;
    	break;
    	//If the cube size is 4, init a 4 sized cube and set the display length for each square
    	case 4: cube = new Cube4x4();
    		squareW = size4x4;
    	break;
    	//If the cube size is 5, init a 5 sized cube and set the display length for each square
    	case 5: cube = new Cube5x5();
    		squareW = size5x5;
		break;
    	}
    }//End MyPanel
    
    /**
     * Set the cube size
     * 
     * @param size the size of the cube to display
     */
    public void setCubeSize(int size) {
    	//Switch case for the cube size
    	switch (size) {
    	//If the cube size is 2, init a 2 sized cube and set the display length for each square
    	case 2: cube = new Cube2x2();
    		squareW = size2x2;
		break;
		//If the cube size is 3, init a 3 sized cube and set the display length for each square
    	case 3: cube = new Cube3x3();
    		squareW = size3x3;
    	break;
    	//If the cube size is 4, init a 4 sized cube and set the display length for each square
    	case 4: cube = new Cube4x4();
    		squareW = size4x4;
    	break;
    	//If the cube size is 5, init a 5 sized cube and set the display length for each square
    	case 5: cube = new Cube5x5();
    		squareW = size5x5;
		break;
    	}
    }//End setCubeSize

    /**
     * @param c the cube face array 
     * @param g the graphics element
     * @param sx the x coordinate of the face
     * @param sy the y coordinate of the face
     * @param size the cube size
     */
    public static void printFace (char[][] c, Graphics g, int sx, int sy, int size) {
    	
    	//Iterate through the array of face given and show that face with the color
    	for (int x = 0; x < size; x++) {
        	//Switch case for the face of a cube
        	switch (c[0][x]) {
        	//Set the color of the square to correspond with what character is in the current element
        	case 'g':
        		g.setColor(Color.GREEN);
        		break;
        	case 'o':
        		g.setColor(new Color(255, 165, 0));
        		break;
        	case 'b':
        		g.setColor(Color.BLUE);
        		break;
        	case 'r':
        		g.setColor(Color.RED);
        		break;
        	case 'w':
        		g.setColor(Color.WHITE);
        		break;
        	case 'y':
        		g.setColor(new Color(130, 130, 0));
        		break;
        	}
        	
        	//Draw the square at a certain coordinate with the size given, all relative to the starting point
			g.fillRect(sx + (x*(squareW+2)), sy, squareW, squareW);
			//Draw a black border around the squares
			g.setColor(Color.black);
			g.drawRect(sx + (x*(squareW+2)), sy, squareW, squareW);
	 		//Iterate through the array of face given and show that face with the color
	 		for (int y = 0; y < size; y++) {
				//Switch case for the face of a cube
				switch (c[y][x]) {
				//Set the color of the square to correspond with what character is in the current element
		     	case 'g':
		     		g.setColor(Color.GREEN);
		     		break;
		     	case 'o':
		     		g.setColor(new Color(255, 165, 0));
		     		break;
		     	case 'b':
		     		g.setColor(Color.BLUE);
		     		break;
		     	case 'r':
		     		g.setColor(Color.RED);
		     		break;
		     	case 'w':
		     		g.setColor(Color.WHITE);
		     		break;
		     	case 'y':
		     		g.setColor(Color.YELLOW);
		     		break;
		     	}
		     	
				//Draw the square at a certain coordinate with the size given, all relative to the starting point
				g.fillRect(sx + (x*(squareW+2)), sy + (y*(squareW+2)), squareW, squareW);
				//Draw a black border around the squares
				g.setColor(Color.black);
				g.drawRect(sx + (x*(squareW+2)), sy + (y*(squareW+2)), squareW, squareW);
			}
        }
    }//End printFace
    
    /**
	 * Show the entire cube net
	 *
     * @param cube the cube object itself
     * @param g the graphics element to draw
     * @param x the x-coordinate for the cube net
     * @param y the y-coordinate for the cube net
     * @param size the size of the cube
     */
    public static void printCube (Cube cube, Graphics g, int x, int y)	{
    	//Init separation integer
    	int separation = 0;
    	
    	//Switch case for size
    	switch (cube.getCubeSize()) {
	    	//If the size of the cube is 2, make the separation between each face 50
	    	case 2: separation = 50;
			break;
			//If the size of the cube is 2, make the separation between each square 55
	    	case 3: separation = 55;
	    	break;
	    	//If the size of the cube is 2, make the separation between each square 57
	    	case 4: separation = 57;
			break;
			//If the size of the cube is 2, make the separation between each square 58
	    	case 5: separation = 58;
			break;
    	}
    	
    	//Print each of the 6 faces in the cube net by calling the printFace method and separating them using the separation integer
    	printFace(cube.getFrontFace(), g, x, y, cube.getCubeSize());
        printFace(cube.getUpFace(), g, x, y-separation, cube.getCubeSize());
        printFace(cube.getDownFace(), g, x, y+separation, cube.getCubeSize());
        printFace(cube.getLeftFace(), g, x-separation, y, cube.getCubeSize());
        printFace(cube.getRightFace(), g, x+separation, y, cube.getCubeSize());
        printFace(cube.getBackFace(), g, x+(2*separation), y, cube.getCubeSize());
    }//End printCube
    
    
    /**
	 * Return the algorithm for the scramble
	 *
     * @return the algorithm made for the scramble
     */
    public static String getAlgorithm() {
    	return cube.getScrambleAlg();
    }//End getAlgorithm
    
    /**
     * Creates a new scramble for the cube	
     */
    public static void newScramble() {
    	//Switch case for the cube size
    	switch (cube.getCubeSize()) {
    	//If the cube size is 2, init a 2 sized cube and set the display length for each square
    	case 2: cube = new Cube2x2();
    		squareW = size2x2;
		break;
		//If the cube size is 3, init a 3 sized cube and set the display length for each square
    	case 3: cube = new Cube3x3();
    		squareW = size3x3;
    	break;
    	//If the cube size is 4, init a 4 sized cube and set the display length for each square
    	case 4: cube = new Cube4x4();
    		squareW = size4x4;
    	break;
    	//If the cube size is 5, init a 5 sized cube and set the display length for each square
    	case 5: cube = new Cube5x5();
    		squareW = size5x5;
		break;
    	}
    }//End newScramble
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        
        //Print the cube net
        printCube(cube, g, 90, 82);
    }//End paintComponent
}//End class