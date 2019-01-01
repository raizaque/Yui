package System.Solaire.com.WebServiceArtifact.DAO.Interface;

import java.util.List;

import System.Solaire.com.WebServiceArtifact.Entitry.SimulationDB;

public interface ISimulationDAO {
	boolean addSimulation(SimulationDB simulationDB);
	List<SimulationDB> selectAllSimulations();
	List<?> selectDetailSimulationUtilisateur(long IdUtilisateur);
	List<?> selectDetailSimulationPartagerUtilisateur(long IdUtilisateur);
}
