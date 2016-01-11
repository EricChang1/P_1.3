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
	 * @param dimensions Length of the container in the three directions
	 * Calls the Block constructor with the default_value of 0
	 */
	public Container (ArrayList <Integer> dimensions)
	{
		//no look up documenation
		super(dimensions, DEFAULT_VALUE);
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
				//Nothing implemented in glue that I could use to place the block
				//placing is done by the container => store in hashmap
				block.glueBlock(pos);
				//@TODO edit make sure adjacency matrix is edited and list of vectors in basic shape
				//this container inherits from
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
		else throw BockNotFoundException(pos);
	}
	
	/** @return a string representation of the container**/
	public String toString()
	{
		return "Height: " + this.getHeight() + ", width: " + this.getWidth() + " glues at: " + this.mGluedBlocks;
	}
	
	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos remains within the container
		The position refers to the top-left corner of the smallest possible rectangle containig the block

		 **edit: not really needed anymore :(
		 *
	public boolean checkPositionValid (Block block, Position pos)
	{
		ArrayList<Integer> position = pos.getPosition();
			
		//First check: Is pos (equivalent to minPos) inside the borders of this container?
		for (int i=0; i<position.length; i++) {
			int minDim = position.get(i);
			if ((minDim < 0) || (minDim > this.getDimension(i))) return false;
		}
		
		Position maxDim = block.getMaxDimension(pos);
		ArrayList<Integer> maxPos = maxDim.getPosition();
		
		//Second check: Is the maxPos also inside of this container`?
		for (int i=0; i<maxPos.length; i++) {
			int maxDim = maxPos.get(i);
			if ((maxDim < 0)||(maxDim > this.getDimension(i))) return false;
		}
				
		return true;
	}

		**/



	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos does not cause any overlapping with previously placed blocks
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public boolean checkPositionOverlap (Block block, Position pos)
	{
		ArrayList<Integer> minPos = pos.getPosition();
		Position maxDim = block.getMaxDimension(pos);
		ArrayList<Integer> maxPos = maxDim.getPosition();

		ArrayList<Double> l1 = new Line(minPos, maxPos);
		//doesn't work
		//check: for every connection c1 between two vertices of block
			//and for every connection c2 within the container (including pieces already placed)
				//whether c1 and c2 intersect
		for (int i=0; i<mGluedBlocks.size(); i++){
			if (Line.doIntersect(l1, mGluedBlocks.get(i))) return false;
		}
		return true;
	}
	
	//unnecessary
	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos remains within the container and does not cause any overlapping with previously placed blocks
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public boolean checkPosition (Block block, Position pos)
	{
		//if (checkPositionValid(block, pos)) {
			if (checkPositionOverlap(block, pos)) return true;				
		//}
		return false;
	}

	private HashMap <Glue, Block> mGluedBlocks;
	
	private final static int DEFAULT_VALUE;
}
