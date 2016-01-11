import java.util.ArrayList;

public class BasicShape {


	public BasicShape(ArrayList<MatrixHandler<Integer>> vectors, MatrixHandler adjMatrix){

		ArrayList<Integer> dimensions = new ArrayList<Integer>();

		//How can this work if calcDim( ) is void?
		this.dimensions = calcDim(vectors);
		this.adjMatrix = adjMatrix.clone();

	}
	
	/**
	 * @return the dimensions of a shape given an index. (you dont say :O what is this index?)
	 */
	public double getDimensions(int index){

		return dimensions.get(index);
	}
	/** Calculates the dimensions of a shape
	** @param vectors Arraylist containing all the vectors
	*/
	public void calcDim(ArrayList<MatrixHandler<Integer>> vectors){
		
		if !(numberOfMH(vectors)) throw new EmptyStackException();

			for(int i=0; i<vectors.size(); i++){

				int max = maximum(vectors.get(i),i);
				int min = minimum(vectors.get(i),i);
				dimensions.add(max-min);

		}
	}
	/** compares that all the Matrix Handlers have the same number of rows
	* @param vectors Arraylist containing all the vectors
	* @return false if one Matrix Handler doesn't have the same number of rows
	*/
	public boolean numberOfMH(ArrayList<MatrixHandler<Integer>> vectors){

		int numberOfRows=vectors.get(0).getRows();
		for(int temp: vectors){
			if(temp!=numberOfRows)
				return false;
		}
	}
	/** calculates the maximum vector value
	* @param vector Arraylist containing all the vectors
	* @param index The index of the vector in the Matrix Handler
	* @return the maximum value.
	*/
	public int maximum(ArrayList<MatrixHandler<Integer>> vectors, int index){

	int max = Integer.MIN_VALUE;
    	for(int temp: vectors){
       		if(vectors.get(temp).get(index) > max){
          		  max = vectors.get(temp).get(index);
       		}
   		}
    	return max;

	}
	/** calculates the minimum vector value
	* @param vector Arraylist containing all the vectors
	* @param index The index of the vector in the Matrix Handler
	* @return the minimum value.
	*/
	public int minimum(ArrayList<MatrixHandler<Integer>> vectors, int index){

	int min = Integer.MAX_VALUE;
    	for(int temp: vectors){
       		if(vectors.get(temp).get(index) < min){
          		  min= vectors.get(temp).get(index);
       		}
   		}
    	return min;
	}

	/**
	 * @param index Finds corner point in this position
	 * @return All the points that are connected with this point
     */
	public Arraylist<MatrixHandler<Integer>> lookUpConnections(int index){

	Arraylist<MatrixHandler<Integer>> connections = new Arraylist<MatrixHandler<Integer>>();
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
	public MatrixHandler rotationMatrix(double angle1, double angle2){
		double radAngle1 = Math.toRadian (angle1);
		double radAngle2 = Math.toRadian (angle2);

		MatrixHandler <Float> rotationMatrix1 = new MatrixHandler <Float> (3, 3);
		rotationMatrix1.setCell (0, 0, Math.cos (radAngle1));
		rotationMatrix1.setCell (1, 0, Math.sin (radAngle1));
		rotationMatrix1.setCell (0, 1, -Math.sin (radAngle1));
		rotationMatrix1.setCell (1, 1, -Math.cos (radAngle1));
		rotationMatrix1.setCell (2, 2, 1);

		MatrixHandler <Float> rotationMatrix2 = new MatrixHandler <Float> (3, 3);
		rotationMatrix2.setCell (0, 0, Math.cos (radAngle2));
		rotationMatrix2.setCell (1, 0, -Math.sin (radAngle2));
		rotationMatrix2.setCell (0, 1, 1);
		rotationMatrix2.setCell (1, 1, Math.sin (radAngle2));
		rotationMatrix2.setCell (2, 2, Math.cos (radAngle2));

		return rotationMatrix1.multiply(rotationMatrix2);

	}
	
	/** Performs actual rotation
	 * @param rotMatrix created from rotationMatrix()
	 * @return matrix after rotation
	 */
	public MatrixHandler rotate(MatrixHandler rotMatrix){

		for(int cCounter=0; cCounter<vectors.size();cCounter++){
			vectors.set(cCounter,rotMatrix.multiply(vectors.get(cCounter))));
		}

	}
	
	private ArrayList<Matrix> vectors;
	private ArrayList<Integer> dimensions;
	private MatrixHandler<Integer> adjMatrix;
}
