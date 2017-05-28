
/**
* TXTView is the view for the Tetris game in text format
* It displays information to the user. 
* It is updated by the controller.
*/

public class TXTView{
	// holds instructions for Tetris
	String instructions;
	// holds current message
	String message;


	/**
	* Constructor. Initializes instance variables.
	*/
	public TXTView(){
		
	}



	/**
	* Create the display for instructions
	*/

	public void createDisplay(){

	}
	
	public void draw(TPiece a){
		String[][] converted = convert(a);
		for (int i=0; i<converted[0].length; i++){
			for (int j=0; j<converted.length; j++){
				System.out.print(converted[j][i]);
			}
			System.out.println();
		}
	}
	
	public void drawBoard(int[][] board){
		int [][] convertedX = board;
		// creates empty array of string arrays (first dimension)
		String[][] converted = new String [convertedX.length][];
		// lays out dimension and copy at 2nd dimension
		for (int i =0; i< convertedX.length; i++){
			String[] length = new String[convertedX[i].length];
			converted[i] = length;
		}
		// going through every dimension in 2D Array and translate from 1/0 to x,space
		for (int i=0; i<converted.length; i++){
			for (int j=0; j<converted[i].length; j++){
				if (convertedX[i][j] == 1){
					converted[i][j] = "X";
				}
				else{
					converted[i][j] = " ";
				}
			}
		}
		
		// print out new string arrays
		System.out.println("----------");
		for (int i = 0; i < converted[0].length; i++){
			for (int j =0; j<converted.length; j++){
				System.out.print(converted[j][i]);
			}
				System.out.println();
		}
		System.out.println("----------");
			
	}
	
	
	
	/**
	 * convert int array into string array for displaying
	 */
	public String[][] convert (TPiece a){
		int [][] convertedX = a.getPiece();
		// creates empty array of string arrays
		String[][] converted = new String [convertedX.length][];
		// lays out dimension and copy at 2nd dimension
		for (int i =0; i< convertedX.length; i++){
			String[] length = new String[convertedX[i].length];
			converted[i] = length;
		}
		
		
		
		
		for (int i=0; i<converted.length; i++){
			for (int j=0; j<converted[i].length; j++){
				if (convertedX[i][j] == 1){
					converted[i][j] = "X";
				}
				else{
					converted[i][j] = " ";
				}
			}
		}
		return converted;
	}


	/**
	* Called by the controller
	* Updates the score seen by user
	*/
	public void updateScore(int[] score){

	}
}