import java.io.*;import java.net.*;
public class armstrongC extends Thread
{
	Thread t1,t2;
	DatagramSocket client;
	DatagramPacket dp;
	BufferedReader br;
	byte[] send=new byte[50];
	byte[] rec=new byte[50];
	InetAddress ip;
	String str,a;
	armstrongC()
	{
		try
		{
			client=new DatagramSocket();
			ip=InetAddress.getLocalHost();
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void run()
	{
			if(Thread.currentThread()==t1)
			{
				try
				{
					dp=new DatagramPacket(rec,rec.length);
					client.receive(dp);
					str=new String(rec);
					System.out.println("from server:"+str);
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
					System.out.println("Enter a three digit number");
					a=br.readLine();
					send=a.getBytes();
					dp=new DatagramPacket(send,send.length,ip,5555);
					client.send(dp);
				}
				catch(Exception e)
				{
					System.out.println("error :"+e);
				}
			}
		
	}
	public static void main(String args[])
	{
		armstrongC c=new armstrongC();
		c.t1=new Thread(c);
		c.t2=new Thread(c);
		c.t1.start();
		c.t2.start();
	}
}
