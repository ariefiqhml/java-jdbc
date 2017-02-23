import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
	JLabel lName, lPass;
	JTextField tfname = new JTextField(15);
	JPasswordField tfpass = new JPasswordField(15);
	JButton bLogin, bReset;
	String name;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public Login() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/access", "root", "");
			pst = con.prepareStatement("select * from login where username=? and password=?");
			
		
		} catch (Exception ei) {
			System.out.println("Connection Fail");
		}

		GridBagConstraints c = new GridBagConstraints();

		setLayout(new GridBagLayout());
		setTitle("LOG IN SESSION");

		lName = new JLabel("UserName:");
		c.gridx = 0;
		c.gridy = 0;
		add(lName, c);

		lPass = new JLabel("Password:");
		c.gridx = 0;
		c.gridy = 1;
		add(lPass, c);

		c.insets = new Insets(5, 10, 0, 0);

		tfname = new JTextField(20);
		c.gridx = 1;
		c.gridy = 0;
		add(tfname, c);
		tfpass = new JPasswordField(20);
		c.gridx = 1;
		c.gridy = 1;
		add(tfpass, c);

		c.insets = new Insets(50, 40, 20, 90);

		 bReset = new JButton("RESET");
		c.gridx = 1;
		c.gridy = 2;
		add(bReset, c);

		c.insets = new Insets(50, 110, 20, 20);
		bLogin = new JButton("LOGIN");
		c.gridx = 1;
		c.gridy = 2;
		add(bLogin, c);

		setBackground(Color.white);
		setSize(420,220);
		setVisible(true);

		bLogin.addActionListener(this);
		bReset.addActionListener(this);

		
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == bLogin) {
			char[] temp_pwd = tfpass.getPassword();
			String pwd = null;
			pwd = String.copyValueOf(temp_pwd);
			if (tfname.getText().isEmpty() || tfname.getText() == null || pwd.isEmpty() || pwd == null) {
				JOptionPane.showMessageDialog(null, "Username and Passsword Cannot Empty");
			} else {
				if (checkLogin(tfname.getText(), pwd) == true) {
					JOptionPane.showMessageDialog(null, "Success");
					name = JOptionPane.showInputDialog(null, "What is Your name");
					
					Menu f = new Menu();
					f.setVisible(true);
					f.getNama(name);
					setVisible(false);
					
				
				
				}
			}

		} else if (ae.getSource() == bReset) {
			tfname.setText("");
			tfpass.setText("");
		}
	}




	private boolean checkLogin(String uname, String pwd) {
		try {
			pst.setString(1, uname);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Wrong Username or Password");
				return false;
			}
		} catch(Exception e){
			System.out.println("Error while validating" + e);
			return false;
		}
	}

	public static void main(String args[]) {
		new Login();
	}	

}
