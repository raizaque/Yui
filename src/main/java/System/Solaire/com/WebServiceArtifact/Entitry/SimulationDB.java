package System.Solaire.com.WebServiceArtifact.Entitry;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import ch.qos.logback.core.status.Status;

@Component
@Entity
@Table(name = "simulation")
public class SimulationDB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 149864634048919661L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Lob
	@Column(name = "Simulation")
	private byte[] simulation;
	@Column(name = "iutilisateur")
	private long iUtilisateur;
	@Column(name = "nom_fichier")
	private String nomFichier;
	@Column(name = "utilisateur_actuel")
	private String utilisateurActuel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getSimulation() {
		return simulation;
	}

	public void setSimulation(byte[] simulation) {
		this.simulation = simulation;
	}

	public long getiUtilisateur() {
		return iUtilisateur;
	}

	public void setiUtilisateur(long iUtilisateur) {
		this.iUtilisateur = iUtilisateur;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public String getUtilisateurActuel() {
		return utilisateurActuel;
	}

	public void setUtilisateurActuel(String utilisateurActuel) {
		this.utilisateurActuel = utilisateurActuel;
	}

}
