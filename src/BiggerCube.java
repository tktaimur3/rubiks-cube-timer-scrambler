public class BiggerCube extends Cube {
	
	
	//data field\\
		
	//data field\\
	
	
	
	//constructors\\
	
	BiggerCube() {

	}
	
	BiggerCube(int cubeSize, int base, int amplitude){
		setCubeSize(cubeSize);
		fillArrays();
		scramble(base, amplitude);
	}
	
	
	void frontWideRotation(){
		frontRotation();
		
		int len = getCubeSize() - 1;
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = getUpFace()[len-1][len-i];
			getUpFace()[len-1][len-i] = getLeftFace()[i][len-1];
			getLeftFace()[i][len-1] = getDownFace()[1][i];
			getDownFace()[1][i] = getRightFace()[len-i][1];
			getRightFace()[len-i][1] = temp[len-i];
		}	
		
	}
	
	void backWideRotation() { 
		backRotation();
		
		int len = getCubeSize() - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = getUpFace()[1][i];
			getUpFace()[1][i] = getRightFace()[i][len-1];
			getRightFace()[i][len-1] = getDownFace()[len-1][len-i];
			getDownFace()[len-1][len-i] = getLeftFace()[len-i][1];
			getLeftFace()[len-i][1] = temp[len-i];
		}	
		
	}
	
	void leftWideRotation() {
		leftRotation();
		
		int len = getCubeSize() - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = getUpFace()[len-i][1];
			getUpFace()[len-i][1] = getBackFace()[i][len-1];
			getBackFace()[i][len-1] = getDownFace()[len-i][1];
			getDownFace()[len-i][1] = getFrontFace()[len-i][1];
			getFrontFace()[len-i][1] = temp[len-i];
		}	
				
	}
	
	void rightWideRotation() { 
		rightRotation();
		
		int len = getCubeSize() - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = getUpFace()[i][len-1];
			getUpFace()[i][len-1] = getFrontFace()[i][len-1];
			getFrontFace()[i][len-1] = getDownFace()[i][len-1];
			getDownFace()[i][len-1] = getBackFace()[len-i][1];
			getBackFace()[len-i][1] = temp[len-i];
		}	
				
	}
	
	void upWideRotation() { 
		upRotation();
		
		int len = getCubeSize() - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = getBackFace()[1][i];
			getBackFace()[1][i] = getLeftFace()[1][i];
			getLeftFace()[1][i] = getFrontFace()[1][i];
			getFrontFace()[1][i] = getRightFace()[1][i];
			getRightFace()[1][i] = temp[len-i];
		}	
					
	}
	
	void downWideRotation() { 
		downRotation();
		
		int len = getCubeSize() - 1;		
		
		//char[] temp = faceOnTop[topRow];
		char[] temp = new char[len+1];
		
		for(int i = 0; i < len+1; i++){
			temp[len-i] = getFrontFace()[len-1][len-i];
			getFrontFace()[len-1][len-i] = getLeftFace()[len-1][len-i];
			getLeftFace()[len-1][len-i] = getBackFace()[len-1][len-i];
			getBackFace()[len-1][len-i] = getRightFace()[len-1][len-i];
			getRightFace()[len-1][len-i] = temp[len-i];
		}	
					
	}
		
	
	//constructors\\
	
	
	
	//methods\\
	
	void scramble(int base, int amplitude) {
		int numMoves = (  (int)(Math.random() * (amplitude+1) ) ) + base; //should generate an int from 15 to 20
		int move; 
		int rotationType;
		int lastMove = 0;
		String scrambleAlg = "";
		
		for(int i = 0; i < numMoves; i++) {
						
			move = (int) (Math.random() * 12) + 1; //should generate an int from 1 to 6
			rotationType = (int) (Math.random() * 3) + 1; //should generate an int from 1 to 3
			
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
				
					
				case 7: 
					for(int k = 0; k < rotationType; k++){
						frontWideRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "Fw ";
								break;
							
						case 2: scrambleAlg += "Fw2 ";
								break;
							
						case 3: scrambleAlg += "Fw' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 8: 
					for(int k = 0; k < rotationType; k++){
						backWideRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "Bw ";
								break;
							
						case 2: scrambleAlg += "Bw2 ";
								break;
							
						case 3: scrambleAlg += "Bw' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 9: 
					for(int k = 0; k < rotationType; k++){
						leftWideRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "Lw ";
								break;
							
						case 2: scrambleAlg += "Lw2 ";
								break;
							
						case 3: scrambleAlg += "Lw' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 10: 
					for(int k = 0; k < rotationType; k++){
						rightWideRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "Rw ";
								break;
							
						case 2: scrambleAlg += "Rw2 ";
								break;
							
						case 3: scrambleAlg += "Rw' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 11: 
					for(int k = 0; k < rotationType; k++){
						upWideRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "Uw ";
								break;
							
						case 2: scrambleAlg += "Uw2 ";
								break;
							
						case 3: scrambleAlg += "Uw' ";
								break;
						
						default: //invalid
								break;			
					}
					
					break;
				
					
				case 12: 
					for(int k = 0; k < rotationType; k++){
						downWideRotation();
					}
					
					switch(rotationType) {
						case 1: scrambleAlg += "Dw ";
								break;
							
						case 2: scrambleAlg += "Dw2 ";
								break;
							
						case 3: scrambleAlg += "Dw' ";
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
		
		this.setScrambleAlg(scrambleAlg);
	}
	
	//insert any new rotations which have to be coded
	
	//methods\\
	
}
