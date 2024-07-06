import java.util.*;
import java.io.*;
public class leakyBucket
{
	public static void main(String []args) throws InterruptedException
	{
		int n,store,bucketsize,incoming,outgoing;
		Scanner sc=new Scanner(System.in);
		store=0;
		System.out.println("Enter the bucket size: ");
		bucketsize=sc.nextInt();
		System.out.println("Enter the no.of items: ");
		n=sc.nextInt();	
		System.out.println("Enter the incoming rate: ");
		incoming=sc.nextInt();
		System.out.println("Enter the outgoing rate: ");
		outgoing=sc.nextInt();
		while(n!=0)
		{
			System.out.println("Incoming "+incoming);
			if(incoming<(bucketsize-store))
			{
				store+=incoming;
				System.out.println("Bucket buffer size is"+store+"out of "+bucketsize);
			}
			else
			{
				System.out.println("Packet loss="+(incoming-(bucketsize-store)));
				store=bucketsize;
				System.out.println("Buffer is full!");
			}
			store-=outgoing;
			System.out.println("After outgoing"+store+"packets are left out of"+bucketsize+"in the buffer");
			n--;
			Thread.sleep(300);
		}	
	}
}
