package algorithm;

import java.util.ArrayList;

public class Block extends BasicShape 
{
	public Block (double value, BasicShape bShape)
	{
		super (bShape);
		this.value = value;
	}

	public double getValue()
	{
		return value;
	}

	protected double value;
	private BasicShape shape;
}