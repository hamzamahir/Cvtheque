import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.ComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Comptences extends JFrame {

	private JPanel contentPane;
	private JTextField txt_competence;
	Connexion cn= new Connexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comptences frame = new Comptences();
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
	public Comptences() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nouvelle Comp\u00E9tence :");
		lblNewLabel.setBounds(82, 171, 144, 22);
		contentPane.add(lblNewLabel);
		lblNewLabel.hide();
		
		txt_competence = new JTextField();
		txt_competence.setBounds(250, 171, 199, 22);
		contentPane.add(txt_competence);
		txt_competence.setColumns(10);
		txt_competence.hide();
		
		JLabel lblCompetences = new JLabel("Comp\u00E9tences :");
		lblCompetences.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblCompetences.setBounds(256, 37, 166, 39);
		contentPane.add(lblCompetences);
		
		JButton ajouter_comp = new JButton("Ajouter");
		
		ajouter_comp.setBounds(155, 280, 131, 34);
		contentPane.add(ajouter_comp);
		
		JFrame f=this;
		JButton continuer_comp = new JButton("Continuer");
		continuer_comp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cn.commitTransaction();
					JOptionPane.showMessageDialog(continuer_comp, "Operation validée avec succés!");
					new Langues().setVisible(true);
					f.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		continuer_comp.setBounds(429, 280, 131, 34);
		contentPane.add(continuer_comp);
		//////////////////
		JComboBox<String>  comboBox = new JComboBox<String>();
		comboBox.addItem("Competences");
		comboBox.addItemListener(
		        new ItemListener() {
		            @Override
		            public void itemStateChanged(ItemEvent e) {
		                 // now there's nothing happen when we select the first item
		                if(comboBox.getSelectedIndex()>0){ 
		                    System.out.println("YOU CLICK INDEX-"+comboBox.getSelectedItem());
		               }
		            }
		        }
		        );     
		for(String competence:cn.getCompetences())
		{
			comboBox.addItem(competence);
		}
		comboBox.setBounds(250, 136, 199, 22);
		contentPane.add(comboBox);
		////////////////////////
	
		JButton ajouter_comp_1_1 = new JButton("+");
		ajouter_comp_1_1.hide();
		
		JLabel lblComptence = new JLabel("Comp\u00E9tence :");
		lblComptence.setBounds(82, 136, 144, 22);
		contentPane.add(lblComptence);
		
		JButton ajouter_comp_1 = new JButton("+");
		ajouter_comp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_competence.isShowing())
				{
					lblNewLabel.hide();
					txt_competence.hide();
					ajouter_comp_1_1.hide();
				}else {
					lblNewLabel.show();
					txt_competence.show();
					ajouter_comp_1_1.show();
				}
				
			}
		});
		ajouter_comp_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ajouter_comp_1.setBounds(473, 135, 43, 22);
		contentPane.add(ajouter_comp_1);
		
		
		ajouter_comp_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String intitule = txt_competence.getText();
				try {
					if(!cn.getCompetences().stream().anyMatch(intitule::equalsIgnoreCase))
					{
						cn.setCompetence(intitule);
						lblNewLabel.hide();
						txt_competence.hide();
						ajouter_comp_1_1.hide();
						comboBox.addItem(intitule);
						JOptionPane.showMessageDialog(ajouter_comp_1_1, intitule + " a ete ajouté à la liste des compétences avec succès!");
					}else {
						JOptionPane.showMessageDialog(ajouter_comp_1_1, intitule + " existe déjà sur la liste!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		ajouter_comp_1_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ajouter_comp_1_1.setBounds(331, 206, 43, 22);
		contentPane.add(ajouter_comp_1_1);
		ajouter_comp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idcmp = cn.getCompetenceId((String) comboBox.getSelectedItem());
					cn.beginTransaction();
					cn.setCompetence_Candidate(idcmp, InfosCandidats.id);
					JOptionPane.showMessageDialog(ajouter_comp_1_1, comboBox.getSelectedItem() + " a ete ajouté au candidat avec succès!");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(ajouter_comp,"cette compétence est déjà liée à ce candidat!");
				}
			}
		});
	}
}
