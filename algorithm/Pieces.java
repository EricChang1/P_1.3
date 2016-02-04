package algorithm;

import algorithm.Matrix.*;
import java.util.*;

public class Pieces {
	
	
	public ArrayList<ArrayList<DoubleMatrix>> createPents()
	{
		ArrayList<ArrayList<DoubleMatrix>>pentominos = new ArrayList<ArrayList<DoubleMatrix>>();
		pentominos.add(createLPent());
		pentominos.add(createPPent());
		pentominos.add(createTPent());
		return pentominos;
	}
	
	public ArrayList<DoubleMatrix> createLPent()
	{
		ArrayList<DoubleMatrix> pentL = new ArrayList<DoubleMatrix>();
		DoubleMatrix vectorL1 = new DoubleMatrix(3,1);
		vectorL1.setCell(0, 0, 0);
		vectorL1.setCell(1, 0, 0);
		vectorL1.setCell(2, 0, 0);
		pentL.add(vectorL1);
		
		DoubleMatrix vectorL2 = new DoubleMatrix(3,1);
		vectorL2.setCell(0, 0, 2);
		vectorL2.setCell(1, 0, 0);
		vectorL2.setCell(2, 0, 0);
		pentL.add(vectorL2);
		
		DoubleMatrix vectorL3 = new DoubleMatrix(3,1);
		vectorL3.setCell(0, 0, 2);
		vectorL3.setCell(1, 0, 0);
		vectorL3.setCell(2, 0, 1);
		pentL.add(vectorL3);
		
		DoubleMatrix vectorL4 = new DoubleMatrix(3,1);
		vectorL4.setCell(0, 0, 2);
		vectorL4.setCell(1, 0, 1);
		vectorL4.setCell(2, 0, 1);
		pentL.add(vectorL4);
		
		DoubleMatrix vectorL5 = new DoubleMatrix(3,1);
		vectorL5.setCell(0, 0, 2);
		vectorL5.setCell(1, 0, 1);
		vectorL5.setCell(2, 0, 0);
		pentL.add(vectorL5);
		
		DoubleMatrix vectorL6 = new DoubleMatrix(3,1);
		vectorL6.setCell(0, 0, 1);
		vectorL6.setCell(1, 0, 1);
		vectorL6.setCell(2, 0, 0);
		pentL.add(vectorL6);
		
		DoubleMatrix vectorL7 = new DoubleMatrix(3,1);
		vectorL7.setCell(0, 0, 1);
		vectorL7.setCell(1, 0, 1);
		vectorL7.setCell(2, 0, 1);
		pentL.add(vectorL7);
		
		DoubleMatrix vectorL8 = new DoubleMatrix(3,1);
		vectorL8.setCell(0, 0, 1);
		vectorL8.setCell(1, 0, 4);
		vectorL8.setCell(2, 0, 0);
		pentL.add(vectorL8);
		
		DoubleMatrix vectorL9 = new DoubleMatrix(3,1);
		vectorL9.setCell(0, 0, 1);
		vectorL9.setCell(1, 0, 4);
		vectorL9.setCell(2, 0, 1);
		pentL.add(vectorL9);
		
		DoubleMatrix vectorL10 = new DoubleMatrix(3,1);
		vectorL10.setCell(0, 0, 0);
		vectorL10.setCell(1, 0, 4);
		vectorL10.setCell(2, 0, 1);
		pentL.add(vectorL10);
		
		DoubleMatrix vectorL11 = new DoubleMatrix(3,1);
		vectorL11.setCell(0, 0, 0);
		vectorL11.setCell(1, 0, 4);
		vectorL11.setCell(2, 0, 0);
		pentL.add(vectorL11);
		
		DoubleMatrix vectorL12 = new DoubleMatrix(3,1);
		vectorL12.setCell(0, 0, 0);
		vectorL12.setCell(1, 0, 0);
		vectorL12.setCell(2, 0, 1);
		pentL.add(vectorL12);
		return pentL;
	}
	public ArrayList<ArrayList<DoubleMatrix>> getConnectedL()
	{
		DoubleMatrix L1 = new DoubleMatrix(3,1);
		L1.setCell(0, 0, 0);
		L1.setCell(1, 0, 0);
		L1.setCell(2, 0, 0);
		DoubleMatrix L2 = new DoubleMatrix(3,1);
		L2.setCell(0, 0, 2);
		L2.setCell(1, 0, 0);
		L2.setCell(2, 0, 0);
		DoubleMatrix L3 = new DoubleMatrix(3,1);
		L3.setCell(0, 0, 2);
		L3.setCell(1, 0, 0);
		L3.setCell(2, 0, 1);
		DoubleMatrix L4 = new DoubleMatrix(3,1);
		L4.setCell(0, 0, 2);
		L4.setCell(1, 0, 1);
		L4.setCell(2, 0, 1);
		DoubleMatrix L5 = new DoubleMatrix(3,1);
		L5.setCell(0, 0, 2);
		L5.setCell(1, 0, 1);
		L5.setCell(2, 0, 0);
		DoubleMatrix L6 = new DoubleMatrix(3,1);
		L6.setCell(0, 0, 1);
		L6.setCell(1, 0, 1);
		L6.setCell(2, 0, 0);
		DoubleMatrix L7 = new DoubleMatrix(3,1);
		L7.setCell(0, 0, 1);
		L7.setCell(1, 0, 1);
		L7.setCell(2, 0, 1);
		DoubleMatrix L8 = new DoubleMatrix(3,1);
		L8.setCell(0, 0, 1);
		L8.setCell(1, 0, 4);
		L8.setCell(2, 0, 0);
		DoubleMatrix L9 = new DoubleMatrix(3,1);
		L9.setCell(0, 0, 1);
		L9.setCell(1, 0, 4);
		L9.setCell(2, 0, 1);
		DoubleMatrix L10 = new DoubleMatrix(3,1);
		L10.setCell(0, 0, 0);
		L10.setCell(1, 0, 4);
		L10.setCell(2, 0, 1);
		DoubleMatrix L11 = new DoubleMatrix(3,1);
		L11.setCell(0, 0, 0);
		L11.setCell(1, 0, 4);
		L11.setCell(2, 0, 0);
		DoubleMatrix L12 = new DoubleMatrix(3,1);
		L12.setCell(0, 0, 0);
		L12.setCell(1, 0, 0);
		L12.setCell(2, 0, 1);

		ArrayList<ArrayList<DoubleMatrix>> connectLPent = new ArrayList<ArrayList<DoubleMatrix>>();
		ArrayList<DoubleMatrix> connectL1 = new ArrayList<DoubleMatrix>();
		connectL1.add(L2);connectL1.add(L11);connectL1.add(L12);
		
		ArrayList<DoubleMatrix> connectL2 = new ArrayList<DoubleMatrix>();
		connectL2.add(L1);connectL1.add(L3);connectL1.add(L5);
		
		ArrayList<DoubleMatrix> connectL3 = new ArrayList<DoubleMatrix>();
		connectL3.add(L2);connectL1.add(L4);connectL1.add(L12);
		
		ArrayList<DoubleMatrix> connectL4 = new ArrayList<DoubleMatrix>();
		connectL4.add(L3);connectL1.add(L5);connectL1.add(L7);
		
		ArrayList<DoubleMatrix> connectL5 = new ArrayList<DoubleMatrix>();
		connectL5.add(L2);connectL1.add(L4);connectL1.add(L6);
		
		ArrayList<DoubleMatrix> connectL6 = new ArrayList<DoubleMatrix>();
		connectL6.add(L5);connectL1.add(L7);connectL1.add(L8);
		
		ArrayList<DoubleMatrix> connectL7 = new ArrayList<DoubleMatrix>();
		connectL7.add(L4);connectL1.add(L6);connectL1.add(L9);
		
		ArrayList<DoubleMatrix> connectL8 = new ArrayList<DoubleMatrix>();
		connectL8.add(L6);connectL1.add(L9);connectL1.add(L11);
		
		ArrayList<DoubleMatrix> connectL9 = new ArrayList<DoubleMatrix>();
		connectL9.add(L7);connectL1.add(L8);connectL1.add(L10);
		
		ArrayList<DoubleMatrix> connectL10 = new ArrayList<DoubleMatrix>();
		connectL10.add(L9);connectL1.add(L11);connectL1.add(L12);
		
		ArrayList<DoubleMatrix> connectL11 = new ArrayList<DoubleMatrix>();
		connectL11.add(L1);connectL1.add(L8);connectL1.add(L10);
		
		ArrayList<DoubleMatrix> connectL12 = new ArrayList<DoubleMatrix>();
		connectL12.add(L3);connectL1.add(L4);connectL1.add(L10);
		
		connectLPent.add(connectL1);connectLPent.add(connectL2);connectLPent.add(connectL3);connectLPent.add(connectL4);connectLPent.add(connectL5);connectLPent.add(connectL6);
		connectLPent.add(connectL7);connectLPent.add(connectL8);connectLPent.add(connectL9);connectLPent.add(connectL10);connectLPent.add(connectL11);connectLPent.add(connectL12);
		
		return connectLPent;
	}

	
	public ArrayList<DoubleMatrix> createPPent()
	{
		ArrayList<DoubleMatrix>  pentP = new ArrayList<DoubleMatrix> ();
		DoubleMatrix vectorP1 = new DoubleMatrix(3,1);
		vectorP1.setCell(0, 0, 0);
		vectorP1.setCell(1, 0, 0);
		vectorP1.setCell(2, 0, 0);
		pentP.add(vectorP1);
		DoubleMatrix vectorP2 = new DoubleMatrix(3,1);
		vectorP2.setCell(0, 0, 1);
		vectorP2.setCell(1, 0, 0);
		vectorP2.setCell(2, 0, 0);
		pentP.add(vectorP2);
		DoubleMatrix vectorP3 = new DoubleMatrix(3,1);
		vectorP3.setCell(0, 0, 1);
		vectorP3.setCell(1, 0, 1);
		vectorP3.setCell(2, 0, 0);
		pentP.add(vectorP3);
		DoubleMatrix vectorP4 = new DoubleMatrix(3,1);
		vectorP4.setCell(0, 0, 2);
		vectorP4.setCell(1, 0, 1);
		vectorP4.setCell(2, 0, 0);
		pentP.add(vectorP4);
		DoubleMatrix vectorP5 = new DoubleMatrix(3,1);
		vectorP5.setCell(0, 0, 2);
		vectorP5.setCell(1, 0, 3);
		vectorP5.setCell(2, 0, 0);
		pentP.add(vectorP5);
		DoubleMatrix vectorP6 = new DoubleMatrix(3,1);
		vectorP6.setCell(0, 0, 0);
		vectorP6.setCell(1, 0, 3);
		vectorP6.setCell(2, 0, 0);
		pentP.add(vectorP6);
		DoubleMatrix vectorP7 = new DoubleMatrix(3,1);
		vectorP7.setCell(0, 0, 0);
		vectorP7.setCell(1, 0, 0);
		vectorP7.setCell(2, 0, 1);
		pentP.add(vectorP7);
		DoubleMatrix vectorP8 = new DoubleMatrix(3,1);
		vectorP8.setCell(0, 0, 1);
		vectorP8.setCell(1, 0, 0);
		vectorP8.setCell(2, 0, 1);
		pentP.add(vectorP8);
		DoubleMatrix vectorP9 = new DoubleMatrix(3,1);
		vectorP9.setCell(0, 0, 1);
		vectorP9.setCell(1, 0, 1);
		vectorP9.setCell(2, 0, 1);
		pentP.add(vectorP9);
		DoubleMatrix vectorP10 = new DoubleMatrix(3,1);
		vectorP10.setCell(0, 0, 2);
		vectorP10.setCell(1, 0, 1);
		vectorP10.setCell(2, 0, 1);
		pentP.add(vectorP10);
		DoubleMatrix vectorP11 = new DoubleMatrix(3,1);
		vectorP11.setCell(0, 0, 2);
		vectorP11.setCell(1, 0, 3);
		vectorP11.setCell(2, 0, 1);
		pentP.add(vectorP11);
		DoubleMatrix vectorP12 = new DoubleMatrix(3,1);
		vectorP12.setCell(0, 0, 0);
		vectorP12.setCell(1, 0, 3);
		vectorP12.setCell(2, 0, 1);
		pentP.add(vectorP12);
		return pentP;
	}
	public ArrayList<ArrayList<DoubleMatrix>> getConnectedP()
	{
		DoubleMatrix P1 = new DoubleMatrix(3,1);
		P1.setCell(0, 0, 0);
		P1.setCell(1, 0, 0);
		P1.setCell(2, 0, 0);

		DoubleMatrix P2 = new DoubleMatrix(3,1);
		P2.setCell(0, 0, 1);
		P2.setCell(1, 0, 0);
		P2.setCell(2, 0, 0);

		DoubleMatrix P3 = new DoubleMatrix(3,1);
		P3.setCell(0, 0, 1);
		P3.setCell(1, 0, 1);
		P3.setCell(2, 0, 0);

		DoubleMatrix P4 = new DoubleMatrix(3,1);
		P4.setCell(0, 0, 2);
		P4.setCell(1, 0, 1);
		P4.setCell(2, 0, 0);

		DoubleMatrix P5 = new DoubleMatrix(3,1);
		P5.setCell(0, 0, 2);
		P5.setCell(1, 0, 3);
		P5.setCell(2, 0, 0);

		DoubleMatrix P6 = new DoubleMatrix(3,1);
		P6.setCell(0, 0, 0);
		P6.setCell(1, 0, 3);
		P6.setCell(2, 0, 0);

		DoubleMatrix P7 = new DoubleMatrix(3,1);
		P7.setCell(0, 0, 0);
		P7.setCell(1, 0, 0);
		P7.setCell(2, 0, 1);

		DoubleMatrix P8 = new DoubleMatrix(3,1);
		P8.setCell(0, 0, 1);
		P8.setCell(1, 0, 0);
		P8.setCell(2, 0, 1);

		DoubleMatrix P9 = new DoubleMatrix(3,1);
		P9.setCell(0, 0, 1);
		P9.setCell(1, 0, 1);
		P9.setCell(2, 0, 1);
	
		DoubleMatrix P10 = new DoubleMatrix(3,1);
		P10.setCell(0, 0, 2);
		P10.setCell(1, 0, 1);
		P10.setCell(2, 0, 1);
	
		DoubleMatrix P11 = new DoubleMatrix(3,1);
		P11.setCell(0, 0, 2);
		P11.setCell(1, 0, 3);
		P11.setCell(2, 0, 1);
	
		DoubleMatrix P12 = new DoubleMatrix(3,1);
		P12.setCell(0, 0, 0);
		P12.setCell(1, 0, 3);
		P12.setCell(2, 0, 1);
		
		ArrayList<DoubleMatrix> connectP1 = new ArrayList<DoubleMatrix>();
		connectP1.add(P2);connectP1.add(P6);connectP1.add(P7);
		
		ArrayList<DoubleMatrix> connectP2 = new ArrayList<DoubleMatrix>();
		connectP2.add(P1);connectP2.add(P3);connectP2.add(P8);
		
		ArrayList<DoubleMatrix> connectP3 = new ArrayList<DoubleMatrix>();
		connectP3.add(P2);connectP3.add(P4);connectP3.add(P9);
		
		ArrayList<DoubleMatrix> connectP4 = new ArrayList<DoubleMatrix>();
		connectP4.add(P3);connectP4.add(P5);connectP4.add(P10);
		
		ArrayList<DoubleMatrix> connectP5 = new ArrayList<DoubleMatrix>();
		connectP5.add(P4);connectP5.add(P6);connectP5.add(P11);
		
		ArrayList<DoubleMatrix> connectP6 = new ArrayList<DoubleMatrix>();
		connectP6.add(P1);connectP6.add(P5);connectP6.add(P12);
		
		ArrayList<DoubleMatrix> connectP7 = new ArrayList<DoubleMatrix>();
		connectP7.add(P1);connectP7.add(P8);connectP7.add(P12);
		
		ArrayList<DoubleMatrix> connectP8 = new ArrayList<DoubleMatrix>();
		connectP8.add(P2);connectP8.add(P7);connectP8.add(P9);
		
		ArrayList<DoubleMatrix> connectP9 = new ArrayList<DoubleMatrix>();
		connectP9.add(P3);connectP9.add(P8);connectP9.add(P10);
		
		ArrayList<DoubleMatrix> connectP10 = new ArrayList<DoubleMatrix>();
		connectP10.add(P4);connectP10.add(P9);connectP10.add(P11);
		
		ArrayList<DoubleMatrix> connectP11 = new ArrayList<DoubleMatrix>();
		connectP11.add(P5);connectP11.add(P10);connectP11.add(P12);
		
		ArrayList<DoubleMatrix> connectP12 = new ArrayList<DoubleMatrix>();
		connectP12.add(P6);connectP12.add(P7);connectP12.add(P11);
		
		ArrayList<ArrayList<DoubleMatrix>> connectPPent = new ArrayList<ArrayList<DoubleMatrix>>();
		connectPPent.add(connectP1);connectPPent.add(connectP2);connectPPent.add(connectP3);connectPPent.add(connectP4);connectPPent.add(connectP5);connectPPent.add(connectP6);
		connectPPent.add(connectP7);connectPPent.add(connectP8);connectPPent.add(connectP9);connectPPent.add(connectP10);connectPPent.add(connectP11);connectPPent.add(connectP12);
		
		return connectPPent;
	}
	
