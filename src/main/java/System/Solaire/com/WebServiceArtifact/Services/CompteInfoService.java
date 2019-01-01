package System.Solaire.com.WebServiceArtifact.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.ICompteInfoDAO;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.CompteInfoRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
import System.Solaire.com.WebServiceArtifact.Services.Interface.ICompteInfoService;
@Service
public class CompteInfoService implements ICompteInfoService{
	@Autowired
	ICompteInfoDAO iCompteInfoDAO;
	@Autowired
	CompteInfo compteInfo;
	@Autowired
	CompteInfoRepository compteInfoRepository;
	@Override
	public List<CompteInfo> getActiveUsers() {
		return iCompteInfoDAO.getActiveUsers();
	}
	@Override
	public CompteInfo getCompteById(int compteInfoID) {
		compteInfo.setId(compteInfoID);
		return iCompteInfoDAO.getCompteById(compteInfo);
	}
	@Override
	public boolean addCompte(CompteInfo compteInfo) {
		String uniqueID =compteInfo.getPseudo()+UUID.randomUUID().toString();
		compteInfo.setToken(uniqueID);
        if (iCompteInfoDAO.compteExists(compteInfo.getPseudo())) {
	        return false;
         } else {
	        iCompteInfoDAO.addCompte(compteInfo);
	        return true;
         }
	}
	@Override
	public void updateCompte(CompteInfo compteInfo) {
		iCompteInfoDAO.updateCompte(compteInfo);
	}
	@Override
	public boolean deleteCompte(int CompteInfoID) {
		iCompteInfoDAO.deleteCompte(CompteInfoID);
		return true;
	}
	@Override
	public CompteInfo getCompte(String pseudo, String motDePasse) {
		this.compteInfoRepository.findByPseudoAndMotDePasse(pseudo,motDePasse);
		return null;
	}
	@Override
	public CompteInfo getCompteByEmail(String email) {
		
		return this.iCompteInfoDAO.getcompteByEmail(email);
	}
	@Override
	public boolean modiferUtilisateurs(String listUtilisateur) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean modifer(CompteInfo compte) {
		this.iCompteInfoDAO.modifier(compte);
		return true;
	}
	@Override
	public void deleteCompteByID(long parseLong) {
		this.iCompteInfoDAO.deleteCompte(parseLong);
		
	}
}
