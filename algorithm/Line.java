package algorithm;

import algorithm.Matrix.*;

/**
 * class modelling a line
 * @author martin
 */
public class Line 
{
	
	@SuppressWarnings("serial")
	public static class LineDimensionMismatchException extends IllegalArgumentException
	{
		public LineDimensionMismatchException () {}
		
		public LineDimensionMismatchException (String message) { super (message); }
	}
	
	/**
	 * scales down v to length 1
	 * @param v vector given
	 * @return v scaled to length 1
	 */
	public static DoubleMatrix normVector (DoubleMatrix v)
	{
		DoubleMatrix normedV = v.clone();
		double length = Math.sqrt (v.vectorProduct (v.getColumn(0), v.getColumn(0)));
		for (int cRow = 0; cRow < v.getRows(); ++cRow)
			normedV.setCell (cRow, 0, v.getCell (cRow, 0) / length);
		return normedV;
	}
	
	/**
	 * Calculates parameter s for equation
	 * p1 + r*v1 = p2 + s * v2
	 * @param p1 start point of line 1
	 * @param p2 start point of line 2
	 * @param v1 vector defining line 1
	 * @param v2 vector defining line 2
	 * @param ind1 dimension index 1
	 * @param ind2 dimension index 2
	 * @return
	 */
	public static double calcLineIntersectParam (Glue p1, Glue p2, DoubleMatrix v1, DoubleMatrix v2, int ind1, int ind2)
	{
		double numSum1 = v1.getCell(ind1, 0) * (p1.getPosition().get(ind2) - p2.getPosition().get(ind2));
		double numSum2 = v1.getCell(ind2, 0) * (p2.getPosition().get(ind1) - p1.getPosition().get(ind1));
		double denom = v2.getCell(ind2, 0) * v1.getCell (ind1, 0) - v2.getCell(ind1, 0) * v1.getCell(ind2, 0);
		return ((numSum1 + numSum2) / denom);
	}
	
	/**
	 * Checks whether two floating types are equal, using a static epsilon
	 * @param d1 float 1
	 * @param d2 float 2
	 * @return whether float 1 == float 2
	 */
	public static boolean floatEquals (double d1, double d2)
	{
		double e = 0.00001;
		return (d1 - d2 >= -e && d1 - d2 <= e);
	}
	
	/**
	 * constructs line defined between p1 and p2
	 * @param p1 start point of the line
	 * @param p2 end point of the line
	 */
	public Line (Glue p1, Glue p2)
	{
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
			eq.setCell(cDim, 1, (double) (this.getFirst().getPosition(cDim)));
		}
		return eq;
	}
	
	/**
	 * @param l2 second line to check relation for
	 * @return linear equation in matrix form, where the vectors of this object and line vectors are first
	 */
	public DoubleMatrix loadEquationMatrix (Line l2)
	{
		DoubleMatrix eqL2 = l2.loadEquationMatrix(), eqThis = this.loadEquationMatrix();
		DoubleMatrix eq = new DoubleMatrix (mP1.getDimension(), 3);
		eq.copyValues(eqThis, 0, 0, 0, 0, eqL2.getRows(), 1);
		eq.copyValues(eqL2, 0, 1, 0, 0, eqL2.getRows(), 1);
		for (int cDim = 0; cDim < mP1.getDimension(); ++cDim)
			eq.setCell(cDim, 2, eqL2.getCell(cDim, 1) - eqThis.getCell(cDim, 1));
		return eq;
	}
	
	/**
	 * @return a n x 1 matrix representing the normed vector defining this line
	 */
	public DoubleMatrix getLineVector()
	{
		return mNormedLineVector.clone();
	}
	
	/**
	 * @return starting point of line
	 */
	public Glue getFirst()
	{
		return mP1;
	}
	
	/**
	 * @return end point of line
	 */
	public Glue getSecond()
	{
		return mP2;
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
