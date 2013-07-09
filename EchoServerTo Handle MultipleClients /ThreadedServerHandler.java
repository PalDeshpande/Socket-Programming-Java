import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ThreadedServerHandler extends Thread {
	Socket clientOrder = null;
	int clientno = 0;

	ThreadedServerHandler(Socket order, int i) {
		if (order != null) {
			clientOrder = order;
			clientno = i;
			System.out.println("Serving thread for client no:" + clientno);
			start();
		}
	}

	@Override
	public void run() {
		PrintStream os = null;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					clientOrder.getInputStream()));
			os = new PrintStream(clientOrder.getOutputStream());
			String inputLine;
			while ((inputLine = in.readLine()) != null) {

				// Handle input as 'Quit'
				if (inputLine.equals("Quit")) {
					System.out.println("Closing thread for client no:"
							+ clientno);
					os.close();
					in.close();
					clientOrder.close();
					break;
				} else {
					System.out.println("Input [" + inputLine + "] come in");
					os.println(inputLine);
				}
			}
		} catch (IOException e) {
			System.out.println("Stream closed");
		}
	}
}
