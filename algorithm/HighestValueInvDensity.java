package algorithm;

import java.util.ArrayList;

public class HighestValueInvDensity implements SelectionHeuristic {
	
	public HighestValueInvDensity()
	{
		
	}
	
	public int getBestBlock(ArrayList<Resource> list) {
		
		double maxValue=0;
		
		ArrayList <Integer> minPos = new ArrayList <Integer> ();
		minPos.add(0); minPos.add(0); minPos.add(0); 
		Position min = new Position(minPos);
		
		
		for(int box=0; box<list.size();box++){
			if (list.get(box).getInventory()>0 || list.get(box).isInfinite()==true)
			{
				Position max = list.get(box).getBlock().getMaxDimension(min);
				int x = max.getPosition().get(0);
				int y = max.getPosition().get(1);
				int z = max.getPosition().get(2);
				double cubeVolume = x*y*z;
				double newValue = cubeVolume/list.get(box).getBlock().getValue(); 
				if(newValue > maxValue)
				{
					maxValue=newValue;
					index=box;
				}
			}
		}
		return index;
	}
	private int index;
}
