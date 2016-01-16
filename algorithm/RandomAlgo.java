package algorithm;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author martin
 *	algorithm which chooses one piece and one vertex in container randomly
 *	chosen piece gets placed at random location
 *	pieces are placed until no vertex is available anymore
 */
public class RandomAlgo extends Algorithm
{
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
		super.run();
		while (!isAlgoDone())
		{
			ArrayList <Integer> shuffledVertices = getShuffledIndices(getContainer().getNumberOfVertices());
			ArrayList<Integer> shuffledPieces = getShuffledIndices(getPieces().size());
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
				Resource res = getPieces().get(blockList.get(iBlock));
				if (!res.isEmpty())
				{
					//be careful about copying here
					ArrayList<Position> relats = getContainer().getRelativePlacements(res.getBlock(), vertexList.get(iVertex));
					for (Position relat : relats)
					{
						if (getContainer().checkPositionOverlap(res.getBlock(), relat))
						{
							getContainer().placeBlock(res.getBlock(), relat);
							placed = true;
						}
					}
				}
				++iBlock;
			}
			++iVertex;
		}
		
		if (!placed)
			setAlgoDone();
	}
}
