package algorithm;
import java.util.ArrayList;

public class BasicShape {
	public BasicShape(ArrayList <Vectors> vectors)
	{
		
	}
	
	
	/** @return Width of a shape
	 */
	public double getWidth()
	{
		return width;
	}
	/** @return Height of a shape
	 */
	public double getHeight()
	{
		return height;
	}
	
	/** Creates a rotation matrix based on given angles of rotation
	 * @param angle1 Desired amount of rotation in horizontal axis (in degrees)
	 * @param angle2 Desired amount of rotation in vertical axis (in degrees)
	 * @return rotation matrix
	 */
	public MatrixHandler rotationMatrix(double angle1, double angle2)
	{

	}
	
	/** Performs actual rotation
	 * @param rotMatrix created from rotationMatrix()
	 * @return matrix after rotation
	 */
	public MatrixHandler rotate(MatrixHandler rotMatrix)
	{

	}
	
	private double width, height;
}
