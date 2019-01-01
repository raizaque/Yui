package System.Solaire.com.WebServiceArtifact.DAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.ICompteInfoDAO;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.CompteInfoRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
@Repository
@Transactional
public class CompteInfoDAO implements ICompteInfoDAO{
	@PersistenceContext
	private EntityManager manager;
	// get a user by it pseudo and mot_de_passe
	@Autowired
	private CompteInfoRepository compteInfoRepository;
	@Override
	public CompteInfo getActiveUser(CompteInfo compteInfo) {
		List<?> list = manager.createQuery("SELECT u FROM CompteInfo u "
				+ "WHERE Pseudo= :Pseudo")
				.setParameter("Pseudo", compteInfo.getPseudo()).getResultList();
		if(!list.isEmpty()) {
			compteInfo = (CompteInfo)list.get(0);
		}
		return compteInfo;
	}

	@Override
	public CompteInfo getActiveUser(String username) {
		
		CompteInfo compteinfo=new CompteInfo();
		List<?> list = manager.createQuery("SELECT u FROM CompteInfo u "
				+ "WHERE Pseudo= :Pseudo")
				.setParameter("Pseudo", username).getResultList();
		if(!list.isEmpty()) {
			compteinfo = (CompteInfo)list.get(0);
		}
		return compteinfo;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<CompteInfo> getActiveUsers() {
		String hql = "FROM CompteInfo";
		return (List<CompteInfo>) manager.createQuery(hql).getResultList();
	}
	@Override
	public void addCompte(CompteInfo compteinfo) {
		manager.persist(compteinfo);
	}
	@Override
	public void updateCompte(CompteInfo compteinfo) {

		CompteInfo compte=new CompteInfo();
		compte =getCompteById(compteinfo);
		compte.setPseudo(compteinfo.getPseudo());
		compte.setRole(compteinfo.getRole());
		compte.setMotDePasse(compteinfo.getMotDePasse());
		manager.flush();
	}
	@Override
	public void deleteCompte(long compteinfo) {
		this.compteInfoRepository.deleteById(compteinfo);
	}
	@Override
	public CompteInfo getCompteById(CompteInfo compteinfo) {
		
		return manager.find(CompteInfo.class, compteinfo.getId());
	}

	@Override
	public boolean compteExists(String pseudo) {
		if(this.compteInfoRepository.findByPseudo(pseudo)!=null) {
			return true;
		}
		else
			return false;
	}

	@Override
	public CompteInfo getCompteById(long id_Compte) {
		return (CompteInfo) this.compteInfoRepository.findById(id_Compte);
	}

	@Override
	public CompteInfo getcompteByEmail(String email) {
		
		return this.compteInfoRepository.findByPseudo(email);
	}

	@Override
	public void modifier(CompteInfo compte) {
		this.compteInfoRepository.save(compte);
		
	}
	 
}
