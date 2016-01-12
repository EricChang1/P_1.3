package algorithm;

import algorithm.Matrix.*;

/**
 * class modelling a line
 * @author martin
 */
public class Line 
{
	
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
			mNormedLineVector.setCell (cCoord, 0, (double)p1.getPosition().get(cCoord) - (double)p2.getPosition().get (cCoord));
		mNormedLineVector = normVector (mNormedLineVector);
	}
	
	
	public boolean doIntersect (Line l2)
	{
		if (l2.mP1.getDimension() != this.mP1.getDimension())
			throw new LineDimensionMismatchException ("l2 does not have same dimension");
		
		//for begin points p1, p2 belonging to two different lines
		//for vectors v1, v2 belonging to two different lines this needs to hold
		//p1 + r * v1 = p2 + s * v2
		//for every dimension index x, y, s and r need to be the same (it is sufficient to check 1)
		//s = (v1(x)(p2(y) - p1(y)) - v1(y)(p2(x) - p1(x))) / v2(y)v1(x) - v2(x)v1(y)

		double s = 0.0;
		for (int cCoord = 1; cCoord < mP1.getDimension(); ++cCoord)
		{
			double testS = calcLineIntersectParam(this.mP1, l2.mP1, this.mNormedLineVector, l2.mNormedLineVector, 0, cCoord);
			if (cCoord == 1)
				s = testS;
			else if (!floatEquals (s, testS))
				return false;
		}
		if (!l2.isScalarOnLine (s))
			return false;
		
		//calculate parameter r using parameter s
		double r = l2.mP1.getPosition().get(0) + s * l2.mNormedLineVector.getCell(0, 0);
		r -= this.mP1.getPosition().get(0);
		r /= this.mNormedLineVector.getCell(0, 0);
		if (!this.isScalarOnLine(r))
			return false;
		
		return true;
	}
	
	/**
	 * @param l2 line to compare orientation with
	 * @return true if this and l2 are paralell (or possibly identical
	 */
	public boolean isSameOrientation (Line l2)
	{
		return this.mNormedLineVector.equals (l2.mNormedLineVector);
	}
	
	/**
	 * @param scalar scalar of normed vector in line
	 * @return true if mP1 + mNormedLineVector * scalar <= mP2
	 */
	public boolean isScalarOnLine (double scalar)
	{
		for (int cDim = 0; cDim < mP1.getDimension(); ++cDim)
		{
			double coord = (double)mP1.getPosition().get(cDim) + mNormedLineVector.getCell(cDim, 0) * scalar;
			if (mP1.getPosition().get(cDim) < mP2.getPosition().get(cDim) && coord > mP2.getPosition().get(cDim))
				return false;
			if (mP1.getPosition().get(cDim) > mP2.getPosition().get(cDim) && coord < mP2.getPosition().get(cDim))
				return false;
		}
		return true;
	}
	
	
	private Glue mP1, mP2;
	private DoubleMatrix mNormedLineVector;
}
