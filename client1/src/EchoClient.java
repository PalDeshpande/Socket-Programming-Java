import java.io.*;
import java.net.*;

public class EchoClient {
	public static void main(String[] args) {

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {

			echoSocket = new Socket("127.0.0.1", 333);
			// data stream to the echo socket frm client
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			// data stream from server to the client
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			System.out.println("connected to server");
			System.out.println("Please input something");
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(
					System.in));
			String userInput;

			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				System.out.println("echo: " + in.readLine());

				if (userInput.equals("Quit")) {
					System.out.println("Closing client");
					out.close();
					in.close();
					stdIn.close();
					echoSocket.close();
					System.exit(1);
				}
				System.out.println("Please input something");

			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: Pallavi’s MacBook Pro.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: Pallavi’s MacBook Pro.");
			System.exit(1);
		}

	}

}
