import java.io.*;
import java.util.*;
import java.net.*;
public class meanUdpS
{
	public static void main(String args[]) throws Exception
	{
		DatagramSocket server=new DatagramSocket(5555);
		byte rec[]=new byte[50];
		DatagramPacket dp=new DatagramPacket(rec,rec.length);
		server.receive(dp);
		InetAddress ip=dp.getAddress();
		int port=dp.getPort();
		String str=new String(rec).trim();
		System.out.println("Received from Client"+str);
		String numbers[]=str.split(",");
		int num[]=new int[numbers.length];
		int sum=0;
		for(int i=0;i<numbers.length;i++)
		{
			num[i]=Integer.parseInt(numbers[i]);
			sum+=num[i];
		}
		double mean=(double)sum/numbers.length;
		String a=String.valueOf(mean);
		byte send[]=a.getBytes();
		dp=new DatagramPacket(send,send.length,ip,port);
		server.send(dp);
	}
}
