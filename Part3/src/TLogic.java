import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
* TLogic is the model for Tetris
* It stores information about the current state of the game.
* It has functions that can be called by the controller.
*
*/



public class TLogic{

	private int score;
	private ArrayList<TPiece> alivePieces;
	public int width,height;



	public TLogic(int width, int height){
		alivePieces = new ArrayList<TPiece>();
		this.width = width;
		this.height = height;
	}
	
	
	public void fall(TPiece piece){
		Point2D point = piece.position;
		int x = (int)point.getX();
		int y = (int)point.getY();
		if (!detectCollision(piece, x, y + 1) && inBound(piece, x, y + 1)){
			piece.position.setLocation(x,y+=1);
		}

	}
	
	public PieceType randomPiece(){
		Random randomEnum = new Random();
		PieceType piece = PieceType.values()[randomEnum.nextInt(PieceType.values().length)];
		return piece;
	}
	
	
	public boolean detectCollision (TPiece currentPiece, int posX, int posY){
		int [][] board = getBoard(currentPiece);
		// Get that piece's shape as a 2d array
		int[][] shape = currentPiece.getPiece();
		// Go through each column the piece occupies on the board
		for (int x = 0; x<shape.length; x++){
			// Go through each row the piece occupies on the board (within the designated column)
			for (int y = 0; y < shape[0].length; y++) {
				int xOnBoard = posX + x;
				int yOnBoard = posY + y;
				if (inBound(x + posX,y + posY)) {
					if (board[xOnBoard][yOnBoard] == 1){
						if (shape[x][y] == 1){
							return true;
						}
					}					
				}
				else if (posY+y >= height){
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	
	public int[][] getBoard(TPiece ignoredPiece){
		// Create a 2d int array of the size of the board
				int[][] board = new int[width][height];
				// Loop through all the active pieces 
				for (int i=0; i<alivePieces.size();i++){
					// Get a reference to the piece at the current index
					TPiece currentPiece = alivePieces.get(i);
					
					if (ignoredPiece == currentPiece){
						continue;
					}
					// Get that piece's shape as a 2d array
					int[][] shape = currentPiece.getPiece();
					// Go through each column the piece occupies on the board
					for (int x = 0; x<shape.length; x++){
						// Go through each row the piece occupies on the board (within the designated column)
						for (int y = 0; y < shape[0].length; y++) {
							Point2D pos = currentPiece.position;
							// Set the cell on the board to either 1 or 0 based on whether part of the piece is there (pulling from the shape property)
							if (inBound(x+(int)pos.getX(),y+(int)pos.getY())){
							board[x + (int) pos.getX()][y + (int) pos.getY()] = shape[x][y];
							}
						}
					}
				}
				// Return the whole (entirely filled in) board
					return board;
		
	}
	
	
	
	public void move (int xx, int yy,TPiece piece){
		Point2D point = piece.position;
		int x = (int)point.getX();
		int y = (int)point.getY();

		piece.position.setLocation (x-xx,y-yy);
		
		if (!inBound(piece)){
			piece.position.setLocation(x, y);
		}
		
		
	}
	
	
	public int[][] getBoard(){
		// Create a 2d int array of the size of the board
		int[][] board = new int[width][height];
		// Loop through all the active pieces 
		for (int i=0; i<alivePieces.size();i++){
			// Get a reference to the piece at the current index
			TPiece currentPiece = alivePieces.get(i);
			// Get that piece's shape as a 2d array
			int[][] shape = currentPiece.getPiece();
			// Go through each column the piece occupies on the board
			for (int x = 0; x<shape.length; x++){
				// Go through each row the piece occupies on the board (within the designated column)
				for (int y = 0; y < shape[0].length; y++) {
					Point2D pos = currentPiece.position;
					// Set the cell on the board to either 1 or 0 based on whether part of the piece is there (pulling from the shape property)
					if (inBound(x+(int)pos.getX(),y+(int)pos.getY()) && shape[x][y] == 1){
					board[x + (int) pos.getX()][y + (int) pos.getY()] = shape[x][y];
					}
				}
			}
		}
		// Return the whole (entirely filled in) board
			return board;
	}
	
	
	
	public boolean inBound(int xxx, int yyy){
		if (xxx >= 0 && xxx< width && yyy < height && yyy >= 0 ){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean inBound (TPiece piece, int posX, int posY) {
		boolean isInBound = true;
		Point2D[] bound = piece.getBound(posX, posY);
		for(int i = 0; i < bound.length; i++) {
			isInBound &= inBound((int) bound[i].getX(), (int) bound[i].getY());
		}
		return isInBound;
	}
	
	public boolean inBound (TPiece piece){
		boolean isInBound = true;
		Point2D [] bound = piece.getBound();
		for (int i = 0; i< bound.length; i++){
			isInBound &= inBound((int)bound[i].getX(),(int)bound[i].getY());
		}
		return isInBound;
	}

	
	public ArrayList<TPiece> getActive (){
		return alivePieces;
	}
	
	/**
	 * this function rotates the TPiece
	 * @param piece
	 * @param deltaRotation
	 */
	public void rotate (TPiece piece, int deltaRotation){
		piece.rotation += deltaRotation;
		piece.rotation %= 4;
		if (piece.rotation < 0) {
			piece.rotation += 4;
		}
		piece.refreshRows();
		if (!inBound(piece)){
			piece.rotation -= deltaRotation;
			piece.rotation %= 4;
			piece.refreshRows();
		}
	}
	
	
	/**
	 * This function is called when collision is detected and it creates a new TPieceb
	 * @param x
	 * @param y
	 * @param piece
	 * @return
	 */
	
	public TPiece spawn (int x, int y, PieceType piece){
		TPiece newPiece = new TPiece(x,y,piece); //just for fun, later change into random piecetype
		this.alivePieces.add(newPiece);
		score += 10;
		return newPiece;
	}
	
	
	public void findDeadRows(){
		int [][] board = getBoard();
		for (int i=0; i<height; i++){
			if (fullRow(i, board)) {
				killRow(i);
				score += 100;
			}
		}
	}
	
	public boolean fullRow(int row, int[][] board){
		for (int i=0; i<board.length; i++){
			if (board[i][row] == 0){
				return false;
			}
		}
		return true;
	}
	
	public void killRow(int row){
		for (int i=0; i<alivePieces.size();i++){
			alivePieces.get(i).killRow(row);
		}
	}
	
	
	/**
	* The getScore method returns the current score 
	* @return the current score
	*/
	public int getScore(){
		return score;
	}

}
