import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends Frame implements ActionListener{
	
	JButton btnInsert = new JButton("INSERT");
	JButton btnDelete = new JButton("DELETE");
	JButton btnUpdate = new JButton("UPDATE");
	JButton btnSearch = new JButton("SEARCH");
	JButton btnLogout = new JButton("LOGOUT");
		
	
	public void getNama(String nama){
		JPanel panel1 = new JPanel();
		String name = nama;		
		JLabel welcome = new JLabel("Welcome: "+name);
		panel1.add(welcome);
		add(panel1,BorderLayout.NORTH);
	}
	
	
	public Menu(){	
			
		JPanel panel = new JPanel();
		add(panel,BorderLayout.CENTER);
		
		panel.add(btnInsert); panel.add(btnDelete); panel.add(btnUpdate); panel.add(btnSearch); panel.add(btnLogout);
		btnInsert.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnSearch.addActionListener(this);
		btnLogout.addActionListener(this);
		
	
		setTitle("Menu");
		setSize(500,100);
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new WindowEventHandler());
		
	}

	
	public static void main(String args[]){	
		new Menu();
	
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnLogout){
			
			int n = JOptionPane.showConfirmDialog(
				    null,
				    "ARE YOU TO LOGOUT ?",
				    "EXIT			",
				    JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
		else if(ae.getSource() == btnInsert){
			new Insert();
		}
		else if(ae.getSource() == btnUpdate){
			new Update();
		}
		else if(ae.getSource() == btnDelete){
			new Delete();
		}else if(ae.getSource() == btnSearch){
			new Search();
		}
		
	}
	class WindowEventHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			int n = JOptionPane.showConfirmDialog(
				    null,
				    "ARE YOU SURE TO EXIT ?",
				    "EXIT ?",
				    JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
		}
	}

