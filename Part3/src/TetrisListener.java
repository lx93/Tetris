import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisListener implements ActionListener, KeyListener {
	TLogic logic;
	TController controller;
	TetrisPanel panel;
	
	public TetrisListener (TLogic logic, TController controller, TetrisPanel panel){
		this.logic = logic;
		this.controller = controller;
		this.panel = panel;
		panel.addKeyListener(this);
	}
	


public void keyTyped(KeyEvent e){}
public void keyPressed(KeyEvent e){
	if (e.getKeyCode()== KeyEvent.VK_A){
		logic.move(1, 0, controller.activePiece);
	}
	if (e.getKeyCode()== KeyEvent.VK_D){
		logic.move(-1, 0, controller.activePiece);
	}
	if (e.getKeyCode()== KeyEvent.VK_S){
		logic.move(0, -1, controller.activePiece);
	}
	if (e.getKeyCode()== KeyEvent.VK_Q){
		logic.rotate(controller.activePiece,+1);
	}
	if (e.getKeyCode()== KeyEvent.VK_E){
		logic.rotate(controller.activePiece,-1);
	}
	if (logic.detectCollision(controller.activePiece, (int)controller.activePiece.position.getX(), (int)controller.activePiece.position.getY()+1)){
		controller.activePiece = logic.spawn(3, 0, logic.randomPiece());	
		}
	panel.repaint();
}

public void keyReleased(KeyEvent e){}


	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		logic.fall(controller.activePiece);
		if (logic.detectCollision(controller.activePiece, (int)controller.activePiece.position.getX(), (int)controller.activePiece.position.getY()+1)){
			controller.activePiece = logic.spawn(3, 0, logic.randomPiece());	
			}
		logic.findDeadRows();
		panel.updateScore();
		panel.repaint();
	}

}
