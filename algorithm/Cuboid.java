package algorithm;

import java.util.ArrayList;

import algorithm.Matrix.*;

/**
 * class modelling a cuboid
 * @author martin
 */
public class Cuboid extends GeoShape 
{

	public Cuboid(Glue p1, Glue p2) 
	{
		super(p1, p2);
		
		IntegerMatrix diff = new IntegerMatrix(getDimension(), 1);
		for (int cDim = 0; cDim < getDimension(); ++cDim)
			diff.setCell(cDim, 0, p2.getPosition(cDim) - p1.getPosition (cDim));
		mV1 = new DoubleMatrix (getDimension(), 1);
		mV1.setCell (0, 0, diff.getCell(0, 0));
		mV2 = new DoubleMatrix(getDimension(), 1);
		mV2.setCell (1, 0, diff.getCell (1, 0));
		mV3 = new DoubleMatrix (getDimension(), 1);
		mV3.setCell (2, 0, diff.getCell (2, 0));
	}

	@Override
	public ArrayList<DoubleMatrix> getVectors() 
	{
		ArrayList <DoubleMatrix> vecs = new ArrayList<Matrix.DoubleMatrix>();
		vecs.add (mV1.clone());
		vecs.add (mV2.clone());
		vecs.add (mV3.clone());
		return vecs;
	}

	@Override
	public DoubleMatrix loadEquationMatrix() 
	{
		DoubleMatrix eq = new DoubleMatrix (3, 4);
		eq.copyValues(mV1, 0, 0, 0, 0, getDimension(), 1);
		eq.copyValues(mV2, 0, 1, 0, 0, getDimension(), 1);
		eq.copyValues(mV3, 0, 2, 0, 0, getDimension(), 1);
		eq.copyValues(getFirst().toDoubleMatrix(), 0, 3, 0, 0, getDimension(), 1);
		return eq;
	}
	
	public Container toContainer()
	{
		IntegerMatrix diff = new IntegerMatrix(getDimension(), 1);
		for (int cDim = 0; cDim < getDimension(); ++cDim)
			diff.setCell(cDim, 0, getSecond().getCell(cDim, 0) - getFirst().getCell(cDim, 0));
		return new Container (diff.getCell(0, 0), diff.getCell(1, 0), diff.getCell(2, 0));
	}
	
	private DoubleMatrix mV1, mV2, mV3;
}
