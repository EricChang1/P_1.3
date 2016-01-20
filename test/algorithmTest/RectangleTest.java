package test.algorithmTest;


import geometry.IntersectionSolver;
import geometry.Line;
import geometry.Rectangle;
import models.Glue;
import models.Matrix.*;

import org.junit.Test;
import org.junit.Assert.*;

import algorithm.*;

public class RectangleTest 
{
	public static void main (String[] args)
	{
		RectangleTest test = new RectangleTest();
		test.intersectTest();
		test.parallelTest();
	}
	
	public RectangleTest()
	{
		IntegerMatrix p = new IntegerMatrix (3, 1);
		Glue p1 = new Glue (p);
		
		p.setCell(0, 0, 5);
		p.setCell(1, 0, 4);
		Glue p2 = new Glue (p);
		
		p = p1.toVector();
		p.setCell(2, 0, 1);
		Glue p3 = new Glue (p);
		
		p = p2.toVector();
		p.setCell(2, 0, 1);
		Glue p4 = new Glue (p);
		
		p = new IntegerMatrix (3, 1);
		p.setCell(2, 0, 3);
		Glue p5 = new Glue (p);
		
		p.setCell(0, 0, -2);
		p.setCell(1, 0, -2);
		p.setCell(2, 0, -3);
		Glue p6 = new Glue (p);
		
		mBase = new Rectangle (p1, p2);
		mLineIntersect = new Line (p5, p6);
		mLineParallel = new Line (p3, p4);
	}
	
	public void intersectTest()
	{
		IntersectionSolver solver = new IntersectionSolver(mBase, mLineIntersect);
		System.out.println ("intersection");
		System.out.println (" intersects " + solver.getSolutionType());
	}
	
	public void parallelTest()
	{
		IntersectionSolver solver = new IntersectionSolver(mBase, mLineParallel);
		System.out.println ("parallel");
		System.out.println (" intersects " + solver.getSolutionType());
	}
	
	
	private Line mLineIntersect, mLineParallel;
	private Rectangle mBase;
	private IntersectionSolver mIntersect, mParallel;
}
