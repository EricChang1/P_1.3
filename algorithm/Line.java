import java.util.ArrayList;

public class Line {

    /**
	 * @param pos2 Position
	 * Constructs a default line between 0,0,0 and pos2
	 */
	public Line(ArrayList<Integer> pos2) {
		this.pos1 = new Position();
		this.pos2 = new Position(pos2);
        line = createLine(pos1, pos2);
	}
	
	/**
	 * @param pos1 First position
	 * @param pos2 Second position
	 * Constructs a line between pos1 and pos2
	 */
	public Line(ArrayList<Integer> pos1, ArrayList<Integer> pos2) {
		this.pos1 = new Position(pos1);
		this.pos2 = new Position(pos2);
        line = createLine(pos1, pos2);
	}

    /**
     * Constructs a line between two matrix objects
     * @param m1 Matrix1
     * @param m2 Matrix2
     */
    public Line(Matrix m1, Matrix m2){
        if ((m1.getColumns() == 1) && (m2.getColumns() == 1)) {
            if ((m1.getRows() == 3) && (m2.getRows() ==3)) {
                ArrayList<Integer> matrix1 = new ArrayList<Integer>();
                ArrayList<Integer> matrix2 = new ArrayList<Integer>();
                for (int i=0; i<m1.getRows(); i++) {
                    Int[] m1 = m1.getRow(i);
                    Int[] m2 = m2.getRow(i);
                    matrix1.add(m1[0]);
                    matrix2.add(m2[0]);
                }
                new Line(matrix1, matrix2);
            }
            else System.out.println("Matrices are not equal of size, therefore no Line can be constructed.");
        }
        else System.out.println("Matrices are not equal of size, therefore no Line can be constructed.");
    }

    public ArrayList<Double> createLine(Position pos1, Position pos2) {
        int i = 0;
        int x = pos2.get(++i) - pos1.get(i);
        int y = pos2.get(++i) - pos1.get(i);
        int z = pos2.get(++i) - pos1.get(i);
        double length = Math.sqr((x*x)+(y*y)+(z*z));
        ArrayList<Double> scaleDownLine = ArrayList<Double>();

        if (length != 1) {
            double scaleDownX = Math.sqr((x/length)*(x/length));
            double scaleDownY = Math.sqr((y/length)*(y/length));
            double scaleDownZ = Math.sqr((z/length)*(z/length));
            scaleDownLine.add(scaleDownX);
            scaleDownLine.add(scaleDownY);
            scaleDownLine.add(scaleDownZ);
            scaleDownLine.add(length);
        }
        else {
            scaleDownLine.add(x);
            scaleDownLine.add(y);
            scaleDownLine.add(z);
            scaleDownLine.add(1);
        }

        return scaleDownLine;
    }


    public boolean doIntersect(ArrayList<Double> line1, ArrayList<Double> line2) {
        double x1 = line1.get(0);
        double y1 = line1.get(1);
        double z1 = line1.get(2);
        double scale1 = line1.get(3);
        double x2 = line2.get(0);
        double y2 = line2.get(1);
        double z2 = line2.get(2);
        double scale2 = line2.get(3);





    }

	private Position pos1;
	private Position pos2;

    // Stores x, y, z, scaling factor in this order
    private ArrayList<Double> line;
}