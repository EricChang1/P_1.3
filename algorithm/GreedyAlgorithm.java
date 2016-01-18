package algorithm;
import java.util.ArrayList;

import algorithm.*;

public class GreedyAlgorithm {
	
	public GreedyAlgorithm(EvaluationHeuristic e, SelectionHeuristic s, Container mtruck, ArrayList<Resource> nList){
		this.currentE = e;
		this.currentS = s;
		this.truck = mtruck;
		this.list = nList;
		mX = 0;
		mY = 0;
		mZ = 0;
	}
	
	public Block chooseHighestValueBlock(ArrayList<Resource> nList){
		double maxValue = 0;
		for(int count=0; count<nList.size(); count++){
			double currentV = nList.get(count).getBlock().getValue();
			if(currentV>maxValue){
				maxValue=currentV;
			}
		}
		int index=0;
		for(int count2=0; count2<nList.size();count2++){
			if(nList.get(count2).getBlock().getValue()==maxValue){
				index=count2;
			}
		}
		return nList.get(index).getBlock();
	}
	
	public void placeBlock(Position pos, Block block){
		truck.placeBlock(block, pos);
	}
	
	public double getScore(){
		return currentE.getScore(truck);
	}
	
	public Block getPiece()
	{
		int index = currentS.getBestBlock(list);
		return list.get(index).getBlock();
	}
	
	public Position findLeftTopBack()
	{
		int X = truck.getDimensions(0);
		int Y = truck.getDimensions(1);
		int Z = truck.getDimensions(2);
		
		ArrayList<Integer> posMax = new ArrayList<Integer>();
		posMax.add(X);posMax.add(Y);posMax.add(Z);
		Position max = new Position(posMax);
		for (int x=mX; x<=X;x++)
		{
			for (int y=mY;y<=Y; y++)
			{
				for (int z=mZ;z<=Z;z++)
				{
					ArrayList<Integer> pos = new ArrayList<Integer>();
					pos.add(x);pos.add(y);pos.add(z);
					Position position  = new Position(pos);
					
					//If no block, then position is free, return position
					if (truck.hasBlockAt(position)==false)
					{
						return position;
					}
					mZ++; //Next time search is performed, doesn't have to start at 0,0,0
				}
				mY++;
			}
			mX++;
		}
		return max;
	}
	public Container findSolution()
	{
		//get left top back position
		Position currentPos = this.findLeftTopBack();
		//Use selection Heuristics to get best block
		Block currentBlock = this.getPiece();
		
		for (int i=0; i<4; i++)
		{
			for (int j=0;j<4;j++)
			{
				Container cloneTruck = truck.clone();
				Matrix<Double> rotationMatrix = BasicShape.rotationMatrix(i*90, j*90);
				currentBlock.rotate(rotationMatrix);
				cloneTruck.placeBlock(currentBlock, currentPos);
				if (this.getScore()>score)
				{
					score = this.getScore();
					truck = cloneTruck;
				}				
			}
		}
		
		if(mX !=truck.getDimensions(0)&& mY !=truck.getDimensions(1)&& mZ !=truck.getDimensions(2))
		{
			return this.findSolution();
		}
		else
		{
			return truck;	
		}
	}
	
	private EvaluationHeuristic currentE;
	private SelectionHeuristic currentS;
	private Container truck;
	private ArrayList<Resource> list;
	private int mX,mY,mZ;
	private double score;
	
}
