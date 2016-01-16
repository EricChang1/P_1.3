package algorithm;

import java.util.ArrayList;

import algorithm.Matrix.IntegerMatrix;

/**
 * class performing a hill climbing algorithm
 * pieces are drawn from a set of pieces
 * @author martin
 */
public class HillClimber extends Algorithm
{
	/**
	 * parametric constructor
	 * @param container starting container
	 * @param pieces pieces available
	 */
	public HillClimber (Container container, ArrayList <Resource> pieces, SelectionHeuristic select, EvaluationHeuristic eval)
	{
		super (container, pieces);
		mSelect = select;
		mEval = eval;
	}
	
	/**
	 * Sets position where the algorithm will start placing
	 * @param start
	 */
	public void setStartingPosition (Position start)
	{
		mStartingPosition = start;
	}
	
	/**
	 * runs the algorithm
	 */
	public void run()
	{
		super.run();
		if (getContainer().checkPositionInside 1(mStartingPosition))
			throw new IllegalStateException ("starting position is not within container");
		
		boolean init = true;
		
		while (!isAlgoDone())
		{
			Block chosen = selectPiece();
			if (chosen != null)
			{	
				Glue pos = null;
				if (!init)
					pos = placeHeuristic (chosen);
				else
					pos = mStartingPosition;
				
				Glue optimal = explore(pos, chosen);
				if (optimal != null && getContainer().checkPositionOverlap(chosen, optimal))
					getContainer().placeBlock (chosen, optimal);
				else
					setAlgoDone();
			}
			else
				setAlgoDone();
			init = false;
		}
	}
	
	/**
	 * searches adjacent places for higher score
	 * @param initial where place is first placed
	 * @param place piece to place
	 * @return locally optimal position
	 */
	private Glue explore (Glue initial, Block place)
	{
		Glue optimal = initial;
		double bestScore = Double.MIN_VALUE;
		boolean change;
		do
		{
			change = false;
			
			for (int cCoord = 0; cCoord < optimal.getDimension(); ++cCoord)
			{
				for (int cMove = -1; cMove <= 1; cMove += 2)
				{
					IntegerMatrix mat = optimal.toVector();
					mat.setCell(cCoord, 0, mat.getCell(cCoord, 0) + cMove);
					Position variant = new Position (mat);
					Container clone = getContainer().clone();
					
					if (placeAndCompare (variant, place, clone, bestScore))
					{
						optimal = variant;
						bestScore = mEval.getScore (clone);
						change = true;
					}
				}
			}
		}while (change);
		return optimal;
	}
	
	/**
	 * @return the evaluation heuristic's preferred piece
	 */
	private Block selectPiece()
	{
		ArrayList <Block> available = new ArrayList <Block>();
		for (Resource r : getPieces())
		{
			if (!r.isEmpty())
				available.add (r.getBlock());
		}
		int selectIndex = mSelect.getBestBlock (available);
		if (selectIndex < available.size())
		{
			getPieces().get(selectIndex).deduct();
			return getPieces().get(selectIndex).getBlock();
		}
		return null;
	}
	
	/**
	 * @param place piece to place
	 * @return position where place is heuristically placed
	 */
	private Position placeHeuristic (Block place)
	{
		Position maxPos = null;
		double maxVal = Double.MIN_VALUE;
		for (int cVertex = 0; cVertex < getContainer().getNumberOfVertices(); ++cVertex)
		{
			ArrayList <Position> relatives = getContainer().getRelativePlacements(place, cVertex);
			for (Position relative : relatives)
			{
				Container clone = getContainer().clone();
				if (placeAndCompare (relative, place, clone, maxVal))
				{
					maxPos = relative;
					maxVal = mEval.getScore (clone);
				}
			}
		}
		return maxPos;
	}
	
	/**
	 * @param pos position where to place block
	 * @param block piece to place
	 * @param clone clone of container to place block in
	 * @param bestScore best score seen
	 * @return true if block can be placed and yields a score higher than bestScore
	 */
	private boolean placeAndCompare (Position pos, Block block, Container clone, double bestScore)
	{
		if (clone.checkPositionInside(pos) && clone.checkPositionOverlap(block, pos))
		{
			clone.placeBlock(block, pos);
			if (mEval.getScore (clone) > bestScore)
				return true;
		}
		return false;
	}
	
	private SelectionHeuristic mSelect;
	private EvaluationHeuristic mEval;
	private Glue mStartingPosition;
}
