package algorithm;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author martin
 *	algorithm which chooses one piece and one vertex in container randomly
 *	chosen piece gets placed at random location
 *	pieces are placed until no vertex is available anymore
 */
public class RandomAlgo implements Runnable
{
	/**
	 * constructs algo from container and pieces
	 * @param container container to place pieces in
	 * @param pieces pieces available
	 */
	public RandomAlgo (Container container, ArrayList<Resource> pieces)
	{
		mContainer = container.clone();
		mPieces = (ArrayList<Resource>) pieces.clone();
		mPlacingDone = false;
	}
	
	/**
	 * @return shuffled list of indices to vertices in container
	 */
	public ArrayList <Integer> getShuffledIndices (int maxIndex)
	{
		ArrayList <Integer> indices = new ArrayList <Integer>(); 
		for (int cIndex = 0; cIndex < maxIndex; ++cIndex)
			indices.add (new Integer (cIndex));
		Random rand = new Random (System.currentTimeMillis());
		for (int cShuffle = 0; cShuffle < maxIndex; ++cShuffle)
		{
			int iSwap1 = 0, iSwap2 = 0;
			while (iSwap1 == iSwap2)
			{
				iSwap1 = rand.nextInt(indices.size()); 
				iSwap2 = rand.nextInt(indices.size());
			}
			Integer temp = indices.get(iSwap1);
			indices.set (iSwap1, indices.get(iSwap2));
			indices.set (iSwap2, temp);
		}
		return indices;
	}
	
	@Override
	/**
	 * runs the algorithm
	 */
	public void run() 
	{
		while (!mPlacingDone)
		{
			ArrayList <Integer> shuffledVertices = getShuffledIndices(mContainer.getNumberOfVertices());
			ArrayList<Integer> shuffledPieces = getShuffledIndices(mPieces.size());
			doRandomPlacements(shuffledVertices, shuffledPieces);
		}
		
	}
	
	/**
	 * tries to place a piece at vertices in vertexList until possible
	 * @param vertexList list of vertices giving order in which vertices are tried
	 */
	private void doRandomPlacements (ArrayList <Integer> vertexList, ArrayList<Integer> blockList)
	{
		boolean placed = false;
		int iVertex = 0;
		while (!placed && iVertex < vertexList.size())
		{
			int iBlock = 0;
			while (!placed && iBlock < blockList.size())
			{
				Resource res = mPieces.get(blockList.get(iBlock));
				if (!res.isEmpty())
				{
					//be careful about copying here
					ArrayList<Position> relats = mContainer.getRelativePlacements(res.getBlock(), vertexList.get(iVertex));
					for (Position relat : relats)
					{
						if (mContainer.checkPositionOverlap(res.getBlock(), relat))
						{
							mContainer.placeBlock(res.getBlock(), relat);
							placed = true;
						}
					}
				}
				++iBlock;
			}
			++iVertex;
		}
		
		if (!placed)
			mPlacingDone = true;
	}
	
	private Container mContainer;
	private ArrayList <Resource> mPieces;
	private boolean mPlacingDone;
}
