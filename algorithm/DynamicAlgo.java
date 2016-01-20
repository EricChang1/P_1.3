package algorithm;

import geometry.Cuboid;

import java.util.*;
import java.util.Map.Entry;

import models.Block;
import models.Container;
import models.Glue;
import models.Resource;
import models.Matrix.*;


public class DynamicAlgo extends Algorithm 
{
	
	public static class Subset
	{	
		public Subset (LinkedList <Block> blocks)
		{
			mBlocks = blocks;
			mVolume = 0;
		}
		
		public Subset clone()
		{
			Subset s = new Subset (mBlocks);
			s.mVolume = this.mVolume;
			return s;
		}
		
		public LinkedList <Block> getBlocks()
		{
			LinkedList<Block> blocks = new LinkedList<Block>();
			for (Block b : mBlocks)
				blocks.add(b.clone());
			return blocks;
		}
		
		
		public int getVolume()
		{
			return mVolume;
		}
		
		public boolean equals (Subset comp)
		{
			return this.mBlocks.equals (comp.mBlocks);
		}
		
		public boolean equals (LinkedList <Block> comp)
		{
			return comp.equals(mBlocks);
		}
		
		public void addBlock (Block bAdd, int idAdd)
		{
			mBlocks.add(bAdd.clone());
			mVolume += bAdd.getVolume();
		}
		
		private LinkedList <Block> mBlocks;
		private int mVolume;
	}
	
	
	public DynamicAlgo() {}
	
	/**
	 * @param emptyCubes list of cubes
	 * @param existing list of existing orders using the previous cubes
	 * @return list of permutations of cubes using all elements up until the previous one
	 */
	public ArrayList <LinkedList <Cuboid>> generateCubePermutations (ArrayList <Cuboid> emptyCubes, LinkedList<Cuboid> existing)
	{
		ArrayList <LinkedList<Cuboid>> newOrders = new ArrayList<LinkedList<Cuboid>>();
		for (int insertPos = 0; insertPos <= existing.size(); ++insertPos)
		{
			LinkedList <Cuboid> newOne = (LinkedList<Cuboid>)existing.clone();
			newOne.add(insertPos, emptyCubes.get(0));
			if (emptyCubes.size() > 1)
			{
				ArrayList <Cuboid> reducedCubes = new ArrayList <Cuboid> (emptyCubes.subList(1, emptyCubes.size() - 1));
				ArrayList <LinkedList <Cuboid>> complete = generateCubePermutations (reducedCubes, newOne);
				newOrders.addAll (complete);
			}
			newOrders.add (newOne);
		}
		return newOrders;
	}
	
	public Container searchBlockPermutations (Container c, LinkedList <Block> subset)
	{
		Container cut = c.clone();
		cut.addMissingRectanglePoints();
		ArrayList <Cuboid> emptyCubes = cut.decomposeIntoCuboids();
		ArrayList <LinkedList<Cuboid>> orders = generateCubePermutations(emptyCubes, new LinkedList <Cuboid>());
		
		double maxVal = 0;
		LinkedList <Cuboid> maxOrder = null;
		for (LinkedList <Cuboid> order : orders)
		{
			double currVal = 0;
			LinkedList <Block> remain = (LinkedList<Block>) subset.clone();
			for (Cuboid cube : order)
			{
				if (!remain.isEmpty())
				{
					int d = cube.getDimensions().get(0);
					int w = cube.getDimensions().get(1);
					int h = cube.getDimensions().get(2);
					int s = 0;
					while (s < mSubsets.size() && !mSubsets.get(s).equals(remain))
						++s;
					currVal += mLookupTable.get(d, w, h, s);
				}
			}
		}
		//carry on? read up first again...
	}
	
	public void init (Container container, ArrayList <Resource> pieces)
	{
		super.init(container, pieces);
		generatePowerSet();
		mLookupTable = new LookupTable(container.getDimensions(0), container.getDimensions(1), container.getDimensions(2), mSubsets.size());
	}
	
	public void run()
	{
		int dimension = 3;
		Glue zeroPos = new Glue (new IntegerMatrix (dimension, 1));
		
		for (int cDepth = 0; cDepth <= getContainer().getDimensions(0); ++cDepth)
		{
			for (int cWidth = 0; cWidth <= getContainer().getDimensions(1); ++cWidth)
			{
				for (int cHeight = 0; cHeight <= getContainer().getDimensions(2); ++cHeight)
				{
					for (int cSet = 0; cSet < mSubsets.size(); ++cSet)
					{
						for (int cPiece = 0; cPiece < getPieces().size(); ++cPiece)
						{
							Block bRef = getPieces().get(cPiece).getBlock();
							double current = 0;
							if (bRef.getDimensions(0) <= cDepth && bRef.getDimensions(1) <= cWidth &&
								bRef.getDimensions(2) <= cHeight)
							{
								Container c = new Container (cDepth, cHeight, cWidth);
								c.placeBlock(bRef, zeroPos);
								
								
								current += c.getValue();
								ArrayList <Cuboid> emptyCubes = c.decomposeIntoCuboids();
								//problem: order matters
								//solution: brute force
								for (Cuboid emptyC : emptyCubes)
								{
									IntegerMatrix size = new IntegerMatrix(dimension, 1);
									ArrayList <DoubleMatrix> cubeVecs = emptyC.getVectors();
									for (int cDim = 0; cDim < dimension; ++cDim)
										size.setCell(cDim, 0, (int)(double)cubeVecs.get(cDim).getCell(cDim, 0));
									current += mLookupTable.get(size.getCell(0, 0), size.getCell(1, 0), size.getCell(2, 0), cPiece - 1);
								}
								if (mLookupTable.get(cDepth, cWidth, cHeight, cSet) < current)
									mLookupTable.set(current, cDepth, cWidth, cHeight, cSet);
							}
						}
					}
				}
			}
		}
	}
	
	private void generatePowerSet()
	{
		mSubsets.add(new Subset(new LinkedList <Block>()));
		for (int cRes = 0; cRes < getPieces().size(); ++cRes)
		{
			Resource res = getPieces().get(cRes);
			int maxIndex = mSubsets.size();
			for (int cExistSub = 0; cExistSub < maxIndex; ++cExistSub)
			{
				Subset last = mSubsets.get(cExistSub);
				while (last.getVolume() + res.getBlock().getVolume() <= getContainer().getVolume())
				{
					Subset curr = last.clone();
					curr.addBlock(res.getBlock(), (int)Math.pow(getPieces().size(), cRes));
					mSubsets.add(curr);
					last = curr;
				}
			}
		}
	}
	
	private ArrayList <Subset> mSubsets;
	private LookupTable mLookupTable;
}
