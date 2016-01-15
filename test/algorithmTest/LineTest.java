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
		//test.intersectTest();
		test.identicalTest();
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
		
		v.setCell(0, 0, 6);
		v.setCell(1, 0, 0);
		Glue pIdentical2 = new Glue (v);
		mIdentical = new Line (pBasic1, pIdentical2);//0|0|0 - 6|0|0
		
		v.setCell(1, 0, 2);
		Glue pParallel1 = new Glue (v);
		v.setCell(0, 0, 0);
		Glue pParallel2 = new Glue (v);
		mParalell = new Line (pParallel1, pParallel2);//6|2|0 - 0|2|0
		
		mTouching = new Line (pBasic2, pIdentical2); //5|0|0 - 6|0|0
		
		v.setCell(0, 0, 2);
		v.setCell(1, 0, -2);
		v.setCell(2, 0, 1);
		Glue pNeither1 = new Glue (v);
		v.setCell(1, 0, 2);
		Glue pNeither2 = new Glue(v);
		mNeither = new Line (pNeither1, pNeither2);//2|-2|1 - 2|2|1 
		
	}
	
	@Test
	public void intersectTest()
	{
		assertTrue (mBasic.doIntersect(mIntersect));
	}
	
	@Test
	public void parallelTest()
	{
		System.out.println ("parallel " + mBasic.doIntersect(mParalell) + ", " + mBasic.isSameOrientation(mParalell));
		assertTrue (!mBasic.doIntersect(mParalell) && mBasic.isSameOrientation(mParalell));
	}
	
	//@Test
	public void identicalTest()
	{
		System.out.println ("Identical " + mBasic.doIntersect(mIdentical) + ", " + mBasic.isSameOrientation(mIdentical));
		//assertTrue (mBasic.doIntersect(mIdentical) && mBasic.isSameOrientation(mIdentical));
	}
	
	@Test
	public void touchingTest()
	{
		assertTrue (!mBasic.doIntersect(mTouching));
	}
	
	@Test
	public void neitherTest()
	{
		assertTrue (!mBasic.doIntersect(mNeither) && !mBasic.isSameOrientation(mNeither));
	}
	
	private Line mBasic, mIntersect, mIdentical, mParalell, mTouching, mNeither;
}
