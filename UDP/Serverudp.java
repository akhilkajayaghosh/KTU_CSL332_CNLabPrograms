import java.io.*;
import java.net.*;
public class Serverudp extends Thread
{
	Thread t1,t2;
	BufferedReader br;
	byte[] rec;
	byte[] send=new byte[20];
	DatagramSocket server;
	DatagramPacket dp=null;
	public static InetAddress ip;
	public static int port;
	String str,a;
	Serverudp()
	{
		try
		{
			server=new DatagramSocket(5555);
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		catch(Exception e)
		{
			System.out.println("error :"+e);
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
					rec=new byte[20];
					dp=new DatagramPacket(rec,rec.length);
					server.receive(dp);
					ip=dp.getAddress();
					port=dp.getPort();
					str=new String(rec);
					System.out.println("from client:"+str);
				}
				catch(Exception e)
				{
					System.out.println("error :"+e);
				}
			}
			else
			{
				try
				{
					a=br.readLine();
					send=a.getBytes();
					dp=new
					DatagramPacket(send,send.length,ip,port);
					server.send(dp);
				}
				catch(Exception e)
				{
					System.out.println("error :"+e);
				}
			}
		}
	}
	public static void main(String args[])
	{
		Serverudp s=new Serverudp();
		s.t1=new Thread(s);
		s.t2=new Thread(s);
		s.t1.start();
		s.t2.start();
	}
}
