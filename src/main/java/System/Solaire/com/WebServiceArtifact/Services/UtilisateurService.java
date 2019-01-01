package System.Solaire.com.WebServiceArtifact.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.ICompteInfoDAO;
import System.Solaire.com.WebServiceArtifact.DAO.Interface.ISocieteDAO;
import System.Solaire.com.WebServiceArtifact.DAO.Interface.IUtilisateurDAO;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;
import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;
import System.Solaire.com.WebServiceArtifact.Model.UtilisateurFromServeur;
import System.Solaire.com.WebServiceArtifact.Services.Interface.ICompteInfoService;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IUtilisateurService;
@Service
public class UtilisateurService implements IUtilisateurService
{
	@Autowired
	IUtilisateurDAO utilisateurDAO;
	@Autowired
	ICompteInfoDAO compteInfoDAO;
	@Autowired
	ISocieteDAO societeDAO;
	@Autowired
	ICompteInfoService compteService;
	@Override
	public List<UtilisateurFromServeur> findall() {
		List<UtilisateurFromServeur> listutilisateur =new ArrayList<>();
		 this.utilisateurDAO.findall().forEach((object)->{
			 UtilisateurFromServeur user = new UtilisateurFromServeur();
			 user.setUtilisateur(object);
			 user.setCompte(this.compteInfoDAO.getCompteById(Long.parseLong(object.getCompte())));
			 listutilisateur.add(user);
		 });
		 return listutilisateur;
	}
	@Override
	public boolean addUtilisateur(Utilisateur user) {
		return this.utilisateurDAO.addUtilisateur(user);
		
	}
	boolean updateall;
	@Override
	public boolean modiferUtilisateurs(String listUtilisateur) {
		updateall=true;
		ObjectMapper mapper = new ObjectMapper();
		List<UtilisateurFromServeur> obj = new ArrayList<UtilisateurFromServeur>();
		try {
			obj = mapper.readValue(listUtilisateur,
					mapper.getTypeFactory().constructCollectionType(List.class, UtilisateurFromServeur.class));
			obj.forEach((object)->{
				if(!this.utilisateurDAO.modifier(object.getUtilisateur()))
					updateall=false;
				if(!this.compteService.modifer(object.getCompte()))
					updateall=false;
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return updateall;
	}
	@Override
	public boolean supprimerUtilisateurs(long id) {
		Utilisateur user=this.utilisateurDAO.findById(id);
		this.compteService.deleteCompteByID(Long.parseLong(user.getCompte()));
		this.utilisateurDAO.deleteById(user.getId());
		return true;
	}
	@Override
	public Utilisateur selectByIdCompte(String idcompte) {
		return this.utilisateurDAO.findByCompte(""+idcompte);
	}

}
