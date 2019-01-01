package System.Solaire.com.WebServiceArtifact.Entitry;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// using jpa and hibernate annotation
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="compte")
public class CompteInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5503588312485310867L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="Role")
	private String role;
	@Column(name="Pseudo")
	private String pseudo;
	@Column(name="mot_de_passe")
	private String motDePasse;
	@Column(name="token")
	private String token;
	@Column(name="dateFIn")
	private String fin;
	public CompteInfo() {
	}
	

	public CompteInfo(long id, String role, String pseudo, String motDePasse, String token, String fin) {
		super();
		this.id = id;
		this.role = role;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.token = token;
		this.fin = fin;
	}


	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public CompteInfo(String email, String motDePasse2, String dateFinLicence, String role2) {
		this.pseudo=email;
		this.motDePasse=motDePasse2;
		this.fin=dateFinLicence;
		this.role=role2;
	}
}
