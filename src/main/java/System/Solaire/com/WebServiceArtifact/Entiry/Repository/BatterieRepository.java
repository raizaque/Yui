package System.Solaire.com.WebServiceArtifact.Entiry.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
@Component
@RepositoryRestResource(collectionResourceRel = "Batterie", path = "Batterie")
public interface BatterieRepository extends PagingAndSortingRepository<Batterie, Long>{
	List<Batterie> findByCapacite(@Param("Capacite") double capacite);
	Batterie findById(@Param("id") long id);
	List<Batterie> findBySociete(@Param("societe")  long societeid);
}	
