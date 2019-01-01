package System.Solaire.com.WebServiceArtifact.Entitry;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="partage")
public class Partage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1868669482278778104L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="id_Simulation")
	private long idSimulation;
	@Column(name="id_Utilisateur_source	")
	private long idUtilisateursource	;
	@Column(name="id_Utilisateur_recepteur")
	private long utilisateurRecepteur;
	public Partage() {
		
	}	
	public Partage(long idSimulation,long idUtilisateursource,long utilisateurRecepteur) {
		this.idSimulation = idSimulation;
		this.idUtilisateursource = idUtilisateursource;
		this.utilisateurRecepteur = utilisateurRecepteur;
	}
	public long getIdSimulation() {
		return idSimulation;
	}
	public void setIdSimulation(long idSimulation) {
		this.idSimulation = idSimulation;
	}
	public long getIdUtilisateursource() {
		return idUtilisateursource;
	}
	public void setIdUtilisateursource(long idUtilisateursource) {
		this.idUtilisateursource = idUtilisateursource;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUtilisateurRecepteur() {
		return utilisateurRecepteur;
	}
	public void setUtilisateurRecepteur(long utilisateurRecepteur) {
		utilisateurRecepteur = utilisateurRecepteur;
	}
	
}
