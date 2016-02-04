package algorithm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import algorithm.Matrix.*;


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
	 * @param d depth of container
	 * @param w width of container
	 * @param h height of container
	 * @return list of vectors defining a cuboid container of given d, w, h
	 */
	public static ArrayList <IntegerMatrix> computeInitDimVectors (int d, int w, int h)
	{
		ArrayList <IntegerMatrix> vecs = new ArrayList<Matrix.IntegerMatrix>();
		IntegerMatrix vec = null;
		for (int cVertex = 0; cVertex < 8; ++cVertex)
		{
			vec = new IntegerMatrix(3, 1);
			if (cVertex > 3)
				vec.setCell(0, 0, d);
			if (cVertex % 4 == 2 || cVertex % 4 == 3)
				vec.setCell(1, 0, w);
			if (cVertex % 2 == 1)
				vec.setCell(2, 0, h);
			vecs.add (vec);
		}
		return vecs;
	}
	
	/**
	 * constructs an adjacency matrix for verts for a cuboid container
	 * @param verts set of vectors in the same dimension
	 * @return adjacency matrix for verts
	 */
	public static IntegerMatrix computeInitAdjacencyMatrix (ArrayList <IntegerMatrix> verts)
	{
		IntegerMatrix adj = new IntegerMatrix (verts.size(), verts.size());
		for (int cVertex = 0; cVertex < verts.size(); ++cVertex)
		{
			for (int cConnect = 0; cConnect < verts.size(); ++cConnect)
			{
				if (cVertex != cConnect)
				{
					int cSame = 0, cDiff = 0;
					for (int cCoord = 0; cCoord < verts.get(cVertex).getRows(); ++cCoord)
					{
						if (verts.get(cVertex).getCell(cCoord, 0).equals(verts.get(cConnect).getCell (cCoord, 0)))
							++cSame;
						else
							++cDiff;
					}
					if (cSame == 2 && cDiff == 1)
					{
						adj.setCell(cVertex, cConnect, 1);
						adj.setCell(cConnect, cVertex, 1);
					}
				}
			}
		}
		return adj;
	}
	
	/**
	 * @param d given depth
	 * @param w given width
	 * @param h given height
	 * @return cuboid basic shape of d x w x h
	 */
	public static BasicShape constructInitShape (int d, int w, int h)
	{
		ArrayList <IntegerMatrix> vertices = computeInitDimVectors(d, w, h);
		IntegerMatrix adjMat = computeInitAdjacencyMatrix(vertices);
		return new BasicShape(vertices, adjMat);
	}
	
	/**
	 * @param dimensions depth, width, height (x1,x2,x3) of the container
	 * Calls the Block constructor with the default_value of 0
	 */
	public Container (int d, int w, int h)
	{
		super(constructInitShape (d, w, h), 0);
		mGluedBlocks = new HashMap<Glue, Block>();
	}
	
	/**
	 * @return deep copy of this
	 */
	public Container clone()
	{
		Container clone = new Container (getDimensions(0), getDimensions(1), getDimensions(2));
		for (Entry <Glue, Block> entry : mGluedBlocks.entrySet())
			clone.mGluedBlocks.put(entry.getKey().clone(), entry.getValue().clone());
		
		return clone;
	}
	
	/**	Places a block at the specified position
		@param block the block object to place
		@param pos the position to place block 
		Precondition: block is placeable at position
		Postcondition: Container will contain block at pos if pos does not refer to another block already placed
		The position refers to the uppermost top-left corner of the smallest possible cuboid containing the block
	**/
	public void placeBlock (Block block, Glue pos)
	{
		Glue glue = pos.clone();
		block.glue (pos);
		mGluedBlocks.put(glue, block);
		addShape(block);
		//@TODO keep track of volume of placed blocks
	}
	
	/** @param pos Position queried block is at
		@return block at pos as clone
		@throws BlockNotFoundException
	**/
	public Block getBlockAt (Position pos) throws BlockNotFoundException
	{
		if (mGluedBlocks.containsKey(pos))
			return mGluedBlocks.get(pos).clone();
		else 
			throw new BlockNotFoundException ("There is no block at" + pos);
	}
	
	/** @return a string representation of the container**/
	public String toString()
	{
		//return "Height: " + getDimensions(0) + ",\n width: " + getDimensions(1) + "\nglues at: " + this.mGluedBlocks;
		return "container with " + mGluedBlocks.size() + " blocks";
	}
	
	/**
	 * @param pos position to check
	 * @return true if there is a block associated with pos
	 */
	public boolean hasBlockAt (Position pos)
	{
		return mGluedBlocks.containsKey(pos);
	}
	
	/**	@param block the block object to place
		@param pos the position to place block 
		@return true if block placed at pos does not cause any overlapping with previously placed blocks
		The position refers to the top-left corner of the smallest possible rectangle containig the block
	**/
	public boolean checkPositionOverlap (Block block, Glue pos)
	{
		Glue prevPos = block.getGlue();
		block.glue(pos);
		ArrayList<Rectangle> blockSides = block.getRectangles();
		ArrayList <Line> containerLines = this.getConnectingLines();
		
		for (Rectangle sBlock : blockSides)
		{
			for (Line lContainer : containerLines)
			{
				//Problem: need to check whether line from block interesects a place within the container => rectangle class
				IntersectionSolver is = new IntersectionSolver(sBlock, lContainer);
				if (is.getSolutionType() == IntersectionSolver.Result.ONE && is.isWithinBounds())
				{
					Glue isect = is.getIntersection();
					//if intersection is not begin or end
					if (block.getVertexIndex(isect.toVector()) >= block.getNumberOfVertices())
					{
						block.glue (prevPos);
						return false;
					}
				}
			}
		}
		block.glue (prevPos);
		return true;
	}
	
	/**
	 * @param mStartingPosition position to check
	 * @return true if position is within the container
	 */
	public boolean checkPositionInside (Glue mStartingPosition)
	{
		for (int cCoord = 0; cCoord < mStartingPosition.getDimension(); ++cCoord)
		{
			int coord = mStartingPosition.getPosition(cCoord);
			if (coord < 0 || coord > getDimensions(cCoord))
				return false;
		}
		return true;
	}
	
	/**
	 * @return number of blocks placed in this container
	 */
	public int getAmountOfBlocks() {
		return mGluedBlocks.size();
	}
	
	private HashMap <Glue, Block> mGluedBlocks;
}
