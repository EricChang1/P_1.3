package algorithm;

import java.util.ArrayList;

public class MaximumDensity implements EvaluationHeuristic{
	
	public MaximumDensity(){
	}
	
	public double getScore(Container Container){
		ArrayList <Integer> minPos = new ArrayList <Integer> ();
		minPos.add(0);minPos.add(0);minPos.add(0);
		Position min = new Position(minPos);
		Position max = Container.getMaxDimension(min);
		int x = max.getPosition().get(0);
		int y = max.getPosition().get(1);
		int z = max.getPosition().get(2);
		double cubeVolume = x*y*z;
		if(cubeVolume>score)
		{
			cubeVolume = score;
		}
		
		return score;
	}
	

	private int score;
}
