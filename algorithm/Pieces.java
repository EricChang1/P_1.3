package algorithm;

import algorithm.Matrix.*;
import java.util.*;

public class Pieces {
	
	
	public Block createTBlock()
	{
		ArrayList<IntegerMatrix> T = createTPent();
		ArrayList<ArrayList<IntegerMatrix>> cT = getConnectedT();
		IntegerMatrix iT = BasicShape.buildAdjacencyMatrix(T, cT);
		Block blockT = new Block(T, iT, 5);
		
		return blockT;
		
	}
	
	public Block createPBlock()
	{
		ArrayList<IntegerMatrix> P = createPPent();
		ArrayList<ArrayList<IntegerMatrix>> cP = getConnectedP();
		IntegerMatrix iP = BasicShape.buildAdjacencyMatrix(P, cP);
		Block blockP = new Block(P, iP, 5);
		
		return blockP;
	}
	
	public Block createLBlock()
	{
		ArrayList<IntegerMatrix> L = createLPent();
		ArrayList<ArrayList<IntegerMatrix>> cL = getConnectedL();
		IntegerMatrix iL = BasicShape.buildAdjacencyMatrix(L, cL);
		Block blockL = new Block(L, iL, 5);
		
		return blockL;
	}
	
	public ArrayList<IntegerMatrix> createLPent()
	{
		ArrayList<IntegerMatrix> pentL = new ArrayList<IntegerMatrix>();
		IntegerMatrix vectorL1 = new IntegerMatrix(3,1);
		vectorL1.setCell(0, 0, 0);
		vectorL1.setCell(1, 0, 0);
		vectorL1.setCell(2, 0, 0);
		pentL.add(vectorL1);
		
		IntegerMatrix vectorL2 = new IntegerMatrix(3,1);
		vectorL2.setCell(0, 0, 2);
		vectorL2.setCell(1, 0, 0);
		vectorL2.setCell(2, 0, 0);
		pentL.add(vectorL2);
		
		IntegerMatrix vectorL3 = new IntegerMatrix(3,1);
		vectorL3.setCell(0, 0, 2);
		vectorL3.setCell(1, 0, 0);
		vectorL3.setCell(2, 0, 1);
		pentL.add(vectorL3);
		
		IntegerMatrix vectorL4 = new IntegerMatrix(3,1);
		vectorL4.setCell(0, 0, 2);
		vectorL4.setCell(1, 0, 1);
		vectorL4.setCell(2, 0, 1);
		pentL.add(vectorL4);
		
		IntegerMatrix vectorL5 = new IntegerMatrix(3,1);
		vectorL5.setCell(0, 0, 2);
		vectorL5.setCell(1, 0, 1);
		vectorL5.setCell(2, 0, 0);
		pentL.add(vectorL5);
		
		IntegerMatrix vectorL6 = new IntegerMatrix(3,1);
		vectorL6.setCell(0, 0, 1);
		vectorL6.setCell(1, 0, 1);
		vectorL6.setCell(2, 0, 0);
		pentL.add(vectorL6);
		
		IntegerMatrix vectorL7 = new IntegerMatrix(3,1);
		vectorL7.setCell(0, 0, 1);
		vectorL7.setCell(1, 0, 1);
		vectorL7.setCell(2, 0, 1);
		pentL.add(vectorL7);
		
		IntegerMatrix vectorL8 = new IntegerMatrix(3,1);
		vectorL8.setCell(0, 0, 1);
		vectorL8.setCell(1, 0, 4);
		vectorL8.setCell(2, 0, 0);
		pentL.add(vectorL8);
		
		IntegerMatrix vectorL9 = new IntegerMatrix(3,1);
		vectorL9.setCell(0, 0, 1);
		vectorL9.setCell(1, 0, 4);
		vectorL9.setCell(2, 0, 1);
		pentL.add(vectorL9);
		
		IntegerMatrix vectorL10 = new IntegerMatrix(3,1);
		vectorL10.setCell(0, 0, 0);
		vectorL10.setCell(1, 0, 4);
		vectorL10.setCell(2, 0, 1);
		pentL.add(vectorL10);
		
		IntegerMatrix vectorL11 = new IntegerMatrix(3,1);
		vectorL11.setCell(0, 0, 0);
		vectorL11.setCell(1, 0, 4);
		vectorL11.setCell(2, 0, 0);
		pentL.add(vectorL11);
		
		IntegerMatrix vectorL12 = new IntegerMatrix(3,1);
		vectorL12.setCell(0, 0, 0);
		vectorL12.setCell(1, 0, 0);
		vectorL12.setCell(2, 0, 1);
		pentL.add(vectorL12);
		return pentL;
	}
	public ArrayList<ArrayList<IntegerMatrix>> getConnectedL()
	{
		IntegerMatrix L1 = new IntegerMatrix(3,1);
		L1.setCell(0, 0, 0);
		L1.setCell(1, 0, 0);
		L1.setCell(2, 0, 0);
		IntegerMatrix L2 = new IntegerMatrix(3,1);
		L2.setCell(0, 0, 2);
		L2.setCell(1, 0, 0);
		L2.setCell(2, 0, 0);
		IntegerMatrix L3 = new IntegerMatrix(3,1);
		L3.setCell(0, 0, 2);
		L3.setCell(1, 0, 0);
		L3.setCell(2, 0, 1);
		IntegerMatrix L4 = new IntegerMatrix(3,1);
		L4.setCell(0, 0, 2);
		L4.setCell(1, 0, 1);
		L4.setCell(2, 0, 1);
		IntegerMatrix L5 = new IntegerMatrix(3,1);
		L5.setCell(0, 0, 2);
		L5.setCell(1, 0, 1);
		L5.setCell(2, 0, 0);
		IntegerMatrix L6 = new IntegerMatrix(3,1);
		L6.setCell(0, 0, 1);
		L6.setCell(1, 0, 1);
		L6.setCell(2, 0, 0);
		IntegerMatrix L7 = new IntegerMatrix(3,1);
		L7.setCell(0, 0, 1);
		L7.setCell(1, 0, 1);
		L7.setCell(2, 0, 1);
		IntegerMatrix L8 = new IntegerMatrix(3,1);
		L8.setCell(0, 0, 1);
		L8.setCell(1, 0, 4);
		L8.setCell(2, 0, 0);
		IntegerMatrix L9 = new IntegerMatrix(3,1);
		L9.setCell(0, 0, 1);
		L9.setCell(1, 0, 4);
		L9.setCell(2, 0, 1);
		IntegerMatrix L10 = new IntegerMatrix(3,1);
		L10.setCell(0, 0, 0);
		L10.setCell(1, 0, 4);
		L10.setCell(2, 0, 1);
		IntegerMatrix L11 = new IntegerMatrix(3,1);
		L11.setCell(0, 0, 0);
		L11.setCell(1, 0, 4);
		L11.setCell(2, 0, 0);
		IntegerMatrix L12 = new IntegerMatrix(3,1);
		L12.setCell(0, 0, 0);
		L12.setCell(1, 0, 0);
		L12.setCell(2, 0, 1);

		ArrayList<ArrayList<IntegerMatrix>> connectLPent = new ArrayList<ArrayList<IntegerMatrix>>();
		ArrayList<IntegerMatrix> connectL1 = new ArrayList<IntegerMatrix>();
		connectL1.add(L2);connectL1.add(L11);connectL1.add(L12);
		
		ArrayList<IntegerMatrix> connectL2 = new ArrayList<IntegerMatrix>();
		connectL2.add(L1);connectL1.add(L3);connectL1.add(L5);
		
		ArrayList<IntegerMatrix> connectL3 = new ArrayList<IntegerMatrix>();
		connectL3.add(L2);connectL1.add(L4);connectL1.add(L12);
		
		ArrayList<IntegerMatrix> connectL4 = new ArrayList<IntegerMatrix>();
		connectL4.add(L3);connectL1.add(L5);connectL1.add(L7);
		
		ArrayList<IntegerMatrix> connectL5 = new ArrayList<IntegerMatrix>();
		connectL5.add(L2);connectL1.add(L4);connectL1.add(L6);
		
		ArrayList<IntegerMatrix> connectL6 = new ArrayList<IntegerMatrix>();
		connectL6.add(L5);connectL1.add(L7);connectL1.add(L8);
		
		ArrayList<IntegerMatrix> connectL7 = new ArrayList<IntegerMatrix>();
		connectL7.add(L4);connectL1.add(L6);connectL1.add(L9);
		
		ArrayList<IntegerMatrix> connectL8 = new ArrayList<IntegerMatrix>();
		connectL8.add(L6);connectL1.add(L9);connectL1.add(L11);
		
		ArrayList<IntegerMatrix> connectL9 = new ArrayList<IntegerMatrix>();
		connectL9.add(L7);connectL1.add(L8);connectL1.add(L10);
		
		ArrayList<IntegerMatrix> connectL10 = new ArrayList<IntegerMatrix>();
		connectL10.add(L9);connectL1.add(L11);connectL1.add(L12);
		
		ArrayList<IntegerMatrix> connectL11 = new ArrayList<IntegerMatrix>();
		connectL11.add(L1);connectL1.add(L8);connectL1.add(L10);
		
		ArrayList<IntegerMatrix> connectL12 = new ArrayList<IntegerMatrix>();
		connectL12.add(L3);connectL1.add(L4);connectL1.add(L10);
		
		connectLPent.add(connectL1);connectLPent.add(connectL2);connectLPent.add(connectL3);connectLPent.add(connectL4);connectLPent.add(connectL5);connectLPent.add(connectL6);
		connectLPent.add(connectL7);connectLPent.add(connectL8);connectLPent.add(connectL9);connectLPent.add(connectL10);connectLPent.add(connectL11);connectLPent.add(connectL12);
		
		return connectLPent;
	}

	
	public ArrayList<IntegerMatrix> createPPent()
	{
		ArrayList<IntegerMatrix>  pentP = new ArrayList<IntegerMatrix> ();
		IntegerMatrix vectorP1 = new IntegerMatrix(3,1);
		vectorP1.setCell(0, 0, 0);
		vectorP1.setCell(1, 0, 0);
		vectorP1.setCell(2, 0, 0);
		pentP.add(vectorP1);
		IntegerMatrix vectorP2 = new IntegerMatrix(3,1);
		vectorP2.setCell(0, 0, 1);
		vectorP2.setCell(1, 0, 0);
		vectorP2.setCell(2, 0, 0);
		pentP.add(vectorP2);
		IntegerMatrix vectorP3 = new IntegerMatrix(3,1);
		vectorP3.setCell(0, 0, 1);
		vectorP3.setCell(1, 0, 1);
		vectorP3.setCell(2, 0, 0);
		pentP.add(vectorP3);
		IntegerMatrix vectorP4 = new IntegerMatrix(3,1);
		vectorP4.setCell(0, 0, 2);
		vectorP4.setCell(1, 0, 1);
		vectorP4.setCell(2, 0, 0);
		pentP.add(vectorP4);
		IntegerMatrix vectorP5 = new IntegerMatrix(3,1);
		vectorP5.setCell(0, 0, 2);
		vectorP5.setCell(1, 0, 3);
		vectorP5.setCell(2, 0, 0);
		pentP.add(vectorP5);
		IntegerMatrix vectorP6 = new IntegerMatrix(3,1);
		vectorP6.setCell(0, 0, 0);
		vectorP6.setCell(1, 0, 3);
		vectorP6.setCell(2, 0, 0);
		pentP.add(vectorP6);
		IntegerMatrix vectorP7 = new IntegerMatrix(3,1);
		vectorP7.setCell(0, 0, 0);
		vectorP7.setCell(1, 0, 0);
		vectorP7.setCell(2, 0, 1);
		pentP.add(vectorP7);
		IntegerMatrix vectorP8 = new IntegerMatrix(3,1);
		vectorP8.setCell(0, 0, 1);
		vectorP8.setCell(1, 0, 0);
		vectorP8.setCell(2, 0, 1);
		pentP.add(vectorP8);
		IntegerMatrix vectorP9 = new IntegerMatrix(3,1);
		vectorP9.setCell(0, 0, 1);
		vectorP9.setCell(1, 0, 1);
		vectorP9.setCell(2, 0, 1);
		pentP.add(vectorP9);
		IntegerMatrix vectorP10 = new IntegerMatrix(3,1);
		vectorP10.setCell(0, 0, 2);
		vectorP10.setCell(1, 0, 1);
		vectorP10.setCell(2, 0, 1);
		pentP.add(vectorP10);
		IntegerMatrix vectorP11 = new IntegerMatrix(3,1);
		vectorP11.setCell(0, 0, 2);
		vectorP11.setCell(1, 0, 3);
		vectorP11.setCell(2, 0, 1);
		pentP.add(vectorP11);
		IntegerMatrix vectorP12 = new IntegerMatrix(3,1);
		vectorP12.setCell(0, 0, 0);
		vectorP12.setCell(1, 0, 3);
		vectorP12.setCell(2, 0, 1);
		pentP.add(vectorP12);
		return pentP;
	}
	public ArrayList<ArrayList<IntegerMatrix>> getConnectedP()
	{
		IntegerMatrix P1 = new IntegerMatrix(3,1);
		P1.setCell(0, 0, 0);
		P1.setCell(1, 0, 0);
		P1.setCell(2, 0, 0);

		IntegerMatrix P2 = new IntegerMatrix(3,1);
		P2.setCell(0, 0, 1);
		P2.setCell(1, 0, 0);
		P2.setCell(2, 0, 0);

		IntegerMatrix P3 = new IntegerMatrix(3,1);
		P3.setCell(0, 0, 1);
		P3.setCell(1, 0, 1);
		P3.setCell(2, 0, 0);

		IntegerMatrix P4 = new IntegerMatrix(3,1);
		P4.setCell(0, 0, 2);
		P4.setCell(1, 0, 1);
		P4.setCell(2, 0, 0);

		IntegerMatrix P5 = new IntegerMatrix(3,1);
		P5.setCell(0, 0, 2);
		P5.setCell(1, 0, 3);
		P5.setCell(2, 0, 0);

		IntegerMatrix P6 = new IntegerMatrix(3,1);
		P6.setCell(0, 0, 0);
		P6.setCell(1, 0, 3);
		P6.setCell(2, 0, 0);

		IntegerMatrix P7 = new IntegerMatrix(3,1);
		P7.setCell(0, 0, 0);
		P7.setCell(1, 0, 0);
		P7.setCell(2, 0, 1);

		IntegerMatrix P8 = new IntegerMatrix(3,1);
		P8.setCell(0, 0, 1);
		P8.setCell(1, 0, 0);
		P8.setCell(2, 0, 1);

		IntegerMatrix P9 = new IntegerMatrix(3,1);
		P9.setCell(0, 0, 1);
		P9.setCell(1, 0, 1);
		P9.setCell(2, 0, 1);
	
		IntegerMatrix P10 = new IntegerMatrix(3,1);
		P10.setCell(0, 0, 2);
		P10.setCell(1, 0, 1);
		P10.setCell(2, 0, 1);
	
		IntegerMatrix P11 = new IntegerMatrix(3,1);
		P11.setCell(0, 0, 2);
		P11.setCell(1, 0, 3);
		P11.setCell(2, 0, 1);
	
		IntegerMatrix P12 = new IntegerMatrix(3,1);
		P12.setCell(0, 0, 0);
		P12.setCell(1, 0, 3);
		P12.setCell(2, 0, 1);
		
		ArrayList<IntegerMatrix> connectP1 = new ArrayList<IntegerMatrix>();
		connectP1.add(P2);connectP1.add(P6);connectP1.add(P7);
		
		ArrayList<IntegerMatrix> connectP2 = new ArrayList<IntegerMatrix>();
		connectP2.add(P1);connectP2.add(P3);connectP2.add(P8);
		
		ArrayList<IntegerMatrix> connectP3 = new ArrayList<IntegerMatrix>();
		connectP3.add(P2);connectP3.add(P4);connectP3.add(P9);
		
		ArrayList<IntegerMatrix> connectP4 = new ArrayList<IntegerMatrix>();
		connectP4.add(P3);connectP4.add(P5);connectP4.add(P10);
		
		ArrayList<IntegerMatrix> connectP5 = new ArrayList<IntegerMatrix>();
		connectP5.add(P4);connectP5.add(P6);connectP5.add(P11);
		
		ArrayList<IntegerMatrix> connectP6 = new ArrayList<IntegerMatrix>();
		connectP6.add(P1);connectP6.add(P5);connectP6.add(P12);
		
		ArrayList<IntegerMatrix> connectP7 = new ArrayList<IntegerMatrix>();
		connectP7.add(P1);connectP7.add(P8);connectP7.add(P12);
		
		ArrayList<IntegerMatrix> connectP8 = new ArrayList<IntegerMatrix>();
		connectP8.add(P2);connectP8.add(P7);connectP8.add(P9);
		
		ArrayList<IntegerMatrix> connectP9 = new ArrayList<IntegerMatrix>();
		connectP9.add(P3);connectP9.add(P8);connectP9.add(P10);
		
		ArrayList<IntegerMatrix> connectP10 = new ArrayList<IntegerMatrix>();
		connectP10.add(P4);connectP10.add(P9);connectP10.add(P11);
		
		ArrayList<IntegerMatrix> connectP11 = new ArrayList<IntegerMatrix>();
		connectP11.add(P5);connectP11.add(P10);connectP11.add(P12);
		
		ArrayList<IntegerMatrix> connectP12 = new ArrayList<IntegerMatrix>();
		connectP12.add(P6);connectP12.add(P7);connectP12.add(P11);
		
		ArrayList<ArrayList<IntegerMatrix>> connectPPent = new ArrayList<ArrayList<IntegerMatrix>>();
		connectPPent.add(connectP1);connectPPent.add(connectP2);connectPPent.add(connectP3);connectPPent.add(connectP4);connectPPent.add(connectP5);connectPPent.add(connectP6);
		connectPPent.add(connectP7);connectPPent.add(connectP8);connectPPent.add(connectP9);connectPPent.add(connectP10);connectPPent.add(connectP11);connectPPent.add(connectP12);
		
		return connectPPent;
	}
	
