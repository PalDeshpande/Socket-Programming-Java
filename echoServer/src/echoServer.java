import java.io.*;
import java.net.*;

public class echoServer {
	public static void main(String args[]) {

		ServerSocket echoServer = null;
		String inputLine;
		PrintStream os;
		Socket clientSocket = null;

		System.out.println("Waiting for request under port 8888");
		try {
			echoServer = new ServerSocket(8888);
		} catch (IOException e) {
			System.out.println(e);
		}
		// Create a socket object from the ServerSocket to listen and accept
		// connections.
		try {
			clientSocket = echoServer.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());

			while ((inputLine = in.readLine()) != null) {

				// Handle input as 'Quit'
				if (inputLine.equals("Quit")) {
					os.close();
					in.close();
					clientSocket.close();
					echoServer.close();
					System.exit(1);
				} else {
					System.out.println("Input [" + inputLine + "] come in");
					os.println(inputLine);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}