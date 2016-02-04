package algorithm;
import java.util.ArrayList;

public class Scaler
{
	public class InconsistentVectorsException extends IllegalArgumentException
	{
		/**default contructor**/		
		public InconsistentVectorsException()
		{

		}
		
		/**	constructs exception from message
			@para message the message**/
		public InconsistentVectorsException (String message)
		{

		}
	}	

	/**	constructs Scaler from a set of vectors
		@param vectors set of vectors
		precondition: vectors needs to contain only matrices with one column and the same amount of rows
		@throws InconsistentVectorsException
	**/
	public Scaler (ArrayList <MatrixHandler> vectors)
	{

	}
	
	/**	scales vectors using scaling factor determined at construction
		@param vectors set of vectors
		precondition: vectors needs to contain only matrices with one column and the same amount of rows
		@throws InconsistentVectorsException
	**/
	public ArrayList <MatrixHandler> scale (ArrayList <MatrixHandler> vectors)
	{

	}

	/** @return scaling factor**/
	public float getScalingFactor()
	{
		return mScalingFactor;
	}

	private double mScalingFactor;
}
