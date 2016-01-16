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
	 * @param index index of coordinate
	 * @return coordinate at index
	 */
	public int getPosition (int index)
	{
		return pos.get(index);
	}
	
	
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
	
		
	private ArrayList<Integer> pos;
}
