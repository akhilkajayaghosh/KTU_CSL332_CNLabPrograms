import java.util.*;
import java.io.*;
import java.text.*;
import java.net.*;
import java.lang.System;
public class Client
{
	static Socket con;
	public static void main(String args[]) throws Exception
	{
		try
		{
			int v[]=new int[20];
			int n=0;
			Random rands=new Random();
			int rand=0;
			con=new Socket("localhost",5555);
			DataOutputStream out=new DataOutputStream(con.getOutputStream());
			DataInputStream in=new DataInputStream(con.getInputStream());
			int p=in.read();
			System.out.println("No.of frames ="+p);
			for(int i=0;i<p;i++)
			{
				v[i]=in.read();
				System.out.print(v[i]+" ");
			}
			rand=rands.nextInt(p);
			v[rand]=-1;
			System.out.println("\nReceived Frames");
			for(int i=0;i<p;i++)
			{
				System.out.print(v[i]+" ");
			}
			for(int i=0;i<p;i++)
			{
				if(v[i]==-1)
				{
					System.out.println("\nRequest to retransmit from packet no."+(i+1)+" again");
					n=i;
					out.write(n);
					out.flush();
				}
			}
			System.out.println();
			v[n]=in.read();
			System.out.println("Received frame "+v[n]);
			System.out.println("Quiting..");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
