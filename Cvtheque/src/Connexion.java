import java.sql.* ;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
public class Connexion {
	public static Statement statement;
	private static  Connection con;
	public static int cnt=0;
	public Connexion() {
		if(cnt==0)
		{
			 try
			  {
				  Class.forName("oracle.jdbc.driver.OracleDriver");
				  this.con  = DriverManager.getConnection(
						  "jdbc:oracle:thin:@localhost:1521:orclcv","cv","cv");
				  this.statement =  this.con.createStatement();
				  
						  
			  }	catch(Exception e) {
			  
				  JOptionPane.showMessageDialog(null, e);
				  
			  }
			 cnt++;
		}else {
			System.out.println("connexion is already openned");
		}
		
	}
	
	public static void setCandidat(int idSituation,String nom,String prenom,String date,String numero,String email,String adresse) throws SQLException
	{
		String query = "INSERT INTO CANDIDAT ( IDSITUATIONF,NOM,PRENOM,DTNAISSANCE, TELEPHONE, EMAIL, ADRESSE) VALUES ('"+idSituation+"', '"+nom+"', '"+prenom+"', TO_DATE('"+date+"', 'DD/MM/RR'), '"+numero+"','"+email+"', '"+adresse+"')" ;
		 statement.executeUpdate(query);
		
	}
	public static void setExp(int idville, int idcandidat,String post,String etab,String debut,String fin) throws SQLException
	{
		String query = "INSERT INTO experienceprofessionnelle (IDVILLE, IDCANDIDAT, POSTE, ETABPOSTE, DEBUT, FIN)  VALUES ('"+idville+"', '"+idcandidat+"', '"+post+"','"+etab+"', TO_DATE('"+debut+"', 'DD/MM/RR'), TO_DATE('"+fin+"', 'DD/MM/RR') )" ;
		 statement.executeUpdate(query);
		
	}
	public static void setLangue(String intitule) throws SQLException
	{
		String query = "insert into langues(INTITULELANGUE) VALUES('"+intitule+"') ";
		statement.executeUpdate(query);
	}
	public static void setLoisir(String intitule) throws SQLException
	{
		String query = "insert into loisirs(INTITTULELOISIRS) VALUES('"+intitule+"') ";
		statement.executeUpdate(query);
	}
	public static void setLoisir_candidat(int idcandidat,int idloisir) throws SQLException
	{
		String query = "insert into cand_loi(idcandidat,idloisir) VALUES("+idcandidat+", "+idloisir+") ";
		statement.executeUpdate(query);
	}
	public static void setCompetence(String intitule) throws SQLException
	{
		String query = "insert into competences(INTITULECMP) VALUES('"+intitule+"') ";
		statement.executeUpdate(query);
	}
	public static void setCompetence_Candidate(int idcmp,int idcandidat) throws SQLException
	{
		String query = "insert into cand_comp VALUES('"+idcandidat+"','"+idcmp+"') ";
		statement.executeUpdate(query);
	}
	
	public static void setCandidate_Langue_niveau(int idlangue,int idniveau, int idcandidat) throws SQLException
	{
		String query = "insert into langue_niveau_candidat VALUES('"+idlangue+"','"+idniveau+"','"+idcandidat+"') ";
		statement.executeUpdate(query);
	}
	public static int getNextCandidatId() throws SQLException
	{
		ResultSet rs = statement.executeQuery("select idcandidat_seq1.currval from candidat  WHERE ROWNUM = 1");
		int id = 0;
		while (rs.next())
		{
			 id = rs.getInt("CURRVAL");
		}rs.close();
		return id;
		
	}
	
	public static void setFormation(int idcandidat, int idville, String diplome, String etab, int annee) throws SQLException
	{
		String query = "INSERT INTO FORMATIONETDIPLOMES (IDCANDIDAT, IDVILLE, DIPLOME, ETABLISSEMENTDIPLOME, ANNEEOBT) VALUES ('"+idcandidat+"', '"+idville+"', '"+diplome+"', '"+etab+"', '"+annee+"')";
		statement.executeUpdate(query);
	}
	
