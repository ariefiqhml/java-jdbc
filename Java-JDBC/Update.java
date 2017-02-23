import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Update extends Frame implements ActionListener {
	
	JLabel tajuk2, Lname, Lnomatrik, Lprogram, Lphone, jabatan, phone, Lsearch;
	JTextField Tname, Tmatric, Tprogram, Tphone, Tsearch;
	JButton Bupdate, Bsearch;
	
	Connection con;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	public Update(){
		setLayout(new FlowLayout());
		Lsearch = new JLabel("Insert Name : ");
		Tsearch = new JTextField(10);
		Bsearch = new JButton("SEARCH");
		add(Lsearch); add(Tsearch); add(Bsearch);
		Bupdate = new JButton("UPDATE");
		Lname =new JLabel("Name : "); Tname = new JTextField(25);
		Lnomatrik = new JLabel("Register Number : "); Tmatric = new JTextField(25);
		Lprogram = new JLabel("Program : "); Tprogram = new JTextField(25);
		Lphone = new JLabel("Phone Number : "); Tphone = new JTextField(25);
		
		add(Lname);add(Tname);add(Lnomatrik);add(Tmatric);
		add(Lprogram);add(Tprogram);add(Lphone);add(Tphone);
		add(Bupdate);	
		Bsearch.addActionListener(this);
		Bupdate.addActionListener(this);
		setSize(325,350); setVisible(true); setTitle("Update Page"); setLocationRelativeTo(null);
		addWindowListener(new WindowEventHandler());
		
	}
	public static void main(String[] args) {
	new Update();
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
					Tname.setEditable(true);
					Tmatric.setText(rs.getString("matric"));
					Tmatric.setEditable(true);
					Tprogram.setText(rs.getString("program"));
					Tprogram.setEditable(true);
					Tphone.setText(rs.getString("nohp"));
					Tphone.setEditable(true);
					
					JOptionPane.showMessageDialog(null, "Data Successfully Retrive", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Data not Found");
				}
				con.close();
				st.close();
				rs.close();
			}catch(Exception ei){
				System.out.println("SQL code does not execute");
			}
		}else if(pilihan == Bupdate ){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/access","root","");
				st=con.createStatement();
				String sql = "update daftar set name=?,matric=?,program=?,nohp=? where name=?" ;
				pst = con.prepareStatement(sql);
				
				pst.setString(1,Tname.getText());
				pst.setString(2,Tmatric.getText());
				pst.setString(3,Tprogram.getText());
				pst.setString(4,Tphone.getText());
				pst.setString(5,Tname.getText());
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Data Successfully Update", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				
				con.close();
				pst.close();
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
