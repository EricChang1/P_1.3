package algorithm;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import algorithm.Matrix.*;

public class BasicShape
{
	public static enum RelatPos {FRONT, BACK, LEFT, RIGHT, ABOVE, BELOW};
	
	@SuppressWarnings("serial")
	public static class BadNumberOfRowsException extends IllegalArgumentException
	{
		public BadNumberOfRowsException() {super(); }
		
		public BadNumberOfRowsException (String message) { super (message); }
	}
	
	@SuppressWarnings("serial")
	public static class BadNumberOfCollumsException extends IllegalArgumentException
	{
		public BadNumberOfCollumsException() {super(); }
		
		public BadNumberOfCollumsException (String message) { super (message); }
	}
	
	/**
	 * @param points list of points
	 * @param connected 2d list of points connected to points
	 * @return adjacency matrix containing connections in connected to points
	 */
	public static IntegerMatrix buildAdjacencyMatrix (ArrayList <IntegerMatrix> points, ArrayList <ArrayList <IntegerMatrix>> connected)
	{
		IntegerMatrix adj = new IntegerMatrix (points.size(), points.size());
		for (int cPoint = 0; cPoint < points.size(); ++cPoint)
		{
			for (IntegerMatrix connect : connected.get(cPoint))
			{
				int cFindConnect = 0;
				while (cFindConnect < points.size() && !points.get(cFindConnect).equals(connect))
					++cFindConnect;
				adj.setCell(cPoint, cFindConnect, 1);
				adj.setCell(cFindConnect, cPoint, 1);
			}
		}
		return adj;
	}
	
	/** Creates a rotation matrix based on given angles of rotation
	 * @param angle1 Desired amount of rotation in x2 axis (in degrees)
	 * @param angle2 Desired amount of rotation in x3 axis (in degrees)
	 * @return rotation matrix
	 */
	public static Matrix<Double> rotationMatrix(double angle1, double angle2){
		double radAngle1 = Math.toRadians (angle1);
		double radAngle2 = Math.toRadians (angle2);
		//rotation matrix for y axis
		Matrix.DoubleMatrix rotationMatrix1 = new Matrix.DoubleMatrix (3, 3);
		rotationMatrix1.setCell (0, 0, Math.cos (radAngle1));
		rotationMatrix1.setCell (2, 0, -Math.sin (radAngle1));
		rotationMatrix1.setCell (1, 1, 1.0);
		rotationMatrix1.setCell (0, 2, Math.sin (radAngle1));
		rotationMatrix1.setCell (2, 2, Math.cos (radAngle1));
		//rotation matrix for z axis
		Matrix.DoubleMatrix rotationMatrix2 = new Matrix.DoubleMatrix (3, 3);
		rotationMatrix2.setCell (0, 0, Math.cos (radAngle2));
		rotationMatrix2.setCell (1, 0, Math.sin (radAngle2));
		rotationMatrix2.setCell (0, 1, -Math.sin (radAngle2));
		rotationMatrix2.setCell (1, 1, Math.cos (radAngle2));
		rotationMatrix2.setCell (2, 2, 1.0);

		return rotationMatrix1.multiply (rotationMatrix2, new Matrix.DoubleMatrix (3, 3));
	}
	
	/**
	 * @param r relative position
	 * @param dimension dimension of the vector
	 * @return return vector to relative position in dimension
	 */
	public static IntegerMatrix getRelativePosVector (RelatPos r, int dimension)
	{
		IntegerMatrix v = new IntegerMatrix (dimension, 1);
		switch (r)
		{
		case ABOVE:	v.setCell(2, 0, 1);
			break;
		case BACK:	v.setCell(0, 0, -1);
			break;
		case BELOW:	v.setCell(2, 0, -1);
			break;
		case FRONT:	v.setCell(0, 0, 1);
			break;
		case LEFT:	v.setCell(1, 0, -1);
			break;
		case RIGHT:	v.setCell(1, 0, 1);
		}
		return v;
	}
	