	public static void beginTransaction() throws SQLException
	{
		con.setAutoCommit(false);
	}
	public static void commitTransaction() throws SQLException
	{
		con.setAutoCommit(true);
		con.commit();
	}
	public static void rollbackTransaction() throws SQLException
	{
		statement.executeQuery("Rollback");
	}
	public static List<String> getSituations() throws SQLException
	{
		List<String> tableau = new ArrayList<>() ;
		ResultSet rs = statement.executeQuery("select intitulesituation from situationfamiliale");
		  while(rs.next()){
		       		     
		         String sf = rs.getString("intitulesituation");
		         tableau.add(sf);
		  }rs.close();
		  return tableau;
	}
	public static List<String> getNiveaux() throws SQLException
	{
		List<String> tableau = new ArrayList<>() ;
		ResultSet rs = statement.executeQuery("select intituleniveau from niveau");
		  while(rs.next()){
		       		     
		         String sf = rs.getString("intituleniveau");
		         tableau.add(sf);
		  }rs.close();
		  return tableau;
	}
	
	public static List<String> getVilles() throws SQLException
	{
		List<String> tableau = new ArrayList<>() ;
		ResultSet rs = statement.executeQuery("select intituleville from ville");
		  while(rs.next()){
		       		     
		         String sf = rs.getString("intituleville");
		         tableau.add(sf);
		  }rs.close();
		  return tableau;
	}
	
	public static List<String> getLangues() throws SQLException
	{
		List<String> tableau = new ArrayList<>() ;
		ResultSet rs = statement.executeQuery("select intitulelangue from langues");
		  while(rs.next()){
		       		     
		         String sf = rs.getString("intitulelangue");
		         tableau.add(sf);
		  }rs.close();
		  return tableau;
	}
	public static List<String> getCompetences() throws SQLException
	{
		List<String> tableau = new ArrayList<>() ;
		ResultSet rs = statement.executeQuery("select intitulecmp from competences");
		  while(rs.next()){
		       		     
		         String sf = rs.getString("intitulecmp");
		         tableau.add(sf);
		  }rs.close();
		  return tableau;
	}
	public static List<String> getLoisirs() throws SQLException
	{
		List<String> tableau = new ArrayList<>() ;
		ResultSet rs = statement.executeQuery("select INTITTULELOISIRS from loisirs");
		  while(rs.next()){
		       		     
		         String sf = rs.getString("INTITTULELOISIRS");
		         tableau.add(sf);
		  }rs.close();
		  return tableau;
	}
	public static int getSituationId(String intitule) throws SQLException
	{
		int sf = 1;
		ResultSet rs = statement.executeQuery("select idsituationf from situationfamiliale  where situationfamiliale.intitulesituation = '"+intitule+"'");
		 while( rs.next())
		 {
	          sf = rs.getInt("idsituationf");

		 }
		  rs.close();
		   return sf;
		 
	}
	public static int getLoisirID(String intitule) throws SQLException
	{
		int sf = 1;
		ResultSet rs = statement.executeQuery("select idloisir from loisirs  where loisirs.intittuleloisirs = '"+intitule+"'");
		 while( rs.next())
		 {
	          sf = rs.getInt("idloisir");

		 }
		  rs.close();
		   return sf;
		 
	}
	public static int getVilleId(String intitule) throws SQLException
	{
		int sf = 1;
		ResultSet rs = statement.executeQuery("select idville from ville  where ville.intituleville = '"+intitule+"'");
		 while( rs.next())
		 {
	          sf = rs.getInt("idville");

		 }
		  rs.close();
		   return sf;
		 
	}
	public static int getCompetenceId(String intitule) throws SQLException
	{
		int sf = 1;
		ResultSet rs = statement.executeQuery("select IDCOMPETENCE from COMPETENCES  where COMPETENCES.INTITULECMP = '"+intitule+"'");
		 while( rs.next())
		 {
	          sf = rs.getInt("IDCOMPETENCE");
		 }
		  rs.close();
		   return sf;
		 
	}
	public static int getLangueId(String intitule) throws SQLException
	{
		int sf = 1;
		ResultSet rs = statement.executeQuery("select idlangue from langues  where langues.intitulelangue = '"+intitule+"'");
		 while( rs.next())
		 {
	          sf = rs.getInt("idlangue");
		 }
		  rs.close();
		   return sf;
		 
	}
	public static int getNiveauId(String intitule) throws SQLException
	{
		int sf = 1;
		ResultSet rs = statement.executeQuery("select idniveau from niveau  where niveau.intituleniveau = '"+intitule+"'");
		 while( rs.next())
		 {
	          sf = rs.getInt("idniveau");
		 }
		  rs.close();
		   return sf;
	}
	public static void main(String args[]) throws SQLException
	{
		Connexion cn = new Connexion();
		System.out.println(cn.getSituationId("test"));
	}

}



