package algorithm;

public class Resource implements Cloneable
{
	
	public Resource(Block Block, int Inventory, double Volume, boolean Infinite)
	{
		block = Block.clone();
		inventory = Inventory;
		volume = Volume;
		infinite = Infinite;
	}
	
	/**
	 * deep copy clone method
	 */
	public Resource clone()
	{
		return new Resource (block, inventory, volume, infinite);
	}
	
	public void refill()
	{
		inventory++;
	}
	
	public void deduct()
	{
		if (inventory>0)
			inventory --;
		
		if (infinite)
			refill();
	}
	public int getInventory()
	{
		return inventory;
	}
	public boolean isInfinite()
	{
		return infinite;
	}
	
	/**
	 * @return true if inventory == 0 or !infinite
	 */
	public boolean isEmpty()
	{
		return (inventory == 0 || !isInfinite());
	}
	
	public Block getBlock()
	{
		return block;
	}
	
	public double getVolume()
	{
		return volume;
	}
	
	
	private Block block;
	private int inventory;
	private double volume;
	private boolean infinite;
	
}
