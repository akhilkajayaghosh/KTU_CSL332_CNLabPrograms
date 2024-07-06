import java.util.*;
import java.net.*;
import java.io.*;
public class FileServer
{
	private static ServerSocket serverSocket;
	private static Socket clientSocket = null;
	public static void main(String[] args) throws IOException
	{
		Random r = new Random();
		try
		{
			serverSocket = new ServerSocket(4444);
			System.out.println("Server started.");
		}
		catch (Exception e)
		{
			System.err.println("Port already in use.");
			System.exit(1);
		}
		while (true)
		{
			try
			{
				clientSocket = serverSocket.accept();
				System.out.println("Accepted connection : " + clientSocket);
				Thread t = new Thread(new ClientConnection(clientSocket,
				r.nextInt()));
				t.start();
			}
			catch (Exception e)
			{
				System.err.println("Error in connection attempt.");
			}
		}
	}
}
