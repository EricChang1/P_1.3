package algorithm;
import java.util.ArrayList;

import models.*;

public class HighestValueVertices implements SelectionHeuristic {
	
	public HighestValueVertices()
	{
		
	}
	
	public int getBestBlock(ArrayList<Resource> list){
		
		int index=-1;
		int maxValue=0;
		for(int box=0; box<list.size(); box++){
			int newValue = (int)list.get(box).getBlock().getValue()/list.get(box).getBlock().getNumberOfVertices();
			if(newValue > maxValue){
				maxValue=newValue;
				index++;
			}
		}
		return index;
	}
	
}
