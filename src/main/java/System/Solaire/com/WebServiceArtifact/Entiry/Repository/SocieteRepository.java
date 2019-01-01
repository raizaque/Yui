package System.Solaire.com.WebServiceArtifact.Entiry.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;

@Component
@RepositoryRestResource(collectionResourceRel = "Societe", path = "Societe")
public interface SocieteRepository extends PagingAndSortingRepository<Societe, Long>{
	Societe findById(long id);
}
