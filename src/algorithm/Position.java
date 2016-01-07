package algorithm;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Position {

	/** 
	 * Default constructor that constructs a Position(0,0,0) 
	 */
	public Position() {
		this.x = 0;
		this.y = 0;
		this.z = 0;		
	}
	
	/** 
	 * @param pos ArrayList with 3 elements stored in it (otherwise: exception throwed)
	 * Constructs the position from these values (x,y,z)
	 */
	public Position(ArrayList pos) {
		if (pos.size() != 3) throw new EmptyStackException();
		else {
			this.x = (int) pos.get(0);
			this.y = (int) pos.get(1);
			this.z = (int) pos.get(2);
		}
	}
	
	/**
	 * @return ArrayList with the three values stored in it (x,y,z)
	 */
	public ArrayList getPosition() {
		ArrayList<Integer> pos = new ArrayList<Integer>();
		pos.add(this.x);
		pos.add(this.y);
		pos.add(this.z);
		return pos;
	}
	
	/**
	 * @param pos ArrayList with three values inside (otherwise: exception throwed)
	 */
	public void setPosition(ArrayList pos) {
		if (pos.size() != 3) throw new EmptyStackException();
		else {
			this.x = (int) pos.get(0);
			this.y = (int) pos.get(1);
			this.z = (int) pos.get(2);
		}
	}
	
	/**
	 * @param values ArrayList with the three values inside of it (x,y,z)
	 * @return True if position is changed and in the range
	 */
	public boolean movePosition(ArrayList values) {
		if (values.size() != 3) throw new EmptyStackException();
		
	}
	
	private int x, y, z;
}


	