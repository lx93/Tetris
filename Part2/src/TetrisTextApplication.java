import java.awt.event.*;
import java.util.*;


public class TetrisTextApplication {
	
	public static void main(String[] args){
	Scanner scanner = new Scanner(System.in);
	TController controller = new TController();
	TLogic logic = controller.logic;
	controller.activePiece = logic.spawn(3, 0, logic.randomPiece());
	TXTView view = new TXTView();
	System.out.println("Please enter a move (l,r,d,z,x) or type Quit to end.");
	view.drawBoard(logic.getBoard());
	
	while (true){
	System.out.println("Please enter a move (l,r,d,z,x) or type Quit to end.");
	String usrInput = scanner.next();
	processInput(usrInput, controller, logic);
	logic.findDeadRows();
	view.drawBoard(logic.getBoard());
	}
		
	}
	
	
	
	
	public static void processInput (String scanner, TController controller, TLogic logic){
		if (scanner.equals("l")){
			logic.move(1, 0, controller.activePiece);
		}
		if (scanner.equals("r")){
			logic.move(-1, 0, controller.activePiece);
		}
		if (scanner.equals("d")){
			logic.move(0, -1, controller.activePiece);
		}
		if (scanner.equals("u")){
			logic.move(0, 1, controller.activePiece);
		}
		if (scanner.equals("z")){
			logic.rotate(controller.activePiece,+1);
		}
		if (scanner.equals("x")){
			logic.rotate(controller.activePiece,-1);
		}
		if (logic.detectCollision(controller.activePiece, (int)controller.activePiece.position.getX(), (int)controller.activePiece.position.getY()+1)){
			controller.activePiece = logic.spawn(3, 0, logic.randomPiece());	
			}
	}
	
	
	
	
}
