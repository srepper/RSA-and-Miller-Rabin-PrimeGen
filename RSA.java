package crypto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class RSA
{
	private static int p = 17;
	private static int q = 11;
	private static int n = p * q;
	private static int phi = (p-1) * (q-1);
	private static int e;
	private static int d;
	private static Random rand;
	
	public static String encrypt(String fileName) throws Exception
	{
		String outName = fileName.substring(0, fileName.length() - 4);
		outName += "C.txt";
		Path file = Paths.get(fileName);
		Path outFile = Paths.get(outName);
		byte[] fileArray = Files.readAllBytes(file);
		rand = new Random();
		
		//	Calculate e
		while(gcd((e = rand.nextInt(phi) + 1),phi) != 1);
		
		//	Calculate d (possibly negative)
		d = extEuclid(e, phi).getA(); 
		
		//  Iterate d to positive value 
		while(d < 0)
			d += phi;
		
		int ct = exponentiate(fileArray[0], e, n);
		
		byte[] c = new byte[fileArray.length];
		
		c[0] = (byte)ct;
		
		for(int i = 1; i < fileArray.length; i++)
			c[i] = (byte)(fileArray[i] ^ c[i-1]);
	
		Files.write(outFile, c);
		
		return outName;  
	}
	
	public static String decrypt(String fileName) throws Exception
	{
		String outName;
		if(fileName.endsWith("C.txt"))
			outName = fileName.substring(0, fileName.length() - 5);
		else
			outName = fileName.substring(0, fileName.length() - 4);
			
		outName += "M.txt";
		Path file = Paths.get(fileName);
		Path restored = Paths.get(outName);
		byte[] c = Files.readAllBytes(file);
		
		byte[] m = new byte[c.length];
		for(int i = c.length-1; i > 0; i--)
		{
			m[i] = (byte)(c[i] ^ c[i-1]);
		}
		
		int ct = exponentiate(c[0], d, n);
		m[0] = (byte)ct;
		
		Files.write(restored, m);
		
		return outName;
	}
	
	public static int exponentiate(int base, int exponent, int mod)
	{
		int result = 1;
		while(exponent > 0)
		{
			if((exponent & 0x1) == 1)
				result = ((result * base) % mod);
			exponent = exponent >>> 1;
			base = base * base % mod;
		}
		return result;
	}
		
	private static int gcd(int a, int b)
	{
		int r;
		while(a != 0)
		{
			r = b % a;
			b = a;
			a = r;
		}
		
		return b;
	}
	
	private static Pair extEuclid(int a, int b)
	{
		Pair p;
		if(b == 0)
			return new Pair(1, 0);
		else
		{
			int r = a % b;
			int q = a / b;
			p = extEuclid(b, r);
			return new Pair(p.getB(), p.getA() - q * p.getB());
		}
	}
}
