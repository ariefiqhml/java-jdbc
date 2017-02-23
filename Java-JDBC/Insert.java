import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.sql.*;

public class Insert extends Frame implements ActionListener {
	JLabel tajuk, tajuk2, Lname, Lnomatrik, Lprogram, Lphone, jabatan, phone;
	JTextField Tname, Tmatric, Tprogram, Tphone;
	JButton Bsave, Breset;
	
	Connection con;
	Statement st;
	ResultSet rs;
	String db;
	
	public Insert(){
	
		
		setLayout(new FlowLayout());
		
		Bsave = new JButton("INSERT");
		Breset = new JButton("RESET");
		Lname =new JLabel("Name : "); Tname = new JTextField(25);
		Lnomatrik = new JLabel("Register Number : "); Tmatric = new JTextField(25);
		Lprogram = new JLabel("Program : "); Tprogram = new JTextField(25);
		Lphone = new JLabel("Phone Number : "); Tphone = new JTextField(25);
		
		add(Lname);add(Tname);add(Lnomatrik);add(Tmatric);
		add(Lprogram);add(Tprogram);add(Lphone);add(Tphone);
		add(Bsave);add(Breset);	
		Bsave.addActionListener(this);Breset.addActionListener(this);
		setSize(325,350);setVisible(true); setTitle("Insert Page "); setLocationRelativeTo(null);
		addWindowListener(new WindowEventHandler());
		
	}
	public static void main(String[] args){
		new Insert();

	
	}
	public void actionPerformed(ActionEvent e){
		Object pilihan =e.getSource();
		if(pilihan == Bsave){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/access","root","");
				st= con.createStatement();
				
				 st.executeUpdate("insert into daftar(name,matric,program,nohp) values('"+Tname.getText()+"','"+Tmatric.getText()+"','"+Tprogram.getText()+"','"+Tphone.getText()+"')");
				JOptionPane.showMessageDialog(null, "Data Sucessfully Added","Confirmation",JOptionPane.INFORMATION_MESSAGE);
				
			
			}catch(Exception ei){
				System.out.println("SQL code does not execute");
			}
		}else{
			Tname.setText("");
			Tmatric.setText("");
			Tprogram.setText("");
			Tphone.setText("");
			JOptionPane.showMessageDialog(null," The form already Reset");
		}
	}
	class WindowEventHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			setVisible(false);
		}
}

}
