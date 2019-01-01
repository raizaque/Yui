package System.Solaire.com.WebServiceArtifact.DAO.Interface;

import java.util.List;

import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;

public interface IBatterieDAO {
	List<Batterie> getBatteries();
	Batterie getBatterieByCapacite(double capacite);
	List<Batterie> getBatteries(double min, double max);
	Batterie getBatterie(long id);
	List<Batterie> getBatteries(long societeid);
	boolean insert(Batterie batterie);
	boolean supprimer(Batterie batterie);
	List<Batterie> getAll();
}
