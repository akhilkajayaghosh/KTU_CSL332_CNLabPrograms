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
