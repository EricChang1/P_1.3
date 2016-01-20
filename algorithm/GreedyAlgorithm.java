package algorithm;
import java.util.ArrayList;

import models.BasicShape;
import models.Block;
import models.Container;
import models.Matrix;
import models.Position;

import algorithm.*;

public class GreedyAlgorithm extends Algorithm {
	
	public GreedyAlgorithm(EvaluationHeuristic e, SelectionHeuristic s){
		this.currentE = e;
		this.currentS = s;
	}
	
	@Override
		public void run() 
	{
		super.run();
		while (!isAlgoDone())
		{
			placeBlock();
		}
	}
	
	public Position findLeftTopBack()
	{
		int X = getContainer().getDimensions(0);
		int Y = getContainer().getDimensions(1);
		int Z = getContainer().getDimensions(2);
		
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
					if (getContainer().hasBlockAt(position)==false)
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
	public void placeBlock()
	{
		//get left top back position
		Position currentPos = this.findLeftTopBack();
		//Use selection Heuristics to get best block
		Block currentBlock = getPieces().get(currentS.getBestBlock(getPieces())).getBlock();
		
		for (int i=0; i<4; i++)
		{
			for (int j=0;j<4;j++)
			{
				Container cloneTruck = getContainer().clone();
				Matrix<Double> rotationMatrix = BasicShape.rotationMatrix(i*90, j*90);
				currentBlock.rotate(rotationMatrix);
				cloneTruck.placeBlock(currentBlock, currentPos);
				if (currentE.getScore(getContainer())>score)
				{
					score = currentE.getScore(getContainer());
					getContainer().placeBlock(currentBlock, currentPos);
				}				
			}
		}	
		if(mX !=getContainer().getDimensions(0)&& mY !=getContainer().getDimensions(1)&& mZ !=getContainer().getDimensions(2))
		{
			this.placeBlock();
		}
		
	}
	
	private EvaluationHeuristic currentE;
	private SelectionHeuristic currentS;
	private int mX,mY,mZ;
	private double score;
	
}
