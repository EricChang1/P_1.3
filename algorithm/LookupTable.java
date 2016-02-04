package algorithm;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class LookupTable extends ArrayList <ArrayList <ArrayList <ArrayList <Double>>>>
{
	public LookupTable (int d, int w, int h, int pieces)
	{
		for (int cD = 0; cD < d; ++cD)
		{
			ArrayList <ArrayList<ArrayList <Double>>> newD = new ArrayList<ArrayList<ArrayList<Double>>>();
			for (int cW = 0; cW < w; ++cW)
			{
				ArrayList <ArrayList<Double>> newW = new ArrayList<ArrayList<Double>>();
				for (int cH = 0; cH < h; ++cH)
					newW.add (new ArrayList <Double> (pieces));
				newD.add (newW);
			}
			add (newD);
		}
	}
	
	public double get (int d, int w, int h, int p)
	{
		return get (d).get(w).get(h).get(p);
	}
	
	public void set (double val, int d, int w, int h, int p)
	{
		get(d).get(w).get(h).set(p, val);
	}
	
}
