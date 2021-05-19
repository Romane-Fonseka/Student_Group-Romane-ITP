package stu;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Cons_ss {

	private JFrame frame;
	private JTextField txtL1;
	private JTextField txtL2;
	private JTextField txtL3;
	private JTextField textFieldGID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cons_ss window = new Cons_ss();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cons_ss() {
		initialize();
		Connect();
		table_load();
	}
	
	 Connection con;
	 PreparedStatement pst;
	 ResultSet rs;
	 private JTable table_cons_ss;
	 
	//insert the db conn
		
		public void Connect() {
			try {
				Class.forName("com.mysql.jdbc.Driver"); //com.mysql.jdbc.Driver
				con = DriverManager.getConnection("jdbc:mysql://localhost/timetablemgt", "root", "root");
			}
			catch(ClassNotFoundException ex){
				
				
			}
			catch(SQLException ex) {
				
			}
			
		}
		
		//table db
		public void table_load() {
			try {
					pst = con.prepareStatement("select * from cons_ss");
					rs = pst.executeQuery();
					table_cons_ss.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Create Conseccutive Sessions");
		frame.setBounds(100, 100, 1552, 718);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Conseccutive Sessions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 645, 467);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblsub = new JLabel("Subject");
		lblsub.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblsub.setHorizontalAlignment(SwingConstants.CENTER);
		lblsub.setBounds(28, 32, 86, 26);
		panel.add(lblsub);
		
		JLabel lblSubjectCode = new JLabel("Subject Code");
		lblSubjectCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSubjectCode.setBounds(300, 32, 102, 26);
		panel.add(lblSubjectCode);
		
		JLabel lblFirstSession = new JLabel("First Session");
		lblFirstSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstSession.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFirstSession.setBounds(28, 98, 102, 26);
		panel.add(lblFirstSession);
		
		JLabel lblSecondSession = new JLabel("Second Session");
		lblSecondSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondSession.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSecondSession.setBounds(28, 167, 114, 26);
		panel.add(lblSecondSession);
		
		JLabel lblThridSession = new JLabel("Thrid Session");
		lblThridSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblThridSession.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThridSession.setBounds(28, 244, 108, 26);
		panel.add(lblThridSession);
		
		JLabel lblGroupId = new JLabel("Group ID");
		lblGroupId.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGroupId.setBounds(42, 321, 86, 26);
		panel.add(lblGroupId);
		
		JLabel lblLecturer1 = new JLabel("Lecturer 1");
		lblLecturer1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecturer1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLecturer1.setBounds(300, 103, 86, 26);
		panel.add(lblLecturer1);
		
		JLabel lblLecturer2 = new JLabel("Lecturer 2");
		lblLecturer2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecturer2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLecturer2.setBounds(300, 167, 86, 26);
		panel.add(lblLecturer2);
		
		JLabel lblLecturer3 = new JLabel("Lecturer 3");
		lblLecturer3.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecturer3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLecturer3.setBounds(300, 249, 86, 26);
		panel.add(lblLecturer3);
		
		JComboBox<String> comboBoxSub = new JComboBox<String>();
		comboBoxSub.setModel(new DefaultComboBoxModel<String>(new String[] {"DS", "ITP", "SE", "NDM"}));
		comboBoxSub.setBounds(159, 34, 75, 22);
		panel.add(comboBoxSub);
		
		JComboBox<String> comboBoxSubCode = new JComboBox<String>();
		comboBoxSubCode.setModel(new DefaultComboBoxModel<String>(new String[] {"IT1010", "IT1020", "IT3010", "IT4010"}));
		comboBoxSubCode.setBounds(448, 34, 75, 22);
		panel.add(comboBoxSubCode);
		
		JComboBox<String> comboBoxLec = new JComboBox<String>();
		comboBoxLec.setModel(new DefaultComboBoxModel<String>(new String[] {"Lec", "Tute", "Lab"}));
		comboBoxLec.setBounds(159, 100, 75, 22);
		panel.add(comboBoxLec);
		
		JComboBox<String> comboBoxTute = new JComboBox<String>();
		comboBoxTute.setModel(new DefaultComboBoxModel<String>(new String[] {"Tute", "Lec", "Lab"}));
		comboBoxTute.setBounds(159, 169, 75, 22);
		panel.add(comboBoxTute);
		
		JComboBox<String> comboBoxLab = new JComboBox<String>();
		comboBoxLab.setModel(new DefaultComboBoxModel<String>(new String[] {"Lab", "Tute", "Lec"}));
		comboBoxLab.setBounds(159, 246, 75, 22);
		panel.add(comboBoxLab);
		
		txtL1 = new JTextField();
		txtL1.setBounds(434, 100, 116, 22);
		panel.add(txtL1);
		txtL1.setColumns(10);
		
		txtL2 = new JTextField();
		txtL2.setColumns(10);
		txtL2.setBounds(436, 169, 114, 22);
		panel.add(txtL2);
		
		txtL3 = new JTextField();
		txtL3.setColumns(10);
		txtL3.setBounds(434, 246, 116, 22);
		panel.add(txtL3);
		
		textFieldGID = new JTextField();
		textFieldGID.setColumns(10);
		textFieldGID.setBounds(139, 323, 116, 22);
		panel.add(textFieldGID);
		
		
		//Save button
		JButton btnNewButton = new JButton("Create Conseccutive Sessions");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//create relavent variables
				String GID, Subj, Sub_code, first_s, lec_1, second_s, lec_2, thrid_s, lec_3;
				//INT
				GID = textFieldGID.getText();
				Subj = comboBoxSub.getActionCommand();
				Sub_code = comboBoxSubCode.getActionCommand();
				first_s = comboBoxLec.getActionCommand();
				lec_1 = txtL1.getText();
				second_s = comboBoxTute.getActionCommand();
				lec_2 = txtL2.getText();
				thrid_s = comboBoxLab.getActionCommand();
				lec_3 = txtL3.getText();
				
				try {
					pst = con.prepareStatement("insert into cons_ss(GID, Subj, Sub_code, first_s, lec_1, second_s, lec_2, thrid_s, lec_3)"
							+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
					
					pst.setString(1, GID);
					Subj = comboBoxSub.getSelectedItem().toString();
					pst.setString(2, Subj);
					Sub_code = comboBoxSubCode.getSelectedItem().toString();
					pst.setString(3, Sub_code);
					first_s = comboBoxLec.getSelectedItem().toString();
					pst.setString(4, first_s);
					pst.setString(5, lec_1);
					second_s = comboBoxTute.getSelectedItem().toString();
					pst.setString(6, second_s);
					pst.setString(7, lec_2);
					thrid_s = comboBoxLab.getSelectedItem().toString();
					pst.setString(8, thrid_s);
					pst.setString(9, lec_3);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added.");
					table_load();
					
					textFieldGID.setText("");
					comboBoxSub.setSelectedItem("");
					comboBoxSubCode.setSelectedItem("");
					comboBoxLec.setSelectedItem("");
					txtL1.setText("");
					comboBoxTute.setSelectedItem("");
					txtL2.setText("");
					comboBoxLab.setSelectedItem("");
					txtL3.setText("");
					
					
				}
				catch(SQLException e1){
					e1.printStackTrace();
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(74, 392, 251, 31);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(670, 13, 864, 499);
		frame.getContentPane().add(scrollPane);
		
		table_cons_ss = new JTable();
		scrollPane.setViewportView(table_cons_ss);
		
		//Delete Button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//using GID
				String GID;
				
				GID = textFieldGID.getText();
				try {
					pst = con.prepareStatement("delete from cons_ss where GID =?");
					
					pst.setString(1, GID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted.");
					table_load();
					
					textFieldGID.setText("");
					comboBoxSub.setSelectedItem("");
					comboBoxSubCode.setSelectedItem("");
					comboBoxLec.setSelectedItem("");
					txtL1.setText("");
					comboBoxTute.setSelectedItem("");
					txtL2.setText("");
					comboBoxLab.setSelectedItem("");
					txtL3.setText("");
					}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(889, 547, 128, 36);
		frame.getContentPane().add(btnDelete);
		
		//Update button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String GID, Subj, Sub_code, first_s, lec_1, second_s, lec_2, thrid_s, lec_3;
				
				GID = textFieldGID.getText();
				Subj = comboBoxSub.getActionCommand();
				Sub_code = comboBoxSubCode.getActionCommand();
				first_s = comboBoxLec.getActionCommand();
				lec_1 = txtL1.getText();
				second_s = comboBoxTute.getActionCommand();
				lec_2 = txtL2.getText();
				thrid_s = comboBoxLab.getActionCommand();
				lec_3 = txtL3.getText();
				
				try {
					pst = con.prepareStatement("update cons_ss set GID=?, Subj=?, Sub_code=?, first_s=?, lec_1=?, "
							+ "second_s=?, lec_2=?, thrid_s=?, lec_3=?");
					
					pst.setString(1, GID);
					Subj = comboBoxSub.getSelectedItem().toString();
					pst.setString(2, Subj);
					Sub_code = comboBoxSubCode.getSelectedItem().toString();
					pst.setString(3, Sub_code);
					first_s = comboBoxLec.getSelectedItem().toString();
					pst.setString(4, first_s);
					pst.setString(5, lec_1);
					second_s = comboBoxTute.getSelectedItem().toString();
					pst.setString(6, second_s);
					pst.setString(7, lec_2);
					thrid_s = comboBoxLab.getSelectedItem().toString();
					pst.setString(8, thrid_s);
					pst.setString(9, lec_3);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated.");
					table_load();
					
					textFieldGID.setText("");
					comboBoxSub.setSelectedItem("");
					comboBoxSubCode.setSelectedItem("");
					comboBoxLec.setSelectedItem("");
					txtL1.setText("");
					comboBoxTute.setSelectedItem("");
					txtL2.setText("");
					comboBoxLab.setSelectedItem("");
					txtL3.setText("");
				
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(712, 547, 128, 36);
		frame.getContentPane().add(btnUpdate);
	}
}
