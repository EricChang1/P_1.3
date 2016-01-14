package test.algorithmTest;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import algorithm.Matrix.*;

/**
 * Test class for matrix class
 * @author martin
 */
public class MatrixTest 
{
	
	
	public MatrixTest()
	{
		mIMat = new IntegerMatrix(3, 3);
		mDMat = new DoubleMatrix(3, 3);
	}
	
	public void setValues()
	{
		for (int cnt = 0; cnt < mIMat.getRows() && cnt < mIMat.getColumns(); ++cnt)
			mIMat.setCell(cnt, cnt, cnt + 1);
		for (int cnt = 0; cnt < mDMat.getRows() && cnt < mDMat.getColumns(); ++cnt)
			mDMat.setCell(cnt, cnt, (double)(cnt + 1));
	}
	
	@Test
	public void cloneTest() 
	{
		IntegerMatrix cloneIMat = mIMat.clone();
		
		DoubleMatrix cloneDMat = mDMat.clone();
		setValues();
		
		assertTrue (!mIMat.equals (cloneIMat) && !mDMat.equals (cloneDMat));
	}
	
	@Test
	public void conversionTest()
	{
		DoubleMatrix convIMat = mIMat.toDoubleMatrix();
		Random gen = new Random (System.currentTimeMillis());
		for (int cnt = 0; cnt < convIMat.getColumns() * convIMat.getRows(); ++cnt)
		{
			int randRow = gen.nextInt(mIMat.getRows());
			int randCol = gen.nextInt (mIMat.getColumns());
			double conv = convIMat.getCell(randRow, randCol);
			double orig = mIMat.getCell(randRow, randCol).doubleValue();
			assertTrue (conv == orig);
		}
		
		IntegerMatrix convDMat = mDMat.toIntegerMatrix();
		for (int cnt = 0; cnt < convDMat.getColumns() * convDMat.getRows(); ++cnt)
		{
			int randRow = gen.nextInt(mDMat.getRows());
			int randCol = gen.nextInt (mDMat.getColumns());
			double conv = convDMat.getCell(randRow, randCol);
			double orig = mDMat.getCell(randRow, randCol).doubleValue();
			assertTrue (conv == orig);
		}
	}
	
	@Test
	public void vectorProductTest()
	{
		assertTrue (mIMat.vectorProduct(mIMat.getColumn(0), mIMat.getColumn(1)) == 0);
		assertTrue (mDMat.vectorProduct(mDMat.getColumn(0), mDMat.getColumn(1)) == 0.0);
	}
	
	
	@Test
	public void countTest()
	{
		setValues();
		for (int cnt = 0; cnt < mIMat.getRows(); ++cnt)
		{
			assertTrue (mIMat.countCol(cnt) == 1);
			assertTrue (mIMat.countRow(cnt) == 1);
		}
		
		for (int cnt = 0; cnt < mDMat.getRows(); ++cnt)
		{
			System.out.println ("count col " + mDMat.countCol(cnt));
			assertTrue (mDMat.countCol(cnt) == 1);
			assertTrue (mDMat.countRow(cnt) == 1);
		}
	}
	
	@Test
	public void dimensionEqualsTest()
	{
		assertTrue (mIMat.dimensionEquals(mDMat));
	}
	
	@Test
	public void emptyFilledTest()
	{
		setValues();
		for (int cnt = 0; cnt < mIMat.getRows(); ++cnt)
		{
			assertFalse (mIMat.isColEmpty(cnt));
			assertFalse (mIMat.isColFilled(cnt));
			assertFalse (mIMat.isRowEmpty(cnt));
			assertFalse (mIMat.isRowFilled(cnt));
		}
	}
	
	@Test
	public void copyTest()
	{
		IntegerMatrix sub = new IntegerMatrix(2, 1);
		int rowOff = 1, colOff = 1;
		sub.copyValues(mIMat, 0, 0, rowOff, colOff, sub.getRows(), sub.getColumns());
		for (int cRow = 0; cRow < sub.getRows(); ++cRow)
		{
			for (int cCol = 0; cCol < sub.getColumns(); ++cCol)
				assertTrue (sub.getCell(cRow, cCol).equals (mIMat.getCell(cRow + rowOff, cCol + colOff)));
		}
	}
	
	IntegerMatrix mIMat;
	DoubleMatrix mDMat;
}
