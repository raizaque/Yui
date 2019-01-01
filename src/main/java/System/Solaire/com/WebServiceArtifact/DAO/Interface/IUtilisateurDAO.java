package System.Solaire.com.WebServiceArtifact.DAO.Interface;

import java.util.List;

import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;

public interface IUtilisateurDAO {

	List<Utilisateur> findall();

	boolean addUtilisateur(Utilisateur user);

	boolean supprimerUtilisateur(Utilisateur utilisateur);

	boolean modifier(Utilisateur utilisateur);

	Utilisateur findById(long id);

	void deleteById(long id);

	Utilisateur findByCompte(String string);

}
