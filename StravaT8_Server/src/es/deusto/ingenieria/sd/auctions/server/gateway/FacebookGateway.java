package es.deusto.ingenieria.sd.auctions.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import es.deusto.ingenieria.sd.auctions.server.MainProgram;

public class FacebookGateway implements IGateway {

	private static FacebookGateway instance;

	private String serverIP;
	private int serverPort;
	private static String DELIMITER = "#";

	public FacebookGateway() {
		serverPort = MainProgram.getSocketPort();
		serverIP = MainProgram.getServerIP();
	}

	public boolean userValidation(String email) {
		System.out.println("/////////////Marker INFINITO");
		boolean answer = true;

		try (Socket socket = new Socket(serverIP, serverPort);
				// Streams to send and receive information are created from the Socket
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

			// Send request (one String) to the server
			out.writeUTF("userValidation" + DELIMITER + email);
			System.out.println(" - Verifying Facebook email from '" + socket.getInetAddress().getHostAddress() + ":"
					+ socket.getPort() + "' -> '" + email + "'");

			// Read response (one boolean) from the server
			answer = in.readBoolean();
			System.out.println(" - Recieving verification from '" + socket.getInetAddress().getHostAddress() + ":"
					+ socket.getPort() + "' -> '" + answer + "'");
			return answer;
		} catch (UnknownHostException e) {
			System.err.println("# Trans. SocketClient: Socket error: " + e.getMessage());
			return false;
		} catch (EOFException e) {
			System.err.println("# Trans. SocketClient: EOF error: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.err.println("# Trans. SocketClient: IO error: " + e.getMessage());
			return false;
		}
	}

	public boolean passwordValidation(String email, String password) {

		boolean answer = false;

		try (Socket socket = new Socket(serverIP, serverPort);
				// Streams to send and receive information are created from the Socket
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

			// Send request (one String) to the server
			out.writeUTF("passwordValidation" + DELIMITER + email + DELIMITER + password);
			System.out.println(" - Verifying Facebook password from '" + socket.getInetAddress().getHostAddress() + ":"
					+ socket.getPort() + "' -> '" + email + "'");

			// Read response (one String) from the server
			answer = in.readBoolean();
			System.out.println(" - Recieving verification from '" + socket.getInetAddress().getHostAddress() + ":"
					+ socket.getPort() + "' -> '" + answer + "'");

		} catch (UnknownHostException e) {
			System.err.println("# Trans. SocketClient: Socket error: " + e.getMessage());
			return false;
		} catch (EOFException e) {
			System.err.println("# Trans. SocketClient: EOF error: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.err.println("# Trans. SocketClient: IO error: " + e.getMessage());
			return false;
		}
		return answer;
	}

	public static FacebookGateway getInstance() {
		if (instance == null) {
			instance = new FacebookGateway();
		}
		return instance;
	}
}