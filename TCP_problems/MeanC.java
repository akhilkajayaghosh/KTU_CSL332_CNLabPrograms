/*Write a program for client server communication using TCP where client
sends an array of integers to the server. The Server should calculate and send back
the arithmetic mean which should be displayed in client.*/
    
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MeanC {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Socket client = new Socket("127.0.0.1", 5555);
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Enter the number of elements in the array:");
            int n = sc.nextInt();

            System.out.println("Enter " + n + " numbers:");
            StringBuilder numbersBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                numbersBuilder.append(num).append(",");
            }
            output.println(numbersBuilder.toString());

            String response = input.readLine();
            System.out.println("Received from server: " + response);
            
            //client.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
