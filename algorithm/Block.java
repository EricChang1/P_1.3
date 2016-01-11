public class Block extends BasicShape {
	
	/**
	*Constructor to pass the matrix handler vectors and the value.
	*@param matrixHVectors Arraylist containing the matrix handler vectors.
	*@param value Value assigned to the shape.
	*/
	public Block(ArrayList<MatrixHandler> matrixHVectors, double value){
		
	}
	/**
	*Constructor to pass the value with the BasicShape object
	*@param value Value assigned to the shape
	*@param bShape Object of BasicShape
	*/
	public Block(double value, BasicShape bShape){

	}

	/**
	 * @param minPos the position at which this blocks upper left corner is sitting
	 * @return the maxPos, which is equivalent to the position of the bottom right corner
     */
	public Position getMaxDimension(Position minPos) {

		//needs to get passed an index, but idk what that should be so maybe I dont use it correctly
		int x = this.getDimensions(0) + minPos.get(0);
		int y = this.getDimensions(1) + minPos.get(1);
		int z = this.getDimensions(2) + minPos.get(2);
		ArrayList<Integer> maxPos = new ArrayList<Integer>();
		maxPos.add(x);
		maxPos.add(y);
		maxPos.add(z);
		Position max = new Position(maxPos);
		return max;
	}

	/**
	*Method to get the value.
	*@return the value.
	*/
	public double getValue(){
		return value;
	}

	protected double value;
	private BasicShape shape;
}