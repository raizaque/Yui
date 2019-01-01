package System.Solaire.com.WebServiceArtifact.Model;

import java.io.Serializable;

public class NotificationModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6228886553526583250L;
	private long idNotification;
	private long idUtilisateur;
	private boolean vu;
	private String description ;
	private String typeObject;
	private long idObject;
	
	public NotificationModel() {
	}
	
	public NotificationModel(long idNotification, long idUtilisateur, boolean vu, String description) {
		super();
		this.idNotification = idNotification;
		this.idUtilisateur = idUtilisateur;
		this.vu = vu;
		this.description = description;
	}
	public NotificationModel(long idNotification, long idUtilisateur, boolean vu) {
		super();
		this.idNotification = idNotification;
		this.idUtilisateur = idUtilisateur;
		this.vu = vu;
	}
	public NotificationModel(long idNotification, long idUtilisateur, boolean vu, String typeObject, long idObject) {
		this.idNotification = idNotification;
		this.idUtilisateur = idUtilisateur;
		this.vu = vu;
		this.typeObject=typeObject;
		this.idObject=idObject;
	}
	
	public String getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(String typeObject) {
		this.typeObject = typeObject;
	}

	public long getIdObject() {
		return idObject;
	}

	public void setIdObject(long idObject) {
		this.idObject = idObject;
	}

	public long getIdNotification() {
		return idNotification;
	}
	public void setIdNotification(long idNotification) {
		this.idNotification = idNotification;
	}
	public long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public boolean isVu() {
		return vu;
	}
	public void setVu(boolean vu) {
		this.vu = vu;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
