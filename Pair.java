package crypto;

public class Pair
{
	private int a;
	private int b;
	
	public Pair(int n)
	{
		b = n - 1;
	}
	
	public Pair(int ay, int be)
	{
		a = ay;
		b = be;
	}
	
	public Pair factor()
	{
		while(b % 2 == 0)
		{
			b = b/2;
			a++;
		}
		return this;
	}
	
	public int getA()
	{
		return a;
	}
	
	public int getB()
	{
		return b;
	}
}
