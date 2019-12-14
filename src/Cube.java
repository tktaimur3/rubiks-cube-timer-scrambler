

public abstract class Cube {
	
	//data field\\	
	
	private String scrambleAlg = "";
	private int cubeSize;
	private char[][] frontFace;
	private char[][] backFace;
	private char[][] leftFace;
	private char[][] rightFace;
	private char[][] upFace;
	private char[][] downFace;
	
	//data field\\
	
	
	
	//constructors\\
	
	Cube(){		
	}
	
	Cube(int cubeSize, int base, int amplitude){
		setCubeSize(cubeSize);
		fillArrays();
		scramble(base, amplitude);
	}
	
	//constructors\\
	
	
	
	//methods\\
	
	void fillArrays(){
		
		frontFace = new char[cubeSize][cubeSize];
		backFace = new char[cubeSize][cubeSize];
		leftFace = new char[cubeSize][cubeSize];
		rightFace = new char[cubeSize][cubeSize];
		upFace = new char[cubeSize][cubeSize];
		downFace = new char[cubeSize][cubeSize];	
		
		for(int i = 0; i < cubeSize; i++) {
			for(int k = 0; k < cubeSize; k++) {
				frontFace[i][k] = 'g';
			}
		}
		
		for(int i = 0; i < cubeSize; i++) {
			for(int k = 0; k < cubeSize; k++) {
				backFace[i][k] = 'b';
			}
		}
		
		for(int i = 0; i < cubeSize; i++) {
			for(int k = 0; k < cubeSize; k++) {
				leftFace[i][k] = 'o';
			}
		}
		
		for(int i = 0; i < cubeSize; i++) {
			for(int k = 0; k < cubeSize; k++) {
				rightFace[i][k] = 'r';
			}
		}
		
		for(int i = 0; i < cubeSize; i++) {
			for(int k = 0; k < cubeSize; k++) {
				upFace[i][k] = 'w';
			}
		}
		
		for(int i = 0; i < cubeSize; i++) {
			for(int k = 0; k < cubeSize; k++) {
				downFace[i][k] = 'y';
			}
		}
		
		
	}

	
	void scramble(int base, int amplitude){ 
		int numMoves = (  (int)(Math.random() * (amplitude+1)) ) + base;
		int move; 
		int rotationType;
		int lastMove = 0;
		
		for(int i = 0; i < numMoves; i++) {
			move = (int) (Math.random() * 6) + 1;
			rotationType = (int) (Math.random() * 3) + 1;
			
			if(move != lastMove){
				lastMove = move;
				switch(move) {			
				case 1: 
						for(int k = 0; k < rotationType; k++){
							frontRotation();
						}
						
						switch(rotationType) {
							case 1: scrambleAlg += "F ";
									break;
								
							case 2: scrambleAlg += "F2 ";
									break;
								
							case 3: scrambleAlg += "F' ";
									break;
							
							default: //invalid
									break;			
						}
						
						break;
				
				case 2: 
					for(int k = 0; k < rotationType; k++){
						backRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "B ";
								break;
							
						case 2: scrambleAlg += "B2 ";
								break;
							
						case 3: scrambleAlg += "B' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 3: 
					for(int k = 0; k < rotationType; k++){
						leftRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "L ";
								break;
							
						case 2: scrambleAlg += "L2 ";
								break;
							
						case 3: scrambleAlg += "L' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 4: 
					for(int k = 0; k < rotationType; k++){
						rightRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "R ";
								break;
							
						case 2: scrambleAlg += "R2 ";
								break;
							
						case 3: scrambleAlg += "R' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 5: 
					for(int k = 0; k < rotationType; k++){
						upRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "U ";
								break;
							
						case 2: scrambleAlg += "U2 ";
								break;
							
						case 3: scrambleAlg += "U' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 6: 
					for(int k = 0; k < rotationType; k++){
						downRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "D ";
								break;
							
						case 2: scrambleAlg += "D2 ";
								break;
							
						case 3: scrambleAlg += "D' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
				default:
						break;			
			}
				
			}
			else{
				i--;
			}
			
			
			
		}
	}

	
	void frontRotation() {
		innerRotation(frontFace);
		
		int len = cubeSize-1;
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = upFace[len][len-i];
			upFace[len][len-i] = leftFace[i][len];
			leftFace[i][len] = downFace[0][i];
			downFace[0][i] = rightFace[len-i][0];
			rightFace[len-i][0] = temp[len-i];
		}		
	}
	
	void backRotation() { 
		innerRotation(backFace);
		
		int len = cubeSize - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = upFace[0][i];
			upFace[0][i] = rightFace[i][len];
			rightFace[i][len] = downFace[len][len-i];
			downFace[len][len-i] = leftFace[len-i][0];
			leftFace[len-i][0] = temp[len-i];
		}	
		
	}
	
	void leftRotation() {
		innerRotation(leftFace);
		
		int len = cubeSize - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = upFace[len-i][0];
			upFace[len-i][0] = backFace[i][len];
			backFace[i][len] = downFace[len-i][0];
			downFace[len-i][0] = frontFace[len-i][0];
			frontFace[len-i][0] = temp[len-i];
		}	
				
	}
	
	void rightRotation() { 
		innerRotation(rightFace);
		
		int len = cubeSize - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = upFace[i][len];
			upFace[i][len] = frontFace[i][len];
			frontFace[i][len] = downFace[i][len];
			downFace[i][len] = backFace[len-i][0];
			backFace[len-i][0] = temp[len-i];
		}	
				
	}
	
	void upRotation() { 
		innerRotation(upFace);
		
		int len = cubeSize - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = backFace[0][i];
			backFace[0][i] = leftFace[0][i];
			leftFace[0][i] = frontFace[0][i];
			frontFace[0][i] = rightFace[0][i];
			rightFace[0][i] = temp[len-i];
		}	
					
	}
	
