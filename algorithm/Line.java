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
		super (p1.getDimension());
		mP1 = p1.clone();
		mP2 = p2.clone();
		mNormedLineVector = new DoubleMatrix (mP1.getDimension(), 1);
		for (int cCoord = 0; cCoord < mP1.getDimension(); ++cCoord)
			mNormedLineVector.setCell (cCoord, 0, (double)p2.getPosition().get(cCoord) - (double)p1.getPosition().get (cCoord));
		mNormedLineVector = normVector (mNormedLineVector);
	}
	
	/**
	 * @return 2 column matrix: [lineVector, first position vector]
	 */
	public DoubleMatrix loadEquationMatrix()
	{
		DoubleMatrix eq = new DoubleMatrix (mP1.getDimension(), 2);
		for (int cDim = 0; cDim < mP1.getDimension(); ++cDim)
		{
			eq.setCell(cDim, 0, this.mNormedLineVector.getCell(cDim, 0));
			eq.setCell(cDim, 1, (double) (mP1.getPosition(cDim)));
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
	
	/**
	 * @return a list containing the start and end point of this line
	 */
	public ArrayList <IntegerMatrix> getPoints()
	{
		ArrayList <IntegerMatrix> points = new ArrayList<IntegerMatrix>();
		points.add (mP1.toVector());
		points.add (mP2.toVector());
		return points;
	}
	
	/**
	 * @param l2 line to compare orientation with
	 * @return true if this and l2 are parallel (or possibly identical)
	 */
	public boolean isSameOrientation (Line l2)
	{
		DoubleMatrix neg = new DoubleMatrix(mNormedLineVector.getRows(), 1);
		for (int cRow = 0; cRow < mNormedLineVector.getRows(); ++cRow)
			neg.setCell(cRow, 0, -mNormedLineVector.getCell (cRow, 0));
		return (this.mNormedLineVector.equals (l2.mNormedLineVector) || neg.equals(l2.mNormedLineVector));
	}
	
	private Glue mP1, mP2;
	private DoubleMatrix mNormedLineVector;
}
