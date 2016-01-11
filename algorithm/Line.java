import java.util.ArrayList;

public class Line {

    /**
	 * @param pos2 Position
	 * Constructs a default line between 0,0,0 and pos2
	 */
	public Line(ArrayList<Integer> pos2) {
		this.pos1 = new Position();
		this.pos2 = new Position(pos2);
        this.line = createLine(pos1, pos2);
	}
	
	/**
	 * @param pos1 First position
	 * @param pos2 Second position
	 * Constructs a line between pos1 and pos2
	 */
	public Line(ArrayList<Integer> pos1, ArrayList<Integer> pos2) {
		this.pos1 = new Position(pos1);
		this.pos2 = new Position(pos2);
        this.line = createLine(pos1, pos2);
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

    /**
     *
     * @param pos1 Position of point1
     * @param pos2 Position of point2
     * @return ArrayList<Double> containing The scaled down (length=1) version of the line (x,y,z) with the scaling factor as fourth element
     */
    public ArrayList<Double> createLine(Position pos1, Position pos2) {
        int i = 0;
        // !! Not sure if pos.get(0) or pos.get(1) returns the first index !!
        int x2 = pos2.get(++i);
        int x1 = pos1.get(i);
        int y2 = pos2.get(++i);
        int y1 = pos1.get(i);
        int z2 = pos2.get(++i);
        int z1 = pos1.get(i);

        int x = x2 - x1;
        int y = y2 - y1;
        int z = z2 - z1;

        double length = Math.sqr((x*x)+(y*y)+(z*z));
        ArrayList<Double> scaleDownLine = ArrayList<Double>();

        if (length != 1) {
            double scaleDownX = (x/length);
            double scaleDownY = (y/length);
            double scaleDownZ = (z/length);
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

        scaleDownLine.add(x1);
        scaleDownLine.add(y1);
        scaleDownLine.add(z1);
        scaleDownLine.add(x2);
        scaleDownLine.add(y2);
        scaleDownLine.add(z2);


        return scaleDownLine;
    }

    /**
     * @param line1 ArrayList<Double> containing all relevant information about line1 in the correct order (10 elements)
     * @param line2 ArrayList<Double> containing all relevant information about line2 in the correct order (10 elements)
     * @return true: Lines do intersect -- false: they don't
     */
    public boolean doIntersect(ArrayList<Double> line1, ArrayList<Double> line2) {
        // Line 1
        double x1 = line1.get(0);
        double y1 = line1.get(1);
        double z1 = line1.get(2);
        double scale1 = line1.get(3);
        int p1x = line1.get(5);
        int p1y = line1.get(6);
        int p1z = line1.get(7);
        int p2x = line1.get(8);
        int p2y = line1.get(9);
        int p2z = line1.get(10);

        // Line 2
        double x2 = line2.get(0);
        double y2 = line2.get(1);
        double z2 = line2.get(2);
        double scale2 = line2.get(3);
        int q1x = line2.get(5);
        int q1y = line2.get(6);
        int q1z = line2.get(7);
        int q2x = line2.get(8);
        int q2y = line2.get(9);
        int q2z = line2.get(10);

        // Check
        double r1 = ( (q1x + x2 - p1x) / (x1) );
        double r2 = ( (q1y + y2 - p1y) / (y1) );
        double r3 = ( (q1z + z2 - p1z) / (z1) );

        double s1 = ( (p1x + x1 - q1x) / (x2) );
        double s2 = ( (p1y + y1 - q1y) / (y2) );
        double s3 = ( (p1z + z1 - q1z) / (z2) );


        if ((r1 == s1) && (r2 == s2) && (r3 == s3)) return true;
        else return false;
    }

	private Position pos1;
	private Position pos2;

    // Stores x, y, z, scaling factor, P1 (x,y,z), P2 (x,y,z) --> 10 elements in this order
    private ArrayList<Double> line;
}