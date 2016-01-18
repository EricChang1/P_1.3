package algorithm;

public class SelectionHeuristicFactory {
	public SelectionHeuristic getHeuristicSelec(SelectionHeuristicType h)
	{
		switch(h)
		{
			case ValuePerVolume: return new HighestValueVolume();
			case ValuePerInverseDensity: return new HighestValueInvDensity();
			case ValuePerVertices: return new HighestValueVertices();
			
		}
		
		return null;
	}


}
