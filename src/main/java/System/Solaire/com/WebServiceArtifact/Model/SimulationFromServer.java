package System.Solaire.com.WebServiceArtifact.Model;

import org.springframework.stereotype.Component;

import System.Solaire.com.WebServiceArtifact.Entitry.SimulationDB;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;
import System.Solaire.com.WebServiceArtifact.Entitry.StatusBD;
import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;

@Component
public class SimulationFromServer {
	private SimulationDB simulation ;
	private Utilisateur utilisateur; 
	private Societe societe;
	private StatusBD description;
	private StatusBD status;
	
	public SimulationFromServer() {
	}
	public SimulationDB getSimulation() {
		return simulation;
	}
	public void setSimulation(SimulationDB simulation) {
		this.simulation = simulation;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Societe getSociete() {
		return societe;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	public StatusBD getDescription() {
		return description;
	}
	public void setDescription(StatusBD description) {
		this.description = description;
	}
	public StatusBD getStatus() {
		return status;
	}
	public void setStatus(StatusBD status) {
		this.status = status;
	}
		
}