	/**
	 * constructs basic shape from parameters
	 * @param vectors list of vectors
	 * @param adjMatrix adjacency matrix
	 */
	@SuppressWarnings("unchecked")
	public BasicShape(ArrayList <IntegerMatrix> vectors, IntegerMatrix adjMatrix){

		this.vectors = (ArrayList<IntegerMatrix>) vectors.clone();
		if (!numberOfCols(vectors)) 
			throw new BadNumberOfCollumsException ("The vectors introduced are not 3x1");
		dimensions = new ArrayList<Integer>();
		calcDim (vectors);
		mPossibleConnections = new ArrayList<ArrayList<RelatPos>>();
		for (int cVertex = 0; cVertex < getNumberOfVertices(); ++cVertex)
			mPossibleConnections.add (new ArrayList <RelatPos> (Arrays.asList(RelatPos.values())));
		this.adjMatrix = adjMatrix.clone();
		ArrayList<Integer> zeroPos = new ArrayList <Integer>();
		for (int cCoord = 0; cCoord < vectors.get(0).getRows(); ++cCoord)
			zeroPos.add(new Integer(0));
		mGlue = new Glue (zeroPos);
		for (int cVertex = 0; cVertex < getNumberOfVertices(); ++cVertex)
			resetConnections(cVertex);
	}
	
	/**
	 * construct basic shape by copying clone
	 * @param clone another basic shape
	 */
	public BasicShape (BasicShape clone)
	{
		this (clone.vectors, clone.adjMatrix);
		mGlue = clone.mGlue;
	}
	
	/**
	 * @return list of line objects, containing a line for each connection
	 */
	public ArrayList <Line> getConnectingLines()
	{
		ArrayList <Line> lines = new ArrayList<Line>();
		for (int cVertex = 0; cVertex < getNumberOfVertices(); ++cVertex)
		{
			
			for (int cConnect = cVertex; cConnect < getNumberOfVertices(); ++cConnect)
			{
				if (adjMatrix.getCell(cVertex, cConnect).equals(new Integer (1)))
					lines.add (new Line (new Glue (getVertex (cVertex)), new Glue (getVertex (cConnect))));
			}
				
		}
		return lines;
	}
	
	/**
	 * @return list of the sides of the basic shape
	 */
	public ArrayList <Rectangle> getRectangles()
	{
		ArrayList <Rectangle> rects = new ArrayList<Rectangle>();
		for (int cVertex = 0; cVertex < getNumberOfVertices(); ++cVertex)
		{
			for (int cOppoVertex = cVertex + 1; cOppoVertex < getNumberOfVertices(); ++cOppoVertex)
			{
				int cConn = cVertex + 1, sharedConn = 0;
				while (cConn < getNumberOfVertices() && sharedConn < 2)
				{
					if (adjMatrix.getCell(cVertex, cConn).equals(1) && 
						adjMatrix.getCell(cOppoVertex, cConn).equals(1))
						++sharedConn;
					++cConn;
				}
				if (sharedConn == 2)
					rects.add(new Rectangle (new Glue (getVertex (cVertex)), new Glue (getVertex (cOppoVertex))));
			}
		}
		return rects;
	}
	
	/**
	 * cuts the empty space into cuboids
	 * @return list of these cuboids
	 */
	public ArrayList <Cuboid> decomposeIntoCuboids()
	{
		BasicShape divided = new BasicShape (this);
		divided.addMissingRectanglePoints();
		
		ArrayList <Cuboid> cuboids = new ArrayList <Cuboid>();
		for (int cRow = 0; cRow < getNumberOfVertices(); ++cRow)
		{
			
			for (int cCol = cRow + 1; cCol < getNumberOfVertices(); ++cCol)
			{
				//if there is a new connection
				if (adjMatrix.getCell(cRow, cCol).equals(0) && 
					divided.adjMatrix.getCell(cRow, cCol).equals(1))
				{
					ArrayList <Integer> triangleBase = findTriangleIndices (cRow, cCol);
					for (int indTriangle : triangleBase)
					{
						ArrayList <Integer> triangleDiag = findTriangleIndices (indTriangle, cRow);
						for (int diag : triangleDiag)
							cuboids.add (new Cuboid (new Glue (getVertex (cRow)), new Glue (getVertex (diag))));
					}
				}
			}
		}
		return cuboids;
	}
	
