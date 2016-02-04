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
			super(message);
		}
	}	

	/**	constructs Scaler from a set of vectors
		@param vectors set of vectors
		precondition: vectors needs to contain only matrices with one column and the same amount of rows
		@throws InconsistentVectorsException
	**/
	public Scaler (ArrayList <Matrix> vec)
	{
		//Clone vec so no changes are applied to original
		ArrayList <Matrix> vectors = (ArrayList<Matrix>) vec.clone();
		
		//Method should exist in MatrixHandler
		for (int i=0; i<vectors.size(); i++)
			if (vectors.get(i).dimensionEquals(vectors.get(0))==false)
			{
				InconsistentVectorsException exception = new InconsistentVectorsException
				("Vectors are not appropiate size"); 
			}
		
		//Loops through all vectors and find the smallest
		for (int i=0; i<vectors.size();i++)
		{
			for (int j=0; j<vectors.get(i).getRows();j++)
			{
				Number numValue = vectors.get(i).getCell(j, 1); //value in current cell
				double currentValue = numValue.doubleValue();
				int digits = String.valueOf(currentValue).length(); //number of digits in current cell
				int intLength = String.valueOf(Math.floor(currentValue)).length(); //number of digits in cell ignoring decimals
				if (smallestValue == 0.0 ||smallestValue>currentValue)
				{
					smallestValue = currentValue;
				}
				if (numDecimal == 0 || numDecimal<digits-intLength)
				{
					numDecimal = digits - intLength; 
				}
			}
		}
		if (commFacCheck(vectors, smallestValue))
		{
			mScalingFactor = smallestValue;
		}
		else 
		{
			mScalingFactor = commFacFinder(vectors);
		}
		
		
	}
	public double commFacFinder(ArrayList <Matrix> vectors)
	{
		//Temporary number multiplied to vector to remove all decimals when searching for common factor
		double removeDecimal = Math.pow(10, numDecimal);
		//Maximum potential common factor
		double half = Math.floor(0.5*smallestValue*removeDecimal);
		
		ArrayList <Matrix> scaledVectors = scale(vectors, removeDecimal);
		
		//Return the lowest common factor, 1 is not needed because it has to be common factor
		for (double i=2; i<=half;i++)
		{
			if(commFacCheck(vectors, i))
			{
				mScalingFactor=i/10;
				return mScalingFactor;
			}
		}
		//if for-loop completes without finding common factor, then 1 must be only common factor
		mScalingFactor=1;
		return mScalingFactor;
	}
	
	
	public boolean commFacCheck(ArrayList <Matrix> vectors, double commFac)
	{
		boolean check=true;
			for (int i=0; i<vectors.size();i++)
			{
				for (int j=0; j<vectors.get(i).getRows();j++)
				{
					Number numValue = vectors.get(i).getCell(j, 1); //value in current cell
					double currentValue = numValue.doubleValue();
					if (currentValue%commFac!=0)
					{
						check=false;
					}
				}
			}
		return check;
	}
	
	/**	scales vectors using scaling factor determined at construction
		@param vectors set of vectors
		precondition: vectors needs to contain only matrices with one column and the same amount of rows
		@throws InconsistentVectorsException
	**/
	public ArrayList <Matrix> scale (ArrayList <Matrix> vectors, double scaler)
	{
		for (int i=0; i<vectors.size(); i++)
			if (vectors.get(i).dimensionEquals(vectors.get(0))==false)
			{
				InconsistentVectorsException exception = new InconsistentVectorsException
				("Vectors are not appropiate size"); 
			}
		for (int i=0; i<vectors.size();i++)
		{
			for (int j=0; j<vectors.get(i).getRows();j++)
			{
				Number numValue = vectors.get(i).getCell(j, 1); //value in current cell
				double currentValue = numValue.doubleValue();
				vectors.get(i).setCell(1, 1, 1);
				Number newValue = currentValue*scaler;
				vectors.get(i).setCell(i, 1, newValue);
			}
		}
		return vectors;
	}

	/** @return scaling factor**/
	public double getScalingFactor()
	{
		return mScalingFactor;
	}
	
	/**Checks that all vectors in an arraylist have 1 column and are same size
	 * @param vectors takes an arraylist of MatrixHandlers
	 * @return true vectors are different sizes
	 */
	
	private double mScalingFactor; //Number that will be applied to all vectors in the END to make them all integers
	private double smallestValue; //Stores the smallest value in an arraylist of integers
	private int numDecimal; // Stores the number of decimals in a value. NOTE: 1.0 would results in numDecimal = 1
}
