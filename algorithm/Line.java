import java.util.ArrayList;

public class Line {
	
	/**
	 * @param pos2 Position
	 * Constructs a default line between 0,0,0 and pos2
	 */
	public Line(pos2) {
		ArrayList<Integer> pos1 = new ArrayList<Integer>();
		this.pos1 = new Position(pos1);
		this.pos2 = new Position(pos2);
	}
	
	/**
	 * @param pos1 First position
	 * @param pos2 Second position
	 * Constructs a default line between pos1 and pos2
	 */
	public Line(pos1, pos2) {
		this.pos1 = new Position(pos1);
		this.pos2 = new Position(pos2);
	}
	
	private Position pos1;
	private Position pos1;
}