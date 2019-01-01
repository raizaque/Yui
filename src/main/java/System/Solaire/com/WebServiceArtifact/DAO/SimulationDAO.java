package System.Solaire.com.WebServiceArtifact.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.ISimulationDAO;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
import System.Solaire.com.WebServiceArtifact.Entitry.SimulationDB;
@Repository
@Transactional(rollbackFor = Exception.class)
public class SimulationDAO implements ISimulationDAO {

	@PersistenceContext
	private EntityManager manager;
	@Override
	public boolean addSimulation(SimulationDB simulationDB) {
			
		this.manager.persist(simulationDB);
		return true;
	}
	@Override
	public List<SimulationDB> selectAllSimulations() {
		String hql = "FROM SimulationDB";
		return (List<SimulationDB>) manager.createQuery(hql).getResultList();
	}
	public List<?> selectDetailSimulationUtilisateur(long IdUtilisateur){
		
		List<?> list=null;
		try {
			list = manager.createQuery("SELECT SBD.id, SBD.nomFichier,SBD.iUtilisateur, SBD.utilisateurActuel"+
					",Uti.id, Uti.nom,Uti.prenom,Uti.Adress "+
					",Soc.id,Soc.nom,Soc.adress"+
					",S.id, S.description,S.idObject, ST.id,ST.description,ST.idObject"+
					" FROM SimulationDB SBD, Utilisateur Uti,Societe Soc,StatusBD S,StatusBD ST"+
					 " WHERE ((SBD.iUtilisateur= Uti.id and Uti.id=:id)) and Uti.idSociete=Soc.id"+
					" and (S.idObject=SBD.id and S.typeObjet='simulation')and"+
					" (ST.idObject=SBD.id and ST.typeObjet='simulationStatus')")
					.setParameter("id", IdUtilisateur)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  list;
	}
	public List<?> selectDetailSimulationPartagerUtilisateur(long IdUtilisateur){
		
		List<?> list=null;
		try {
			list = manager.createQuery("SELECT SBD.id, SBD.nomFichier,SBD.iUtilisateur, SBD.utilisateurActuel"+
					",Uti.id, Uti.nom,Uti.prenom,Uti.Adress "+
					",Soc.id,Soc.nom,Soc.adress"+
					",S.id, S.description,S.idObject, ST.id,ST.description,ST.idObject"+
					" FROM Partage Par,SimulationDB SBD, Utilisateur Uti,Societe Soc,StatusBD S,StatusBD ST"+
					" WHERE ((SBD.iUtilisateur= Uti.id )) and Uti.idSociete=Soc.id and"+
					" (S.idObject=SBD.id and S.typeObjet='simulation')and (ST.idObject=SBD.id and "+
					"ST.typeObjet='simulationStatus') and Par.idSimulation=SBD.id and Par.idUtilisateurRecepteur=:id and"+
					" Par.idUtilisateursource=SBD.iUtilisateur")
					.setParameter("id", IdUtilisateur)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  list;
	}

}
