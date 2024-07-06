import java.io.*;
import java.net.*;
public class PalS {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5555);
            while (true) {
                
                Socket client = server.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                String str = input.readLine();
                String res = isPallindrome(str);
                
                output.println(res);

                client.close();
                server.close();
            }
        } catch (Exception e) {
            System.out.println("Error:"+e);
        }
    }

    private static String isPallindrome(String str) {
        String rev=reverse(str);
        if(str.compareTo(rev)==0)
        {
            return("This is a pallindrome");
        }
        else
        {
            return rev;
        }
    }
    private static String reverse(String str)
    {
        int k=str.length();
        StringBuilder rev=new StringBuilder();
        for(int i=k-1;i>=0;i--)
        {
            rev.append(str.charAt(i));
        }
        return rev.toString();
    }
}
