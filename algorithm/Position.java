
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Position extends Glue{

	/** 
	 * Default constructor that constructs a Position(0,0,0) 
	 */
	public Position(ArrayList<Integer> position){
		super(position);
	}
	/**
	 * @param pos ArrayList with three values inside (otherwise: exception throwed)
	 */
	public void setPosition(ArrayList<Integer> pos) {
		pPosition = pos;
			this.x = (int) pPosition.get(0);
			this.y = (int) pPosition.get(1);
			this.z = (int) pPosition.get(2);
	}
	
	/**
	 * @param values ArrayList with the three values inside of it (x,y,z)
	 * @return True if position is changed and in the range
	 */
	public void movePosition(ArrayList<Integer> values) {
		if (values.size() != 3) throw new EmptyStackException();
		int newX = pPosition.get(0) + values.get(0);
		int newY = pPosition.get(1) + values.get(1);
		int newZ = pPosition.get(2) + values.get(2);
		pPosition.set(0, newX);
		pPosition.set(1, newY);
		pPosition.set(2, newZ);
		setPosition(pPosition);
			
	}
    private ArrayList<Integer> pPosition;
}


	