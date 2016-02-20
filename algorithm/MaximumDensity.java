package algorithm;

import java.util.ArrayList;

public class MaximumDensity implements EvaluationHeuristic{
	
	public MaximumDensity(){
	}
	
	public double getScore(Block block, Position pos){
		
		updateVariables(block, pos);
		int CubeVolume = (x*y*z);
		if(CubeVolume<score)
		{
			score = CubeVolume;

		}
		return score;
	}
	
	private int score=Integer.MAX_VALUE;
	private int x, y, z;
	private int prevX, prevY, prevZ;
	
	//Call this method after placing block
	public void update(Block block, Position pos)
	{
		score=Integer.MAX_VALUE;
		updateVariables(block, pos);
		prevX = x;
		prevY = y;
		prevZ = z;
	}
	
	public void updateVariables(Block block, Position pos)
	{
		int Bx = block.getDimensions(0);
		int By = block.getDimensions(1);
		int Bz = block.getDimensions(2);
		int Px = pos.getPosition().get(0);
		int Py = pos.getPosition().get(1);
		int Pz = pos.getPosition().get(2);
		
		x = Bx+Px;
		y = By+Py;
		z = Bz+Pz;
		
		if (prevX>x)
			x=prevX;
		if (prevY>y)
			x=prevY;
		if (prevZ>z)
			x=prevZ;
	}
	
}
