import java.io.*;
import java.net.*;
public class armstrongS extends Thread
{
	Thread t1,t2;
	BufferedReader br;
	byte[] rec=new byte[50];
	byte[] send=new byte[50];
	DatagramSocket server;
	DatagramPacket dp;
	public static InetAddress ip;
	public static int port;
	String str,a;
	armstrongS()
	{
		try
		{
			server=new DatagramSocket(5555);
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void run()
	{
		if(Thread.currentThread()==t1){
			try
			{
				dp=new DatagramPacket(rec,rec.length);
				server.receive(dp);
				ip=dp.getAddress();
				port=dp.getPort();
				str=new String(rec).trim();
				System.out.println("from client:"+str);
				int num=Integer.valueOf(str);
				int number=num;
				int sum=0,dig;
				while(num>0)
				{
					dig=num%10;
					sum+=dig*dig*dig;
					num=num/10;
				}
				if(sum==number)
					a="The number is amstrong";
				else
					a="Sum of cube of its digit :"+String.valueOf(sum);
				send=a.getBytes();
				dp=new DatagramPacket(send,send.length,ip,port);
				server.send(dp);					
			}
			catch(Exception e)
			{
				System.out.println("error :"+e);
			}
		}
	}
	public static void main(String args[])
	{
		armstrongS s=new armstrongS();
		s.t1=new Thread(s);
		s.t2=new Thread(s);
		s.t1.start();
		s.t2.start();
	}
}
