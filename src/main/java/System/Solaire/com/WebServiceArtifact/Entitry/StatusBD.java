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
@Table(name="status")
public class StatusBD implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1987898078822238962L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="id_objet")
	private long idObject;
	@Column(name="Description")
	private String description;
	@Column(name="type_Objet")
	private String typeObjet;
	 public StatusBD() {
	}
		
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdObject() {
		return idObject;
	}
	public void setIdObject(long idObject) {
		this.idObject = idObject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeObjet() {
		return typeObjet;
	}
	public void setTypeObjet(String typeObjet) {
		this.typeObjet = typeObjet;
	}
	
}
