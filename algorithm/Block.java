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
	*Method to get the value.
	*@return the value.
	*/
	public double getValue(){
		return value;
	}

	protected double value;
	private BasicShape shape;
}