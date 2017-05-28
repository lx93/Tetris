import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TetrisPanel extends JPanel {
	
	int height;
	int width;
	TLogic logic;
	TController controller;
	JLabel label;
	
	
	public TetrisPanel(int height, int width, TLogic logic, TController controller, JLabel label){
		super( new BorderLayout() );
		this.height = height;
		this.width = width;
		this.logic = logic;
		this.label = label;
		this.controller = controller;
		setFocusable(true);

	}
	
	
	public void updateScore(){
		label.setText(logic.getScore()+"");
	}
	
	
	public void paint (Graphics g){
		int [][] board = logic.getBoard();
		for (int w =0; w< width; w++){
			for (int h=0; h<height; h++){
				if (board[w][h] == 1){
					g.setColor(Color.red);
				}
				else{
					g.setColor(Color.white);
				}
				g.fillRect(w*40,h*40,40,40);
			}
		}
	}
}
