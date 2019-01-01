package System.Solaire.com.WebServiceArtifact.Model;

import org.springframework.stereotype.Component;

import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;
import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;

@Component
public class UtilisateurFromServeur {
	Utilisateur utilisateur;
	CompteInfo compte;
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public CompteInfo getCompte() {
		return compte;
	}
	public void setCompte(CompteInfo compte) {
		this.compte = compte;
	}
}
