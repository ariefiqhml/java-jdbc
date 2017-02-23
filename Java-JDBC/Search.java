import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Search extends Frame implements ActionListener {
	
	JLabel  tajuk2, Lname, Lnomatrik, Lprogram, Lphone, jabatan, phone, Lsearch;
	JTextField Tname, Tmatric, Tprogram, Tphone, Tsearch;
	JButton  Bsearch;
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	public Search(){
		setLayout(new FlowLayout());
		Lsearch = new JLabel("Insert Name : ");
		Tsearch = new JTextField(10);
		Bsearch = new JButton("SEARCH");
		add(Lsearch); add(Tsearch); add(Bsearch);
		Lname =new JLabel("Name : "); Tname = new JTextField(25);
		Lnomatrik = new JLabel("Register Number : "); Tmatric = new JTextField(25);
		Lprogram = new JLabel("Program : "); Tprogram = new JTextField(25);
		Lphone = new JLabel("Phone Number : "); Tphone = new JTextField(25);
		
		add(Lname);add(Tname);add(Lnomatrik);add(Tmatric);
		add(Lprogram);add(Tprogram);add(Lphone);add(Tphone);

		Bsearch.addActionListener(this);
		setSize(325,350); setVisible(true); setTitle("Search Page "); setLocationRelativeTo(null);
		addWindowListener(new WindowEventHandler());
		
	}
	public static void main(String[] args) {
		new Search();
	}
	public void actionPerformed(ActionEvent e) {
		Object pilihan =e.getSource();
		if(pilihan == Bsearch){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/access","root","");
				st=con.createStatement();
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
	}
	class WindowEventHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			setVisible(false);
		}
	}

}
