
public class dummyPiece extends TPiece{
		PieceType type;
	
		public int [][] getPiece(){
			return new int[][]{new int[]{1}};
		} 
		
		public dummyPiece(int x, int y, PieceType type){
			super(x,y,type);
		}
		
	}
	
	
	