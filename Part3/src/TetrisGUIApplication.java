import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public class TetrisGUIApplication {	
	public static void main (String[]args){
		int height = 800;
		int width = 400;
		TController controller = new TController();
		TLogic logic = controller.logic;
		controller.activePiece = logic.spawn(3, 0, logic.randomPiece());
		
		
		JFrame GUIFrame = new JFrame ("Tetris!!");
		GUIFrame.setLayout(new BorderLayout());
		
		//Score
		JLabel label;
		label = new JLabel();
		GUIFrame.add(label, BorderLayout.SOUTH);
		
		TetrisPanel panel = new TetrisPanel(logic.height,logic.width,logic, controller, label);
		GUIFrame.setSize(width,height);
		GUIFrame.add(panel);
		
		


		// exit normally on closing the window
		GUIFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		GUIFrame.setVisible( true );
			
		//create Listener
		TetrisListener listener = new TetrisListener(logic,controller,panel);
		
		//Timer
		Timer timer = new Timer(1000,listener);
		timer.start();
		

		}
	
	
	

}
