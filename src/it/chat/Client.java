package it.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Client {
	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private ClientUi ui;
	private final int port = 10000;
	private boolean connected = false;
	
	public Client()	{
		ui = new ClientUi();
		ui.getConnect().addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!connected) {
						ui.getError().setText(" ");
						connect();
					}
				}catch (NullPointerException e1) {
					ui.getError().setText(e1.toString() + " non è possibile inviare messaggi!");
				}
			}
		});
	}
	
	public void connect() {
		ui.getButton().addActionListener(new ActionListener() {
			String msg = "";
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					msg = ui.getTxt().getText();
					out.writeUTF(msg);
					out.flush();
					ui.getTxt().setText(" ");
				}catch (NullPointerException e1) {
					ui.getError().setText(e1.toString() + " non è possibile inviare messaggi!");
				}
				catch (IOException e1) {
					ui.getError().setText(e1.toString());
				}
			}
		});
		
		Thread t = new Thread(new Runnable() {

		public void run() {
			try {
				clientSocket = new Socket("127.0.0.1", port);
				ui.getError().setText("Client connesso al server sulla porta:" + port);
				connected = true;
				in = new DataInputStream(clientSocket.getInputStream());
				out = new DataOutputStream(clientSocket.getOutputStream());
				String msgin = "";
				
				while (!msgin.equals("bye")) {
					msgin = in.readUTF();
					ui.getPanel().setText(ui.getPanel().getText() + "\nServer: " + msgin);
				}
				close();
			}
			catch(EOFException e) {
				run();
			}
			catch (IOException e) {
				connected = false;
				ui.getError().setText(e.toString());
			}
		}
	});
		t.start();
}
	
	public void close() {
		try {
			out.close();
			in.close();
			clientSocket.close();
			connected = false;
		} catch (IOException e) {
			ui.getError().setText(e.toString());
		}
		ui.getError().setText("Client disconnesso con successo!");
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
	
	public boolean getState() {
		return connected;
	}
	
	public void SetState(boolean state) {
		this.connected = state;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Client client = new Client();
	}
}
