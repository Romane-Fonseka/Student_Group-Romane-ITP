package stu;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
//import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tag_list_table {

	private JFrame frame;
	private JTextField txtTagName;
	private JTextField txtTagCode;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Object[] columns = {"ID","Subject Name","Subject Code","Related Tags"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tag_list_table window = new Tag_list_table();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Connection connection = null;

	private JComboBox<String> comboBoxRel_Tag;
	protected JTable model;
	private JTable tableTag;
	//private JButton btnNewButton_update;

	/**
	 * Create the application.
	 */
	public Tag_list_table() {
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
						pst = con.prepareStatement("select * from tags");
						rs = pst.executeQuery();
						tableTag.setModel(DbUtils.resultSetToTableModel(rs));
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
		frame.setBounds(100, 100, 1007, 645);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//update button
		JButton btnNewButton_update = new JButton("Update");
		btnNewButton_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableTag.getModel();
				String sub_name, sub_code, rel_tags	;
				
				sub_name = txtTagName.getText();
				sub_code = txtTagCode.getText();
				rel_tags = comboBoxRel_Tag.getSelectedItem().toString();
				
				try {

					pst = con.prepareStatement("update tags set sub_name=?, sub_code=?, rel_tags=?");
					
					pst.setString(1, sub_name);
					pst.setString(2, sub_code);
					pst.setString(3, rel_tags);
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
					
					
					
				model.addRow(new Object[] {txtTagName.getText(), txtTagCode.getText(), comboBoxRel_Tag.getSelectedItem()				
				});
			}
		});
		btnNewButton_update.setBounds(147, 219, 85, 27);
		frame.getContentPane().add(btnNewButton_update);
		
		
		//delete button
		JButton btnNewButton_delete = new JButton("Delete");
		btnNewButton_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableTag.getModel();
				int i = tableTag.getSelectedRow();
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
				if (i>=0) {
					model.removeRow(i);
				}
			}
		});
		btnNewButton_delete.setBounds(398, 222, 85, 27);
		frame.getContentPane().add(btnNewButton_delete);
		
		
		//reset button
		JButton btnNewButton_reset = new JButton("Reset");
		btnNewButton_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTagName.setText(null);
				txtTagCode.setText(null);
				comboBoxRel_Tag.setSelectedIndex(0);
			}
		});
		btnNewButton_reset.setBounds(707, 222, 85, 27);
		frame.getContentPane().add(btnNewButton_reset);
		
		//subName lbl
		JLabel lblNewLabel = new JLabel("Name of Tag");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(84, 312, 100, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//Subname text
		txtTagName = new JTextField();
		txtTagName.setColumns(10);
		txtTagName.setBounds(224, 312, 154, 24);
		frame.getContentPane().add(txtTagName);
		
		//sub code lbl
		JLabel lblNewLabel_1_1 = new JLabel("Tag Code");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(84, 369, 100, 25);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		//sub code text
		txtTagCode = new JTextField();
		txtTagCode.setColumns(10);
		txtTagCode.setBounds(224, 370, 154, 24);
		frame.getContentPane().add(txtTagCode);
		
		//related tag lable
		JLabel lblNewLabel_1 = new JLabel("Related Tags");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel_1.setBounds(84, 423, 100, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		//related tag dropdown
		comboBoxRel_Tag = new JComboBox<String>();
		comboBoxRel_Tag.setModel(new DefaultComboBoxModel<String>(new String[] {"Lecture", "Tutorial", "Practical", "Evolution"}));
		comboBoxRel_Tag.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		comboBoxRel_Tag.setBounds(224, 423, 154, 21);
		frame.getContentPane().add(comboBoxRel_Tag);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 863, 188);
		frame.getContentPane().add(scrollPane);
		
		tableTag = new JTable();
		scrollPane.setViewportView(tableTag);
		

		
	}
}

