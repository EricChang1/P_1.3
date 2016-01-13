package algorithm;
import java.util.ArrayList;
import java.util.HashMap;


public class Container extends Block
{
	@SuppressWarnings("serial")
	public static class BlockNotFoundException extends IllegalArgumentException
	{
		public BlockNotFoundException () {}
		
		public BlockNotFoundException (Position posFail)
		{
			super ("no corresponding block in container at position " + posFail.toString());
		}

		public BlockNotFoundException (String message) 
		{
			super (message);
		}
		
	}	
	
	@SuppressWarnings("serial")
	public static class WrongPositionException extends IllegalArgumentException
	{
		public WrongPositionException() {}
		
		public WrongPositionException (Position posFail)
		{
			super("position cant be used " + posFail.toString());
		}
		
		public WrongPositionException (String message, Position posFail)
		{
			super(message + " " + posFail.toString());
		}
	}
	
	/**
	 * @param dimensions depth, width, height (x1,x2,x3) of the container
	 * Calls the Block constructor with the default_value of 0
	 */
	public Container (ArrayList <Integer> dimensions)
	{
		super(dimensions, );
	}
	
	/**
	 * @param dimensions depth, width, height (x1,x2,x3) of the container
	 * @return ArrayList<Position> Containing the eight vectors that span the container
	 */
	public static ArrayList<Position> createVectors(ArrayList<Integer> dimensions) {
		Position p8 = new Position(dimensions);
		Position p1 = new Position();
	}
	
	/**	Places a block at the specified position
		@param block the block object to place
		@param pos the position to place block 
		Precondition: block is placeable at position
		Postcondition: Container will contain block at pos if pos does not refer to another block already placed
		@throws WrongPositionException
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public void placeBlock (Block block, Position pos) throws WrongPositionException
	{
		if (checkPositionOverlap(block, pos)) {
				Glue glue = pos.clone();
				mGluedBlocks.put(glue, block);
				addShape(block);
		}
		else throw new WrongPositionException("position is not inside of the container", pos);
	}
	
	/** @param pos Position queried block is at
		@return block at pos
		@throws BlockNotFoundException
	**/
	public Block getBlockAt (Position pos) throws BlockNotFoundException
	{
		if (Block.getValue(pos) != 0) {
			return Block.getValue(pos);
		}
		else throw new BlockNotFoundException(pos);
	}
	
	/** @return a string representation of the container**/
	public String toString()
	{
		return "Height: " + this.getHeight() + ",\n width: " + this.getWidth() + "\nglues at: " + this.mGluedBlocks;
	}
	
	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos does not cause any overlapping with previously placed blocks
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public boolean checkPositionOverlap (Block block, Position pos)
	{
		
		ArrayList<Line> blockVertices = new ArrayList<Line>();
		
		for (int i=0; i < block.getNumberOfVertices(); i++) {
			Matrix<Integer> p = block.getVertex(i);
			ArrayList<Matrix.IntegerMatrix> connections = p.lookUpConnections(i);
			for (int j=0; j<connections.size(); j++) {
				//duplicates alarm!
				Line l1 = new Line(p, connections.get(j));
				blockVertices.add(l1);
			}
		}
		
		int index = getNumberOfVertices();
		
		for (int i=0; i < index; i++) {
			ArrayList<Matrix.IntegerMatrix> connections = lookUpConnections(i);
			Matrix<Integer> point = getVertex(i);
			
			for (int j=0; j < connections.size(); j++) {
				Line l2 = new Line(point, connections.getCell(j));
				if (blockVertices.get(j).doIntersect(l2)) return false;
			}
		}
		return true;
	}

	public int getAmountOfBlocks() {
		return mGluedBlocks.size();
	}
	
	private HashMap <Glue, Block> mGluedBlocks;
	
	
}