	public ArrayList<IntegerMatrix> createTPent()
	{
		ArrayList <IntegerMatrix> pentT = new ArrayList<IntegerMatrix>();
		IntegerMatrix vectorT1 = new IntegerMatrix(3,1);
		vectorT1.setCell(0, 0, 1);
		vectorT1.setCell(1, 0, 0);
		vectorT1.setCell(2, 0, 0);
		pentT.add(vectorT1);
		
		IntegerMatrix vectorT2 = new IntegerMatrix(3,1);
		vectorT2.setCell(0, 0, 2);
		vectorT2.setCell(1, 0, 0);
		vectorT2.setCell(2, 0, 0);
		pentT.add(vectorT2);
		
		IntegerMatrix vectorT3 = new IntegerMatrix(3,1);
		vectorT3.setCell(0, 0, 3);
		vectorT3.setCell(1, 0, 2);
		vectorT3.setCell(2, 0, 0);
		pentT.add(vectorT3);
		
		IntegerMatrix vectorT4 = new IntegerMatrix(3,1);
		vectorT4.setCell(0, 0, 3);
		vectorT4.setCell(1, 0, 3);
		vectorT4.setCell(2, 0, 0);
		pentT.add(vectorT4);
		
		IntegerMatrix vectorT5 = new IntegerMatrix(3,1);
		vectorT5.setCell(0, 0, 0);
		vectorT5.setCell(1, 0, 3);
		vectorT5.setCell(2, 0, 0);
		pentT.add(vectorT5);
		
		IntegerMatrix vectorT6 = new IntegerMatrix(3,1);
		vectorT6.setCell(0, 0, 0);
		vectorT6.setCell(1, 0, 2);
		vectorT6.setCell(2, 0, 0);
		pentT.add(vectorT6);
		
		IntegerMatrix vectorT7 = new IntegerMatrix(3,1);
		vectorT7.setCell(0, 0, 2);
		vectorT7.setCell(1, 0, 0);
		vectorT7.setCell(2, 0, 1);
		pentT.add(vectorT7);
		
		IntegerMatrix vectorT8 = new IntegerMatrix(3,1);
		vectorT8.setCell(0, 0, 1);
		vectorT8.setCell(1, 0, 0);
		vectorT8.setCell(2, 0, 1);
		pentT.add(vectorT8);
		
		IntegerMatrix vectorT9 = new IntegerMatrix(3,1);
		vectorT9.setCell(0, 0, 0);
		vectorT9.setCell(1, 0, 3);
		vectorT9.setCell(2, 0, 1);
		pentT.add(vectorT9);
		
		IntegerMatrix vectorT10 = new IntegerMatrix(3,1);
		vectorT10.setCell(0, 0, 3);
		vectorT10.setCell(1, 0, 3);
		vectorT10.setCell(2, 0, 1);
		pentT.add(vectorT10);
		
		IntegerMatrix vectorT11 = new IntegerMatrix(3,1);
		vectorT11.setCell(0, 0, 3);
		vectorT11.setCell(1, 0, 2);
		vectorT11.setCell(2, 0, 1);
		pentT.add(vectorT11);
		
		IntegerMatrix vectorT12 = new IntegerMatrix(3,1);
		vectorT12.setCell(0, 0, 0);
		vectorT12.setCell(1, 0, 2);
		vectorT12.setCell(2, 0, 1);
		pentT.add(vectorT12);
		
		IntegerMatrix vectorT13 = new IntegerMatrix(3,1);
		vectorT13.setCell(0, 0, 2);
		vectorT13.setCell(1, 0, 2);
		vectorT13.setCell(2, 0, 0);
		pentT.add(vectorT13);
		
		IntegerMatrix vectorT14 = new IntegerMatrix(3,1);
		vectorT14.setCell(0, 0, 2);
		vectorT14.setCell(1, 0, 2);
		vectorT14.setCell(2, 0, 1);
		pentT.add(vectorT14);
		
		IntegerMatrix vectorT15 = new IntegerMatrix(3,1);
		vectorT15.setCell(0, 0, 1);
		vectorT15.setCell(1, 0, 2);
		vectorT15.setCell(2, 0, 1);
		pentT.add(vectorT15);
		
		IntegerMatrix vectorT16 = new IntegerMatrix(3,1);
		vectorT16.setCell(0, 0, 1);
		vectorT16.setCell(1, 0, 2);
		vectorT16.setCell(2, 0, 0);
		pentT.add(vectorT16);
		
		return pentT;
	}
	public ArrayList<ArrayList<IntegerMatrix>> getConnectedT()
	{
		IntegerMatrix T1 = new IntegerMatrix(3,1);
		T1.setCell(0, 0, 1);
		T1.setCell(1, 0, 0);
		T1.setCell(2, 0, 0);

		IntegerMatrix T2 = new IntegerMatrix(3,1);
		T2.setCell(0, 0, 2);
		T2.setCell(1, 0, 0);
		T2.setCell(2, 0, 0);

		IntegerMatrix T3 = new IntegerMatrix(3,1);
		T3.setCell(0, 0, 3);
		T3.setCell(1, 0, 2);
		T3.setCell(2, 0, 0);

		IntegerMatrix T4 = new IntegerMatrix(3,1);
		T4.setCell(0, 0, 3);
		T4.setCell(1, 0, 3);
		T4.setCell(2, 0, 0);

		IntegerMatrix T5 = new IntegerMatrix(3,1);
		T5.setCell(0, 0, 0);
		T5.setCell(1, 0, 3);
		T5.setCell(2, 0, 0);

		IntegerMatrix T6 = new IntegerMatrix(3,1);
		T6.setCell(0, 0, 0);
		T6.setCell(1, 0, 2);
		T6.setCell(2, 0, 0);

		IntegerMatrix T7 = new IntegerMatrix(3,1);
		T7.setCell(0, 0, 2);
		T7.setCell(1, 0, 0);
		T7.setCell(2, 0, 1);

		IntegerMatrix T8 = new IntegerMatrix(3,1);
		T8.setCell(0, 0, 1);
		T8.setCell(1, 0, 0);
		T8.setCell(2, 0, 1);

		IntegerMatrix T9 = new IntegerMatrix(3,1);
		T9.setCell(0, 0, 0);
		T9.setCell(1, 0, 3);
		T9.setCell(2, 0, 1);

		IntegerMatrix T10 = new IntegerMatrix(3,1);
		T10.setCell(0, 0, 3);
		T10.setCell(1, 0, 3);
		T10.setCell(2, 0, 1);

		IntegerMatrix T11 = new IntegerMatrix(3,1);
		T11.setCell(0, 0, 3);
		T11.setCell(1, 0, 2);
		T11.setCell(2, 0, 1);
		
		IntegerMatrix T12 = new IntegerMatrix(3,1);
		T12.setCell(0, 0, 0);
		T12.setCell(1, 0, 2);
		T12.setCell(2, 0, 1);
		
		IntegerMatrix T13 = new IntegerMatrix(3,1);
		T13.setCell(0, 0, 2);
		T13.setCell(1, 0, 2);
		T13.setCell(2, 0, 0);
		
		IntegerMatrix T14 = new IntegerMatrix(3,1);
		T14.setCell(0, 0, 2);
		T14.setCell(1, 0, 2);
		T14.setCell(2, 0, 1);
		
		IntegerMatrix T15 = new IntegerMatrix(3,1);
		T15.setCell(0, 0, 1);
		T15.setCell(1, 0, 2);
		T15.setCell(2, 0, 1);
		
		IntegerMatrix T16 = new IntegerMatrix(3,1);
		T16.setCell(0, 0, 1);
		T16.setCell(1, 0, 2);
		T16.setCell(2, 0, 0);
		
		ArrayList<IntegerMatrix> connectT1 = new ArrayList<IntegerMatrix>();
		connectT1.add(T2);connectT1.add(T8);connectT1.add(T16);
		
		ArrayList<IntegerMatrix> connectT2 = new ArrayList<IntegerMatrix>();
		connectT2.add(T1);connectT2.add(T7);connectT2.add(T13);		
		
		ArrayList<IntegerMatrix> connectT3 = new ArrayList<IntegerMatrix>();
		connectT3.add(T4);connectT3.add(T11);connectT3.add(T13);
		
		ArrayList<IntegerMatrix> connectT4 = new ArrayList<IntegerMatrix>();
		connectT4.add(T3);connectT4.add(T5);connectT4.add(T10);
		
		ArrayList<IntegerMatrix> connectT5 = new ArrayList<IntegerMatrix>();
		connectT5.add(T4);connectT5.add(T6);connectT5.add(T9);
		
		ArrayList<IntegerMatrix> connectT6 = new ArrayList<IntegerMatrix>();
		connectT6.add(T5);connectT6.add(T12);connectT6.add(T16);
		
		ArrayList<IntegerMatrix> connectT7 = new ArrayList<IntegerMatrix>();
		connectT7.add(T2);connectT7.add(T8);connectT7.add(T14);
		
		ArrayList<IntegerMatrix> connectT8 = new ArrayList<IntegerMatrix>();
		connectT8.add(T1);connectT8.add(T7);connectT8.add(T15);
		
		ArrayList<IntegerMatrix> connectT9 = new ArrayList<IntegerMatrix>();
		connectT9.add(T5);connectT9.add(T10);connectT9.add(T12);
		
		ArrayList<IntegerMatrix> connectT10 = new ArrayList<IntegerMatrix>();
		connectT10.add(T4);connectT10.add(T9);connectT10.add(T11);
		
		ArrayList<IntegerMatrix> connectT11 = new ArrayList<IntegerMatrix>();
		connectT11.add(T3);connectT11.add(T10);connectT11.add(T14);
		
		ArrayList<IntegerMatrix> connectT12 = new ArrayList<IntegerMatrix>();
		connectT12.add(T6);connectT12.add(T9);connectT12.add(T15);
		
		ArrayList<IntegerMatrix> connectT13 = new ArrayList<IntegerMatrix>();
		connectT13.add(T2);connectT13.add(T3);connectT13.add(T14);
		
		ArrayList<IntegerMatrix> connectT14 = new ArrayList<IntegerMatrix>();
		connectT14.add(T7);connectT14.add(T11);connectT14.add(T13);
		
		ArrayList<IntegerMatrix> connectT15 = new ArrayList<IntegerMatrix>();
		connectT15.add(T8);connectT15.add(T12);connectT15.add(T16);
		
		ArrayList<IntegerMatrix> connectT16 = new ArrayList<IntegerMatrix>();
		connectT16.add(T1);connectT16.add(T6);connectT16.add(T15);
		
		ArrayList<ArrayList<IntegerMatrix>> connectTPent = new ArrayList<ArrayList<IntegerMatrix>>();
		connectTPent.add(connectT1);connectTPent.add(connectT2);connectTPent.add(connectT3);connectTPent.add(connectT4);connectTPent.add(connectT5);connectTPent.add(connectT6);connectTPent.add(connectT7);connectTPent.add(connectT8);
		connectTPent.add(connectT9);connectTPent.add(connectT10);connectTPent.add(connectT11);connectTPent.add(connectT12);connectTPent.add(connectT13);connectTPent.add(connectT14);connectTPent.add(connectT15);connectTPent.add(connectT16);
	
		return connectTPent;
	}
		
}
