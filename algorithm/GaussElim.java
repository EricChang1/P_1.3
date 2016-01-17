package algorithm;

import algorithm.Matrix.*;

public class GaussElim implements Runnable
{
	public static boolean mDebug = false;
	
	/**
	 * testing main method
	 * @param args
	 */
	public static void main (String[] args)
	{
		DoubleMatrix m = new DoubleMatrix(2, 3);
		m.setCell(0, 0, 2.0);
		m.setCell(0, 1, 1.0);
		m.setCell(0, 2, 32.0);
		m.setCell(1, 0, 1.0);
		m.setCell(1, 2, 9.0);
		GaussElim g = new GaussElim(m);
		g.run();
		g.mMat.print(System.out);
		System.out.println ("all basic " + g.allBasicVariables(0, 1));
	}
	
	/**
	 * swaps elements in r, p given by i1, i2
	 * @param r array representation of matrix
	 * @param p array of pivots
	 * @param i1 row 1
	 * @param i2 row 2
	 */
	public static void swap (Double[][] r, int[] p, int i1, int i2)
	{
		Double[] rtemp = r[i1];
		int ptemp = p[i1];
		r[i1] = r[i2];
		p[i1] = p[i2];
		r[i2] = rtemp;
		p[i2] = ptemp;
	}
	
	/**
	 * Quick sort algorithm, sorting rows such that rows with a low pivot index are first
	 * @param rows array representation of matrix
	 * @param pivotIndex array of pivot indices
	 * @param start row to start sorting at
	 * @param end row to end sorting at
	 */
	public static void quickSort (Double[][] rows, int[] pivotIndex, int start, int end)
	{
		assert (start >= 0 && end < rows.length && start < end);
		int cLess = start, cLarger = end - 1;//pivot is always to the right
		
		while (cLess < cLarger)
		{
			while (pivotIndex[cLess] < pivotIndex[end] && cLess < cLarger)
				++cLess;
			while (pivotIndex[cLarger] > pivotIndex[end] && cLess < cLarger)
				++cLarger;
			if (cLess < cLarger)
			{
				swap (rows, pivotIndex, cLess, cLarger);
			}
		}
		if (pivotIndex[cLess] > pivotIndex[end])
			swap (rows, pivotIndex, cLess, end);
		
		if (cLess - 1 > start)
			quickSort (rows, pivotIndex, start, cLess - 1);
		if (cLess + 1 < end)
			quickSort (rows, pivotIndex, cLess + 1, end);
	}
	
	/**
	 * constructor
	 * @param m matrix to perform algo for
	 */
	public GaussElim (DoubleMatrix m)
	{
		mMat = m.clone();
	}
	
	/**
	 * runs the complete algo
	 */
	public void run()
	{
		if (mDebug)
		{
			System.out.println ("Initial");
			mMat.print(System.out);
		}
		int[] pivots = findPivots();
		order (pivots);
		if (mDebug)
		{
			System.out.println ("ordered");
			mMat.print(System.out);
		}
		forward (pivots);
		if (mDebug)
		{
			System.out.println ("forward");
			mMat.print(System.out);
		}
		backward (pivots);
		if (mDebug)
		{
			System.out.println ("backward");
			mMat.print(System.out);
		}
		scaleToOne (pivots);
		if (mDebug)
		{
			System.out.println ("scaled");
			mMat.print(System.out);
		}
	}
	
	/**
	 * @return a clone of the internal matrix
	 */
	public DoubleMatrix getMatrix()
	{
		return mMat.clone();
	}
	
