package test.algorithmTest;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.*;
import algorithm.Matrix.*;

public class LineTest 
{
	public static void main(String[] args)
	{
		LineTest test = new LineTest();
		test.intersectTest();
		//test.identicalTest();
		/*test.parallelTest();
		test.touchingTest();
		test.neitherTest();*/
	}
	
	
	public LineTest()
	{
		IntegerMatrix v = new IntegerMatrix(3, 1);
		
		Glue pBasic1 = new Glue (v);
		v.setCell(0, 0, 5);
		Glue pBasic2 = new Glue (v);
		mBasic = new Line (pBasic1, pBasic2);//0|0|0 - 5|0|0
		
		v.setCell(0, 0, 2);
		v.setCell(1, 0, -2);
		Glue Intersect1 = new Glue (v);
		v.setCell(1, 0, 2);
		Glue Intersect2 = new Glue (v);
		mIntersect = new Line (Intersect1, Intersect2);//2|-2|0 - 2|2|0
		mIntersectionLines = new IntersectionSolver(mBasic, mIntersect);
		
		v.setCell(0, 0, 6);
		v.setCell(1, 0, 0);
		Glue pIdentical2 = new Glue (v);
		mIdentical = new Line (pBasic1, pIdentical2);//0|0|0 - 6|0|0
		mIdenticalLines = new IntersectionSolver (mBasic, mIdentical);
		
		v.setCell(1, 0, 2);
		Glue pParallel1 = new Glue (v);
		v.setCell(0, 0, 0);System.out.println ("after sorting");
		Glue pParallel2 = new Glue (v);
		mParalell = new Line (pParallel1, pParallel2);//6|2|0 - 0|2|0
		mParallelLines = new IntersectionSolver(mBasic, mParalell);
		
		mTouching = new Line (pBasic2, pIdentical2); //5|0|0 - 6|0|0
		mTouchingLines = new IntersectionSolver (mBasic, mTouching);
		
		
		v.setCell(0, 0, 2);
		v.setCell(1, 0, -2);
		v.setCell(2, 0, 1);
		Glue pNeither1 = new Glue (v);
		v.setCell(1, 0, 2);
		Glue pNeither2 = new Glue(v);
		mNeither = new Line (pNeither1, pNeither2);//2|-2|1 - 2|2|1 
		mNeitherLines = new IntersectionSolver(mBasic, mNeither);		
	}
	
	@Test
	public void intersectTest()
	{
		System.out.println("intersection: " + mIntersectionLines.getSolutionType());
		assertTrue (mIntersectionLines.getSolutionType() == IntersectionSolver.Result.ONE);
	}
	
	@Test
	public void parallelTest()
	{
		assertTrue (mParallelLines.getSolutionType() == IntersectionSolver.Result.INCONSISTENT);
	}
	
	@Test
	public void identicalTest()
	{
		assertTrue (mIdenticalLines.getSolutionType() == IntersectionSolver.Result.INFINITE);
	}
	
	@Test
	public void touchingTest()
	{
		assertTrue (mTouchingLines.getSolutionType() == IntersectionSolver.Result.INFINITE);
	}
	
	@Test
	public void neitherTest()
	{
		assertTrue (mNeitherLines.getSolutionType() == IntersectionSolver.Result.INCONSISTENT);
	}
	
	private Line mBasic, mIntersect, mIdentical, mParalell, mTouching, mNeither;
	private IntersectionSolver mIntersectionLines, mIdenticalLines, mParallelLines, mTouchingLines, mNeitherLines;
}
