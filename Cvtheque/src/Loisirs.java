import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Loisirs extends JFrame {

	private JPanel contentPane;
	private JTextField txt_loisir;
	private Connexion cn= new Connexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loisirs frame = new Loisirs();
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
	public Loisirs() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoisirs = new JLabel("Loisirs");
		lblLoisirs.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblLoisirs.setBounds(281, 26, 166, 39);
		contentPane.add(lblLoisirs);
		
		JLabel lblNewLabel = new JLabel("Loisirs :");
		lblNewLabel.setBounds(53, 110, 56, 16);
		contentPane.add(lblNewLabel);
		
		JButton ajouter_loisirs = new JButton("Ajouter");
	
		ajouter_loisirs.setBounds(45, 261, 140, 34);
		contentPane.add(ajouter_loisirs);
		JFrame f = this;
		
		
		JButton Nouveau_CD = new JButton("Nouveau candidat");
		Nouveau_CD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cn.commitTransaction();
					JOptionPane.showMessageDialog(Nouveau_CD, "Opération terminée!");
					new InfosCandidats().setVisible(true);
					f.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Nouveau_CD.setBounds(463, 261, 140, 34);
		contentPane.add(Nouveau_CD);
		
		/////
		
		
		JButton Terminer = new JButton("Terminer");
		Terminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cn.commitTransaction();
					JOptionPane.showMessageDialog(Terminer, "Opération terminée!");
					f.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Terminer.setBounds(255, 261, 140, 34);
		contentPane.add(Terminer);
		
		
		
		/////
		
		
		
		
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Loisirs");
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
		for(String competence:cn.getLoisirs())
		{
			comboBox.addItem(competence);
		}
		comboBox.setBounds(165, 107, 199, 22);
		contentPane.add(comboBox);
		
		JButton ajouter_loisir = new JButton("+");
		
		ajouter_loisir.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ajouter_loisir.setBounds(388, 106, 43, 22);
		contentPane.add(ajouter_loisir);
		
		JButton ajouter_comp_1 = new JButton("+");
		
		ajouter_comp_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ajouter_comp_1.setBounds(247, 187, 43, 22);
		ajouter_comp_1.hide();
		contentPane.add(ajouter_comp_1);
		
		JLabel lblNouveauLoisir = new JLabel("Nouveau loisir :");
		lblNouveauLoisir.setBounds(53, 153, 101, 16);
		lblNouveauLoisir.hide();
		contentPane.add(lblNouveauLoisir);
		
		txt_loisir = new JTextField();
		txt_loisir.setBounds(165, 150, 199, 22);
		txt_loisir.hide();
		contentPane.add(txt_loisir);
		txt_loisir.setColumns(10);
		ajouter_loisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblNouveauLoisir.isShowing())
				{
					ajouter_comp_1.hide();
					lblNouveauLoisir.hide();
					txt_loisir.hide();
				}else {
					ajouter_comp_1.show();
					lblNouveauLoisir.show();
					txt_loisir.show();
				}
			}
		});
		ajouter_comp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String intitule = txt_loisir.getText();
				try {
					if(!cn.getLoisirs().stream().anyMatch(intitule::equalsIgnoreCase))
					{
						cn.setCompetence(intitule);
						lblNouveauLoisir.hide();
						txt_loisir.hide();
						ajouter_comp_1.hide();
						comboBox.addItem(intitule);
						cn.setLoisir(intitule);
						JOptionPane.showMessageDialog(ajouter_comp_1, intitule + " a été ajouté à la liste des loisirs avec succés!");
					}else {
						JOptionPane.showMessageDialog(ajouter_comp_1, intitule + " existe déjà dans la liste des loisirs!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		ajouter_loisirs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cn.beginTransaction();
					cn.setLoisir_candidat(InfosCandidats.id, cn.getLoisirID((String) comboBox.getSelectedItem()));
					JOptionPane.showMessageDialog(ajouter_loisirs,  comboBox.getSelectedItem()+" a été lié au candidat avec succés!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(ajouter_loisirs,  comboBox.getSelectedItem()+" est déjà  lié au candidat!");
					
				}
				
			}
		});
	}
}
