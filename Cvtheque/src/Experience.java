import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Experience extends JFrame {

	private JPanel contentPane;
	private JTextField txt_postExp;
	private JTextField txt_etabExp;
	Connexion cn = new Connexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Experience frame = new Experience();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Experience() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Poste :");
		lblNewLabel.setBounds(42, 155, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Etablissement :");
		lblNewLabel_1.setBounds(42, 226, 94, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Début :");
		lblNewLabel_2.setBounds(42, 296, 108, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ville :");
		lblNewLabel_3.setBounds(42, 412, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblExperience = new JLabel("Exp\u00E9rience professionnelle : ");
		lblExperience.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblExperience.setBounds(291, 40, 326, 56);
		contentPane.add(lblExperience);
		
		txt_postExp = new JTextField();
		txt_postExp.setBounds(184, 152, 251, 22);
		contentPane.add(txt_postExp);
		txt_postExp.setColumns(10);
		
		txt_etabExp = new JTextField();
		txt_etabExp.setBounds(184, 223, 251, 22);
		contentPane.add(txt_etabExp);
		txt_etabExp.setColumns(10);
		
		
		///
		
		
		JComboBox<String> comboBoxVilleE = new JComboBox<String>();
		comboBoxVilleE.addItem("Ville");
		comboBoxVilleE.addItemListener(
		        new ItemListener() {
		            @Override
		            public void itemStateChanged(ItemEvent e) {
		                 // now there's nothing happen when we select the first item
		                if(comboBoxVilleE.getSelectedIndex()>0){ 
		                    System.out.println("YOU CLICK INDEX-"+comboBoxVilleE.getSelectedItem());
		               }
		            }
		        }
		        );     
		for(String ville:cn.getVilles())
		{
			comboBoxVilleE.addItem(ville);
		}
		comboBoxVilleE.setBounds(184, 409, 251, 22);
		contentPane.add(comboBoxVilleE);
		
		
		///
		JFrame f = this;
		JButton btncontinue = new JButton("Continuer");
		btncontinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cn.commitTransaction();
					JOptionPane.showMessageDialog(btncontinue,"Expériences ajoutées avec succès");
					new Comptences().setVisible(true);
					f.setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btncontinue.setBounds(528, 467, 131, 34);
		contentPane.add(btncontinue);
		
		JDateChooser date_debut = new JDateChooser();
		long millis=System.currentTimeMillis();  
		java.sql.Date datee=new java.sql.Date(millis);  
		date_debut.setDate(datee);
		date_debut.setBounds(184, 290, 251, 22);
		contentPane.add(date_debut);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fin :");
		lblNewLabel_2_1.setBounds(42, 349, 108, 16);
		contentPane.add(lblNewLabel_2_1);
		
		JDateChooser date_fin = new JDateChooser();
		date_fin.setDate(datee);
		date_fin.setBounds(184, 349, 251, 22);
		contentPane.add(date_fin);
		JButton ajouter_exp = new JButton("Ajouter");
		ajouter_exp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String debut =	new SimpleDateFormat("dd/MM/yy").format(date_debut.getCalendar().getTime());
				String fin =	new SimpleDateFormat("dd/MM/yy").format(date_fin.getCalendar().getTime());
				String post = txt_postExp.getText();
				String etab = txt_etabExp.getText();
				
				try {
					int idville = cn.getVilleId((String) comboBoxVilleE.getSelectedItem());
					cn.beginTransaction();
					cn.setExp(idville, InfosCandidats.id, post, etab, debut, fin);
					txt_etabExp.setText("");
					txt_postExp.setText("");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		ajouter_exp.setBounds(194, 467, 131, 34);
		contentPane.add(ajouter_exp);
	}
}
