package test.algorithmTest;

import algorithm.*;
import java.util.*;

import models.Block;
import models.Container;
import models.Glue;
import models.Position;
import models.Matrix.*;

public class ContainerTest 
{
	public static void main (String[] args)
	{
		ContainerTest test = new ContainerTest();
		test.addTest();
	}
	
	public ContainerTest()
	{
		mContainer = new Container(10, 10, 10);
	}
	
	public void addTest()
	{	
		ArrayList <IntegerMatrix> vecs = Container.computeInitDimVectors(2, 3, 3);
		IntegerMatrix adj = Container.computeInitAdjacencyMatrix(vecs);
		Block cuboid1 = new Block (vecs, adj, 2);
		System.out.println("cuboid 1");
		cuboid1.print(System.out);
		
		vecs = Container.computeInitDimVectors(2, 2, 4);
		adj = Container.computeInitAdjacencyMatrix(vecs);
		Block cuboid2 = new Block (vecs, adj, 5);
		System.out.println("cuboid 2");
		cuboid2.print(System.out);
		
		
		Position posZero = setVector (0, 0, 0);
		Position posOutsideConflict = setVector (11, 11, 11);
		
		System.out.println ("demo 0|0|0");
		printPlaceTest (mContainer, cuboid1, posZero);
		System.out.println ("demo 11|11|11");
		printPlaceTest (mContainer, cuboid2, posOutsideConflict);
		
		Position posInsideConflict_Bounds = setVector (9, 9, 9);
		System.out.println ("demo 9|9|9");
		printPlaceTest(mContainer, cuboid1, posInsideConflict_Bounds);
		
		System.out.println ("actually placing cuboid 1 at 0|0|0");
		mContainer.placeBlock(cuboid1, posZero);
		mContainer.print(System.out);
		
		Position posInsideConflict_Placed = setVector (1, 1, 2);
		System.out.println ("demo 2|1|3");
		printPlaceTest (mContainer, cuboid2, posInsideConflict_Placed);
		
		System.out.println ("demo 2|3|3: possible");
		Position vertex = setVector (2, 3, 3);
		ArrayList<Position> relatives = mContainer.getRelativePlacements(cuboid2, mContainer.getVertexIndex(vertex.toVector()));
		for (Position relat : relatives)
		{
			System.out.println (relat.toString());
			printPlaceTest (mContainer, cuboid2, relat);
			if (mContainer.checkPositionInside(relat) && mContainer.checkPositionOverlap(cuboid2, relat))
			{
				mContainer.placeBlock(cuboid2, relat);
				System.out.println ("Can block be found?");
				//Block b = mContainer.getBlockAt(relat);
				mContainer.print(System.out);
				System.out.println ("success!");
				break;
			}
		}
		
	}
	
	public Position setVector (int c1, int c2, int c3)
	{
		IntegerMatrix v3d = new IntegerMatrix (3, 1);
		v3d.setCell(0, 0, c1);
		v3d.setCell(1, 0, c2);
		v3d.setCell(2, 0, c3);
		return new Position (v3d);
	}
	
	public void printPlaceTest (Container c, Block b, Glue g)
	{
		System.out.print ("Placing " + c.checkPositionInside(g));
		System.out.println (" " + c.checkPositionOverlap(b, g));
	}
	
	
	private Container mContainer;
}