	/**
	 * @param indDirect point to be directly connected
	 * @param indIndirect point to be indirectly connected
	 * @return set of all vertices directly connected to indDirect and indirectly connected to indIndirect
	 */
	public ArrayList <Integer> findTriangleIndices (int indDirect, int indIndirect)
	{
		ArrayList <Integer> tPoints = new ArrayList <Integer>();
		IntegerMatrix indirectAdjacency = getIndirectAdjacencyMatrix(indIndirect);
		for (int cCol = 0; cCol < getNumberOfVertices(); ++cCol)
		{
			if (adjMatrix.getCell(indDirect, cCol).equals(1) && 
				indirectAdjacency.getCell (indIndirect, cCol).equals(1))
				tPoints.add (cCol);
		}
		return tPoints;
	}
	
	/**
	 * @param l2 line to search for intersection
	 * @return list of intersection points
	 */
	public ArrayList <IntegerMatrix> getLineIntersections (Line l2)
	{
		ArrayList <IntegerMatrix> intersections = new ArrayList<Matrix.IntegerMatrix>();
		for (Line l1 : getConnectingLines())
		{
			IntersectionSolver solver = new IntersectionSolver (l1, l2);
			if (solver.getSolutionType() == IntersectionSolver.Result.ONE)
				intersections.add (solver.getIntersection().toVector());
		}
		return intersections;
	}
	
	/**
	 * @param place basic shape to place
	 * @param iVertex location to place place at
	 * @return list of positions where place may be placed adjacently to vertex at iVertex
	 */
	public ArrayList <Position> getRelativePlacements (BasicShape place, int iVertex)
	{
		ArrayList <Position> places = new ArrayList<Position>();
		for (RelatPos r : mPossibleConnections.get(iVertex))
		{
			IntegerMatrix pos = getVertex(iVertex);
			switch (r)
			{
			case BACK:	pos.setCell (0, 0, pos.getCell (0, 0) - place.getDimensions(0));
			break;
			case FRONT:	pos.setCell (0, 0, pos.getCell (0, 0) + place.getDimensions(0));
			break;
			case LEFT:	pos.setCell (1, 0, pos.getCell (1, 0) - place.getDimensions(1));
			break;
			case RIGHT:	pos.setCell (1, 0, pos.getCell (1, 0) + place.getDimensions(1));
			break;
			case BELOW:	pos.setCell (2, 0, pos.getCell (2, 0) - place.getDimensions(2));
			break;
			case ABOVE:	pos.setCell (2, 0, pos.getCell (2, 0) + place.getDimensions(2));
			}
			places.add (new Position(pos));
		}
		return places;
	}
	
	/**
	 * @param index index of point to look up connections for
	 * @return array list containing vectors to points connected to point at index each as a clone of original
	 */
	public ArrayList <IntegerMatrix> lookUpConnections(int index){

		ArrayList<IntegerMatrix> connections = new ArrayList<IntegerMatrix>();
		for(int counter=0; counter<adjMatrix.getRows(); counter++){
			if(adjMatrix.getCell(index,counter)!=0){
					connections.add (vectors.get(counter).clone());
			}
		}
		return connections;
	}
	
	/**
	 * @param index index of vertex to search for common connections
	 * @return square matrix containing a 1 for every other vertex that is a shared connection
	 */
	public IntegerMatrix getIndirectAdjacencyMatrix (int index)
	{
		IntegerMatrix indirectAdj = new IntegerMatrix (vectors.size(), vectors.size());
		for (int cRow = 0; cRow < indirectAdj.getRows(); ++cRow)
		{
			for (int cCol = 0; cCol < indirectAdj.getColumns(); ++cCol)
			{
				if (cRow != index && adjMatrix.getCell(cRow, cCol).equals(1) && 
					adjMatrix.getCell(index, cCol).equals(1))
					indirectAdj.setCell(cRow, cCol, 1);
			}
		}
		return indirectAdj;
	}
	