	/**
	 * @param startCol
	 * @param endCol
	 * @return true if all variables within startCol and endCol are basic variables
	 */
	public boolean allBasicVariables (int startCol, int endCol)
	{
		for (int cRow = 0; cRow < 0; ++cRow)
		{
			boolean beyondPivot = false;
			for (int cCol = Math.max (startCol, cRow); cCol <= endCol; ++cCol)
			{
				if (!beyondPivot && !mMat.getCell(cRow, cCol).equals(0.0))
					beyondPivot = false;
				else if (beyondPivot)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * @return array of pivot indices
	 */
	public int[] findPivots()
	{
		int[] pivotIndex = new int[mMat.getRows()];
		for (int cRow = 0; cRow < mMat.getRows(); ++cRow)
		{
			int cCol = 0;
			while (cCol < mMat.getColumns() && mMat.getCell(cRow, cCol) == 0)
				++cCol;
			pivotIndex[cRow] = cCol;
		}
		return pivotIndex;
	}
	
	/**
	 * orders the rows of the matrix such that rows with low pivot indices are placed first
	 * @param pivots array of pivot indices
	 */
	public void order (int[] pivots)
	{
		Double[][] rows = new Double[mMat.getRows()][mMat.getColumns()];
		for (int cRow = 0; cRow < rows.length; ++cRow)
			rows[cRow] = mMat.getRow(cRow).clone();
		
		quickSort (rows, pivots, 0, rows.length - 1);
		System.out.println ("after sorting");
		for (int cRow = 0; cRow < rows.length; ++cRow)
		{
			for (int cCol = 0; cCol < rows[cRow].length; ++cCol)
				mMat.setCell(cRow, cCol, rows[cRow][cCol]);
		}
	}
	
	/**
	 * performs forward step
	 * @param pivots array of pivot indices
	 */
	public void forward (int[] pivots)
	{
		int cRow = 0;
		while (cRow < mMat.getRows() - 1 && pivots[cRow] < mMat.getColumns())
		{
			double pivot = mMat.getCell(cRow, pivots[cRow]);
			for (int cRestRow = cRow + 1; cRestRow < mMat.getRows(); ++cRestRow)
			{
				double c = -mMat.getCell(cRestRow, pivots[cRow]) / pivot;
				boolean beyondPivot = false;
				for (int cRestCol = pivots[cRow]; cRestCol < mMat.getColumns(); ++cRestCol)
				{
					boolean nonZero = (!mMat.getCell (cRestRow, cRestCol).equals (0.0));
					mMat.setCell (cRestRow, cRestCol, mMat.getCell(cRestRow, cRestCol) + c * mMat.getCell(cRow, cRestCol));
					if (!beyondPivot && nonZero && mMat.getCell (cRestRow, cRestCol).equals (0.0))
						++pivots[cRestRow];
					beyondPivot = false;
				}
			}
			++cRow;
		}
	}
	
	/**
	 * performs backward step
	 * @param pivots array of pivot indices
	 */
	public void backward (int[] pivots)
	{
		for (int cRow = mMat.getRows() - 1; cRow > 0; --cRow)
		{
			if (pivots[cRow] != mMat.getColumns())
			{
				double pivot = mMat.getCell (cRow, pivots[cRow]);
				for (int cRestRow = cRow - 1; cRestRow >= 0; --cRestRow)
				{
					double c = -mMat.getCell(cRestRow, pivots[cRow]) / pivot;
					for (int cRestCol = pivots[cRow]; cRestCol < mMat.getColumns(); ++cRestCol)
						mMat.setCell(cRestRow, cRestCol, mMat.getCell(cRestRow, cRestCol) + c * mMat.getCell(cRow, cRestCol));
				}
			}
		}
	}
	
	/**
	 * scales pivots to 1
	 * @param pivots array of pivot indices
	 */
	public void scaleToOne (int[] pivots)
	{
		int cRow = 0;
		while (cRow < mMat.getRows() && pivots[cRow] != mMat.getColumns())
		{
			double c = 1 / mMat.getCell(cRow, pivots[cRow]);
			for (int cCol = pivots[cRow]; cCol < mMat.getColumns(); ++cCol)
				mMat.setCell(cRow, cCol, mMat.getCell(cRow, cCol) * c);
			++cRow;
		}
	}
	
	private DoubleMatrix mMat;
}
