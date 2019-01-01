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
@Table(name = "batterie")
public class Batterie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1081904183266135260L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "Nom")
	private String nom;
	@Column(name = "Capacite")
	private double capacite;
	@Column(name = "Puissance")
	private double Puissance;
	@Column(name = "societe")
	private String societe;

	public Batterie() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getCapacite() {
		return capacite;
	}

	public void setCapacite(double capacite) {
		this.capacite = capacite;
	}

	public double getPuissance() {
		return Puissance;
	}

	public void setPuissance(double puissance) {
		Puissance = puissance;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public Batterie(String nom, double capaciete, double puissance) {
		this.nom = nom;
		this.capacite = capaciete;
		this.Puissance = puissance;
	}

	public Batterie(long id, String nom, double capaciete, double puissance) {
		this.id = id;
		this.nom = nom;
		this.capacite = capaciete;
		this.Puissance = puissance;
	}

	public Batterie(long id) {
		this.id = id;
	}

	public Batterie(String nom, double capaciete, double puissance, String societe) {
		this.id = id;
		this.nom = nom;
		this.capacite = capaciete;
		this.Puissance = puissance;
		this.societe=societe;
	}
}
