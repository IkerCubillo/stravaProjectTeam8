package es.deusto.ingenieria.sd.auctions.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.util.StringTokenizer;

import es.deusto.ingenieria.sd.auctions.currency.remote.IFacebookAuthorization;
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
			System.out.println(" - Verifying Facebook email from '" + socket.getInetAddress().getHostAddress() + ":"
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

//package es.deusto.ingenieria.sd.translation.client;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.EOFException;
//import java.io.IOException;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.StringTokenizer;
//import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//
//public class TranslationSocketClient {
//	
//	private String serverIP;
//	private int serverPort;
//	private static String DELIMITER = "#";
//	
//	public TranslationSocketClient (String servIP, int servPort) {
//		serverIP = servIP;
//		serverPort = servPort;
//	}
//	
//	public String sendMessage(String source, String target, String text){
//		String message = source+DELIMITER+target+DELIMITER+text;
//		String translation = null;
//		StringTokenizer tokenizer = null;
//		
//		/**
//		 * NOTE: try-with resources Statement - https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
//		 * Try statement that declares one or more resources. A resource is an object that must be closed after the program is 
//		 * finished with it. The try-with-resources statement ensures that each resource is closed at the end of the statement.
//		 * Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, 
//		 * can be used as a resource.
//		 */
//		//Declaration of the socket to send/receive information to/from the server (an IP and a Port are needed)
//		try (Socket socket = new Socket(serverIP, serverPort);
//			//Streams to send and receive information are created from the Socket
//			DataInputStream in = new DataInputStream(socket.getInputStream());
//			DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
//			
//			//Send request (one String) to the server
//			out.writeUTF(message);
//			System.out.println(" - Sending data to '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + message + "'");
//			
//			//Read response (one String) from the server
//			translation = in.readUTF();			
//			System.out.println(" - Getting translation from '" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "' -> '" + translation + "'");
//			tokenizer = new StringTokenizer(translation, DELIMITER);
//
//		} catch (UnknownHostException e) {
//			System.err.println("# Trans. SocketClient: Socket error: " + e.getMessage());	
//		} catch (EOFException e) {
//			System.err.println("# Trans. SocketClient: EOF error: " + e.getMessage());
//		} catch (IOException e) {
//			System.err.println("# Trans. SocketClient: IO error: " + e.getMessage());
//		}
//		//return translation;
//		
//		return (tokenizer.nextToken().equals("OK")) ? tokenizer.nextToken() : "ERROR"; 	
//	}
//
//	public static void main(String args[]) {
//		
//		if (args.length < 2) {
//			System.err.println(" # Usage: Trans. SocketClient [SERVER IP] [PORT] ");
//			System.exit(1);
//		}
//		
//		TranslationSocketClient client = new TranslationSocketClient(args[0], Integer.parseInt(args[1]));
//		client.sendMessage("en","es","good morning");
//
//		Scanner sc = new Scanner(System.in);
//		while (true) {			
//			System.out.println("From (en, es, etc) or exit to end:");
//			String lang_origin = sc.nextLine();
//			if (lang_origin.equals("exit") ) {
//				break;
//			}
//			System.out.println("To (en, es, etc):");
//			String lang_target = sc.nextLine();
//			System.out.println("Message to translate:");
//			String message = sc.nextLine();
//			client.sendMessage(lang_origin,lang_target,message);
//		
//		}
//		sc.close();
//	}
//}