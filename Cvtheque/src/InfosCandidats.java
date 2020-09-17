import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JSpinner;
import java.awt.Panel;
import com.toedter.calendar.JDateChooser;

public class InfosCandidats extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nom;
	private JTextField txt_prenom;
	private JTextField txt_adresse;
	private JTextField txt_tele;
	private JTextField txt_email;
	public static int id ;
	 Connexion cn = new Connexion();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfosCandidats frame = new InfosCandidats();
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
	public InfosCandidats() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfos = new JLabel("Informations personnelles  : ");
		lblInfos.setFont(new Font("Sitka Small", Font.BOLD, 20));
		lblInfos.setBounds(282, 34, 326, 65);
		contentPane.add(lblInfos);
		
		JLabel lblNewLabel_2 = new JLabel("T\u00E9l\u00E9phone :");
		lblNewLabel_2.setBounds(40, 404, 72, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail :");
		lblNewLabel_3.setBounds(40, 456, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nom : ");
		lblNewLabel_4.setBounds(40, 143, 56, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_5.setBounds(40, 196, 56, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Date de naissance : ");
		lblNewLabel_6.setBounds(40, 248, 118, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Situation familiale :");
		lblNewLabel_7.setBounds(38, 300, 111, 16);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Adresse : ");
		lblNewLabel_8.setBounds(40, 352, 72, 16);
		contentPane.add(lblNewLabel_8);
		
		txt_nom = new JTextField();
		txt_nom.setBounds(161, 140, 249, 22);
		contentPane.add(txt_nom);
		txt_nom.setColumns(10);
		
		txt_prenom = new JTextField();
		txt_prenom.setBounds(161, 193, 249, 22);
		contentPane.add(txt_prenom);
		txt_prenom.setColumns(10);
		/////////////////////////
		txt_adresse = new JTextField();
		txt_adresse.setBounds(161, 349, 249, 22);
		contentPane.add(txt_adresse);
		txt_adresse.setColumns(10);
		
		txt_tele = new JTextField();
		txt_tele.setBounds(161, 402, 249, 22);
		contentPane.add(txt_tele);
		txt_tele.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setBounds(161, 453, 249, 22);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		///////////////////////////////////////////////////////////////////////////////
		JComboBox<String> comboBoxSF = new JComboBox<String>();
		comboBoxSF.addItem("Situation Familiale");
		comboBoxSF.addItemListener(
		        new ItemListener() {
		            @Override
		            public void itemStateChanged(ItemEvent e) {
		                 // now there's nothing happen when we select the first item
		                if(comboBoxSF.getSelectedIndex()>0){ 
		                  
		               }
		            }
		        }
		        );     
		for(String situation:cn.getSituations())
		{
			comboBoxSF.addItem(situation);
		}
		comboBoxSF.setBounds(161, 297, 249, 22);
		contentPane.add(comboBoxSF);
		//////////////////////////////////////////////////////////////////////////////////////////
		JDateChooser dateChooser = new JDateChooser();
		long millis=System.currentTimeMillis();  
		java.sql.Date datee=new java.sql.Date(millis);  
		dateChooser.setDate(datee);
		dateChooser.setBounds(159, 242, 251, 22);
		contentPane.add(dateChooser);
		JButton btnNewButton = new JButton("Continuer ");
		JFrame f = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nom = txt_nom.getText();
				String prenom = txt_prenom.getText();
				int idsituation = 0;
				String numero = txt_tele.getText();
				String email = txt_email.getText();
				String adresse = txt_adresse.getText();
				try {
					 idsituation = cn.getSituationId((String) comboBoxSF.getSelectedItem());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String date =	new SimpleDateFormat("dd/MM/yy").format(dateChooser.getCalendar().getTime());
				try {
					cn.setCandidat(idsituation, nom, prenom,date,numero,email,adresse);
					InfosCandidats.id = cn.getNextCandidatId();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(btnNewButton,"Candidat ajouté avec succés");
				try {
					Formation formation = new Formation();
					formation.setVisible(true);
					f.setVisible(false);
					

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(679, 466, 131, 34);
		contentPane.add(btnNewButton);
		
		
	}
}
