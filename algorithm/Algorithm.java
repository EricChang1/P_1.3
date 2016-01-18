package algorithm;

import java.util.ArrayList;

/**
 * base class for all algorithms
 * the class can be in 3 states: 1) not started, 2) started but not terminated, 3) terminated
 * on construction and after initialization, the object will be in state 1)
 * after call to run() the object will be in state 2)
 * after call to setAlgoDone() the object will be in state 3)
 * the only way to go back to state 1) from state 2) and 3) is to call init()
 * @author martin
 */
public abstract class Algorithm implements Runnable
{
	@SuppressWarnings("serial")
	public static class AlgorithmNotInitializedException extends IllegalStateException
	{
		public AlgorithmNotInitializedException() {}
		
		public AlgorithmNotInitializedException (String message) {super (message); }
	}
	
	@SuppressWarnings("serial")
	public static class AlgorithmNotStartedException extends IllegalStateException
	{
		public AlgorithmNotStartedException() {}
		
		public AlgorithmNotStartedException (String message) {super (message); }
	}
	
	@SuppressWarnings("serial")
	public static class AlgorithmRunningException extends IllegalStateException
	{
		public AlgorithmRunningException() {}
		
		public AlgorithmRunningException (String message) {super (message); }
	}
	
	@SuppressWarnings("serial")
	public static class AlgorithmTerminatedException extends IllegalStateException
	{
		public AlgorithmTerminatedException() {}
		
		public AlgorithmTerminatedException (String message) {super (message); }
	}
	
	/**
	 * default constructor
	 */
	public Algorithm ()
	{
		mContainer = null;
		mPieces = null;
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
	 * Initializes resources 
	 * Postcondition: starting/termination flags will be reset
	 * @param container container to perform algorithm for
	 * @param pieces pieces available
	 */
	public void init (Container container, ArrayList <Resource> pieces)
	{
		mContainer = container;
		mPieces = pieces;
		mAlgoStarted = false;
		mAlgoDone = false;
	}
	
	/**
	 * run algorithm
	 */
	public void run()
	{
		if (mContainer == null || mPieces == null)
			throw new AlgorithmNotInitializedException ("Missing init parameters to run the algorithm");
		if (mAlgoStarted)
			throw new AlgorithmRunningException ("tried to run algorithm object already running");
		if (mAlgoDone)
			throw new AlgorithmTerminatedException ("tried to run already terminated algorithm");
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
