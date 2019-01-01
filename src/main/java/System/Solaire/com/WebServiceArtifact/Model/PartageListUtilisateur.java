package System.Solaire.com.WebServiceArtifact.Model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class PartageListUtilisateur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1300769973456806169L;
	
	private String id;
	private String text;
	private String parentId;
	private boolean selected;
	
	public PartageListUtilisateur() {
		super();
	}
	public PartageListUtilisateur(String id, String text, String parentId, boolean selected) {
		super();
		this.id = id;
		this.text = text;
		this.parentId = parentId;
		this.selected = selected;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
