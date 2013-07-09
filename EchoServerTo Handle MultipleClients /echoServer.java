import java.io.*;
import java.net.*;

public class echoServer {

	public static void main(String args[]) {
		ServerSocket echoServer = null;
		int i = 0;
		System.out.println("Waiting for request under port 8888");
		try {
			echoServer = new ServerSocket(8888);
			while (true) {
				Socket clientRequest = echoServer.accept();
				new ThreadedServerHandler(clientRequest, i++);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}