	public ArrayList<DoubleMatrix> createTPent()
	{
		ArrayList <DoubleMatrix> pentT = new ArrayList<DoubleMatrix>();
		DoubleMatrix vectorT1 = new DoubleMatrix(3,1);
		vectorT1.setCell(0, 0, 1);
		vectorT1.setCell(1, 0, 0);
		vectorT1.setCell(2, 0, 0);
		pentT.add(vectorT1);
		
		DoubleMatrix vectorT2 = new DoubleMatrix(3,1);
		vectorT2.setCell(0, 0, 2);
		vectorT2.setCell(1, 0, 0);
		vectorT2.setCell(2, 0, 0);
		pentT.add(vectorT2);
		
		DoubleMatrix vectorT3 = new DoubleMatrix(3,1);
		vectorT3.setCell(0, 0, 3);
		vectorT3.setCell(1, 0, 2);
		vectorT3.setCell(2, 0, 0);
		pentT.add(vectorT3);
		
		DoubleMatrix vectorT4 = new DoubleMatrix(3,1);
		vectorT4.setCell(0, 0, 3);
		vectorT4.setCell(1, 0, 3);
		vectorT4.setCell(2, 0, 0);
		pentT.add(vectorT4);
		
		DoubleMatrix vectorT5 = new DoubleMatrix(3,1);
		vectorT5.setCell(0, 0, 0);
		vectorT5.setCell(1, 0, 3);
		vectorT5.setCell(2, 0, 0);
		pentT.add(vectorT5);
		
		DoubleMatrix vectorT6 = new DoubleMatrix(3,1);
		vectorT6.setCell(0, 0, 0);
		vectorT6.setCell(1, 0, 2);
		vectorT6.setCell(2, 0, 0);
		pentT.add(vectorT6);
		
		DoubleMatrix vectorT7 = new DoubleMatrix(3,1);
		vectorT7.setCell(0, 0, 2);
		vectorT7.setCell(1, 0, 0);
		vectorT7.setCell(2, 0, 1);
		pentT.add(vectorT7);
		
		DoubleMatrix vectorT8 = new DoubleMatrix(3,1);
		vectorT8.setCell(0, 0, 1);
		vectorT8.setCell(1, 0, 0);
		vectorT8.setCell(2, 0, 1);
		pentT.add(vectorT8);
		
		DoubleMatrix vectorT9 = new DoubleMatrix(3,1);
		vectorT9.setCell(0, 0, 0);
		vectorT9.setCell(1, 0, 3);
		vectorT9.setCell(2, 0, 1);
		pentT.add(vectorT9);
		
		DoubleMatrix vectorT10 = new DoubleMatrix(3,1);
		vectorT10.setCell(0, 0, 3);
		vectorT10.setCell(1, 0, 3);
		vectorT10.setCell(2, 0, 1);
		pentT.add(vectorT10);
		
		DoubleMatrix vectorT11 = new DoubleMatrix(3,1);
		vectorT11.setCell(0, 0, 3);
		vectorT11.setCell(1, 0, 2);
		vectorT11.setCell(2, 0, 1);
		pentT.add(vectorT11);
		
		DoubleMatrix vectorT12 = new DoubleMatrix(3,1);
		vectorT12.setCell(0, 0, 0);
		vectorT12.setCell(1, 0, 2);
		vectorT12.setCell(2, 0, 1);
		pentT.add(vectorT12);
		
		DoubleMatrix vectorT13 = new DoubleMatrix(3,1);
		vectorT13.setCell(0, 0, 2);
		vectorT13.setCell(1, 0, 2);
		vectorT13.setCell(2, 0, 0);
		pentT.add(vectorT13);
		
		DoubleMatrix vectorT14 = new DoubleMatrix(3,1);
		vectorT14.setCell(0, 0, 2);
		vectorT14.setCell(1, 0, 2);
		vectorT14.setCell(2, 0, 1);
		pentT.add(vectorT14);
		
		DoubleMatrix vectorT15 = new DoubleMatrix(3,1);
		vectorT15.setCell(0, 0, 1);
		vectorT15.setCell(1, 0, 2);
		vectorT15.setCell(2, 0, 1);
		pentT.add(vectorT15);
		
		DoubleMatrix vectorT16 = new DoubleMatrix(3,1);
		vectorT16.setCell(0, 0, 1);
		vectorT16.setCell(1, 0, 2);
		vectorT16.setCell(2, 0, 0);
		pentT.add(vectorT16);
		
		return pentT;
	}
	public ArrayList<ArrayList<DoubleMatrix>> getConnectedT()
	{
		DoubleMatrix T1 = new DoubleMatrix(3,1);
		T1.setCell(0, 0, 1);
		T1.setCell(1, 0, 0);
		T1.setCell(2, 0, 0);

		DoubleMatrix T2 = new DoubleMatrix(3,1);
		T2.setCell(0, 0, 2);
		T2.setCell(1, 0, 0);
		T2.setCell(2, 0, 0);

		DoubleMatrix T3 = new DoubleMatrix(3,1);
		T3.setCell(0, 0, 3);
		T3.setCell(1, 0, 2);
		T3.setCell(2, 0, 0);

		DoubleMatrix T4 = new DoubleMatrix(3,1);
		T4.setCell(0, 0, 3);
		T4.setCell(1, 0, 3);
		T4.setCell(2, 0, 0);

		DoubleMatrix T5 = new DoubleMatrix(3,1);
		T5.setCell(0, 0, 0);
		T5.setCell(1, 0, 3);
		T5.setCell(2, 0, 0);

		DoubleMatrix T6 = new DoubleMatrix(3,1);
		T6.setCell(0, 0, 0);
		T6.setCell(1, 0, 2);
		T6.setCell(2, 0, 0);

		DoubleMatrix T7 = new DoubleMatrix(3,1);
		T7.setCell(0, 0, 2);
		T7.setCell(1, 0, 0);
		T7.setCell(2, 0, 1);

		DoubleMatrix T8 = new DoubleMatrix(3,1);
		T8.setCell(0, 0, 1);
		T8.setCell(1, 0, 0);
		T8.setCell(2, 0, 1);

		DoubleMatrix T9 = new DoubleMatrix(3,1);
		T9.setCell(0, 0, 0);
		T9.setCell(1, 0, 3);
		T9.setCell(2, 0, 1);

		DoubleMatrix T10 = new DoubleMatrix(3,1);
		T10.setCell(0, 0, 3);
		T10.setCell(1, 0, 3);
		T10.setCell(2, 0, 1);

		DoubleMatrix T11 = new DoubleMatrix(3,1);
		T11.setCell(0, 0, 3);
		T11.setCell(1, 0, 2);
		T11.setCell(2, 0, 1);
		
		DoubleMatrix T12 = new DoubleMatrix(3,1);
		T12.setCell(0, 0, 0);
		T12.setCell(1, 0, 2);
		T12.setCell(2, 0, 1);
		
		DoubleMatrix T13 = new DoubleMatrix(3,1);
		T13.setCell(0, 0, 2);
		T13.setCell(1, 0, 2);
		T13.setCell(2, 0, 0);
		
		DoubleMatrix T14 = new DoubleMatrix(3,1);
		T14.setCell(0, 0, 2);
		T14.setCell(1, 0, 2);
		T14.setCell(2, 0, 1);
		
		DoubleMatrix T15 = new DoubleMatrix(3,1);
		T15.setCell(0, 0, 1);
		T15.setCell(1, 0, 2);
		T15.setCell(2, 0, 1);
		
		DoubleMatrix T16 = new DoubleMatrix(3,1);
		T16.setCell(0, 0, 1);
		T16.setCell(1, 0, 2);
		T16.setCell(2, 0, 0);
		
		ArrayList<DoubleMatrix> connectT1 = new ArrayList<DoubleMatrix>();
		connectT1.add(T2);connectT1.add(T8);connectT1.add(T16);
		
		ArrayList<DoubleMatrix> connectT2 = new ArrayList<DoubleMatrix>();
		connectT2.add(T1);connectT2.add(T7);connectT2.add(T13);		
		
		ArrayList<DoubleMatrix> connectT3 = new ArrayList<DoubleMatrix>();
		connectT3.add(T4);connectT3.add(T11);connectT3.add(T13);
		
		ArrayList<DoubleMatrix> connectT4 = new ArrayList<DoubleMatrix>();
		connectT4.add(T3);connectT4.add(T5);connectT4.add(T10);
		
		ArrayList<DoubleMatrix> connectT5 = new ArrayList<DoubleMatrix>();
		connectT5.add(T4);connectT5.add(T6);connectT5.add(T9);
		
		ArrayList<DoubleMatrix> connectT6 = new ArrayList<DoubleMatrix>();
		connectT6.add(T5);connectT6.add(T12);connectT6.add(T16);
		
		ArrayList<DoubleMatrix> connectT7 = new ArrayList<DoubleMatrix>();
		connectT7.add(T2);connectT7.add(T8);connectT7.add(T14);
		
		ArrayList<DoubleMatrix> connectT8 = new ArrayList<DoubleMatrix>();
		connectT8.add(T1);connectT8.add(T7);connectT8.add(T15);
		
		ArrayList<DoubleMatrix> connectT9 = new ArrayList<DoubleMatrix>();
		connectT9.add(T5);connectT9.add(T10);connectT9.add(T12);
		
		ArrayList<DoubleMatrix> connectT10 = new ArrayList<DoubleMatrix>();
		connectT10.add(T4);connectT10.add(T9);connectT10.add(T11);
		
		ArrayList<DoubleMatrix> connectT11 = new ArrayList<DoubleMatrix>();
		connectT11.add(T3);connectT11.add(T10);connectT11.add(T14);
		
		ArrayList<DoubleMatrix> connectT12 = new ArrayList<DoubleMatrix>();
		connectT12.add(T6);connectT12.add(T9);connectT12.add(T15);
		
		ArrayList<DoubleMatrix> connectT13 = new ArrayList<DoubleMatrix>();
		connectT13.add(T2);connectT13.add(T3);connectT13.add(T14);
		
		ArrayList<DoubleMatrix> connectT14 = new ArrayList<DoubleMatrix>();
		connectT14.add(T7);connectT14.add(T11);connectT14.add(T13);
		
		ArrayList<DoubleMatrix> connectT15 = new ArrayList<DoubleMatrix>();
		connectT15.add(T8);connectT15.add(T12);connectT15.add(T16);
		
		ArrayList<DoubleMatrix> connectT16 = new ArrayList<DoubleMatrix>();
		connectT16.add(T1);connectT16.add(T6);connectT16.add(T15);
		
		ArrayList<ArrayList<DoubleMatrix>> connectTPent = new ArrayList<ArrayList<DoubleMatrix>>();
		connectTPent.add(connectT1);connectTPent.add(connectT2);connectTPent.add(connectT3);connectTPent.add(connectT4);connectTPent.add(connectT5);connectTPent.add(connectT6);connectTPent.add(connectT7);connectTPent.add(connectT8);
		connectTPent.add(connectT9);connectTPent.add(connectT10);connectTPent.add(connectT11);connectTPent.add(connectT12);connectTPent.add(connectT13);connectTPent.add(connectT14);connectTPent.add(connectT15);connectTPent.add(connectT16);
	
		return connectTPent;
	}
		
}
