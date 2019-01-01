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
@Table(name="notification")
public class Notification implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1603329845210878859L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="id_utilisateur")
	private long idUtilisateur;
	@Column (name="seen")
	private boolean seen;
	@Column(name="nobject")  
	private long nObject;
	@Column(name="typeobject")
	private String typeobjet;
	public Notification() {
		
	}
	
	public long getNObject() {
		return nObject;
	}

	public void setNObject(long nObject) {
		this.nObject = nObject;
	}

	public String getTypeObjet() {
		return typeobjet;
	}

	public void setTypeobjet(String typeObjet) {
		this.typeobjet = typeObjet;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
}
