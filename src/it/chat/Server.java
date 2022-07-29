package it.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server{
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ServerUi ui;
	private DataInputStream in;
	private DataOutputStream out;
	private boolean running = false;
	private final int port = 10000;
	
	public Server() {
		ui = new ServerUi();
		
		ui.getConnect().addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!running) {
						ui.getError().setText(" ");
						start();
					}
					else {
						ui.getError().setText(" ");
						close();
					}
				}catch (NullPointerException e1) {
					try {
						running = false;
						serverSocket.close();
						ui.getError().setText(" Server fermato con successo");
						
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
	}
	
	public void start() {
		ui.getButton().addActionListener(new ActionListener() {
			String msg = "";
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					msg = ui.getTxt().getText();
					out.writeUTF(msg);
					ui.getTxt().setText(" ");
				}
				catch (NullPointerException e1) {
					ui.getError().setText(e1.toString() + " non è possibile inviare messaggi!");
				} 
				
				catch (IOException e1) {
					ui.getError().setText(e1.toString());
				}
			}
		});
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(port);
					running = true;
					while(true) {
						ui.getError().setText("Server in ascolto sulla porta: " + port);
						clientSocket = serverSocket.accept();
						in = new DataInputStream(clientSocket.getInputStream());
						out = new DataOutputStream(clientSocket.getOutputStream());
						out.flush();
						ui.getError().setText("New user connected " + clientSocket);
						String msgin = " ";
						
						while (!msgin.equals("bye")) {
							msgin = in.readUTF();
							ui.getPanel().setText(ui.getPanel().getText() + "\nClient: " + msgin);
						}
						close();
					}
				}
				catch(EOFException e) {
					run();
				}
				catch (IOException e) {
					//ui.getError().setText(e.toString());
				}
			}
		});
		t.start();
	}

	public void close() {
		try {
			out.close();
			in.close();
			serverSocket.close();
			clientSocket.close();
			running = false;
		} catch (IOException e) {
			ui.getError().setText(e.toString());
		}
		ui.getError().setText(" Server disconnesso con successo!");
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}

	public int getPort() {
		return port;
	}
	
	public static void main(String[] args) {
		Server server  = new Server();
	}
}
