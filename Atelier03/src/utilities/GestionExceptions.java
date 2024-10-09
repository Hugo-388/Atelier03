package utilities;

import java.sql.SQLException;

public abstract class GestionExceptions {
	
	public static void gestionExceptionsStates(SQLException e ,String SQL) {
		
		 switch(e.getSQLState()) {
		 case "08S01" :
			System.out.println("Imposible de se connecter a la base de donnée");
			break;
		 case "28000":
			 System.out.println("Erreur sur le login et sur le mots de passe de connection ");
			 break;
		 case "42000":
			 System.out.println("La base de donnée n'existe pas");
			 System.out.println(SQL);
			 break;
		 case "42S02":
			 System.out.println("Erreur dans la requete de sélection");
			 System.out.println(SQL);
			 break;
		 default:
			 System.out.println("Erreur dans le traitement SQL");
			 System.out.println(e.getSQLState());
			 System.out.println(SQL);
		 }
	}
}
