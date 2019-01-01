package System.Solaire.com.WebServiceArtifact.Entiry.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import System.Solaire.com.WebServiceArtifact.Entitry.SimulationDB;
@Component
@RepositoryRestResource(collectionResourceRel = "simulation", path = "simulation")
public interface SimulationRepository extends PagingAndSortingRepository<SimulationDB, Long>{
	List<SimulationDB> findBynomFichier(@Param("nomFichier") String nomFichier);
	 SimulationDB findFirstByOrderByIdDesc();
	 List<SimulationDB>  findById(long id);
	List<SimulationDB> findByIUtilisateur(long iUtilisateur);
}
