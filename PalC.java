import java.io.*;
import java.net.*;
public class PalC {
    public static void main(String[] args) {

        try {
            Socket client = new Socket("127.0.0.1", 5555);
             BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter output = new PrintWriter(client.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(" Enter the string:");

            String str = userInput.readLine();

            output.println(str);

            String response = input.readLine();
            System.out.println("Received from server: " + response);
        } catch (Exception e) {
            System.out.println("Error:"+e);
        }
    }
}
