package algorithm;

import java.util.ArrayList;
import models.*;

public class HighestValueVolume implements SelectionHeuristic{
	
	public HighestValueVolume()
	{
		
	}
	
	public int getBestBlock(ArrayList<Resource> list){
		
		int index=-1;
		double maxValue=0;
		for(int box=0; box<list.size(); box++){
			double newValue = list.get(box).getBlock().getValue()/list.get(box).getVolume();
			if(newValue > maxValue){
				maxValue=newValue;
				index++;
			}
		}
		return index;
	}
}