	/**
	 * @param index index of vertex
	 * @return vertex at index translated by glued offset
	 */
	public IntegerMatrix getVertex (int index)
	{
		return vectors.get(index).clone();
	}
	
	/**
	 * @param minPos the position at which this blocks upper left corner is sitting
	 * @return the maxPos, which is equivalent to the position of the bottom right corner
     */
	public Position getMaxDimension(Position minPos) 
	{
		ArrayList<Integer> maxPos = new ArrayList<Integer>();
		maxPos.add(getDimensions(0) + minPos.getPosition().get(0));
		maxPos.add(getDimensions(1) + minPos.getPosition().get(1));
		maxPos.add(getDimensions(2) + minPos.getPosition().get(2));
		Position max = new Position(maxPos);
		return max;
	}
	
	/**
	 * @return position where shape is glued at
	 */
	public Glue getGlue()
	{
		return mGlue;
	}
	
	/** calculates the maximum vector value
	* @param vector ArrayList containing all the vectors
	* @param index The index of the vector in the Matrix Handler
	* @return the maximum value.
	*/
	public int maximum(ArrayList <IntegerMatrix> vectors, int index){

		int max = Integer.MIN_VALUE;
    	for(Matrix<Integer> temp : vectors){
       		if(temp.getCell (index, 0) > max){
          		  max = temp.getCell (index, 0);
       		}
   		}
    	return max;

	}
	/** calculates the minimum vector value
	* @param vector ArrayList containing all the vectors
	* @param index The index of the vector in the Matrix Handler
	* @return the minimum value.
	*/
	public int minimum(ArrayList<IntegerMatrix> vectors, int index){

		int min = Integer.MAX_VALUE;
    	for(Matrix<Integer> temp: vectors){
       		if(temp.getCell (index, 0) < min){
          		  min= temp.getCell (index, 0);
       		}
   		}
    	return min;
	}
	
	/** compares that all the Matrix Handlers have the same number of rows
	* @param vectors ArrayList containing all the vectors
	* @return false if one Matrix Handler doesn't have the same number of rows
	*/
	public boolean numberOfMH(ArrayList<IntegerMatrix> vectors){

		int numberOfRows=vectors.get(0).getRows();
		for(Matrix<Integer> temp: vectors){
			if(temp.getRows() != numberOfRows)
				return false;
		}
		return true;
	}
	
	/**
	 * @param vectors set of vectors
	 * @return true if all vectors have the same number of rows
	 */
	public boolean numberOfCols(ArrayList<IntegerMatrix> vectors){

		int numberOfCols = 0;
		for(Matrix<Integer> temp: vectors){
			if (numberOfCols == 0)
				numberOfCols = temp.getColumns();
			else if(temp.getColumns() != numberOfCols)
				return false;
		}
		return true;
	}
	
	/** @return the dimensions of a shape given an index.
	 */
	public int getDimensions(int index){

		return dimensions.get(index);
	}
	
	/**
	 * @return number of vertices defining the shape
	 */
	public int getNumberOfVertices()
	{
		return vectors.size();
	}
	
	/**
	 * @param vertex vertex to search index for
	 * @return index of vertex or vectors.size() if vertex was not found
	 */
	public int getVertexIndex (IntegerMatrix vertex)
	{
		for (int cVertex = 0; cVertex < vectors.size(); ++cVertex)
		{
			if (vectors.get(cVertex).equals(vertex))
				return cVertex;
		}
		return vectors.size();
	}
	
	/** Calculates the dimensions of a shape
	** @param vectors ArrayList containing all the vectors
	*/
	public void calcDim(ArrayList<IntegerMatrix> vectors) throws BadNumberOfRowsException{
		
		if (!numberOfMH(vectors)) 
			throw new BadNumberOfRowsException ("vectors don't have the same dimension");

		for(int i=0; i<vectors.get(0).getRows(); i++){

			int max = maximum (vectors,i);
			int min = minimum (vectors,i);
			dimensions.add(max-min);

		}
	}
	
