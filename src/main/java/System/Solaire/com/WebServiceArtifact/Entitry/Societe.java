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
@Table(name="societe")
public class Societe implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -585803311965081068L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="adress")
	private String adress;
	@Column(name="nom")
	private String nom;
	public Societe() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Societe(String nom, String adresse) {
		this.nom=nom;
		this.adress=adresse;
	}

	public Societe(long id, String nom, String adresse) {
		this.id=id;
		this.nom=nom;
		this.adress=adresse;
	}

	public Societe(long id) {
		this.id=id;
	}
}
