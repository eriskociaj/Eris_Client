import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException; // Import UnknownHostException
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost"; // Server IP address or hostname
        int portNumber = 8080; // Port number the server is listening on

        try (Socket socket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            String userInput;
            System.out.println("Connected to the server. Type your math expressions (e.g., '1 + 2') to send:");
            while (true) {
                System.out.print("Enter expression (or 'quit' to exit): ");
                userInput = scanner.nextLine();

                if ("quit".equalsIgnoreCase(userInput)) {
                    break;
                }

                out.println(userInput); // Send the math expression to the server

                String response = in.readLine(); // Read the response from the server
                System.out.println("Server response: " + response);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}