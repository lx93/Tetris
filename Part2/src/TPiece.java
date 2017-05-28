import java.awt.geom.Point2D;

public class TPiece{
		public Point2D position;
		public int rotation;
		PieceType type;
		public boolean[] aliveRows;

		
		public static int[][][][] shape = {
				
				// I-Piece
			{
				{
					{1, 1, 1, 1}
				},
				{
					{1},
					{1},
					{1},
					{1},
				},
				{
					{1, 1, 1, 1}
				},
				{
					{1},
					{1},
					{1},
					{1},
				}
			},
				// J-Piece
			{
				{
					{1, 0, 0},
					{1, 1, 1}
				},		
				{
					{1, 1},
					{1, 0},
					{1, 0}
				},
				{
					{1, 1, 1},
					{0, 0, 1}
				},		
				{
					{0, 1},
					{0, 1},
					{1, 1}
				}
			},
				// L-Piece
			{
				{
					{0, 0, 1},
					{1, 1, 1}
				},
				{
					{1, 0},
					{1, 0},
					{1, 1}
				},
				{
					{1, 1, 1},
					{1, 0, 0}
				},	
				{
					{1, 1},
					{0, 1},
					{0, 1}
				}
			},
			
				// O-Piece
			{
				{
					{1, 1},
					{1, 1}
				},
				{
					{1, 1},
					{1, 1}
				},
				{
					{1, 1},
					{1, 1}
				},
				{
					{1, 1},
					{1, 1}
				}
				
			},
				// S-Piece
			{
				{
					{0, 1, 1},
					{1, 1, 0}
				},
				{
					{1, 0},
					{1, 1},
					{0, 1}
				},
				{
					{0, 1, 1},
					{1, 1, 0}
				},
				{
					{1, 0},
					{1, 1},
					{0, 1}
				}
			},
				// T-Piece
			{
				{
					{0, 1, 0},
					{1, 1, 1}
				},
				{
					{1, 0},
					{1, 1},
					{1, 0}
				},
				{
					{1, 1, 1},
					{0, 1, 0}
				},
				{
					{0, 1},
					{1, 1},
					{0, 1}
				}
			},
				// Z-Piece
			{
				{
					{1, 1, 0},
					{0, 1, 1}
				},
				{
					{0, 1},
					{1, 1},
					{1, 0}
				},
				{
					{1, 1, 0},
					{0, 1, 1}
				},
				{
					{0, 1},
					{1, 1},
					{1, 0}
				}
			}
		};
				
		
		
		
		
		public TPiece(int x, int y, PieceType type){
			position = new Point2D.Float(x,y);
			rotation = 0;
			this.type = type;
			refreshRows();
			}
		
		
		public void refreshRows(){
			aliveRows = new boolean[getOriginalPiece()[0].length];
			for (int i=0; i<aliveRows.length; i++){
				aliveRows[i] = true;
			}
		}
		
		
		public Point2D[] getBound(){
			int [][] piece = getPiece();
			Point2D[] bound = new Point2D [4];
			bound[0] = position;
			int x = (int)position.getX();
			int y = (int)position.getY();
			Point2D topRight = new Point2D.Float(x+piece.length-1,y);
			Point2D bottomLeft = new Point2D.Float(x,y+piece[0].length-1);
			Point2D bottomRight = new Point2D.Float(x+piece.length-1,y+piece[0].length-1);
			bound[1] = topRight;
			bound[2] = bottomLeft;
			bound[3] = bottomRight;
			return bound;
		}
					
		public Point2D[] getBound(int posX, int posY) {
			int [][] piece = getPiece();
			Point2D[] bound = new Point2D [4];
			bound[0] = position;
			int x = posX;
			int y = posY;
			Point2D topRight = new Point2D.Float(x+piece.length-1,y);
			Point2D bottomLeft = new Point2D.Float(x,y+piece[0].length-1);
			Point2D bottomRight = new Point2D.Float(x+piece.length-1,y+piece[0].length-1);
			bound[1] = topRight;
			bound[2] = bottomLeft;
			bound[3] = bottomRight;
			return bound;
		}
		
		public int countAliveRows(){
			int counter = 0;
			for (int i=0; i < aliveRows.length; i++){
				if (aliveRows[i]){
					counter+=1;
				}
			}
			return counter;
		}
		
		public int [][] getOriginalPiece(){
			return shape[this.type.ordinal()][rotation];
		}
		
		public int [][] getPiece(){
			int [][] originalShape = shape[this.type.ordinal()][rotation];
			int [][] aliveShape = new int[originalShape.length][countAliveRows()];
			int aliveRows = 0;
			for(int y = 0; y < originalShape[0].length; y++){ 
				if (this.aliveRows[y]){
					for (int x = 0; x < originalShape.length; x++){
						aliveShape [x][aliveRows] = originalShape[x][y]; 
					}
					aliveRows ++;
				}
			}
			return aliveShape;
		} 
		
		public void killRow(int y){
			int relativeY = y - (int)position.getY();
			if (relativeY >= 0 && relativeY < aliveRows.length){
				aliveRows[relativeY] = false;
				position.setLocation(position.getX(), position.getY()+1);
			}
		}
		
	}


