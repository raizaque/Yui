package System.Solaire.com.WebServiceArtifact.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.ISocieteDAO;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.SocieteRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;

@Repository
@Transactional
public class SocieteDAO implements ISocieteDAO{

	@Autowired 
	SocieteRepository societeRepository;
	@Override
	public List<Societe> getall() {
		// TODO Auto-generated method stub
		List<Societe> Societes=(ArrayList<Societe>)this.societeRepository.findAll();
		return Societes;
	}
	@Override
	public boolean insert(Societe societe) {
		if(this.societeRepository.save(societe) != null) {
			return true;
		}
		return false;
	}
	@Override
	public boolean supprimer(Societe societe) {
		this.societeRepository.delete(societe);
		return true;
	}
	@Override
	public Societe getSociete(long idSociete) {
		
		return (Societe) this.societeRepository.findById(idSociete);
	}

}
