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
@Table(name="utilisateur")
public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2361030252615010224L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="adress")
	private String adress;
	@Column(name="id_Compte")
	private String compte;
	@Column(name="photo")
	private String photo;
	@Column(name="id_Societe")
	private long societe;
	public Utilisateur(){
	}
	
	public Utilisateur(long id, String nom, String prenom, String adress, String compte, String photo, long societe) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adress = adress;
		this.compte = compte;
		this.photo = photo;
		this.societe = societe;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCompte() {
		return compte;
	}
	public void setCompte(String compte) {
		this.compte = compte;
	}
	public long getSociete() {
		return societe;
	}
	public void setSociete(long societe) {
		this.societe = societe;
	}
	public Utilisateur(String nom, String prenom, String adress, String photo, String societe,String compte) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adress = adress;
		this.compte = compte;
		this.photo = photo;
		this.societe = Long.parseLong(societe);
	}
	public Utilisateur(String nom, String prenom, String adress, String photo, String societe) {
		this.nom = nom;
		this.prenom = prenom;
		this.adress = adress;
		this.photo = photo;
		this.societe = Long.parseLong(societe);
	}

	
}
