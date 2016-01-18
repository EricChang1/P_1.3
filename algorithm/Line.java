package algorithm;

import java.util.ArrayList;

import algorithm.Matrix.*;

/**
 * class modelling a line
 * @author martin
 */
public class Line extends GeoShape
{
	/**
	 * constructs line defined between p1 and p2
	 * @param p1 start point of the line
	 * @param p2 end point of the line
	 */
	public Line (Glue p1, Glue p2)
	{
		super (p1, p2);
		mNormedLineVector = new DoubleMatrix (getFirst().getRows(), 1);
		for (int cCoord = 0; cCoord < getDimension(); ++cCoord)
			mNormedLineVector.setCell (cCoord, 0, (double)p2.getPosition().get(cCoord) - (double)p1.getPosition().get (cCoord));
		mNormedLineVector = normVector (mNormedLineVector);
	}
	
	/**
	 * @return 2 column matrix: [lineVector, first position vector]
	 */
	public DoubleMatrix loadEquationMatrix()
	{
		DoubleMatrix eq = new DoubleMatrix (getDimension(), 2);
		for (int cDim = 0; cDim < getDimension(); ++cDim)
		{
			eq.setCell(cDim, 0, this.mNormedLineVector.getCell(cDim, 0));
			eq.setCell(cDim, 1, (double) (getFirst().getCell(cDim, 0)));
		}
		return eq;
	}
	
	/**
	 * @return a n x 1 matrix representing the normed vector defining this line
	 */
	public ArrayList <DoubleMatrix> getVectors()
	{
		ArrayList <DoubleMatrix> vecs = new ArrayList<DoubleMatrix>();
		vecs.add (mNormedLineVector.clone());
		return vecs;
	}
	
	private DoubleMatrix mNormedLineVector;
}
