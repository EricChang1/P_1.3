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
	public IntersectionSolver (Line l1, Line l2)
	{
		mL1 = l1;
		mL2 = l2;
		solve();
	}
	
	/**
	 * @return position of intersection
	 * @throws IntersectionSolverException if there is no intersection
	 */
	public Position getIntersection() throws IntersectionSolverException
	{
		if (mSolutionType == Result.INCONSISTENT)
			throw new IntersectionSolverException ("non solvable linear equations");
		else if (mSolutionType == Result.INFINITE)
			throw new IntersectionSolverException ("system has infinite many solutions");
		DoubleMatrix p = new DoubleMatrix (mL1.getPoints().get(0).getRows(), 1);
		DoubleMatrix lineVec = mL1.getVectors().get(0);
		for (int cDim = 0; cDim < p.getRows(); ++cDim)
		{
			double val = mL1.getPoints().get(0).getCell(cDim, 0) + mScalar1 * lineVec.getCell(cDim, 0);
			p.setCell(cDim, 0, val);
		}
		return new Position (p.toIntegerMatrix());
	}
	
	
	public Result getSolutionType()
	{
		return mSolutionType;
	}
	
	
	public ArrayList<Double> getScalars()
	{
		ArrayList <Double> scalars = new ArrayList<Double>();
		scalars.add (mScalar1);
		scalars.add (mScalar2);
		return scalars;
	}
	
	
	public ArrayList <Line> getLines()
	{
		ArrayList <Line> lines = new ArrayList<Line>();
		lines.add (mL1);
		lines.add (mL2);
		return lines;
	}
	
	public boolean isSolutionOnline()
	{
		if (mSolutionType == Result.INCONSISTENT)
			throw new IntersectionSolverException ("non solvable linear equations");
		return mOnline;
	}
	
	private void solve()
	{
		DoubleMatrix eq = mL1.loadEquationMatrix(mL2);
		GaussElim solver = new GaussElim(eq);
		solver.run();
		if (solver.isConsistent() && solver.allBasicVariables())
		{
			mSolutionType = Result.ONE;
			mScalar1 = solver.getMatrix().getCell(0, solver.getMatrix().getColumns() - 1);
			mScalar2 = solver.getMatrix().getCell(1, solver.getMatrix().getColumns() - 1);
			Position inter = getIntersection();
			mOnline = mL1.isInRange(inter) && mL2.isInRange(inter);
		}
		else if (!solver.isConsistent())
			mSolutionType = Result.INCONSISTENT;
		else
		{
			mSolutionType = Result.INFINITE;
			mOnline = mL1.isInRange(new Glue (mL2.getPoints().get(0))) || mL1.isInRange(new Glue (mL2.getPoints().get(1)));
		}
		mGelim = solver;
	}
	
	private GaussElim mGelim;
	private Line mL1, mL2;
	private Double mScalar1, mScalar2;
	private Result mSolutionType;
	private boolean mOnline;
}
