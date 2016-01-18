package algorithm;

import java.util.ArrayList;

import algorithm.Matrix.*;


/**
 * performs intersection checking and stores results
 * @author martin
 */
public class IntersectionSolver 
{
	public static enum Result {INCONSISTENT, ONE, INFINITE}
	
	/**
	 * custom exception class for intersection solver
	 * @author martin
	 */
	@SuppressWarnings("serial")
	public static class IntersectionSolverException extends IllegalStateException
	{
		public IntersectionSolverException() {}
		
		public IntersectionSolverException (String message) { super (message); }
	}
	
	/**
	 * constructor
	 * @param l1 line 1
	 * @param l2 line 2
	 */
	public IntersectionSolver (GeoShape s1, GeoShape s2)
	{
		mS1 = s1;
		mS2 = s2;
		mScalars = new ArrayList<Double>();
		solve();
	}
	
	/**
	 * @return position of intersection
	 * @throws IntersectionSolverException if there is no intersection
	 */
	public Position getIntersection (GaussElim solver) throws IntersectionSolverException
	{
		if (mSolutionType == Result.INCONSISTENT)
			throw new IntersectionSolverException ("non solvable linear equations");
		else if (mSolutionType == Result.INFINITE)
			throw new IntersectionSolverException ("system has infinite many solutions");
		
		DoubleMatrix p = mS1.getFirst().toDoubleMatrix();
		ArrayList <DoubleMatrix> vecs = mS1.getVectors();
		for (int cDim = 0; cDim < mS1.getDimension(); ++cDim)
		{
			for (int cVec = 0; cVec < vecs.size(); ++cVec)
				p.setCell(cDim, 0, p.getCell(cDim, 0) + mScalars.get(cVec) * vecs.get(cVec).getCell(cDim,  0));
		}
		return new Position (p.toIntegerMatrix());
	}
	
	
	public Result getSolutionType()
	{
		return mSolutionType;
	}
	
	
	public ArrayList<Double> getScalars()
	{
		return (ArrayList<Double>) mScalars.clone();
	}
	
	
	public ArrayList <GeoShape> getShapes()
	{
		ArrayList <GeoShape> shapes = new ArrayList<GeoShape>();
		shapes.add (mS1);
		shapes.add (mS2);
		return shapes;
	}
	
	public boolean isWithinBounds()
	{
		if (mSolutionType == Result.INCONSISTENT)
			throw new IntersectionSolverException ("non solvable linear equations");
		return mOnline;
	}
	
	private void solve()
	{
		DoubleMatrix eq = mS1.loadEquationMatrix(mS2);
		GaussElim solver = new GaussElim(eq);
		solver.run();
		if (solver.isConsistent() && solver.allBasicVariables())
		{
			mSolutionType = Result.ONE;
			DoubleMatrix solution = solver.getTranslationVector();
			for (int cVar = 0; cVar < solver.getNumberOfBasicVars(); ++cVar)
				mScalars.add (solution.getCell(cVar, 0));
			Position inter = getIntersection (solver);
			mOnline = mS1.isInRange(inter) && mS2.isInRange(inter);
		}
		else if (!solver.isConsistent())
			mSolutionType = Result.INCONSISTENT;
		else
		{
			mSolutionType = Result.INFINITE;
			
		}
		mGelim = solver;
	}
	
	private GaussElim mGelim;
	private GeoShape mS1, mS2;
	private ArrayList <Double> mScalars;
	private Result mSolutionType;
	private boolean mOnline;
}
