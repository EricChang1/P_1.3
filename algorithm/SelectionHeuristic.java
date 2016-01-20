package algorithm;

import java.util.ArrayList;

import models.Resource;

public interface SelectionHeuristic {
	
	public int getBestBlock(ArrayList<Resource> list);

}
