package algorithm;

import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Matrix <T extends Number> 
{
	public static class MatrixOutOfBoundsException extends ArrayIndexOutOfBoundsException
	{
		private static final long serialVersionUID = 1L;

		public MatrixOutOfBoundsException() {super(); }
	
		public MatrixOutOfBoundsException (String message) {super (message); }
	}
	
	public static class MatrixDimensionMismatchException extends IllegalArgumentException
	{
		public MatrixDimensionMismatchException() {super(); }
		
		public MatrixDimensionMismatchException (String message) {super (message); } 
	}


	public static class IntegerMatrix extends Matrix <Integer>
	{
		/** Constructs matrix whose cells are initialized to 0
			@param rows number of rows
			@param cols number of columns
		**/		
		public IntegerMatrix (int rows, int cols)
		{
			super (rows, cols);
			super.mStoreArray = new Integer[rows][cols];
			for (int cRow = 0; cRow < rows; ++cRow)
			{
				for (int cCol = 0; cCol < cols; ++cCol)
					super.mStoreArray[cRow][cCol] = new Integer (0);
			}
		}
		
		public IntegerMatrix clone()
		{
			IntegerMatrix clone = new IntegerMatrix(getRows(), getColumns());
			clone.copyValues(this, 0, 0);
			return clone;
		}

		@Override
		public Integer vectorProduct (Integer[] v1, Integer[] v2) throws MatrixDimensionMismatchException 
		{
			if (v1.length != v2.length)
				throw new MatrixDimensionMismatchException ("Couldn't multiply vectors with " + v1.length + ", " + v2.length + " entries");
			int result = 0;
			for (int cEntry = 0; cEntry < v1.length; ++cEntry)
				result += v1[cEntry] * v2[cEntry];
			return result;
		}	
	}
	
	public static class DoubleMatrix extends Matrix <Double>
	{
		/** Constructs matrix whose cells are initialized to 0
			@param rows number of rows
			@param cols number of columns
		**/		
		public DoubleMatrix (int rows, int cols)
		{
			super (rows, cols);
			super.mStoreArray = new Double[rows][cols];
			for (int cRow = 0; cRow < rows; ++cRow)
			{
				for (int cCol = 0; cCol < cols; ++cCol)
					super.mStoreArray[cRow][cCol] = new Double (0);
			}
		}
		
		public DoubleMatrix clone()
		{
			DoubleMatrix clone = new DoubleMatrix(getRows(), getColumns());
			clone.copyValues(this, 0, 0);
			return clone;
		}

		@Override
		public Double vectorProduct(Double[] v1, Double[] v2) throws algorithm.Matrix.MatrixDimensionMismatchException 
		{
			if (v1.length != v2.length)
				throw new MatrixDimensionMismatchException ("Couldn't multiply vectors with " + v1.length + ", " + v2.length + " entries");
			double result = 0;
			for (int cEntry = 0; cEntry < v1.length; ++cEntry)
				result += v1[cEntry] * v2[cEntry];
			return result;
		}
	}
	
	/** Do nothing constructor which needs to be overridden by subclasses
	 * @param rows rows of matrix
	 * @param cols columns of matrix
	 */
	public Matrix (int rows, int cols)
	{
		mStoreArray = null;
	}
	
	/**
	 * @param v1 vector
	 * @param v2 vector
	 * @return vector product of v1 and v2
	 * @throws MatrixDimensionMismatchException
	 */
	public abstract T vectorProduct (T[] v1, T[] v2) throws MatrixDimensionMismatchException;
	
	//accessor methods
	/**
	 * Multiplies this with multiplier
	 * @param multiplier multiplier matrix
	 * @param result matrix to store result in
	 */
	public Matrix <T> multiply (Matrix<T> multiplier, Matrix<T> result)
	{
		for (int cRow = 0; cRow < this.getRows(); ++cRow)
		{
			for (int cCol = 0; cCol < this.getColumns(); ++cCol)
			{
				T resultCell = vectorProduct (this.getRow(cRow), this.getColumn(cCol));
				result.setCell(cRow, cCol, resultCell);
			}
		}
		return result;
	}
	
	/**
	 * @param row row index
	 * @return array containing elements of row
	 */
	public T[] getRow (int row)
	{
		if (row >= getRows())
			throw new MatrixOutOfBoundsException ("Could not get row with index " + row);
		return mStoreArray[row];
	}
	
	/**
	 * @param col column index
	 * @return array containing elements of column
	 */
	public T[] getColumn (int col)
	{
		if (col >= getColumns())
			throw new MatrixOutOfBoundsException ("Could not get column with index " + col);
		ArrayList <T> vals = new ArrayList <T>();
		for (int cRow = 0; cRow < getRows(); ++cRow)
			vals.add (getCell (cRow, col));
		return (T[]) vals.toArray();
	}
	
	/** Gets the value of the cell safely and returns -1 if not successful 
	 * 	@throws MatrixOutOfBoundsException
	*/
	public T getCell (int row, int col) throws MatrixOutOfBoundsException
	{
		if (row < getRows() && col < getColumns())
			return mStoreArray[row][col];
		else
			throw new MatrixOutOfBoundsException (row + "|" + col + " out of bounds in " + getRows() + "x" + getColumns() + " matrix");
	}
	
	/**
	 * @param rowIndex index of row to count
	 * @return Number of set cells in rowIndex
	 */
	public int countRow (int rowIndex)
	{
		int cnt = 0;
		for (int cCol = 0; cCol < getColumns(); ++cCol)
		{
			if (!getCell(rowIndex, cCol).equals (0))
				++cnt;
		}
		return cnt;
	}
	
	/**
	 * @param index of row to count
	 * @param startIndex  index to start counting at
	 * @param stopIndex index to stop counting at
	 * @return number of set cells in row in given interval
	 */
	public int countRowPart (int rowIndex, int startIndex, int stopIndex)
	{
		int cnt = 0;
		for (int cCol = startIndex; cCol <= stopIndex; ++cCol)
		{
			if (!getCell(rowIndex, cCol).equals (0))
				++cnt;
		}
		return cnt;
	}
	
	/**
	 * @param colIndex index of column to count
	 * @return Number of set cells in colIndex
	 */
	public int countCol (int colIndex)
	{
		int cnt = 0;
		for (int cRow = 0; cRow < getRows(); ++cRow)
		{
			if (!getCell (cRow, colIndex).equals (0))
				++cnt;
		}
		return cnt;
	}
	
	/**
	 * @param colIndex index of column to count
	 * @param startIndex  index to start counting at
	 * @param stopIndex index to stop counting at
	 * @return Number of set cells in colIndex
	 */
	public int countColPart (int colIndex, int startIndex, int stopIndex)
	{
		int cnt = 0;
		for (int cRow = startIndex; cRow <= stopIndex; ++cRow)
		{
			if (!getCell (cRow, colIndex).equals (0))
				++cnt;
		}
		return cnt;
	}	
	
	/** @return number of rows **/
	public int getRows() 
	{
		return mStoreArray.length;
	}
	
	/** @return number of columns **/
	public int getColumns() 
	{
		return mStoreArray[0].length;
	}
	
	/** @return true if internal array is not null **/
	public boolean checkValidArray() 
	{
		return (mStoreArray != null);
	}
	
	/**
	 * @param compare matrix to compare this to
	 * @return true if both matrices have the same dimensions and entries for each cell
	 */
	public boolean equals(Matrix<T> compare) 
	{
		if ((this.getRows() != compare.getRows()) ||
			(this.getColumns() != compare.getColumns())) 
			return false;
		for (int cRow = 0; cRow < compare.getRows(); cRow++) {
			for (int cCol = 0; cCol < compare.getColumns(); cCol++) {
				if (!this.getCell(cRow, cCol).equals(compare.getCell(cRow, cCol))) 
					return false;
			}
		}
		return true;
	}
	
	/**
	 * @param compare matrix to compare dimension to
	 * @return true if dimensions are the same
	 */
	public boolean dimensionEquals (Matrix<T> compare)
	{
		return (this.getRows() == compare.getRows() &&
				this.getColumns() == compare.getColumns());
	}
	
	/**
	 * @param row The index of the row that should be checked
	 * @return True if row is filled
	 */
	public boolean isRowFilled (int row)
	{
		for (int cCol = 0; cCol < getColumns(); cCol++)
		{
			if (getCell (row, cCol).equals (0)) 
				return false;
		}
		return true;
	}
	
	/**
	 * @param row The index of the column that should be checked
	 * @return True if column is filled
	 */
	public boolean isColFilled(int col)
	{
		for (int cRow = 0; cRow < getRows(); ++cRow) 
		{
			if (getCell (cRow, col).equals (0)) 
				return false;
		}
		return true;
	}
	
	/**
	 * @param row index of row to check
	 * @return True if every element in the row is 0
	 */
	public boolean isRowEmpty (int row)
	{
		for (int cCol = 0; cCol < getColumns(); cCol++)
		{
			if (!getCell (row, cCol).equals (0)) 
				return false;
		}
		return true;
	}
	
	/**
	 * @param column col index of column to check
	 * @return True if every element in the column is 0
	 */
	public boolean isColEmpty (int col)
	{
		for (int cRow = 0; cRow < getRows(); ++cRow) 
		{
			if (!getCell (cRow, col).equals (0)) 
				return false;
		}
		return true;
	}
	
	//mutator methods
	/**
	 * Writes value to the given cell
	 * @param row row of the cell to write to
	 * @param col column of the cell to write to
	 * @param value value to write
	 */
	public void setCell (int row, int col, T value) 
	{
		mStoreArray[row][col] = value;
	}
	
	/** Copies values from source to this matrix starting from startRow|startCol
	 * Precondition: source contains enough entries to fill every cell of this matrix while copying
	 * @param source matrix containing values to be copied
	 * @param startRow row in source to start copying at
	 * @param startCol column in source to start copying at
	 */
	public void copyValues (Matrix<T> source, int startRow, int startCol)
	{
		for (int cRow = 0; cRow < getRows(); ++cRow)
		{
			for (int cCol = 0; cCol < getColumns(); ++cCol)
				this.setCell(cRow, cCol, source.getCell (cRow + startRow, cCol + startCol));
		}
	}
	
	/**
	 * @param index of row the line above will be moved to. Precondition: Row > 0
	 * This method copies the row from above to one row below
	 */
	public void moveRow(int row) 
	{
		for (int cCol = 0; cCol < getColumns(); cCol++)
				setCell (row, cCol, getCell (row - 1, cCol));
	}
	
	//debugging methods
	/**
	 * Prints the entries of the matrix using writer
	 * @param writer stream to write to
	 */
	public void print (PrintWriter writer) 
	{
		for (int cRows = 0; cRows < getRows(); cRows++) {
			for (int cCols = 0; cCols < getColumns(); cCols++)
				writer.print(" " + getCell(cCols, cRows) + " ");
			writer.println("");
		}
	}
	
	//private methods
	
	// Data Members
	private T[][] mStoreArray;
}
