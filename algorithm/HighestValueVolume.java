package algorithm;

import java.util.ArrayList;

public class HighestValueVolume implements SelectionHeuristic{
	
	public HighestValueVolume()
	{
		
	}
	
	public int getBestBlock(ArrayList<Resource> list){
		
		double maxValue=0;
		for(int box=0; box<list.size(); box++){
			if (list.get(box).getInventory()>0 || list.get(box).isInfinite()==true)
			{
				double newValue = list.get(box).getBlock().getValue()/list.get(box).getVolume();
				if(newValue > maxValue){
					maxValue=newValue;
					index=box;
				}
			}	
		}
		return index;
	}
	private int index;
}
