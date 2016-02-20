package algorithm;
import java.util.ArrayList;

public class HighestValueVertices implements SelectionHeuristic {
	
	public HighestValueVertices()
	{
		
	}
	
	public int getBestBlock(ArrayList<Resource> list){
		
		int maxValue=0;
		for(int box=0; box<list.size(); box++){
			if (list.get(box).getInventory()>0 || list.get(box).isInfinite()==true)
			{
				int newValue = (int)list.get(box).getBlock().getValue()/list.get(box).getBlock().getNumberOfVertices();
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
