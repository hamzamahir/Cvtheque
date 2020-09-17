import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Formation extends JFrame {

	private JPanel contentPane;
	private JTextField txt_diplome;
	private JTextField txt_etab;
	private JTextField txt_annee;
	Connexion cn = new Connexion();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formation frame = new Formation();
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
	public Formation() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFormation = new JLabel("Formation et dipl\u00F4mes : ");
		lblFormation.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblFormation.setBounds(277, 52, 271, 40);
		contentPane.add(lblFormation);
		
		JLabel lblNewLabel_1 = new JLabel("Dipl\u00F4me :");
		lblNewLabel_1.setBounds(55, 164, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Etablissement :");
		lblNewLabel_2.setBounds(55, 223, 88, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ann\u00E9e d'obtention : ");
		lblNewLabel_3.setBounds(55, 283, 116, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ville :");
		lblNewLabel_4.setBounds(55, 346, 56, 16);
		contentPane.add(lblNewLabel_4);
		
		txt_diplome = new JTextField();
		txt_diplome.setBounds(216, 161, 246, 22);
		contentPane.add(txt_diplome);
		txt_diplome.setColumns(10);
		
		txt_etab = new JTextField();
		txt_etab.setBounds(216, 220, 246, 22);
		contentPane.add(txt_etab);
		txt_etab.setColumns(10);
		
		txt_annee = new JTextField();
		txt_annee.setBounds(216, 280, 246, 22);
		contentPane.add(txt_annee);
		txt_annee.setColumns(10);
		
		JFrame f = this;
		JButton btnNewButton = new JButton("Continuer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cn.commitTransaction();
					JOptionPane.showMessageDialog(btnNewButton,"Formations ajoutées avec succès");
					new Experience().setVisible(true);
					f.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(528, 469, 131, 34);
		contentPane.add(btnNewButton);
		
		
		///
		JComboBox<String> comboBoxVilleF = new JComboBox<String>();
		comboBoxVilleF.addItem("Ville");
		comboBoxVilleF.addItemListener(
		        new ItemListener() {
		            @Override
		            public void itemStateChanged(ItemEvent e) {
		                 // now there's nothing happen when we select the first item
		                if(comboBoxVilleF.getSelectedIndex()>0){ 
		                    System.out.println("YOU CLICK INDEX-"+comboBoxVilleF.getSelectedItem());
		               }
		            }
		        }
		        );     
		for(String ville:cn.getVilles())
		{
			comboBoxVilleF.addItem(ville);
		}
		comboBoxVilleF.setBounds(216, 343, 246, 22);
		contentPane.add(comboBoxVilleF);
		
		JButton ajouter_dip = new JButton("Ajouter");
		ajouter_dip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idville = cn.getVilleId((String) comboBoxVilleF.getSelectedItem());
					String diplome = txt_diplome.getText();
					String etab = txt_etab.getText();
					int annee = Integer.parseInt(txt_annee.getText()) ;
					cn.beginTransaction();
					cn.setFormation(InfosCandidats.id, idville, diplome, etab, annee);
					txt_diplome.setText("");
					txt_annee.setText("");
					txt_etab.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ajouter_dip.setBounds(162, 469, 131, 34);
		contentPane.add(ajouter_dip);
	}

}
