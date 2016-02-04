package algorithm;

public class MaximumNumberPieces implements EvaluationHeuristic{
	
	public MaximumNumberPieces(){
		
	}
	public double getScore(Container box){
		this.truck = box;
		return truck.getValue();
		
	}
	private Container truck;
}
