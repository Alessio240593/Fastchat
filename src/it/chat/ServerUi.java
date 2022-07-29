package it.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ServerUi extends JFrame{
	private JLabel description;
	private JTextArea txt;
	private JButton button;
	private JButton connect;
	private JTextArea panel;
	private JPanel content;
	private JTextArea error;

	public ServerUi() {
		this.setSize(600, 600);
		this.setResizable(false);
		this.setTitle("FASTCHAT");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(createGraphics());
		this.setVisible(true);
	}
	
	public JPanel createGraphics() {
		content = new JPanel();
		content.setLayout(null);
		Border b = BorderFactory.createLineBorder(Color.gray, 15);
		Border blackline = BorderFactory.createTitledBorder(b, "SERVER", 2, 2);
		content.setBorder(blackline);
		
		JLabel title = new JLabel("FASTCHAT", JLabel.CENTER);
		title.setFont(new Font("Verdana", Font.BOLD, 20));
		title.setBounds(50, 0, 500, 80);
		content.add(title);
		
		
		panel = new JTextArea();
		panel.setEditable(false);
		panel.setOpaque(true);
		panel.setBounds(100,80,400, 250);
		panel.setBackground(Color.white);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	    panel.setBorder(border);
	    panel.setFont(new Font("Verdana", Font.PLAIN, 15));
		panel.setLayout(new BorderLayout(600, 350));
		
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setSize(400,250);
		scroll.setBounds(100,80,400, 250);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		content.add(scroll);
		
		txt = new JTextArea();
		txt.setOpaque(true);
		txt.setBounds(100,400, 300, 50);
		txt.setBackground(Color.white);
	    txt.setBorder(border);
		content.add(txt);
		
		description = new JLabel("Inserire il messaggio da inviare:");
		description.setFont(new Font("Verdana", Font.ITALIC, 16));
		description.setBounds(100, 320, 300, 100);
		content.add(description);
		
		button = new JButton("send");
		button.setBounds(400,400, 70, 50);
		content.add(button);
		
		connect = new JButton("start/stop");
		connect.setBounds(470,400, 110, 50);
		content.add(connect);
		
		JLabel description1 = new JLabel("INFO:");
		description1.setFont(new Font("Verdana", Font.BOLD, 11));
		description1.setForeground(Color.red);
		description1.setBounds(40, 487, 50, 50);
		content.add(description1);
		
		error = new JTextArea();
		error.setEditable(false);
		error.setOpaque(true);
		error.setBounds(100,500, 460, 30);
		error.setBackground(Color.white);
		Font font = new Font("Segoe Script", Font.BOLD, 10);
        error.setFont(font);
        error.setForeground(Color.red);
	    error.setBorder(border);
		content.add(error);
		
		return content;
	}

	public JLabel getDescription() {
		return description;
	}

	public void setDescription(JLabel description) {
		this.description = description;
	}

	public JTextArea getTxt() {
		return txt;
	}

	public void setTxt(JTextArea txt) {
		this.txt = txt;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JTextArea getPanel() {
		return panel;
	}

	public void setPanel(JTextArea panel) {
		this.panel = panel;
	}

	public JPanel getContent() {
		return content;
	}

	public void setContent(JPanel content) {
		this.content = content;
	}
	
	
	public JTextArea getError() {
		return error;
	}

	public void setError(JTextArea error) {
		this.error = error;
	}

	public JButton getConnect() {
		return connect;
	}

	public void setConnect(JButton connect) {
		this.connect = connect;
	}
}
