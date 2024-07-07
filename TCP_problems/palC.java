/*Write a program for client server communication using TCP where client
sends a string and server gives back a message “The string is Palindrome” if it is,
and reverse of that string otherwise.*/

import java.io.*;
import java.util.*;
import java.net.*;
public class palC extends Thread
{
	Thread t1,t2;
	BufferedReader br,buf;
	String str,a;
	PrintWriter pout;
	Socket client;
	palC()
	{
		try
		{
			client=new Socket("localhost",5555);
			br=new BufferedReader(new InputStreamReader(System.in));
			buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
			pout=new PrintWriter(client.getOutputStream(),true);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void run()
	{
		while(true)
		{
			if(Thread.currentThread()==t1)
			{
				try
				{
					str=br.readLine();
					pout.println(str);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			else
			{
				try
				{
					a=buf.readLine();
					System.out.println("From Server "+a);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
	}
	public static void main(String []args)
	{
		palC s=new palC();
		s.t1=new Thread(s);
		s.t2=new Thread(s);
		s.t1.start();
		s.t2.start();
	}
}
