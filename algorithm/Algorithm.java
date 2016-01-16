package algorithm;

import java.util.ArrayList;

public abstract class Algorithm implements Runnable
{
	@SuppressWarnings("serial")
	public static class AlgorithmNotStartedException extends IllegalStateException
	{
		public AlgorithmNotStartedException() {}
		
		public AlgorithmNotStartedException (String message) {super (message); }
	}
	
	@SuppressWarnings("serial")
	public static class AlgorithmTerminatedException extends IllegalStateException
	{
		public AlgorithmTerminatedException() {}
		
		public AlgorithmTerminatedException (String message) {super (message); }
	}
	
	/**
	 * parametric constructor
	 * @param container starting container
	 * @param pieces list of available pieces
	 * Postcondition: the object will store the references directly without cloning
	 */
	public Algorithm (Container container, ArrayList <Resource> pieces)
	{
		mContainer = container;
		mPieces = pieces;
		mAlgoStarted = false;
		mAlgoDone = false;
	}
	
	/**
	 * @return a clone of the internal container once the algorithm was started
	 */
	public Container getFilledContainer()
	{
		if (!mAlgoDone)
			throw new AlgorithmNotStartedException ("tried to access presumably filled container before algorithm started");
		return mContainer.clone();
	}
	
	/**
	 * @return true if algorithm was set to terminated
	 */
	public boolean isAlgoDone()
	{
		return mAlgoDone;
	}
	
	/**
	 * @return true if algorithm was started
	 */
	public boolean isAlgoStarted()
	{
		return mAlgoStarted;
	}
	
	/**
	 * run algorithm
	 */
	public void run()
	{
		mAlgoStarted = true;
	}
	
	/**
	 * @return reference to internal container
	 * Precondition algorithm needs to be terminated
	 */
	protected Container getContainer()
	{
		if (!mAlgoStarted)
			throw new AlgorithmNotStartedException ("tried to accepublic void run()");
		if (mAlgoDone)
			throw new AlgorithmTerminatedException ("tried to access internal container after algorithm terminated");
		return mContainer;
	}
	
	/**
	 * @return list of internal resource objects
	 * Precondition: algorithm needs to be started but not terminated
	 */
	protected ArrayList <Resource> getPieces()
	{
		if (!mAlgoStarted)
			throw new AlgorithmNotStartedException ("tried to access internal pieces before algorithm was started");
		if (mAlgoDone)
			throw new AlgorithmTerminatedException ("tried to access internal container after algorithm terminated");
		return mPieces;
	}
	
	/**
	 * set algorithm to done making getContainer and getPieces inaccessible
	 * Precondition: algorithm needs to be started but not terminated
	 */
	protected void setAlgoDone()
	{
		if (!mAlgoStarted)
			throw new AlgorithmNotStartedException ("tried to access internal pieces before algorithm was started");
		mAlgoDone = true;
	}
	
	private Container mContainer;
	private ArrayList <Resource> mPieces;
	private boolean mAlgoStarted, mAlgoDone; 
}
