import java.io.*;
import java.net.*;
public class tcpserver extends Thread
{
	Thread t1,t2;
	BufferedReader br,buf;
	PrintWriter pout;
	String str,a;
	ServerSocket server;
	Socket client;
	tcpserver()
	{
		try
		{
			server=new ServerSocket(5555);
			client=server.accept();
			br=new BufferedReader(new InputStreamReader(System.in));
			buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
			pout=new PrintWriter(client.getOutputStream(),true);
		}
		catch(Exception e)
		{
			System.out.println("error :"+e);
		}
	}
	public void run()
	{
	    while(true){
		if(Thread.currentThread()==t1)
		{
			try
			{
				a=buf.readLine();
				System.out.println("from client :"+a);
				//StringBuilder sb=new StringBuilder();
				String numbers[]=a.split(",");
				int sum=0;
				//int num;
				int num[]=new int[numbers.length];
				for(int i=0;i<numbers.length;i++)
				{
					num[i]=Integer.parseInt(numbers[i])
					sum+=num[i];
				}
				String val=String.valueOf(sum);
				pout.println(val);
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
		tcpserver s=new tcpserver();
		s.t1=new Thread(s);
		s.t2=new Thread(s);
		s.t1.start();
		s.t2.start();
	}
}
