import java.io.*;
import java.net.*;
import java.util.*;
public class primeC extends Thread
{
	Thread t1,t2;
	DatagramSocket client;
	DatagramPacket dp;
	BufferedReader br;
	byte[] send=new byte[20];
	byte[] rec=new byte[20];
	InetAddress ip;
	String str,a;
	primeC()
	{
		try
		{
			client=new DatagramSocket();
			ip=InetAddress.getLocalHost();
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		catch(Exception e)
		{
			System.out.println("error :"+e);
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
					System.out.println("from server:"+"Prime numbers are :"+str);
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
					Scanner sc=new Scanner(System.in);
					System.out.println("Enter the no.of elements");
					int n=sc.nextInt();
					int arr[]=new int[20];
					System.out.println("Enter the elements");
					StringBuilder sb=new StringBuilder();
					for(int i=0;i<n;i++)
					{
						arr[i]=sc.nextInt();
						sb.append(arr[i]).append(",");
					}
					a=sb.toString();
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
		primeC c=new primeC();
		c.t1=new Thread(c);
		c.t2=new Thread(c);
		c.t1.start();
		c.t2.start();
	}
}
