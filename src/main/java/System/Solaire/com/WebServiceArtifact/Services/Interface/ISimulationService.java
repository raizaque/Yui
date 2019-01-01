package System.Solaire.com.WebServiceArtifact.Services.Interface;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

import System.Solaire.com.WebServiceArtifact.Entitry.SimulationDB;
import System.Solaire.com.WebServiceArtifact.Model.PartageListUtilisateur;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;
import System.Solaire.com.WebServiceArtifact.Model.SimulationFromServer;

public interface ISimulationService {
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    ResponseEntity<Simulation> Simulation(MultipartFile file, Simulation simulation) ;
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    void  SimulationPonderation(Simulation simulation) ;
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	void SetParamsPonderation(MultipartFile file, Simulation simulation);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	void SimulationPonderationSansProduction(Simulation simulationClone);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    boolean enregistre(SimulationDB simulationDB, String checkedItems, String description);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    boolean supprimer(long idSimulation);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	List<PartageListUtilisateur> getListPartage(long idSimulation,long idsourceUtilisateur);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	boolean updateListPartager(List<PartageListUtilisateur> checkedItems, long idUtilisateur, long idSimulation);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	boolean updatePartager(List<PartageListUtilisateur> checkedItems, long idUtilisateur, long idSimulation);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	List<SimulationFromServer> selectSimulationPartager(long idUtilisateur);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	List<SimulationFromServer> selectSimulation(long idUtilisateur);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	List<Simulation> compare(String simulations, int idUtilisateur);
}
