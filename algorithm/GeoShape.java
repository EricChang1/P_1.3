package algorithm;

import algorithm.Matrix.*;
import java.util.ArrayList;


public abstract class GeoShape 
{
	@SuppressWarnings("serial")
	public static class BadVectorsException extends IllegalArgumentException
	{
		public BadVectorsException(){}
		
		public BadVectorsException (String message) { super (message); }
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
	 * @param dimension dimension of subspace the shape is in
	 */
	public GeoShape (Glue p1, Glue p2) 
	{ 
		mP1 = p1.clone();
		mP2 = p2.clone();
	}
	
	/**
	 * @return list of vectors defining the shape
	 */
	public abstract ArrayList <DoubleMatrix> getVectors();
	
	/**
	 * @return matrix representing equation for subspace of shape (without delimiters)
	 */
	public abstract DoubleMatrix loadEquationMatrix();
	
	/**
	 * @return first fixed point
	 */
	public IntegerMatrix getFirst()
	{
		return mP1.toVector();
	}
	
	/**
	 * @return second fixed point
	 */
	public IntegerMatrix getSecond()
	{
		return mP2.toVector();
	}
	
	/**
	 * @param g2 shape to check for intersection with this for
	 * @return augmented matrix that can be used to determine the subspace of the intersection between this and g2
	 */
	public DoubleMatrix loadEquationMatrix (GeoShape g2)
	{
		DoubleMatrix eqT = loadEquationMatrix();
		DoubleMatrix eqG = g2.loadEquationMatrix();
		eqG.getScalarMatrix(-1.0, new DoubleMatrix (eqG.getRows(), eqG.getRows())).multiply(eqG, eqG);
		DoubleMatrix eq = new DoubleMatrix (getDimension(), eqT.getColumns() + eqG.getColumns() - 1);
		eq.copyValues(eqT, 0, 0, 0, 0, eqT.getRows(), eqT.getColumns() - 1);
		eq.copyValues(eqG, 0, eqT.getColumns() - 1, 0, 0, eqG.getRows(), eqG.getColumns() - 1);
		for (int cDim = 0; cDim < getDimension(); ++cDim)
		{
			double fixCoord = -(eqG.getCell(cDim, eqG.getColumns() - 1) + eqT.getCell(cDim, eqT.getColumns() - 1));
			eq.setCell(cDim, eq.getColumns() - 1, fixCoord);
		}
		return eq;
	}
	
	/**
	 * @return euclidean distance between defining points
	 */
	public double getDefiningPointDistance()
	{
		double dist = 0;
		for (int cDim = 0; cDim < getDimension(); ++cDim)
			dist += Math.pow (mP2.getPosition(cDim) - mP1.getPosition(cDim), 2);
		return Math.sqrt(dist);
	}
	
	/**
	 * @return dimension of the subspace the shape is in
	 */
	public int getDimension() { return mP1.getDimension(); }
	
	/**
	 * @param g2 shape to compare orientation with
	 * @return true if for all vectors of one shape, there are vectors of the same orientation in the other
	 */
	public boolean isSameOrientationisInRange (GeoShape g2)
	{
		ArrayList <DoubleMatrix> vT = this.getVectors();
		ArrayList <DoubleMatrix> vC = g2.getVectors();
		if (vT.size() < vC.size())
		{
			ArrayList <DoubleMatrix> tmp = vT;
			vT = vC;
			vC = tmp;
		}
		
		Matrix<Double> negT = vT.get(0).getScalarMatrix(-1.0, new DoubleMatrix (vT.get(0).getRows(), vT.get(0).getRows()));
		for (DoubleMatrix v : vC)
		{
			DoubleMatrix neg = new DoubleMatrix (v.getRows(), v.getColumns());
			negT.multiply(v, neg);
			boolean found = false;
			int cFindSame = 0;
			while (cFindSame < vT.size() && !found)
			{
				if (v.equals(vT.get(cFindSame)) || neg.equals(vT.get(cFindSame)))
					found = true;
				else
					++cFindSame;
			}
			if (!found)
				return false;
		}
		return true;
	}
	
	/**
	 * @param p point to check whether it is within the range
	 * @return true if p is contained in the smallest rectangular shape containing all the points of this shape
	 */
	public boolean isInRange (Glue p)
	{
		for (int cDim = 0; cDim < getDimension(); ++cDim)
		{
			int max = Math.max (getFirst().getCell (cDim, 0), getSecond().getCell (cDim, 0));
			int min = Math.min (getFirst().getCell (cDim, 0), getSecond().getCell (cDim, 0));
			if (p.getPosition(cDim) < min || p.getPosition(cDim) > max)
				return false;
		}
		return true;
	}
	
	
	/**
	 * @param g shape to check range overlapping for
	 * @return true if ranges overlap
	 */
	public boolean doesRangeOverlap (GeoShape g)
	{
		for (int cDim = 0; cDim < getDimension(); ++cDim)
		{
			int minT, maxT, minG, maxG;
			minT = Math.min(this.getFirst().getCell(cDim, 0), this.getSecond().getCell(cDim, 0));
			maxT = Math.max(this.getFirst().getCell(cDim, 0), this.getSecond().getCell(cDim, 0));
			minG = Math.min(g.getFirst().getCell(cDim, 0), g.getSecond().getCell(cDim, 0));
			maxG = Math.max(g.getFirst().getCell(cDim, 0), g.getSecond().getCell(cDim, 0));
			if ((minG < minT || maxG > maxT) && (minT < minG || maxT > maxG))
				return false;
		}
		return true;
	}
	
	private Glue mP1, mP2;
}
