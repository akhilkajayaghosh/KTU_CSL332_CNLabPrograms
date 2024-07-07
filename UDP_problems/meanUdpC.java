import java.util.*;
import java.io.*;
import java.net.*;
public class meanUdpC
{
	public static void main(String args[]) throws Exception
	{
		DatagramSocket client=new DatagramSocket();
		Scanner sc=new Scanner(System.in);
		System.out.println("No.of elements");
		int n=sc.nextInt();
		int arr[]=new int[50];
		StringBuilder sb=new StringBuilder();
		System.out.println("Enter the elements");
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
			sb.append(arr[i]).append(",");
		}
		String str=sb.toString();
		byte[] send=str.getBytes();
		InetAddress ip=InetAddress.getLocalHost();
		DatagramPacket dp=new DatagramPacket(send,send.length,ip,5555);
		client.send(dp);
		byte []rec=new byte[50];
		dp=new DatagramPacket(rec,rec.length);
		client.receive(dp);
		String a=new String(rec);
		System.out.println("Mean="+a);
		client.close();
	}
}
