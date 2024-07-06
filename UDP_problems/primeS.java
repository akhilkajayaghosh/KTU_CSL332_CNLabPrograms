import java.io.*;
import java.net.*;
public class primeS extends Thread
{
	Thread t1,t2;
	BufferedReader br;
	byte[] rec=new byte[20];
	byte[] send=new byte[20];
	DatagramSocket server;
	DatagramPacket dp=null;
	public static InetAddress ip;
	public static int port;
	String str,a;
	primeS()
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
					dp=new DatagramPacket(rec,rec.length);
					server.receive(dp);
					ip=dp.getAddress();
					port=dp.getPort();
					str=new String(rec).trim();
					System.out.println("Received from client:"+str);
					StringBuilder sb=new StringBuilder();
					String numbers[]=str.split(",");
				         int[] num = new int[numbers.length];
				         for (int i = 0; i < numbers.length; i++) {
					  num[i] = Integer.parseInt(numbers[i].trim());
				          }
				          for(int j=0;j<numbers.length;j++)
				          {
				          	int flag=0;
				          	for(int k=2;k<num[j];k++)
				          	{
				          		if(num[j]%k==0)
				          		{
				          			flag=1;break;
				          		}
				          	}
				          	if(flag!=1 && num[j]!=1)
				          	{
				          		sb.append(num[j]).append(" ");
				          	}
				          }
			          	a=sb.toString();
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
	}
	public static void main(String args[])
	{
		primeS s=new primeS();
		s.t1=new Thread(s);
		s.t2=new Thread(s);
		s.t1.start();
		s.t2.start();
	}
}
