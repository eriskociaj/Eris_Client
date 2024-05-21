import java.io.BufferedReader; // For reading text from an input stream
import java.io.IOException; 
import java.io.InputStreamReader; // For reading bytes and decoding them into characters
import java.io.PrintWriter; // For writing formatted representations of objects to a text-output stream
import java.net.Socket; // For creating a client socket to connect to a server
import java.net.UnknownHostException; // For handling errors when the IP address of a host could not be determined
import java.util.Scanner; 

public class Client {
    public static void main(String[] args) {
        // Server's IP address or hostname and port number to connect to
        String hostName = "localhost"; // Typically the server's address
        int portNumber = 8080; // The port on which the server is listening

        try (
            
            Socket socket = new Socket(hostName, portNumber); // Establish a connection to the server
        
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);// Output stream to send data to the server
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Input stream to read data from the server
            
            Scanner scanner = new Scanner(System.in)
        ) {
            String userInput;
            // Prompt the user to enter math expressions to send to the server
            System.out.println("\n\nConnected to the server. Type your math expressions bellow (e.g., '1 + 2') to send. \nYou are allowed to do addition, subtraction, multiplication and division.");
            while (true) {
                System.out.print("Enter expression (or 'quit' to exit): ");
                userInput = scanner.nextLine(); 

                if ("quit".equalsIgnoreCase(userInput)) { 
                    break; 
                }

                out.println(userInput); // Send the user input to the server

                String response = in.readLine(); // Read the server's response
                System.out.println("Server response: " + response); // Display the server's response
            }
        } catch (UnknownHostException e) {
            // Handle error if the host's IP address could not be determined
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            // Handle general input/output errors (e.g., network errors)
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1); 
        }
    }
}