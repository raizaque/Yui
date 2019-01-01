package System.Solaire.com.WebServiceArtifact.Entiry.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import System.Solaire.com.WebServiceArtifact.Entitry.Partage;

@Component
@RepositoryRestResource(collectionResourceRel = "Partage", path = "Partage")
public interface PartageRepository  extends PagingAndSortingRepository<Partage, Long>{

	List<Partage> findByIdSimulation(long idSimulation); 
	List<Partage> findByIdUtilisateursource(long idUtilisateursource);
	List<Partage> findByIdSimulationAndIdUtilisateursource(long idSimulation,long idUtilisateursource); 
	void deleteByIdSimulation(long idSimulation);
	@Transactional
	void deleteByIdSimulationAndIdUtilisateursource(long idSimulation, long idUtilisateursource);
	List<Partage> findByUtilisateurRecepteur(long utilisateurRecepteur);
}
