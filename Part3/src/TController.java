import java.awt.event.*;

public class TController implements KeyListener{

	public TLogic logic;
	public TPiece activePiece;
/**
*Constructor, Sets up model and view instance variables
*/
public TController(){
	logic = new TLogic(10,18);
}

/**
*Necessary for KeyListerner
*Listens for key input from user.
*calls updateSpeed and updateOrientation
*/

	public void keyTyped (KeyEvent e){
		
	}

	public void keyPressed(KeyEvent e){

	}

	public void keyReleased (KeyEvent e){

	}



	/** 
	*Updates speed with userInput
	*Calls getScore
	*/
	public void updateSpeed(int userInput){

	}
	
	
}
