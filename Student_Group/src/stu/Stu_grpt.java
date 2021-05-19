package stu;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class Stu_grpt {

	private JFrame frmCreateStudent;
	private JTextField txtGID;
	private JTextField txtSubID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stu_grpt window = new Stu_grpt();
					window.frmCreateStudent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Stu_grpt() {
		
		initialize();
		Connect();
		
	}
	
	 Connection con;
	 PreparedStatement pst;
	 ResultSet rs;
	 
	//insert the db conn
		
			public void Connect() {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); //com.mysql.jdbc.Driver
					con = DriverManager.getConnection("jdbc:mysql://localhost/timetablemgt", "root", "root");
				}
				catch(ClassNotFoundException ex){
					
					
				}
				catch(SQLException ex) {
					
				}
				
			}	 
	 
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize( ) {
		
		frmCreateStudent = new JFrame();
		frmCreateStudent.setTitle("Create Student Group");
		frmCreateStudent.setBounds(100, 100, 830, 504);
		frmCreateStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateStudent.getContentPane().setLayout(null);
		
		//year and semester lable
		JLabel lblNewLabel = new JLabel("Academic Year and Semester");
		lblNewLabel.setBounds(20, 35, 153, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmCreateStudent.getContentPane().add(lblNewLabel);
		
		//year and sem dropdown
		JComboBox<String> comboBoxYear = new JComboBox<>();
		comboBoxYear.setBounds(183, 39, 134, 21);
		comboBoxYear.setModel(new DefaultComboBoxModel<String>(new String[] {"Y1S1", "Y1S2", "Y2S1", "Y2S2", "Y3S1", "Y3S2", "Y4S1", "Y4S2"}));
		frmCreateStudent.getContentPane().add(comboBoxYear);
		
		//course enrolled lable
		JLabel lblNewLabel_1 = new JLabel("Enrolled Course");
		lblNewLabel_1.setBounds(20, 86, 146, 28);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frmCreateStudent.getContentPane().add(lblNewLabel_1);
		
		//course enrolled dropdown
		JComboBox<String> comboBox_EnrollC = new JComboBox<>();
		comboBox_EnrollC.setModel(new DefaultComboBoxModel<String>(new String[] {"Information Technology", "Software Engineering", "Cyber Security", "Data Science"}));
		comboBox_EnrollC.setBounds(183, 90, 134, 21);
		frmCreateStudent.getContentPane().add(comboBox_EnrollC);
		
		
		//Grp ID lable
		JLabel lblNewLabel_2 = new JLabel("Group Number");
		lblNewLabel_2.setBounds(20, 134, 146, 21);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		frmCreateStudent.getContentPane().add(lblNewLabel_2);
		
		// Grp ID numbers 
		JSpinner spinnerGno = new JSpinner();
		spinnerGno.setBounds(231, 135, 51, 20);
		frmCreateStudent.getContentPane().add(spinnerGno);
		
		//subG ID lable
		JLabel lblNewLabel_3 = new JLabel("Sub-Group Number");
		lblNewLabel_3.setBounds(20, 180, 146, 28);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		frmCreateStudent.getContentPane().add(lblNewLabel_3);
		
		//subG ID number
		JSpinner spinnerSubno = new JSpinner();
		spinnerSubno.setBounds(231, 185, 51, 20);
		frmCreateStudent.getContentPane().add(spinnerSubno);
		
		//genarate GID lable
		JLabel lblNewLabel_4 = new JLabel("Group ID");
		lblNewLabel_4.setBounds(341, 134, 101, 21);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		frmCreateStudent.getContentPane().add(lblNewLabel_4);
		
		//genarate sid lable
		JLabel lblNewLabel_5 = new JLabel("Sub-Group ID");
		lblNewLabel_5.setBounds(341, 180, 101, 21);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		frmCreateStudent.getContentPane().add(lblNewLabel_5);
		
		//Gid text
		txtGID = new JTextField();
		txtGID.setEditable(false);
		txtGID.setEnabled(false);
		txtGID.setBounds(439, 134, 96, 24);
		frmCreateStudent.getContentPane().add(txtGID);
		txtGID.setColumns(10);
		
		//SGid text
		txtSubID = new JTextField();
		txtSubID.setEditable(false);
		txtSubID.setEnabled(false);
		txtSubID.setBounds(439, 180, 96, 24);
		frmCreateStudent.getContentPane().add(txtSubID);
		txtSubID.setColumns(10);
		
		//save button
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String AcadY_sem, enroll_c,	G_no, G_ID,	sub_no,	Sub_ID;
				
				AcadY_sem = comboBoxYear.getSelectedItem().toString();
				enroll_c = comboBox_EnrollC.getSelectedItem().toString();
				G_no = spinnerGno.getValue().toString();
				G_ID = txtGID.getText();
				sub_no = spinnerSubno.getValue().toString();
				Sub_ID = txtSubID.getText();
				
				try {

					pst = con.prepareStatement("insert into stu_grp set AcadY_sem=?, enroll_c=?, G_no=?, G_ID=?, sub_no=?, Sub_ID=? ");
					
					pst.setString(1, AcadY_sem);
					pst.setString(2, enroll_c);
					pst.setString(3, G_no);
					pst.setString(4, G_ID);
					pst.setString(5, sub_no);
					pst.setString(6, Sub_ID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Inserted.");
					comboBoxYear.setSelectedItem("");
					comboBox_EnrollC.setSelectedItem("");
					spinnerGno.setValue("");
					txtGID.setText("");
					spinnerSubno.setValue("");
					txtSubID.setText(G_ID + "." );
					
				}
					catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(81, 302, 85, 21);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		frmCreateStudent.getContentPane().add(btnNewButton);
		
		//reset button
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxYear.setSelectedItem("");
				comboBox_EnrollC.setSelectedItem("");
				spinnerGno.setValue(null);
				spinnerSubno.setValue(null);;
				txtGID.setText("");
				txtSubID.setText("");
			}
		});
		btnNewButton_1.setBounds(272, 302, 85, 21);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		frmCreateStudent.getContentPane().add(btnNewButton_1);
		
		//genarate ID transcript
		JButton btnNewButton_2 = new JButton("Genarate ID");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//group ID
				txtGID.setText("");
				String GID = spinnerGno.getModel().getValue().toString();
				txtGID.setText(GID);
				
				//sub-group ID
				txtSubID.setText("");
				String  SubID = spinnerSubno.getModel().getValue().toString();
				txtSubID.setText(GID + "." +  SubID);
		
			}
		});
		btnNewButton_2.setBounds(439, 302, 109, 21);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		frmCreateStudent.getContentPane().add(btnNewButton_2);
			
	}
}
