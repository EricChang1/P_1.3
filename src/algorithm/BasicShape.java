package algorithm;

import java.util.ArrayList;

import algorithm.Matrix.DoubleMatrix;

public class BasicShape 
{
	public static class BadNumberOfRowsException extends IllegalArgumentException
	{
		public BadNumberOfRowsException() {super(); }
		
		public BadNumberOfRowsException (String message) { super (message); }
	}
	
	
	public BasicShape(ArrayList <Matrix<Integer>> vectors, Matrix.IntegerMatrix adjMatrix){

		ArrayList<Integer> dimensions = new ArrayList<Integer>();
		calcDim (vectors);
		this.adjMatrix = adjMatrix.clone();

	}
	
	/** @return the dimensions of a shape given an index.
	 */
	public double getDimensions(int index){

		return dimensions.get(index);
	}
	/** Calculates the dimensions of a shape
	** @param vectors ArrayList containing all the vectors
	*/
	public void calcDim(ArrayList<Matrix<Integer>> vectors) throws BadNumberOfRowsException{
		
		if (!numberOfMH(vectors)) 
			throw new BadNumberOfRowsException ("vectors don't have the same dimension");

		for(int i=0; i<vectors.size(); i++){

			int max = maximum (vectors,i);
			int min = minimum (vectors,i);
			dimensions.add(max-min);

		}
	}
	/** compares that all the Matrix Handlers have the same number of rows
	* @param vectors Arraylist containing all the vectors
	* @return false if one Matrix Handler doesn't have the same number of rows
	*/
	public boolean numberOfMH(ArrayList<Matrix<Integer>> vectors){

		int numberOfRows=vectors.get(0).getRows();
		for(Matrix<Integer> temp: vectors){
			if(temp.getRows() != numberOfRows)
				return false;
		}
		return true;
	}
	/** calculates the maximum vector value
	* @param vector Arraylist containing all the vectors
	* @param index The index of the vector in the Matrix Handler
	* @return the maximum value.
	*/
	public int maximum(ArrayList <Matrix<Integer>> vectors, int index){

		int max = Integer.MIN_VALUE;
    	for(Matrix<Integer> temp : vectors){
       		if(temp.getCell (index, 0) > max){
          		  max = temp.getCell (index, 0);
       		}
   		}
    	return max;

	}
	/** calculates the minimum vector value
	* @param vector Arraylist containing all the vectors
	* @param index The index of the vector in the Matrix Handler
	* @return the minimum value.
	*/
	public int minimum(ArrayList<Matrix<Integer>> vectors, int index){

		int min = Integer.MAX_VALUE;
    	for(Matrix<Integer> temp: vectors){
       		if(temp.getCell (index, 0) < min){
          		  min= temp.getCell (index, 0);
       		}
   		}
    	return min;
	}

	public ArrayList <Matrix.IntegerMatrix> lookUpConnections(int index){

		ArrayList<Matrix.IntegerMatrix> connections = new ArrayList<Matrix.IntegerMatrix>();
		for(int counter=0; counter<adjMatrix.getRows(); counter++){
			if(adjMatrix.getCell(index,counter)!=0){
					connections.add (vectors.get(counter));
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

		Matrix <Double> rotationMatrix1 = new Matrix.DoubleMatrix (3, 3);
		rotationMatrix1.setCell (0, 0, Math.cos (radAngle1));
		rotationMatrix1.setCell (1, 0, Math.sin (radAngle1));
		rotationMatrix1.setCell (0, 1, -Math.sin (radAngle1));
		rotationMatrix1.setCell (1, 1, -Math.cos (radAngle1));
		rotationMatrix1.setCell (2, 2, 1.0);

		Matrix <Double> rotationMatrix2 = new Matrix.DoubleMatrix (3, 3);
		rotationMatrix2.setCell (0, 0, Math.cos (radAngle2));
		rotationMatrix2.setCell (1, 0, -Math.sin (radAngle2));
		rotationMatrix2.setCell (0, 1, 1.0);
		rotationMatrix2.setCell (1, 1, Math.sin (radAngle2));
		rotationMatrix2.setCell (2, 2, Math.cos (radAngle2));

		return rotationMatrix1.multiply (rotationMatrix2, new Matrix.DoubleMatrix (3, 3));
	}
	
	/** Performs actual rotation
	 * @param rotMatrix created from rotationMatrix()
	 * @return matrix after rotation
	 */
	public void rotate (Matrix<Double> rotMatrix){

		for(int cCounter=0; cCounter<vectors.size();cCounter++){
			Matrix.DoubleMatrix result = new Matrix.DoubleMatrix (3,1);
			Matrix <Double> vec = vectors.get(cCounter).toDoubleMatrix();
			rotMatrix.multiply (vec, result);
			vectors.set (cCounter, result.toIntegerMatrix());
		}

	}
	
	private ArrayList<Matrix.IntegerMatrix> vectors;
	private ArrayList<Integer> dimensions;
	private Matrix<Integer> adjMatrix;
}
