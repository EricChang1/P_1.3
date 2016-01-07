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
	public Scaler (ArrayList <MatrixHandler> vec)
	{
		//Clone vec so no changes are applied to original
		ArrayList <MatrixHandler> vectors = vec.clone();
		
		//Method should exist in MatrixHandler
		if (dimensionEquals(false))
		{
			InconsistentVectorsException exception = new InconsistentVectorsException
			("Vectors are not appropiate size"); 
		}
		
		//Loops through all vectors and find the smallest
		for (int i=0; i<vectors.size();i++)
		{
			for (int j=0; j<vectors.getRows();j++)
			{
				double currentValue = vectors.get(i).getRow(j).getCell(); //value in current cell
				int digits = String.valueOf(currentValue).length(); //number of digits in current cell
				int intLength = (int) (Math.floor(currentValue)); //number of digits in cell ignoring decimals
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
	public double commFacFinder(ArrayList <MatrixHandler> vectors)
	{
		//Temporary number multiplied to vector to remove all decimals when searching for common factor
		double removeDecimal = Math.pow(10, numDecimal);
		//Maximum potential common factor
		double half = Math.floor(0.5*smallestValue*removeDecimal);
		
		ArrayList <MatrixHandler> scaledVectors = scale(vectors, removeDecimal);
		
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
	
	
	public boolean commFacCheck(ArrayList <MatrixHandler> vectors, double commFac)
	{
		boolean check=true;
			for (int i=0; i<vectors.size();i++)
			{
				for (int j=0; j<vectors.getRows();j++)
				{
					if (vectors.get(i).getRow(j).getCell()%commFac!=0)
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
	public ArrayList <MatrixHandler> scale (ArrayList <MatrixHandler> vectors, double scaler)
	{
		if (dimensionEquals(false))
		{
			InconsistentVectorsException exception = new InconsistentVectorsException("Vectors are not appropiate size"); 
		}
		for (int i=0; i<vectors.size();i++)
		{
			for (int j=0; j<vectors.getRows();j++)
			{
				double currentCell = vectors.get(i).getRow(j);
				double currentValue = vectors.get(i).getRow(j).getCell();
				currentCell.setCell(currentValue*scaler);
			}
		}
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
	
	/*		//Method should no longer be needed
	public boolean vectorSizeFalseCheck(ArrayList<MatrixHandler> vectors)
	{
		for (int i=0; i<vectors.size();i++)
		{
			if (vectors.get(i).getColumns=1)
			{
				//save current vector height
				int vecHeight = vectors.get(i);
				//check if current vector has same height as previous
				if (i=0|| vecHeight!=vectors.get(i-1).getRows)
				{
					// if they don't have same height, return true;
					return true;
				}
			}
		}
		//if for-loop completes, return false
		return false;
	}
	*/

	private double mScalingFactor; //Number that will be applied to all vectors in the END to make them all integers
	private double smallestValue; //Stores the smallest value in an arraylist of integers
	private int numDecimal; // Stores the number of decimals in a value. NOTE: 1.0 would results in numDecimal = 1
}
