package kz.aitu.oop.practice.practice5;

public class PreciousStones extends Stones
{
	//constructor inherited from Stones
	public PreciousStones(String n, double w, int c)
	{
		super(n, w, c);
	}
	
	//override of toString method
	@Override
	public String toString() {
		return this.getName() + " is precious stone";
	}
}
