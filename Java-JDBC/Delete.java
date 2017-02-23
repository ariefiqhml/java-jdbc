import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Delete extends Frame implements ActionListener {
	
	JLabel  tajuk2, Lname, Lnomatrik, Lprogram, Lphone, jabatan, phone, Lsearch;
	JTextField Tname, Tmatric, Tprogram, Tphone, Tsearch;
	JButton Bdelete, Bsearch;
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	public Delete(){
		setLayout(new FlowLayout());
		Lsearch = new JLabel("Insert Name : ");
		Tsearch = new JTextField(10);
		Bsearch = new JButton("SEARCH");
		add(Lsearch); add(Tsearch); add(Bsearch);
		Bdelete = new JButton("DELETE");
		Lname =new JLabel("Name : "); Tname = new JTextField(25);
		Lnomatrik = new JLabel("Register Number : "); Tmatric = new JTextField(25);
		Lprogram = new JLabel("Program : "); Tprogram = new JTextField(25);
		Lphone = new JLabel("Phone Number : "); Tphone = new JTextField(25);
		

		add(Lname);add(Tname);add(Lnomatrik);add(Tmatric);
		add(Lprogram);add(Tprogram);add(Lphone);add(Tphone);
		add(Bdelete).setVisible(false);;
		
		Bsearch.addActionListener(this);
		Bdelete.addActionListener(this);
		setSize(325,350); setVisible(true); setTitle("Delete Page");setLocationRelativeTo(null);
		addWindowListener(new WindowEventHandler());
		
	}
	

	public static void main(String[] args) {
		new Delete();
	}



	public void actionPerformed(ActionEvent e) {
		Object pilihan =e.getSource();
		if(pilihan == Bsearch){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/access","root","");
				st =con.createStatement();
				rs=st.executeQuery("select * from daftar where name='"+Tsearch.getText()+"'");
				if(rs.next()){
					Tname.setText(rs.getString("name"));
					Tname.setEditable(false);
					Tmatric.setText(rs.getString("matric"));
					Tmatric.setEditable(false);
					Tprogram.setText(rs.getString("program"));
					Tprogram.setEditable(false);
					Tphone.setText(rs.getString("nohp"));
					Tphone.setEditable(false);
					
					JOptionPane.showMessageDialog(null, "Data Successfully Retrive", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					Bdelete.setVisible(true);
					
					con.close();
					st.close();
					rs.close();
				}else{
					JOptionPane.showMessageDialog(null, "Data not Found");
				}
				
			}catch(Exception ei){
				System.out.println("SQL code does not execute");
			}
		}
		if(pilihan ==  Bdelete){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/access","root","");
				st=con.createStatement();
				 st.execute("DELETE from daftar where name='"+Tsearch.getText()+"'"); //use executeUpdate to delete
				 
				 JOptionPane.showMessageDialog(null, "Data Successfully Delete", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				 
				 Tname.setText("");
				 Tmatric.setText("");
				 Tprogram.setText("");
				 Tphone.setText("");
				 
				 Bdelete.setVisible(false);
				 
				 con.close();
				 st.close();
				 rs.close();
					
			}catch(Exception ei){
				System.out.println("SQL code does not execute");
			}
		}
	}
	class WindowEventHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			setVisible(false);
		}
	}
	
}
