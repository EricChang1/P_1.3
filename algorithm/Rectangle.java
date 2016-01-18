package algorithm;

import java.util.ArrayList;

import algorithm.Matrix.*;

public class Rectangle extends GeoShape {

	public Rectangle (Glue p1, Glue p2) 
	{
		super (p1, p2);
		determineVectors();
	}

	@Override
	public ArrayList<DoubleMatrix> getVectors() 
	{
		ArrayList <DoubleMatrix> vecs = new ArrayList<Matrix.DoubleMatrix>();
		vecs.add(mVec1.clone());
		vecs.add(mVec2.clone());
		return vecs;
	}

	@Override
	public DoubleMatrix loadEquationMatrix() 
	{
		DoubleMatrix eq = new DoubleMatrix(getDimension(), 3);
		eq.copyValues(mVec1, 0, 0, 0, 0, getDimension(), 1);
		eq.copyValues(mVec2, 0, 1, 0, 0, getDimension(), 1);
		eq.copyValues(getFirst().toDoubleMatrix(), 0, 2, 0, 0, getDimension(), 1);
		return eq;
	}
	
	private void determineVectors()
	{
		for (int cDim = 0; cDim < getDimension(); ++cDim)
		{
			int pDiff = getFirst().getCell(cDim, 0) - getSecond().getCell(cDim, 0);
			if (pDiff != 0)
			{
				DoubleMatrix v = new DoubleMatrix(getDimension(), 1);
				v.setCell(cDim, 0, pDiff);
				if (mVec1 == null)
					mVec1 = v;
				else if (mVec2 == null)
					mVec2 = v;
				else
					throw new BadVectorsException ("vector belongs to a higher order subspace than a plane");	
			}
		}
		if (mVec1 == null || mVec2 == null)
			throw new BadVectorsException ("vector belongs to a lower order subspace than a plane");
		mVec1 = normVector(mVec1);
		mVec2 = normVector(mVec2);
	}

	private DoubleMatrix mVec1, mVec2;
}
