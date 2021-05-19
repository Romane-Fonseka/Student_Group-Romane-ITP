package stu;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.DriverManager;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Non_Overlapping {

	private JFrame frame;
	private JTable table_non_overlap;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Non_Overlapping window = new Non_Overlapping();
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
	public Non_Overlapping() {
		initialize();
		Connect();
		table_load();
	}
	
 Connection con;
 PreparedStatement pst;
 ResultSet rs;
 private JTextField txtCatIDs;
 
 
	//insert the db conn
	
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
				pst = con.prepareStatement("select * from non_overlap");
				rs = pst.executeQuery();
				table_non_overlap.setModel(DbUtils.resultSetToTableModel(rs));
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
		frame.setTitle("Create Non - Overlapping Session");
		frame.setBounds(100, 100, 1183, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Non - Overlapping Session", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(31, 30, 502, 397);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblcat1 = new JLabel("Category 01");
		lblcat1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblcat1.setHorizontalAlignment(SwingConstants.CENTER);
		lblcat1.setBounds(12, 42, 92, 27);
		panel.add(lblcat1);
		
		JLabel lblcat2 = new JLabel("Category 02");
		lblcat2.setHorizontalAlignment(SwingConstants.CENTER);
		lblcat2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblcat2.setBounds(257, 42, 92, 27);
		panel.add(lblcat2);
		
		JLabel lblcat3 = new JLabel("Category 03\r\n");
		lblcat3.setHorizontalAlignment(SwingConstants.CENTER);
		lblcat3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblcat3.setBounds(12, 125, 92, 27);
		panel.add(lblcat3);
		
		JLabel lblcat4 = new JLabel("Category 04");
		lblcat4.setHorizontalAlignment(SwingConstants.CENTER);
		lblcat4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblcat4.setBounds(257, 125, 92, 27);
		panel.add(lblcat4);
		
		JComboBox<String> comboBoxCat1 = new JComboBox<String>();
		comboBoxCat1.setModel(new DefaultComboBoxModel<String>(new String[] {"EAP", "MAD", "CS", "OOP"}));
		comboBoxCat1.setBounds(129, 44, 109, 25);
		panel.add(comboBoxCat1);
		
		JComboBox<String> comboBoxCat2 = new JComboBox<String>();
		comboBoxCat2.setModel(new DefaultComboBoxModel<String>(new String[] {"MAD", "EAP", "CS", "OOP"}));
		comboBoxCat2.setBounds(381, 44, 109, 24);
		panel.add(comboBoxCat2);
		
		JComboBox<String> comboBoxCat3 = new JComboBox<String>();
		comboBoxCat3.setModel(new DefaultComboBoxModel<String>(new String[] {"CS", "OOP", "MAD", "EAP"}));
		comboBoxCat3.setBounds(129, 125, 109, 24);
		panel.add(comboBoxCat3);
		
		JComboBox<String> comboBoxCat4 = new JComboBox<String>();
		comboBoxCat4.setModel(new DefaultComboBoxModel<String>(new String[] {"OOP", "CS", "EAP", "MAD"}));
		comboBoxCat4.setBounds(381, 127, 109, 25);
		panel.add(comboBoxCat4);
		
		//Save Button
		JButton btnNonOverlap = new JButton("Create Non - Overlapping Session");
		btnNonOverlap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//create the relevent variables
				String Category1, Category2, Category3, Category4;
				//int
				Category1 = comboBoxCat1.getActionCommand();
				Category2 = comboBoxCat2.getActionCommand();
				Category3 = comboBoxCat3.getActionCommand();
				Category4 = comboBoxCat4.getActionCommand();
				
				//insert code
				
				try {
						pst = con.prepareStatement("insert into non_overlap(Category1, Category2, Category3, Category4)"
								+ "values(?, ?, ?, ?)");
						
						Category1 = comboBoxCat1.getSelectedItem().toString();
						pst.setString(1, Category1);
						Category2 = comboBoxCat2.getSelectedItem().toString();
						pst.setString(2, Category2);
						Category3 = comboBoxCat3.getSelectedItem().toString();
						pst.setString(3, Category3);
						Category4 = comboBoxCat4.getSelectedItem().toString();
						pst.setString(4, Category4);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Added.");
						//table load
						table_load();
						comboBoxCat1.setSelectedItem("");
						comboBoxCat2.setSelectedItem("");
						comboBoxCat3.setSelectedItem("");
						comboBoxCat4.setSelectedItem("");
						comboBoxCat1.requestFocus();
						
						}
				catch(SQLException el){
					
					el.printStackTrace();
				}
				
			}
		});
		btnNonOverlap.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNonOverlap.setBounds(25, 224, 273, 36);
		panel.add(btnNonOverlap);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(556, 31, 576, 397);
		frame.getContentPane().add(scrollPane);
		
		table_non_overlap = new JTable();
		scrollPane.setViewportView(table_non_overlap);
		
		//update button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Category1, Category2, Category3, Category4;
				
				Category1 = comboBoxCat1.getActionCommand();
				Category2 = comboBoxCat2.getActionCommand();
				Category3 = comboBoxCat3.getActionCommand();
				Category4 = comboBoxCat4.getActionCommand();
				
				try {
					pst = con.prepareStatement("update non_overlap set Category1=?, Category2=?, Category3=?, Category4=?");
					
					Category1 = comboBoxCat1.getSelectedItem().toString();
					pst.setString(1, Category1);
					Category2 = comboBoxCat2.getSelectedItem().toString();
					pst.setString(2, Category2);
					Category3 = comboBoxCat3.getSelectedItem().toString();
					pst.setString(3, Category3);
					Category4 = comboBoxCat4.getSelectedItem().toString();
					pst.setString(4, Category4);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated.");
					//table load
					table_load();
					
					comboBoxCat1.setSelectedItem("");
					comboBoxCat2.setSelectedItem("");
					comboBoxCat3.setSelectedItem("");
					comboBoxCat4.setSelectedItem("");
					comboBoxCat1.requestFocus();
					
				}catch(SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(584, 464, 97, 37);
		frame.getContentPane().add(btnUpdate);
		
		//Delete Button
		JButton btnDelete = new JButton("Delete\r\n");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//using Cat_ID
				String Cat_ID;
				Cat_ID = txtCatIDs.getText();
				
				try {
					pst = con.prepareStatement("delete from non_overlap where Cat_ID=?");
					
					pst.setString(1, Cat_ID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted");
					table_load();
					
					comboBoxCat1.setSelectedItem("");
					comboBoxCat2.setSelectedItem("");
					comboBoxCat3.setSelectedItem("");
					comboBoxCat4.setSelectedItem("");
					comboBoxCat1.requestFocus();
					
				}
				catch(SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(774, 464, 97, 37);
		frame.getContentPane().add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(31, 454, 451, 83);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCat_ID = new JLabel("Category ID");
		lblCat_ID.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCat_ID.setHorizontalAlignment(SwingConstants.CENTER);
		lblCat_ID.setBounds(29, 29, 97, 26);
		panel_1.add(lblCat_ID);
		
		txtCatIDs = new JTextField();
		txtCatIDs.setBounds(150, 31, 116, 22);
		panel_1.add(txtCatIDs);
		txtCatIDs.setColumns(10);
	}
}
