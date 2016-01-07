import java.util.ArrayList;

public class Glue {

public Glue(ArrayList<Integer> position){
	ArrayList<Integer> pos = position; 
	if (pos.size() != 3) throw new GlueException();
}

/**
* @return Position of glue
*/
public ArrayList<Integer> getPosition(){
		return pos;
}

/** Nested class to create a custom exception
 */
static class GlueException extends IllegalArgumentException{
	
	/**Exception constructor
	 */
	public GlueException(){
		super();
	}
}
	
private ArrayList<Integer> pos;
protected int x, y, z;

}
