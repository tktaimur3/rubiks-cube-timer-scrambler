public class Cube2x2 extends Cube {
	
	
	//data field\\
	
	private int cubeSize = 2;
	
	//data field\\
	
	
	
	//constructors\\
	
	Cube2x2() {
		/*setFrontFace(new char[2][2]);
		setBackFace(new char[2][2]);
		setLeftFace(new char[2][2]);
		setRightFace(new char[2][2]);
		setUpFace(new char[2][2]);
		setDownFace(new char[2][2]);
		
		fillArrays();
		scramble();*/
		super(2, 9, 1);
	}
	
	//testing
	Cube2x2(char[][] frontFace){
		setFrontFace(frontFace);
	}
	
	//constructors\\
	
	
	
	//methods\\
	
	//all methods inherited from super with no overriding
	
	//methods\\
	
}
