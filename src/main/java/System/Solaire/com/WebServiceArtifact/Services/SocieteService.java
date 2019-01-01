package System.Solaire.com.WebServiceArtifact.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.ISocieteDAO;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;
import System.Solaire.com.WebServiceArtifact.Services.Interface.ISocieteService;

@Service
public class SocieteService  implements ISocieteService{
	@Autowired
	ISocieteDAO societeDAO;
	@Override
	public boolean insertSociete(Societe societe) {
		boolean inserted=this.societeDAO.insert(societe);
		return inserted;
	}

	@Override
	public List<Societe> getAll() {
		List<Societe> Societes=this.societeDAO.getall();
		return Societes;
	}
	boolean updateall=true;
	@Override
	public boolean modiferSociete(String societes) {
		updateall=true;
		ObjectMapper mapper = new ObjectMapper();
		List<Societe> obj = new ArrayList<Societe>();
		try {
			obj = mapper.readValue(societes,
					mapper.getTypeFactory().constructCollectionType(List.class, Societe.class));
			obj.forEach((object)->{
				boolean updated=this.societeDAO.insert(object);
				if(updated==false) {
					updateall=false;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return updateall;
	}

	@Override
	public boolean supprimerSociete(Societe societe) {
		this.societeDAO.supprimer(societe);
		return true;
	}

	@Override
	public Societe getSociete(long idSociete) {

		Societe Societes=this.societeDAO.getSociete( idSociete);
		return Societes;
	}

}
