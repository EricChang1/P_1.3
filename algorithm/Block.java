package algorithm;

import java.util.ArrayList;

import algorithm.Matrix.*;

public class Block extends BasicShape implements Cloneable {
	
	/**
	*Constructor to pass the matrix handler vectors and the value.
	*@param matrixHVectors Arraylist containing the vectors defining the shape
	*@param adjMat adjacency matrix of block 
	*@param value Value assigned to the block.
	*/
	public Block (ArrayList<IntegerMatrix> matrixHVectors, IntegerMatrix adjMat, double value)
	{
		super (matrixHVectors, adjMat);
	}
	/**
	*Constructor to pass the value with the BasicShape object
	*@param value Value assigned to the shape
	*@param bShape Object of BasicShape
	*/
	public Block(BasicShape bShape, double value)
	{
		super (bShape);
	}
	
	/**
	 * @return newly constructed Block
	 */
	public Block clone()
	{
		return new Block (this, getValue());
	}

	/**
	*Method to get the value.
	*@return the value.
	*/
	public double getValue(){
		return value;
	}
	
	public void addShape (Block b)
	{
		this.value += b.getValue();
		super.addShape(b);
	}

	private double value;
}