	void downRotation() { 
		innerRotation(downFace);
		
		int len = cubeSize - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = frontFace[len][len-i];
			frontFace[len][len-i] = leftFace[len][len-i];
			leftFace[len][len-i] = backFace[len][len-i];
			backFace[len][len-i] = rightFace[len][len-i];
			rightFace[len][len-i] = temp[len-i];
		}	
					
	}
		
	
	void innerRotation(char[][] face){
		int len = face.length - 1;
		char[] temp = new char[face.length];
		
		//works for corners
		temp[0] = face[0][0];		
		face[0][0] = face[len][0];
		face[len][0] = face[len][len];
		face[len][len] = face[0][len];
		face[0][len] = temp[0];
		
		
		//works for outermost ring
		for(int i = 1; i < len; i++) {
			temp[i] = face[0][i];
		}		
		
		for(int i = 1; i < len; i++){
			//left to top
			face[0][len-i] = face[i][0];
			
			//bottom to left
			face[i][0] = face[len][i];
			
			//right to bottom
			face[len][i] = face[len-i][len];
			
			//top (temp) to right
			face[len-i][len] = temp[len-i];			
		}
		
		//4x4 and up (performs prior moves plus inner stuff)
		if(face.length > 3){
			
			//works for inner corners
			temp[0] = face[1][1];		
			face[1][1] = face[len-1][1];
			face[len-1][1] = face[len-1][len-1];
			face[len-1][len-1] = face[1][len-1];
			face[1][len-1] = temp[0];	
			
			//NEED TO PATCH FOR 5X5
			for(int i = 2; i < len-1; i++) {
				temp[i] = face[1][i];
			}		
			
			for(int i = 2; i < len-1; i++){
				//left to top
				face[1][len-i] = face[i][1];
				
				//bottom to left
				face[i][1] = face[len-1][i];
				
				//right to bottom
				face[len-1][i] = face[len-i][len-1];
				
				//top (temp) to right
				face[len-i][len-1] = temp[len-i];			
			}
			
		}
		
	}
	
	
	void displayFaces(){
		int len = cubeSize;
		
		System.out.println("\nFront");
		for(int i = 0; i < len; i++){
			for(int k = 0; k < len; k++){
				System.out.print(frontFace[i][k]);
			}
			System.out.println();
		}
		
		System.out.println("\nBack");
		for(int i = 0; i < len; i++){
			for(int k = 0; k < len; k++){
				System.out.print(backFace[i][k]);
			}
			System.out.println();
		}
		
		System.out.println("\nLeft");
		for(int i = 0; i < len; i++){
			for(int k = 0; k < len; k++){
				System.out.print(leftFace[i][k]);
			}
			System.out.println();
		}
		
		System.out.println("\nRight");
		for(int i = 0; i < len; i++){
			for(int k = 0; k < len; k++){
				System.out.print(rightFace[i][k]);
			}
			System.out.println();
		}
		
		System.out.println("\nUp");
		for(int i = 0; i < len; i++){
			for(int k = 0; k < len; k++){
				System.out.print(upFace[i][k]);
			}
			System.out.println();
		}
		
		System.out.println("\nDown");
		for(int i = 0; i < len; i++){
			for(int k = 0; k < len; k++){
				System.out.print(downFace[i][k]);
			}
			System.out.println();
		}
		
		
	}
	
	//methods\\
	
	
	
	//getters and setters\\
	
	public String getScrambleAlg() {
		return scrambleAlg;
	}		
	public void setScrambleAlg(String scrambleAlg) {
		this.scrambleAlg = scrambleAlg;
	}

		
	public int getCubeSize() {
		return cubeSize;
	}	
	public void setCubeSize(int cubeSize) {
		this.cubeSize = cubeSize;
	}
	

	
	public char[][] getFrontFace() {
		return frontFace;
	}
	public void setFrontFace(char[][] frontFace) {
		this.frontFace = frontFace;
	}

	
	
	public char[][] getBackFace() {
		return backFace;
	}
	public void setBackFace(char[][] backFace) {
		this.backFace = backFace;
	}

	
	
	public char[][] getLeftFace() {
		return leftFace;
	}
	public void setLeftFace(char[][] leftFace) {
		this.leftFace = leftFace;
	}
	

	
	public char[][] getRightFace() {
		return rightFace;
	}
	public void setRightFace(char[][] rightFace) {
		this.rightFace = rightFace;
	}

	
	
	public char[][] getUpFace() {
		return upFace;
	}
	public void setUpFace(char[][] upFace) {
		this.upFace = upFace;
	}

	
	
	public char[][] getDownFace() {
		return downFace;
	}
	public void setDownFace(char[][] downFace) {
		this.downFace = downFace;
	}

	

	//getters and setters\\	
}