	public void addMissingRectanglePoints()
	{
		//contain: new points, connections of these
		ArrayList <IntegerMatrix> newPoints = new ArrayList<Matrix.IntegerMatrix>();
		ArrayList <ArrayList<IntegerMatrix>> newPointsConnections = new ArrayList<ArrayList<IntegerMatrix>>();
		//iterate through vertices
		for (int cVert = 0; cVert < getNumberOfVertices(); ++cVert)
		{
			//iterate through free connections
			ArrayList<RelatPos> freeConn = mPossibleConnections.get(cVert);
			for (RelatPos free : freeConn)
			{
				//compute line through current vertex and current free connection vector
				IntegerMatrix vertex = getVertex(cVert);
				IntegerMatrix pDirect = getRelativePosVector(free, vertex.getRows());
				boolean beyondDimension = false;
				for (int cDim = 0; cDim < pDirect.getRows(); ++cDim)
				{
					pDirect.setCell(cDim, 0, pDirect.getCell (cDim, 0) + vertex.getCell (cDim, 0));
					if (pDirect.getCell(cDim, 0) < 0 || pDirect.getCell(cDim, 0) > getDimensions (cDim))
						beyondDimension = true;
				}
				if (!beyondDimension)
				{
					Line intersect = new Line (new Glue (vertex), new Glue (pDirect));
					ArrayList <Line> connections = getConnectingLines();
					//search for intersection with existing lines for positive scalar of line vector
					for (Line connect : connections)
					{
						IntersectionSolver solver = new IntersectionSolver (intersect, connect);
						if (solver.getSolutionType() == IntersectionSolver.Result.ONE &&
							solver.getScalars().get(0) > 0)
						{
							//store intersection point & related connections
							IntegerMatrix inter = solver.getIntersection().toVector();
							newPoints.add (inter);
							newPoints.add (vertex);
							newPoints.add (connect.getFirst());
							newPoints.add (connect.getSecond());
							ArrayList<IntegerMatrix> newConnections = new ArrayList<Matrix.IntegerMatrix>();
							newConnections.add (vertex);
							newConnections.add (connect.getFirst());
							newConnections.add (connect.getSecond());
							newPointsConnections.add(newConnections);
							//add empty list for connected points
							for (int cnt = 0; cnt < 3; ++cnt)
								newPointsConnections.add(new ArrayList <IntegerMatrix>());
						}
					}
				}
			}
		}
		IntegerMatrix adjMatMissing = buildAdjacencyMatrix(newPoints, newPointsConnections);
		addVertices (newPoints, adjMatMissing);
	}
	
	/** Performs actual rotation
	 * @param rotMatrix created from rotationMatrix()
	 * @return matrix after rotation
	 */
	public void rotate (Matrix<Double> rotMatrix){

		for(int cCounter=0; cCounter<vectors.size();cCounter++){
			Matrix.DoubleMatrix result = new Matrix.DoubleMatrix (3,1);
			Matrix.DoubleMatrix vec = vectors.get(cCounter).toDoubleMatrix();
			rotMatrix.multiply (vec, result);
			vectors.set (cCounter, result.toIntegerMatrix());
		}
	}
	
	/**
	 * Glues shape to g and translates all vertices
	 * @param g position
	 */
	public void glue (Glue g)
	{
		for (int cVertex = 0; cVertex < getNumberOfVertices(); ++cVertex)
			vectors.set(cVertex, g.translateMat(vectors.get(cVertex), mGlue));
		mGlue = g.clone();
	}
	
	public void print(PrintStream p)
	{
		p.println ("Printing vertices of basic shape");
		for (int cVec = 0; cVec < getNumberOfVertices(); ++cVec)
		{
			p.println ("vector " + cVec + " ");
			vectors.get(cVec).print(System.out);
			p.print("connections: ");
			for (int cConnect = 0; cConnect < getNumberOfVertices(); ++cConnect)
			{
				if (adjMatrix.getCell (cVec, cConnect).equals(1))
					p.print (cConnect + ", ");
			}
		}
	}
	
