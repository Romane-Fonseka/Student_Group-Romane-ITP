package stu;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Parl_S {

	private JFrame frame;
	private JTable table_parl;
	private JTextField txtGIDs;
	private JTextField txtGID;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parl_S window = new Parl_S();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	public  void table_load() 
	 {
			try 
			{
					pst = con.prepareStatement("select * from parl_ss");
					rs = pst.executeQuery();
					table_parl.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(SQLException e) 
			{
				
				e.printStackTrace();
			}
			
		}

	/**
	 * Create the application.
	 */
	public Parl_S() {
		initialize();
		Connect();
		table_load();
	}
	
	 Connection con;
	 PreparedStatement pst;
	 ResultSet rs;
	 
	 public void Connect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); //com.mysql.jdbc.Driver
				con = DriverManager.getConnection("jdbc :mysql://localhost/timetablemgt", "root", "root"); 
			}
			catch(ClassNotFoundException ex){
				
				
			}
			catch(SQLException ex) {
				
			}
			
		}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Create Paralle Sessions");
		frame.setBounds(100, 100, 1133, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Paralle Sessions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 495, 401);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMod1 = new JLabel("Module 1");
		lblMod1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMod1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMod1.setBounds(12, 34, 109, 25);
		panel.add(lblMod1);
		
		JComboBox<String> comboBoxMod1 = new JComboBox<String>();
		comboBoxMod1.setModel(new DefaultComboBoxModel<String>(new String[] {"SE", "CNSE", "IT", "CN"}));
		comboBoxMod1.setBounds(117, 35, 100, 23);
		panel.add(comboBoxMod1);
		
		JLabel lblMod2 = new JLabel("Module 2");
		lblMod2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMod2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMod2.setBounds(246, 34, 109, 25);
		panel.add(lblMod2);
		
		JComboBox<String> comboBoxMod2 = new JComboBox<String>();
		comboBoxMod2.setModel(new DefaultComboBoxModel<String>(new String[] {"CNSE", "SE", "IT", "CN"}));
		comboBoxMod2.setBounds(348, 35, 100, 23);
		panel.add(comboBoxMod2);
		
		JComboBox<String> comboBoxMod3 = new JComboBox<String>();
		comboBoxMod3.setModel(new DefaultComboBoxModel<String>(new String[] {"IT", "CSNE", "CS", "SE"}));
		comboBoxMod3.setBounds(117, 90, 100, 23);
		panel.add(comboBoxMod3);
		
		JLabel lblMod3 = new JLabel("Module 3");
		lblMod3.setHorizontalAlignment(SwingConstants.CENTER);
		lblMod3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMod3.setBounds(12, 89, 109, 25);
		panel.add(lblMod3);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setHorizontalAlignment(SwingConstants.CENTER);
		lblHours.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHours.setBounds(12, 155, 109, 25);
		panel.add(lblHours);
		
		JSpinner spinnerH = new JSpinner();
		spinnerH.setBounds(150, 156, 67, 22);
		panel.add(spinnerH);
		
		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinutes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMinutes.setBounds(246, 155, 109, 25);
		panel.add(lblMinutes);
		
		JSpinner spinnerM = new JSpinner();
		spinnerM.setBounds(381, 156, 67, 22);
		panel.add(spinnerM);
		
		JButton btnpara_ss = new JButton("Create Parelle Session");
		btnpara_ss.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnpara_ss.setBounds(29, 229, 205, 33);
		panel.add(btnpara_ss);
		
		JLabel lblGID = new JLabel("Group ID");
		lblGID.setHorizontalAlignment(SwingConstants.CENTER);
		lblGID.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGID.setBounds(246, 89, 109, 25);
		panel.add(lblGID);
		
		txtGID = new JTextField();
		txtGID.setColumns(10);
		txtGID.setBounds(348, 90, 116, 22);
		panel.add(txtGID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(519, 14, 581, 417);
		frame.getContentPane().add(scrollPane);
		
		table_parl = new JTable();
		scrollPane.setViewportView(table_parl);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String GID, mod_1, mod_2, mod_3, hrs, min;
				
				GID = txtGID.getText();
				mod_1 = comboBoxMod1.getSelectedItem().toString();
				mod_2 = comboBoxMod2.getSelectedItem().toString();
				mod_3 = comboBoxMod3.getSelectedItem().toString();
				hrs = spinnerH.getValue().toString();
				min = spinnerM.getValue().toString();
				
				try {
					pst = con.prepareStatement("update parl_ss set GID=?, mod_1=?, mod_2=?, mod_3=?, hrs=?, min=? ");
					
					pst.setString(1, GID);
					pst.setString(2, mod_1);
					pst.setString(3, mod_2);
					pst.setString(4, mod_3);
					pst.setString(5, hrs);
					pst.setString(6, min);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated.");
					//table load
					table_load();
					
					txtGID.setText("");
					comboBoxMod1.setSelectedItem("");
					comboBoxMod2.setSelectedItem("");
					comboBoxMod3.setSelectedItem("");
					spinnerH.setValue("");
					spinnerM.setValue("");
					
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(520, 447, 97, 38);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  GIDs;
				
				GIDs = txtGIDs.getText();
				
				try {
					pst = con.prepareStatement("delete from parl_ss where GID=?");
					
					pst.setString(1,GIDs);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted.");
					table_load();
					
					txtGID.setText("");
					comboBoxMod1.setSelectedItem("");
					comboBoxMod2.setSelectedItem("");
					comboBoxMod3.setSelectedItem("");
					spinnerH.setValue("");
					spinnerM.setValue("");
					
					
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(657, 447, 97, 38);
		frame.getContentPane().add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 421, 483, 64);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGIDs = new JLabel("Group ID");
		lblGIDs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGIDs.setHorizontalAlignment(SwingConstants.CENTER);
		lblGIDs.setBounds(33, 32, 68, 19);
		panel_1.add(lblGIDs);
		
		txtGIDs = new JTextField();
		txtGIDs.setBounds(129, 30, 116, 22);
		panel_1.add(txtGIDs);
		txtGIDs.setColumns(10);
	}
}
