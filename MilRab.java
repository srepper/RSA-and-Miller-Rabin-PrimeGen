package crypto;

import java.util.Random;


public class MilRab
{
	private static final int MAXPQ = 23169;
	private static Random rand;
	
	public static int generatePrime()
	{
		rand = new Random();
		int prime = rand.nextInt(MAXPQ) * 2 + 1;
			
		while(!testPrime(prime))
			prime = rand.nextInt(MAXPQ) * 2 + 1;
		
		
		System.out.println(prime);
		return prime;
	}
	
	private static boolean testPrime(int prime)
	{
		Pair p = new Pair(prime);
		p.factor();
		
		loop:
		for(int i = 0; i < 5; i++)
		{
			int a = rand.nextInt(prime - 3) + 2;
			int x = RSA.exponentiate(a, p.getB(), prime);
			if(x == 1 || x == prime - 1)
			{
				continue;
			}
			for(int j = 1; j < p.getA(); j++)
			{
				x = RSA.exponentiate(x, 2, prime);
				if(x == 1)
					return false;
				else if(x == prime - 1)
					continue loop;
				
			}
			return false;
		}
		return true;
	}
}
