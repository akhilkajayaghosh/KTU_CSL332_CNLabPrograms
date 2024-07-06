import java.net.*;
import java.io.*;
import java.util.*;
public class ClientConnection implements Runnable
{
	private Socket clientSocket;
	private int id;
	private BufferedReader in = null;
	public ClientConnection(Socket client, int id)
	{
		this.clientSocket = client;
		this.id = id;
	}
	@Override
	public void run()
	{
		try
		{
			in = new BufferedReader(new InputStreamReader(
			clientSocket.getInputStream()));
			String clientSelection;
			while ((clientSelection = in.readLine()) != null)
			{
				switch (clientSelection)
				{
					case "1":receiveFile();
						break;
					case "2":String outGoingFileName;
						while ((outGoingFileName = in.readLine()) != null)
						{
							sendFile(outGoingFileName);
						} 
						break;
					default: System.out.println("Incorrect command received.");
						break;
				}
				in.close();
				break;
			}
		}
		catch (IOException ex)
		{
		//Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public void receiveFile()
	{
		try
		{
			int bytesRead;
			DataInputStream clientData = new
			DataInputStream(clientSocket.getInputStream());
			String fileName = clientData.readUTF();
			OutputStream output = new FileOutputStream(("received_from_client_" +fileName));
			long size = clientData.readLong();
			byte[] buffer = new byte[1024];
			while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int)Math.min(buffer.length, size)))!= -1)
			{
				output.write(buffer, 0, bytesRead);
				size -= bytesRead;
			}
			output.close();
			clientData.close();
			System.out.println("File "+fileName+" received from client.");
		}
		catch (IOException ex)
		{
			System.err.println("Client error. Connection closed.");
		}
	}
	public void sendFile(String fileName)
	{
		try
		{
			OutputStream os = clientSocket.getOutputStream();
			//Sending file name and file size to the server
			DataOutputStream dos = new DataOutputStream(os);
			//handle file read
			try {
				File myFile = new File(fileName);
				byte[] mybytearray = new byte[(int) myFile.length()];
				FileInputStream fis = new FileInputStream(myFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				//bis.read(mybytearray, 0, mybytearray.length);
				DataInputStream dis = new DataInputStream(bis);
				dis.readFully(mybytearray, 0, mybytearray.length);
				//handle file send over socket
				dos.writeInt(200);
				dos.writeUTF(myFile.getName());
				dos.writeLong(mybytearray.length);
				dos.write(mybytearray, 0, mybytearray.length);
				dos.flush();
				System.out.println("File "+fileName+" sent to client.");
			}
			catch(FileNotFoundException e) {
				dos.writeInt(404);
				dos.writeInt(id);
				System.err.print("File not found!");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
