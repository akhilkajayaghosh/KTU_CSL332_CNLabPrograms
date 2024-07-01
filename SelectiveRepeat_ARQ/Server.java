import java.util.*;
import java.io.*;
import java.text.*;
import java.net.*;
import java.lang.System;
public class Server
{
	static ServerSocket s;
	static Socket client;
	static DataInputStream din;
	static DataOutputStream dout;	
	public static void main(String args[]) throws Exception
	{
		try
		{
			int a[]={30,40,50,60,70,80,90,100};
			s=new ServerSocket(5555);
			System.out.println("Waiting for connection..");
			client=s.accept();
			dout=new DataOutputStream(client.getOutputStream());
			din=new DataInputStream(client.getInputStream());			
			System.out.println("The no.of packets send:"+a.length);
			int y=a.length;
			dout.write(y);
			dout.flush();
			for(int i=0;i<y;i++)
			{
			dout.write(a[i]);
			dout.flush();				
			}
			int k=din.read();
			dout.write(a[k]);
			dout.flush();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
