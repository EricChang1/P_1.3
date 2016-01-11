package algorithm;

import java.util.ArrayList;

public class Glue implements Cloneable 
{

	/** Nested class to create a custom exception
	 */
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
	
	public Glue(ArrayList<Integer> position){
		ArrayList<Integer> pos = position; 
		if (pos.size() != 3) throw new GlueException("This ArrayList does not contain the appropriate number of positions");
	}
	
	public Glue clone()
	{
		return new Glue (pos);
	}
	
	/**
	* @return Position of glue
	*/
	public ArrayList<Integer> getPosition(){
			return pos;
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
