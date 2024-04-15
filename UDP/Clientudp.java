import java.io.*;import java.net.*;
public class Clientudp extends Thread
{
Thread t1,t2;
DatagramSocket client;
DatagramPacket dp;
BufferedReader br;
byte[] send=new byte[20];
byte[] rec=new byte[20];
InetAddress ip;
String str,a;
Clientudp()
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
while(true)
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
}
public static void main(String args[])
{
Clientudp c=new Clientudp();
c.t1=new Thread(c);
c.t2=new Thread(c);
c.t1.start();
c.t2.start();
}
}
