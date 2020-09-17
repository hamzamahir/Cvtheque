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

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Langues extends JFrame {

	private JPanel contentPane;
	Connexion cn = new Connexion();
	private JTextField txt_langue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Langues frame = new Langues();
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
	public Langues() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLangues = new JLabel("Langues :");
		lblLangues.setBounds(259, 38, 121, 39);
		lblLangues.setFont(new Font("Sitka Small", Font.BOLD, 20));
		contentPane.add(lblLangues);
		
		JLabel lblNewLabel = new JLabel("Langue :");
		lblNewLabel.setBounds(52, 139, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Niveau : ");
		lblNewLabel_1.setBounds(52, 190, 56, 16);
		contentPane.add(lblNewLabel_1);
		

		
//////////
		JComboBox<String> comboBoxLangue = new JComboBox<String>();
		comboBoxLangue.addItem("Langue");
		comboBoxLangue.addItemListener(
		        new ItemListener() {
		            @Override
		            public void itemStateChanged(ItemEvent e) {
		                 // now there's nothing happen when we select the first item
		                if(comboBoxLangue.getSelectedIndex()>0){ 
		                    System.out.println("YOU CLICK INDEX-"+comboBoxLangue.getSelectedItem());
		               }
		            }
		        }
		        );     
		for(String langue:cn.getLangues())
		{
			comboBoxLangue.addItem(langue);
		}
		comboBoxLangue.setBounds(220, 136, 191, 22);
		contentPane.add(comboBoxLangue);
		JFrame f = this;
		JButton continuer_lng = new JButton("Continuer");
		continuer_lng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cn.commitTransaction();
					JOptionPane.showMessageDialog(continuer_lng,  " Opération validée avec succés!");
					new Loisirs().setVisible(true);
					f.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(continuer_lng, " Une erreur s'est produite !");
				}
			}
		});
		continuer_lng.setBounds(431, 313, 131, 34);
		contentPane.add(continuer_lng);
				
		
		JComboBox <String> comboBoxNiveaux = new JComboBox<String>();
		comboBoxNiveaux.addItem("Niveau");
		comboBoxNiveaux.addItemListener(
		        new ItemListener() {
		            @Override
		            public void itemStateChanged(ItemEvent e) {
		                 // now there's nothing happen when we select the first item
		                if(comboBoxNiveaux.getSelectedIndex()>0){ 
		                    System.out.println("YOU CLICK INDEX-"+comboBoxNiveaux.getSelectedItem());
		               }
		            }
		        }
		        );     
		for(String niveau:cn.getNiveaux())
		{
			comboBoxNiveaux.addItem(niveau);
		}
		comboBoxNiveaux.setBounds(220, 187, 191, 22);
		contentPane.add(comboBoxNiveaux);
		
		JButton ajouter_langue = new JButton("+");
		ajouter_langue.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ajouter_langue.setBounds(442, 135, 43, 22);
		contentPane.add(ajouter_langue);
		
		JLabel lblNouvelleLangue = new JLabel("Nouvelle Langue:");
		lblNouvelleLangue.setBounds(52, 232, 144, 22);
		lblNouvelleLangue.hide();
		contentPane.add(lblNouvelleLangue);
		
		txt_langue = new JTextField();
		txt_langue.setColumns(10);
		txt_langue.setBounds(220, 232, 191, 22);
		txt_langue.hide();
		contentPane.add(txt_langue);
		
		
		JButton btn_new_langue = new JButton("+");
		btn_new_langue.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btn_new_langue.setBounds(301, 267, 43, 22);
		btn_new_langue.hide();
		contentPane.add(btn_new_langue);
		
		
		ajouter_langue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_langue.isShowing())
				{
					lblNouvelleLangue.hide();
					txt_langue.hide();
					btn_new_langue.hide();
				}else {
					lblNouvelleLangue.show();
					txt_langue.show();
					btn_new_langue.show();
				}
			}
		});
		btn_new_langue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String intitule = txt_langue.getText();
				try {
					if(!cn.getLangues().stream().anyMatch(intitule::equalsIgnoreCase))
					{
						cn.setLangue(intitule);
						lblNouvelleLangue.hide();
						txt_langue.hide();
						btn_new_langue.hide();
						comboBoxLangue.addItem(intitule);
						JOptionPane.showMessageDialog(btn_new_langue, intitule + " a été ajouté à la liste des langues avec succés!");
					}
					else {
						JOptionPane.showMessageDialog(btn_new_langue, intitule + " existe déjà sur la liste!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
		});
		JButton ajouter_lng = new JButton("Ajouter");
		ajouter_lng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				 
				try {
					int idlangue = cn.getLangueId((String) comboBoxLangue.getSelectedItem());
					int idniveau = cn.getNiveauId((String) comboBoxNiveaux.getSelectedItem());
					cn.beginTransaction();
					cn.setCandidate_Langue_niveau(idlangue,idniveau,InfosCandidats.id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ajouter_langue,   " déjà lié avec le candidat!");

				}
				
			}
		});
		ajouter_lng.setBounds(123, 313, 131, 34);
		contentPane.add(ajouter_lng);
	}
}
