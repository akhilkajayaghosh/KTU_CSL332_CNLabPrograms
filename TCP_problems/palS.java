/*Write a program for client server communication using TCP where client
sends a string and server gives back a message “The string is Palindrome” if it is,
and reverse of that string otherwise.*/

import java.io.*;
import java.util.*;
import java.net.*;
public class palS extends Thread
{
	Thread t1,t2;
	BufferedReader br,buf;
	String str,a;
	PrintWriter pout;
	ServerSocket server;
	Socket client;
	palS()
	{
		try
		{
			server=new ServerSocket(5555);
			client=server.accept();
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
			try
			{
				a=buf.readLine();
				System.out.println("From CLient "+a);
				StringBuffer bf=new StringBuffer(a);
				String val=bf.reverse().toString();
				if(val.equals(a))
					str="The String is Palindrome";
				else
					str=val;
				pout.println(str);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}
	public static void main(String []args)
	{
		palS s=new palS();
		s.t1=new Thread(s);
		s.t2=new Thread(s);
		s.t1.start();
		s.t2.start();
	}
}
