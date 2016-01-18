package algorithm;

import algorithm.Matrix.*;
import java.util.ArrayList;


public abstract class GeoShape 
{
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
	public GeoShape (int dimension) { mDimension = dimension; }
	
	/**
	 * @return list of vectors defining the shape
	 */
	public abstract ArrayList <DoubleMatrix> getVectors();
	
	/**
	 * @return list of points that delimit the shape
	 */
	public abstract ArrayList <IntegerMatrix> getPoints();
	
	/**
	 * @return matrix representing equation for subspace of shape (without delimiters)
	 */
	public abstract DoubleMatrix loadEquationMatrix();
	
	/**
	 * @param g2 shape to check for intersection with this for
	 * @return augmented matrix that can be used to determine the subspace of the intersection between this and g2
	 */
	public DoubleMatrix loadEquationMatrix (GeoShape g2)
	{
		DoubleMatrix eqT = loadEquationMatrix();
		DoubleMatrix eqG = g2.loadEquationMatrix();
		eqG.getScalarMatrix(-1.0).multiply(eqG, eqG);
		DoubleMatrix eq = new DoubleMatrix (mDimension, eqT.getColumns() + eqG.getColumns() - 1);
		eq.copyValues(eqT, 0, 0, 0, 0, eqT.getRows(), eqT.getColumns() - 1);
		eq.copyValues(eqG, 0, eqT.getColumns() - 1, 0, 0, eqG.getRows(), eqG.getColumns() - 1);
		for (int cDim = 0; cDim < mDimension; ++cDim)
		{
			double fixCoord = -(eqG.getCell(cDim, eqG.getColumns() - 1) + eqT.getCell(cDim, eqT.getColumns() - 1));
			eq.setCell(cDim, eq.getColumns() - 1, fixCoord);
		}
		return eq;
	}
	
	/**
	 * @return dimension of the subspace the shape is in
	 */
	public int getDimension() { return mDimension; }
	
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
		
		Matrix<Double> negT = vT.get(0).getScalarMatrix(-1.0);
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
		ArrayList <IntegerMatrix> points = getPoints();
		for (int cDim = 0; cDim < mDimension; ++cDim)
		{
			int max = points.get(0).getCell(cDim, 0);
			int min = max;
			for (int cPoints = 1; cPoints < points.size(); ++cPoints)
			{
				max = Math.max(max, points.get(cPoints).getCell(cDim, 0));
				min = Math.min(max, points.get(cPoints).getCell(cDim, 0));
			}
			
			if (p.getPosition(cDim) < min || p.getPosition(cDim) > max)
				return false;
		}
		return true;
	}
	
	private int mDimension;
}
