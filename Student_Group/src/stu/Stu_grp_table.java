package stu;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stu_grp_table {

	private JFrame frame;
	public JTable tableStu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stu_grp_table window = new Stu_grp_table();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Connection connection = null;
	private JButton btnNewButton_update;
	private JButton btnNewButton_delete;
	private JButton btnNewButton_reset;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBoxYear;
	private JLabel lblNewLabel_1;
	private JComboBox<String> comboBox_EnrollC;
	private JLabel lblNewLabel_2;
	private JSpinner spinnerGno;
	private JLabel lblNewLabel_3;
	private JSpinner spinnerSubno;
	private JLabel lblNewLabel_4;
	private JTextField txtGID;
	private JLabel lblNewLabel_5;
	private JTextField txtSubID;
	private JScrollPane scrollPane;

	/**
	 * Create the application.
	 */
	public Stu_grp_table() {
		initialize( );
		Connect();
		table_load();
	}
	
	 Connection con;
	 PreparedStatement pst;
	 ResultSet rs;
	 
	 public void Connect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); //com.mysql.jdbc.Driver
				con = DriverManager.getConnection("jdbc:mysql://localhost/timetablemgt", "root","root");//localhost:8080/phpmyadmin/db_structure.php?db=timetablemgt
			}
			catch(ClassNotFoundException ex){
				
				
			}
			catch(SQLException ex) {
				
			}
			
		}
	 
	//table db
		public void table_load() {
			try {
					pst = con.prepareStatement("select * from stu_grp");
					rs = pst.executeQuery();
					tableStu.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
			
		}

	/**
	 * Initialize the contents of the frame.
	 * 				
			
									//group ID
				txtGID.setText("");
				String value = spinnerGno.getModel().getValue().toString();
				txtGID.setText("Y1.S1.G" + value);
				
				//sub-group ID
				txtSubID.setText("");
				String  value1 = spinnerSubno.getModel().getValue().toString();
				txtSubID.setText(value + "." +  value1);
						
						});
					
				}
	 * 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1007, 645);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//update buttopn
		btnNewButton_update = new JButton("Update");
		btnNewButton_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = tableStu.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel)tableStu.getModel();
				
				String AcadY_sem, enroll_c,	G_no, G_ID,	sub_no,	Sub_ID;
				
				AcadY_sem = comboBoxYear.getSelectedItem().toString();
				enroll_c = comboBox_EnrollC.getSelectedItem().toString();
				G_no = spinnerGno.getValue().toString();
				G_ID = txtGID.getText();
				sub_no = spinnerSubno.getValue().toString();
				Sub_ID = txtSubID.getText();
				
				try {

				pst = con.prepareStatement("update stu_grp set AcadY_sem=?, enroll_c=?, G_no=?, G_ID=?, sub_no=?, Sub_ID=? ");
				
				pst.setString(1, AcadY_sem);
				pst.setString(2, enroll_c);
				pst.setString(3, G_no);
				pst.setString(4, G_ID);
				pst.setString(5, sub_no);
				pst.setString(6, Sub_ID);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Updated.");
				//table load
				table_load();
				
				comboBoxYear.setSelectedItem("");
				comboBox_EnrollC.setSelectedItem("");
				spinnerGno.setValue("");
				txtGID.setText("");
				spinnerSubno.setValue("");
				txtSubID.setText("." + txtGID);
				
			}
				catch(SQLException e1) {
				e1.printStackTrace();
			}
				if(i >= 0) {
					model.toString();
					tableStu.setValueAt(comboBoxYear, i, 0);
					tableStu.setValueAt(comboBox_EnrollC, i, 1);
					tableStu.setValueAt(spinnerGno, i, 2);
					tableStu.setValueAt(txtGID, i, 3);
					tableStu.setValueAt(spinnerSubno, i, 4);
					tableStu.setValueAt(txtSubID, i, 5);
					}
				
			}
			
		});
		btnNewButton_update.setBounds(123, 218, 85, 27);
		frame.getContentPane().add(btnNewButton_update);
		
		//delete button 
		btnNewButton_delete = new JButton("Delete");
		btnNewButton_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableStu.getModel();
				int row = tableStu.getSelectedRow();
				//String cell = tableStu.getModel().getValueAt(row, 0);				
				
					try 
					{
						pst = con.prepareStatement("delete from non_overlap where Sub_ID = cell");
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Unable to delete the record");
						table_load();
					} 
					catch (SQLException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (row>=0) {
						model.removeRow(row);
					}
				}
				
				
			
		});
		
		btnNewButton_delete.setBounds(398, 218, 85, 27);
		frame.getContentPane().add(btnNewButton_delete);
		
		
		//reset button
		btnNewButton_reset = new JButton("Reset\r\n");
		btnNewButton_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxYear.setSelectedIndex(0);
				comboBox_EnrollC.setSelectedIndex(0);
				spinnerGno.setValue(null);
				spinnerSubno.setValue(null);;
				txtGID.setText("");
				txtSubID.setText(null);
			}
		});
		btnNewButton_reset.setBounds(702, 218, 85, 27);
		frame.getContentPane().add(btnNewButton_reset);
		
		//year and sem lbl
		lblNewLabel = new JLabel("Academic Year and Semester");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(86, 306, 153, 28);
		frame.getContentPane().add(lblNewLabel);
		
		//year select drop down
		comboBoxYear = new JComboBox<String>();
		comboBoxYear.setModel(new DefaultComboBoxModel<String>(new String[] {"Y1S1", "Y1S2", "Y2S1", "Y2S2", "Y3S1", "Y3S2", "Y4S1", "Y4S2"}));
		comboBoxYear.setBounds(296, 310, 134, 21);
		frame.getContentPane().add(comboBoxYear);
		
		//course lbl
		lblNewLabel_1 = new JLabel("Enrolled Course");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(86, 358, 146, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		//course dropdown
		comboBox_EnrollC = new JComboBox<String>();
		comboBox_EnrollC.setModel(new DefaultComboBoxModel<String>(new String[] {"Information Technology", "Software Engineering", "Cyber Security", "Data Science"}));
		comboBox_EnrollC.setBounds(296, 362, 134, 21);
		frame.getContentPane().add(comboBox_EnrollC);
		
		//group spinner lbl
		lblNewLabel_2 = new JLabel("Group Number");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(86, 419, 146, 21);
		frame.getContentPane().add(lblNewLabel_2);
		
		//group spinner
		spinnerGno = new JSpinner();
		spinnerGno.setBounds(340, 420, 51, 20);
		frame.getContentPane().add(spinnerGno);
		
		//subG lbl
		lblNewLabel_3 = new JLabel("Sub-Group Number");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(86, 460, 146, 28);
		frame.getContentPane().add(lblNewLabel_3);
		
		//subG spinner
		spinnerSubno = new JSpinner();
		spinnerSubno.setBounds(340, 465, 51, 20);
		frame.getContentPane().add(spinnerSubno);
		
		//Gid lbl
		lblNewLabel_4 = new JLabel("Group ID");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(502, 423, 101, 21);
		frame.getContentPane().add(lblNewLabel_4);
		
		//gid text
		txtGID = new JTextField();
		txtGID.setEditable(false);
		txtGID.setColumns(10);
		txtGID.setBounds(653, 420, 96, 24);
		frame.getContentPane().add(txtGID);
		
		//sgid lbl
		lblNewLabel_5 = new JLabel("Sub-Group ID");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setBounds(502, 468, 101, 21);
		frame.getContentPane().add(lblNewLabel_5);
		
		//sgid text
		txtSubID = new JTextField();
		txtSubID.setEditable(false);
		txtSubID.setColumns(10);
		txtSubID.setBounds(653, 465, 96, 24);
		frame.getContentPane().add(txtSubID);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 930, 197);
		frame.getContentPane().add(scrollPane);
		
		tableStu = new JTable();
		scrollPane.setViewportView(tableStu);
	}
}
