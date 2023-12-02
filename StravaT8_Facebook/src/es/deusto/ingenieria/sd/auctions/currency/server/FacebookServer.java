package es.deusto.ingenieria.sd.auctions.currency.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class FacebookServer {

	private static int serverPort;
	private static String DELIMITER = "#";
	public Map<String, String> userMap = new HashMap<>();

	public FacebookServer() {
		initializeData();
	}

	private void initializeData() {
		userMap.put("asier@opendeusto.com", "password1");
		userMap.put("cubillo@opendeusto.com", "password3");
		userMap.put("fletch@opendeusto.com", "password4");
	}

	public void startServer() {
		try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
			System.out.println("Server listening on port " + serverPort);

			while (true) {
				// Wait for a client to connect
				Socket clientSocket = serverSocket.accept();
				System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostAddress());

				// Handle the client's request in a separate thread
				Thread clientHandlerThread = new Thread(() -> handleClient(clientSocket));
				clientHandlerThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleClient(Socket clientSocket) {
		try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

			// Read the request from the client
			String request = in.readUTF();
			System.out.println("Received request: " + request);

			// Process the request based on the command
			boolean isValid = processRequest(request);

			// Send the response back to the client
			out.writeBoolean(isValid);
			System.out.println("Sent response: " + isValid);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the client socket
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean processRequest(String request) {
		// Split the request based on the delimiter
		String[] parts = request.split(DELIMITER);

		// Check the command or identifier (first part of the request)
		String command = parts[0];
		String email = parts[1];

		try {
			// Perform different actions based on the command
			switch (command) {
			case "userValidation":
				return FacebookUserValidation(email);

			case "passwordValidation":
				String password = parts[2];
				return FacebookPasswordValidation(email, password);

			default:
				System.out.println("call does not exist");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean FacebookUserValidation(String email) throws RemoteException {
		if (this.userMap.containsKey(email)) {
			System.out.println("validated: " + email);
			return true;
		}
		return false;
	}

	private boolean FacebookPasswordValidation(String email, String password) throws RemoteException {
		if (!FacebookUserValidation(email)) {
			System.err.println("  # Error in Facebook service: email does not exist");
			return false;
		} else {
			if (this.userMap.get(email).equals(password)) {
				return true;
			} else {
				System.err.println("  # Error in Facebook service: incorrect password for " + email);
				return false;
			}
		}
	}

	public static void main(String[] args) {
		serverPort = Integer.parseInt(args[0]);
		FacebookServer server = new FacebookServer();
		server.startServer();
	}
}
