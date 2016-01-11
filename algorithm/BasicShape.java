package algorithm;

import java.util.ArrayList;
import algorithm.Matrix.*;

public class BasicShape 
{
	public final static int DIM = 3;
	
	@SuppressWarnings("serial")
 	public static class BadNumberOfRowsException extends IllegalArgumentException
 	{
 		public BadNumberOfRowsException() {super(); }
 		
 		public BadNumberOfRowsException (String message) { super (message); }
 	}

	public BasicShape(ArrayList<IntegerMatrix> vectors, IntegerMatrix adjMatrix){

		ArrayList<Integer> dimensions = new ArrayList<Integer>();
		calcDim(vectors);
		this.adjMatrix = adjMatrix.clone();

	}
	
	/**
	 * @return the dimensions of a shape given an index.
	 */
	public int getDimensions(int index){

		return dimensions.get(index);
	}
	
	/** Calculates the dimensions of a shape
	** @param vectors ArrayList containing all the vectors
	*/
	public void calcDim(ArrayList<IntegerMatrix> vectors){
		
		if (!numberOfMH(vectors)) 
			throw new BadNumberOfRowsException ("Vectors given to construct basic shape have different dimensions");

			for(int i=0; i<vectors.size(); i++){

				int max = maximum(vectors,i);
				int min = minimum(vectors,i);
				dimensions.add(max-min);

		}
	}
	/** compares that all the Matrix Handlers have the same number of rows
	* @param vectors ArrayList containing all the vectors
	* @return false if one Matrix Handler doesn't have the same number of rows
	*/
	public boolean numberOfMH(ArrayList<IntegerMatrix> vectors){

		int numberOfRows=vectors.get(0).getRows();
		for (int cVec = 0; cVec < vectors.size(); ++cVec)
		{
			if (vectors.get(cVec).getRows() != numberOfRows)
				return false;
		}
		return true;
	}
	/** calculates the maximum vector value
	* @param vector ArrayList containing all the vectors
	* @param index The index of the vector in the Matrix Handler
	* @return the maximum value.
	*/
	public int maximum(ArrayList<IntegerMatrix> vectors, int index){

		int max = Integer.MIN_VALUE;
		for (int cVec = 0; cVec < vectors.size(); ++cVec)
		{
	   		if (vectors.get(cVec).getCell (index, 0) > max){
	      		  max = vectors.get(cVec).getCell (index, 0);
	   		}
		}
    	return max;

	}
	/** calculates the minimum vector value
	* @param vector ArrayList containing all the vectors
	* @param index The index of the vector in the Matrix Handler
	* @return the minimum value.
	*/
	public int minimum(ArrayList<IntegerMatrix> vectors, int index)
	{
		int min = Integer.MAX_VALUE;
    	for (int cVec = 0; cVec < vectors.size(); ++cVec)
    	{
       		if (vectors.get(cVec).getCell (index, 0) < min){
          		  min = vectors.get(cVec).getCell (index, 0);
       		}
   		}
    	return min;
	}

	/**
	 * @param index Finds corner point in this position
	 * @return All the points that are connected with this point
     */
	public ArrayList<IntegerMatrix> lookUpConnections(int index)
	{
		ArrayList<IntegerMatrix> connections = new ArrayList<IntegerMatrix>();
		for(int counter=0; counter<adjMatrix.getRows(); counter++){
			if(adjMatrix.getCell(index,counter)!=0){
					connections.add(vectors.get(counter));
				}
		}
		return connections;
	}
	
	/** Creates a rotation matrix based on given angles of rotation
	 * @param angle1 Desired amount of rotation in horizontal axis (in degrees)
	 * @param angle2 Desired amount of rotation in vertical axis (in degrees)
	 * @return rotation matrix
	 */
	public Matrix rotationMatrix(double angle1, double angle2){
		double radAngle1 = Math.toRadians (angle1);
		double radAngle2 = Math.toRadians (angle2);

		Matrix <Double> rotationMatrix1 = new DoubleMatrix (DIM, DIM);
		rotationMatrix1.setCell (0, 0, Math.cos (radAngle1));
		rotationMatrix1.setCell (1, 0, Math.sin (radAngle1));
		rotationMatrix1.setCell (0, 1, -Math.sin (radAngle1));
		rotationMatrix1.setCell (1, 1, -Math.cos (radAngle1));
		rotationMatrix1.setCell (2, 2, 1.0);

		Matrix <Double> rotationMatrix2 = new DoubleMatrix (DIM, DIM);
		rotationMatrix2.setCell (0, 0, Math.cos (radAngle2));
		rotationMatrix2.setCell (1, 0, -Math.sin (radAngle2));
		rotationMatrix2.setCell (0, 1, 1.0);
		rotationMatrix2.setCell (1, 1, Math.sin (radAngle2));
		rotationMatrix2.setCell (2, 2, Math.cos (radAngle2));
		
		Matrix <Double> result = new DoubleMatrix (DIM, DIM);
		return rotationMatrix1.multiply (rotationMatrix2, result);

	}
	
	/** Performs actual rotation
	 * @param rotMatrix created from rotationMatrix()
	 * @return matrix after rotation
	 */
	public void rotate(Matrix<Double> rotMatrix)
	{

		for(int cCounter=0; cCounter<vectors.size();cCounter++)
		{
			DoubleMatrix result = new DoubleMatrix (DIM, DIM);
			DoubleMatrix vec = vectors.get (cCounter).toDoubleMatrix();
			rotMatrix.multiply (vec, result);
			vectors.set (cCounter, result.toIntegerMatrix());
		}
		
	}
	
	private ArrayList<IntegerMatrix> vectors;
	private ArrayList<Integer> dimensions;
	private IntegerMatrix adjMatrix;
}
