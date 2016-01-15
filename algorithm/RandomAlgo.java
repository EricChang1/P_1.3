package algorithm;

import java.util.ArrayList;
import java.util.Random;
import algorithm.Matrix.*;

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
	public RandomAlgo (Container container, <Resourceclass> pieces)
	{
		mContainer = container.clone();
		mPieces = pieces.clone();
		mPlacingDone = false;
	}
	
	@Override
	/**
	 * runs the algorithm
	 */
	public void run() 
	{
		while (!mPlacingDone)
		{
			ArrayList <Integer> shuffledVertices = getShuffledVertexList();
			doRandomPlacements(shuffledVertices);
		}
		
	}
	
	/**
	 * @return shuffled list of indices to vertices in container
	 */
	private ArrayList <Integer> getShuffledVertexList()
	{
		ArrayList <Integer> verts = new ArrayList <Integer>(); 
		for (int cVertex = 0; cVertex < mContainer.getNumberOfVertices(); ++cVertex)
			verts.add (new Integer (cVertex));
		Random rand = new Random (System.currentTimeMillis());
		for (int cShuffle = 0; cShuffle < mContainer.getNumberOfVertices(); ++cShuffle)
		{
			int iSwap1 = 0, iSwap2 = 0;
			while (iSwap1 == iSwap2)
			{
				iSwap1 = rand.nextInt(verts.size()); 
				iSwap2 = rand.nextInt(verts.size());
			}
			Integer temp = verts.get(iSwap1);
			verts.set (iSwap1, verts.get(iSwap2));
			verts.set (iSwap2, temp);
		}
		return verts;
	}
	
	/**
	 * tries to place a piece at vertices in vertexList until possible
	 * @param vertexList list of vertices giving order in which vertices are tried
	 */
	private void doRandomPlacements (ArrayList <Integer> vertexList)
	{
		Random gen = new Random();
		boolean placed = false;
		int iVertex = 0;
		while (!placed && iVertex < vertexList.size())
		{
			Block place = mPieces.getBlock();
			ArrayList <Position> relats = mContainer.getRelativePlacements(place, vertexList.get (iVertex));
			Position randPos = relats.get (gen.nextInt (relats.size()));
			if (mContainer.checkPositionOverlap (place, randPos))
			{
				mContainer.placeBlock(place, randPos);
				placed = true;
			}
			else
				++iVertex;
		}
		
		if (!placed)
			mPlacingDone = true;
	}
	
	private Container mContainer;
	private <ResourceClass> mPieces;
	private boolean mPlacingDone;
}
