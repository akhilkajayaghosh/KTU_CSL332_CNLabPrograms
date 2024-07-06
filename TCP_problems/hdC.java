import java.io.*;
import java.util.*;
import java.net.*;
public class hdC extends Thread
{
	Thread t1,t2;
	String str,a;
	Socket client;
	BufferedReader br,buf;
	PrintWriter pout;
	hdC()
	{
		try
		{
			client=new Socket("localhost",5555);
			br=new BufferedReader(new InputStreamReader(System.in));
			buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
			pout=new PrintWriter(client.getOutputStream(),true);
			System.out.println("Enter two strings of equal length");
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
					System.out.println("from Server: "+a);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
	}
	public static void main(String args[])
	{
		hdC s=new hdC();
		s.t1=new Thread(s);
		s.t2=new Thread(s);
		s.t1.start();
		s.t2.start();
	}
}
