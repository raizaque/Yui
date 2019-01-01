package System.Solaire.com.WebServiceArtifact.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.IBatterieDAO;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.BatterieRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;

@Repository
@Transactional
public class BatterieDAO implements IBatterieDAO{
	@Autowired
	BatterieRepository batterieRepository;
	@PersistenceContext
	private EntityManager manager;
	@Override
	
	public List<Batterie> getBatteries() {
		return (List<Batterie>)this.batterieRepository.findAll();
	}

	@Override
	public Batterie getBatterieByCapacite(double capacite) {
		Batterie batterie=new Batterie();
		List<?> list = manager.createQuery("SELECT u FROM Batterie u "
				+ "WHERE Capacite= :capacite")
				.setParameter("capacite", capacite).getResultList();
		if(!list.isEmpty()) {
			batterie = (Batterie)list.get(0);
		}
		return batterie;
	}
	@Override
	public List<Batterie> getBatteries(double min, double max){
		
		String hql = "FROM Batterie WHERE Capacite<= :max and Capacite>= :min order by Capacite";
		List<Batterie> resultList = (List<Batterie>) manager.createQuery(hql).setParameter("max", max)
				.setParameter("min", min)
				.getResultList();
		return resultList;
	}
	@Override
	public boolean insert(Batterie batterie) {
		if(this.batterieRepository.save(batterie) != null) {
			return true;
		}
		return false;
	}
	@Override
	public boolean supprimer(Batterie batterie) {
		this.batterieRepository.delete(batterie);
		return true;
	}

	@Override
	public Batterie getBatterie(long id) {
		return (Batterie) this.batterieRepository.findById(id);
	}

	@Override
	public List<Batterie> getBatteries(long societeid) {
		
		return (List<Batterie>) this.batterieRepository.findBySociete(societeid) ;
	}
	@Override
	public List<Batterie> getAll() {
		return (List<Batterie>) this.batterieRepository.findAll();
	}
}
