package algorithm;

import java.util.ArrayList;

import algorithm.Matrix.*;

public class Glue implements Cloneable 
{

	/** Nested class to create a custom exception
	 */
	@SuppressWarnings("serial")
	static class GlueException extends IllegalArgumentException{
		
		/**Exception constructor
		 */
		public GlueException(){
			super();
		}
		public GlueException(String message) {
			super(message);
			}
	}
	
	@SuppressWarnings("serial")
	public static class NotAVectorException extends IllegalArgumentException
	{
		public NotAVectorException() {}
		
		public NotAVectorException (String message) {super (message); }
	}
	
	public Glue(ArrayList<Integer> position)
	{
		if (position == null)
			throw new NullPointerException("null argument");
		pos = (ArrayList<Integer>) position.clone(); 
		if (pos.size() != 3) throw new GlueException("This ArrayList does not contain the appropriate number of positions");
	}
	
	/**
	 * constructor from matrix
	 * @param vec n x 1 matrix containing position vector
	 */
	public Glue (Matrix<Integer> vec)
	{
		if (vec.getColumns() != 1)
			throw new NotAVectorException ("Matrix of size " + vec.getRows() + " x " + vec.getColumns() + " is not a vector!");
		pos = new ArrayList<Integer>();
		for (int cRow = 0; cRow < vec.getRows(); ++cRow)
			pos.add(vec.getCell (cRow, 0));
	}
	
	public Glue clone()
	{
		return new Glue (pos);
	}
	
	/**
	* @return Position of glue
	*/
	public ArrayList<Integer> getPosition(){
			return (ArrayList<Integer>) pos.clone();
	}
	
	/**
	 * @param v matrix to translate
	 * @return matrix v translated by position held by the object
	 */
	public IntegerMatrix translateMat (IntegerMatrix v, Glue before)
	{
		IntegerMatrix trans = v.clone();
		for (int cRow = 0; cRow < trans.getRows(); ++cRow)
		{
			int diff = this.getPosition(cRow) - before.getPosition(cRow);
			for (int cCol = 0; cCol < trans.getColumns(); ++cCol)
				trans.setCell(cRow, cCol, trans.getCell(cRow, cCol) + diff);
		}
		return trans;
	}
	
	public String toString()
	{
		String s = "position ";
		for (int cCoord = 0; cCoord < getDimension(); ++cCoord)
			s += getPosition(cCoord) + " ";
		return s;
	}
	
	/**
	 * @param index index of coordinate
	 * @return coordinate at index
	 */
	public int getPosition (int index)
	{
		return pos.get(index);
	}
	
	/**
	 * @return the coordinates in vector form as a integer matrix of size dim x 1
	 */
	public IntegerMatrix toVector()
	{
		IntegerMatrix vec = new IntegerMatrix (getDimension(), 1);
		for (int cCoord = 0; cCoord < getDimension(); ++cCoord)
			vec.setCell (cCoord, 0, getPosition().get(cCoord));
		return vec;
	}
	
	/**
	 * @return dimension of subspace the point is in
	 */
	public int getDimension()
	{
		return pos.size();
	}
	
	public boolean equals (Glue comp)
	{
		for (int cDim = 0; cDim < getDimension(); ++cDim)
		{
			if (this.getPosition(cDim) != comp.getPosition(cDim))
				return false;
		}
		return true;
	}
	
	
		
	private ArrayList<Integer> pos;
}
