package System.Solaire.com.WebServiceArtifact.Services.Interface;

import java.util.List;

import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;
import System.Solaire.com.WebServiceArtifact.Model.UtilisateurFromServeur;

public interface IUtilisateurService {

	List<UtilisateurFromServeur> findall();

	boolean addUtilisateur(Utilisateur user);

	boolean modiferUtilisateurs(String listUtilisateur);

	boolean supprimerUtilisateurs(long id);

	Utilisateur selectByIdCompte(String idcompte);

}
