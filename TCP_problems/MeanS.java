/*Write a program for client server communication using TCP where client
sends an array of integers to the server. The Server should calculate and send back
the arithmetic mean which should be displayed in client.*/

import java.io.*;
import java.net.*;
public class MeanS {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5555);
            while (true) {
                
                Socket client = server.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                String Str = input.readLine();
                String[] numbers = Str.split(",");
                int[] num = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    num[i] = Integer.parseInt(numbers[i].trim());
                }
                double mean = Mean(num);
                output.println("Arithmetic Mean: " + mean);

                client.close();
                //server.close();
            }
        } catch (Exception e) {
            System.out.println("Error:"+e);
        }
    }

    private static double Mean(int[] numbers) {
        if (numbers.length == 0) {
           System.err.println("Array is empty");
        }

        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }
}
