package algorithm;

public class EvaluationHeuristicFactory {
	
	public EvaluationHeuristic getHeuristic(EvaluationHeuristicType h)
	{
		switch(h)
		{
			case MaxDensity: return new MaximumDensity();
			case MaxPieces: return new MaximumNumberPieces();
			
		}
		
		return null;
	}

}
