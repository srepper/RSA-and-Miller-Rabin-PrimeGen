1.  Contents
2.  Compiling
3.  Execution
4.  Notes

*****   1 - Contents   *****

The crypto.zip archive contains the [crypto] cirectory:

[crypto]

WIthin the [crypto] directory are the following files:

Crypto.java
RSA.java
MilRab.java
Pair.java



*****   2 - Compiling   *****

Navigate to the directory containing [crypto].
Use the following command to compile the program:

> javac crypto.java

All .java files will be compiled into classes within the [crypto] directory.



*****  3 - Execution   *****

Since all of the .java files are within the crypto package, execution takes place 
from the directory containing [crypto] (!!! NOT the [crypto] directory itself !!!).  
Use the following command to execute the program:

> java crypto/Crypto

Upon opening, the program has a 200x100 pixel frame with three buttons for 
encryption, decryption, and random prime generation.  The encryption and 
decryption buttons will open a file chooser within the current directory to 
select a .txt file with which to work.



*****   4 - Notes   *****

My original intent was to instantiate an RSA simulation that instantiated 
Miller-Rabin tested prime generation with each run of the program.  However, 
since I did not implement the BigInteger class (in an attempt to use only 
original work), exponentiation (and thus also primality testing), I encountered 
errors due integer limits.  I instead chose to hard-code p and q, randomly 
generate e (per program instance), and implement Miller-Rabin tested prime 
generation as a separate feature.

Due to my not implementing the BigInteger class, the Miller-Rabin prime 
generation is also quite limited in scope.  The program is currently coded to 
generate primes up to a maximum value of 46,339 (roughly the square root of
2^31-1).