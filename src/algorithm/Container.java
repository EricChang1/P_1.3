package algorithm;
import java.util.ArrayList;


public static class Container extends Block
{
	public class BlockNotFoundException extends IllegalArgumentException
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
	
	public class WrongPositionException extends IllegalArgumentException
	{
		public IllegalArgumentException() {}
		
		public IllegalArgumentException(Position posFail)
		{
			super("position cant be used " + posFail.toString());
		}
		
		public IllegalArgumentException(String message, Position posFail)
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
		if (checkPositionValid(block, pos)) {
			if (checkPositionOverlap(block, pos)) {
				//Place Block at pos
			}
			else throw WrongPositionException("position overlaps", pos);
		}
		else throw WrongPositionException("position is not inside of the container", pos);
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
		
	}
	
	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos remains within the container
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
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
		
		ArrayList<Integer> lengthPos = new ArrayList<Integer>();
		
		int xDiff = maxPos.get(0) - minPos.get(0);
		
		
	}
	
	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos remains within the container and does not cause any overlapping with previously placed blocks
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public boolean checkPosition (Block block, Position pos)
	{
		if (checkPositionValid(block, pos)) {
			if (checkPositionOverlap(block, pos)) return true;				
		}
		return false;
	}

	private HashMap <Glue, Block> mGluedBlocks;
	
	private final int DEFAULT_VALUE;
}
