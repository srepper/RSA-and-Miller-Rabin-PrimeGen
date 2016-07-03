package crypto;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;

public class Crypto extends JFrame
{
	private static String info = "Encryption and decryption only work in the " 
		+ "current instance of the program (e is generated randomly).";
	public static void main(String[] args) throws IOException
	{
		JOptionPane.showMessageDialog(null, info, 
				"Info", JOptionPane.PLAIN_MESSAGE);
		Crypto frame = new Crypto();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Crypto");
		frame.setMinimumSize(new Dimension(200,100));
		frame.pack();
		frame.setVisible(true);
	}
	
	public Crypto()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel cryptButtons = new CryptButtons();
		JPanel primeButton = new PrimeButton();
		
		mainPanel.add(cryptButtons, BorderLayout.CENTER);
		mainPanel.add(primeButton, BorderLayout.SOUTH);
		add(mainPanel);
		
		setLocation(400, 300);
		
	}
}


class CryptButtons extends JPanel
{
	JPanel cryptButtons;
	JButton btnEncrypt;
	JButton btnDecrypt;
	
	public CryptButtons()
	{
		cryptButtons = new JPanel();
		btnEncrypt = new JButton("Encrypt File");
		btnDecrypt = new JButton("Decrypt File");
		
		btnEncrypt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fileEncrypt();
			}
		});
		
		btnDecrypt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fileDecrypt();
			}
		});
		
		cryptButtons.add(btnEncrypt);
		cryptButtons.add(btnDecrypt);
		add(cryptButtons);
	}
	
	private void fileEncrypt()
	{	
		File result;
		int retVal;
		String fileName = null;
		Scanner s;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"TXT", "txt");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(new File("readme.txt"));
		retVal = chooser.showOpenDialog(this.getParent());
		if(retVal == JFileChooser.APPROVE_OPTION)
		{
			try{
				fileName = RSA.encrypt(chooser.getSelectedFile().getName());
			}
			catch(Exception except){
					Error.fileNotFound();
			}
			//fileName += "C.txt";
			try{
				if(fileName != null)
				{
					String output = "A new file has been written: \n\n" 
						+ fileName + "\n\n\n\n" + "File contents:  \n\n";
					result = new File(fileName);
					s = new Scanner(result);
					
					while(s.hasNextLine())
					{
						output += s.nextLine();
					}
					JOptionPane.showMessageDialog(null, output, 
								"Results", JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					Error.encryptionError();
				}
			} catch(Exception except){
				Error.fileWriteError();
			}
		}
	}
	
	private void fileDecrypt()
	{
		File result;
		int retVal;
		String fileName = null;
		Scanner s;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"TXT", "txt");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(new File("readme.txt"));
		retVal = chooser.showOpenDialog(this.getParent());
		if(retVal == JFileChooser.APPROVE_OPTION)
		{
			try{
				fileName = RSA.decrypt(chooser.getSelectedFile().getName());
			}
			catch(Exception except){
					Error.fileNotFound();
			}
			try{
				if(fileName != null)
				{
					String output = "A new file has been written: \n\n" 
						+ fileName + "\n\n\n\n" + "File contents:  \n\n";
					result = new File(fileName);
					s = new Scanner(result);
					
					while(s.hasNextLine())
					{
						output += s.nextLine();
					}
					JOptionPane.showMessageDialog(null, output, 
								"Results", JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					Error.decryptionError();
				}
			} catch(Exception except){
				Error.fileWriteError();
			}
		}
	}
}

class PrimeButton extends JPanel
{
	JPanel primeButton;
	JButton btnPrime;
	
	public PrimeButton()
	{
		primeButton = new JPanel();
		btnPrime = new JButton("Generate Prime");
		
		btnPrime.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				genPrime();
			}
		});
		
		primeButton.add(btnPrime);
		add(primeButton);
	}
	
	private int genPrime()
	{
		int prime = MilRab.generatePrime();
		JOptionPane.showMessageDialog(null, prime, 
				"Prime", JOptionPane.PLAIN_MESSAGE);
		return prime;
	}
}


class Error
{	
	public static void fileNotFound()
	{
		JOptionPane.showMessageDialog(null, "File not found.", 
				"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void fileWriteError()
	{
		JOptionPane.showMessageDialog(null, "Error writing file output.", 
				"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void encryptionError()
	{
		JOptionPane.showMessageDialog(null, "Encryption Error", 
				"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void decryptionError()
	{
		JOptionPane.showMessageDialog(null, "Decryption Error", 
				"Error", JOptionPane.ERROR_MESSAGE);
	}
}