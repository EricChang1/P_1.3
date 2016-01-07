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

	public Container (ArrayList <Integer> dimensions)
	{
	
	}
	
	/**	Places a block at the specified position
		@param block the block object to place
		@param pos the position to place block 
		Precondition: block is placeable at position
		Postcondition: Container will contain block at pos if pos does not refer to another block already placed
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public void placeBlock (Block block, Position pos)
	{

	}
	
	/** @param pos Position queried block is at
		@return block at pos
		@throws BlockNotFoundException
	**/
	public Block getBlockAt (Position pos) throws BlockNotFoundException
	{
	
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

	}
	
	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos does not cause any overlapping with previously placed blocks
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public boolean checkPositionOverlap (Block block, Position pos)
	{

	}
	
	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos remains within the container and does not cause any overlapping with previously placed blocks
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public boolean checkPosition (Block block, Position pos)
	{

	}

	private HashMap <Glue, Block> mGluedBlocks;
}