	/**
	 * Expands this shape by adding vectors of bs and connecting bs' vertices with existing ones
	 * @param bs shape to add to this shape
	 */
	protected void addShape (Block b)
	{
		BasicShape bs = (BasicShape)b;
		addVertices (bs.vectors, bs.adjMatrix);
	}
	
	/**
	 * adds missing points in newVertices to list of vectors in their exact order
	 * adds elements to mPossibleConnections
	 * fills in connections in adjacent
	 * @param newVertices vertices to add
	 * @param adjacent adjacency matrix containing connections of vertices to add
	 */
	private void addVertices (ArrayList <IntegerMatrix> newVertices, IntegerMatrix adjacent)
	{
		//stores indices in list in this object of every element in newVertices
		ArrayList <Integer> addedIndices = new ArrayList<Integer>();
		//add vertices not yet contained to the end
		for (int cNewVertex = 0; cNewVertex < newVertices.size(); ++cNewVertex)
		{
			int cVertex = getVertexIndex (newVertices.get(cNewVertex));
			if (cVertex == vectors.size())
			{
				vectors.add (newVertices.get(cNewVertex));
				mPossibleConnections.add (new ArrayList <RelatPos> (Arrays.asList (RelatPos.values())));
			}
			addedIndices.add (cVertex);
		}
		
		//copy adjacency matrix into larger one if necessary
		if (vectors.size() > adjMatrix.getRows())
		{
			IntegerMatrix oldAdjMat = adjMatrix;
			adjMatrix = new IntegerMatrix (vectors.size(), vectors.size());
			adjMatrix.copyValues(oldAdjMat, 0, 0, 0, 0, oldAdjMat.getRows(), oldAdjMat.getColumns());
		}
		
		//fill in connections in adjacent
		for (int cNewVertex = 0; cNewVertex < newVertices.size(); ++cNewVertex)
		{
			int iVertex = addedIndices.get(cNewVertex);
			for (int cAdj = 0; cAdj < adjacent.getColumns(); ++cAdj)
			{
				if (adjacent.getCell (cNewVertex, cAdj).equals(1))
				{
					int iAdj = getVertexIndex (newVertices.get(cAdj));
					adjMatrix.setCell (iVertex, iAdj, 1);
					adjMatrix.setCell (iAdj, iVertex, 1);
				}
			}
		}
		
		//compute remaining connections
		for (int addedIndex : addedIndices)
			resetConnections (addedIndex);
	}
	
	/**
	 * Recalculates list of available connections for vertex at index iVertex
	 * @param iVertex
	 */
	private void resetConnections (int iVertex)
	{
		ArrayList <IntegerMatrix> connections = lookUpConnections(iVertex);
		IntegerMatrix vertex = getVertex(iVertex);
		ArrayList <RelatPos> remain = mPossibleConnections.get(iVertex);
		for (IntegerMatrix conn : connections)
		{
			if (conn.getCell(0, 0) < vertex.getCell(0, 0))
				remain.remove(RelatPos.BACK);
			else if (conn.getCell(0, 0) > vertex.getCell(0, 0))
				remain.remove(RelatPos.FRONT);
			if (conn.getCell(1, 0) < vertex.getCell (1, 0))
				remain.remove(RelatPos.LEFT);
			else if (conn.getCell(1, 0) > vertex.getCell(1, 0))
				remain.remove (RelatPos.RIGHT);
			if (conn.getCell(2, 0) < vertex.getCell(2, 0))
				remain.remove (RelatPos.BELOW);
			else if (conn.getCell (2, 0) > vertex.getCell(2, 0))
				remain.remove(RelatPos.ABOVE);
		}
	}
	
	private ArrayList<IntegerMatrix> vectors;
	private ArrayList<Integer> dimensions;
	private ArrayList <ArrayList <RelatPos>> mPossibleConnections;
	private IntegerMatrix adjMatrix;
	private Glue mGlue;
}