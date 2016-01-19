package algorithm;

import java.util.ArrayList;
import algorithm.Matrix.*;

public class DynamicAlgo extends Algorithm 
{
	public DynamicAlgo() {}
	
	public void init (Container container, ArrayList <Resource> pieces)
	{
		super.init(container, pieces);
		mLookupTable = new LookupTable(container.getDimensions(0), container.getDimensions(1), container.getDimensions(2), pieces.size());
	}
	
	public void run()
	{
		int dimension = 3;
		Glue zeroPos = new Glue (new IntegerMatrix (dimension, 1));
		
		for (int cDepth = 0; cDepth <= getContainer().getDimensions(0); ++cDepth)
		{
			for (int cWidth = 0; cWidth <= getContainer().getDimensions(1); ++cWidth)
			{
				for (int cHeight = 0; cHeight <= getContainer().getDimensions(2); ++cHeight)
				{
					for (int cPiece = 0; cPiece < getPieces().size(); ++cPiece)
					{
						Block bRef = getPieces().get(cPiece).getBlock();
						double prev = 0, current = 0;
						if (cHeight > 0)//look up S[d, w, h - 1, k]
							prev = mLookupTable.get(cDepth, cWidth, cHeight - 1, cPiece);
						else if (cWidth > 0)//look up S[d, w - 1, h, k]
							prev = mLookupTable.get(cDepth, cWidth - 1, cHeight, cPiece);
						else if (cDepth > 0)
							prev = mLookupTable.get(cDepth - 1, cWidth, cHeight, cPiece);
						if (bRef.getDimensions(0) <= cDepth && bRef.getDimensions(1) <= cWidth &&
							bRef.getDimensions(2) <= cHeight)
						{
							Container c = new Container (cDepth, cHeight, cWidth);
							c.placeBlock(bRef, zeroPos);
							current += c.getValue();
							ArrayList <Cuboid> emptyCubes = c.decomposeIntoCuboids();
							//problem: order matters
							//solution: brute force
							for (Cuboid emptyC : emptyCubes)
							{
								IntegerMatrix size = new IntegerMatrix(dimension, 1);
								ArrayList <DoubleMatrix> cubeVecs = emptyC.getVectors();
								for (int cDim = 0; cDim < dimension; ++cDim)
									size.setCell(cDim, 0, (int)(double)cubeVecs.get(cDim).getCell(cDim, 0));
								current += mLookupTable.get(size.getCell(0, 0), size.getCell(1, 0), size.getCell(2, 0), cPiece - 1);
							}
						}
					}
				}
			}
		}
	}
	
	LookupTable mLookupTable;
}
