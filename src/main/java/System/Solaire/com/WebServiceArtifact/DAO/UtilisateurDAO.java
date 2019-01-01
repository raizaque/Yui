package System.Solaire.com.WebServiceArtifact.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.IUtilisateurDAO;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.UtilisateurRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;

@Repository
@Transactional
public class UtilisateurDAO implements IUtilisateurDAO{
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Override
	public List<Utilisateur> findall() {
		return (List<Utilisateur>)utilisateurRepository.findAll();
	}
	@Override
	public boolean addUtilisateur(Utilisateur user) {
		if(this.utilisateurRepository.save(user)!=null) {
			return true;
		}
		else
			return false;
	}
	@Override
	public boolean supprimerUtilisateur(Utilisateur utilisateur) {
		this.utilisateurRepository.delete(utilisateur);
		return true;
	}
	@Override
	public boolean modifier(Utilisateur utilisateur) {
		this.utilisateurRepository.save(utilisateur);
		return true;
	}
	@Override
	public Utilisateur findById(long id) {
		return this.utilisateurRepository.findById(id);
	}
	@Override
	public void deleteById(long id) {
		this.utilisateurRepository.deleteById(id);
		
	}
	@Override
	public Utilisateur findByCompte(String compte) {
		return this.utilisateurRepository.findByCompte(compte);
	}

}
