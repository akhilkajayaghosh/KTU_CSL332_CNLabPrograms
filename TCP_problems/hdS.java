/*Implement a TCP client and server. The client will pass the server two strings of
equal length (eg: “fridge and fruits”). On receiving two strings from a client, the server
should calculate the hamming distance (in eg. it is 4) between them and send back to the
client. The client will display the received distance and exit.
[Hint: Hamming distance between two strings of equal length is the number of positions
at which the corresponding characters are different.]*/

import java.io.*;
import java.util.*;
import java.net.*;
public class hdS extends Thread
{
	Thread t1,t2;
	String str,a,b;
	int count=0;
	ServerSocket server;
	Socket client;
	BufferedReader br,buf;
	PrintWriter pout;
	hdS()
	{
		try
		{
			server=new ServerSocket(5555);
			System.out.println("Waiting for Connection...");
			client=server.accept();
			System.out.println("Connected Successfully");
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
				try
				{
					a=buf.readLine();
					b=buf.readLine();
					System.out.println("Strings received from client: "+a+" "+b);
					for(int i=0;i<a.length();i++)
					{
						if(a.charAt(i)!=b.charAt(i))
							count++;
					}
					pout.println("Hamming distance="+count);
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			
		}
	}
	public static void main(String args[])
	{
		hdS s=new hdS();
		s.t1=new Thread(s);
		s.t2=new Thread(s);
		s.t1.start();
		s.t2.start();
	}
}
