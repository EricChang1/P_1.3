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
		DoubleMatrix p = new DoubleMatrix (mL1.getFirst().getDimension(), 1);
		for (int cDim = 0; cDim < p.getRows(); ++cDim)
			p.setCell(cDim, 0, mL1.getFirst().getPosition(cDim) + mScalar1 * mL1.getLineVector().getCell(cDim, 0));
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
	
	public boolean solutionOnline()
	{
		return mOnline;
	}
	
	private void solve()
	{
		DoubleMatrix eq = mL1.loadEquationMatrix(mL2);
		GaussElim solver = new GaussElim(eq);
		solver.run();
		if (solver.isConsistent() && solver.allBasicVariables())
		{
			mScalar1 = solver.getMatrix().getCell(0, solver.getMatrix().getColumns());
			mScalar2 = solver.getMatrix().getCell(1, solver.getMatrix().getColumns());
			Position inter = getIntersection();
			mOnline = true;
			int cDim = 0;
			while (cDim < mL1.getFirst().getDimension() && mOnline)
			{
				if (inter.getPosition(cDim) < Math.min(mL1.getFirst().getPosition(cDim), mL1.getSecond().getPosition (cDim)) &&
					inter.getPosition(cDim) > Math.max(mL1.getFirst().getPosition(cDim), mL1.getSecond().getPosition (cDim)) &&
					inter.getPosition(cDim) < Math.min(mL2.getFirst().getPosition(cDim), mL2.getSecond().getPosition (cDim)) &&
					inter.getPosition(cDim) > Math.max(mL2.getFirst().getPosition(cDim), mL2.getSecond().getPosition (cDim)))
					mOnline = false;
				++cDim;
			}
		}
		else if (solver.allBasicVariables())
			mSolutionType = Result.INCONSISTENT;
		else
			mSolutionType = Result.INFINITE;
	}
	
	private Line mL1, mL2;
	private Double mScalar1, mScalar2;
	private Result mSolutionType;
	private boolean mOnline;
}
