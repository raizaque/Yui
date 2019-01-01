package System.Solaire.com.WebServiceArtifact.Entiry.Repository;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
@Component
@RepositoryRestResource(collectionResourceRel = "CompteInfo", path = "CompteInfo")
public interface CompteInfoRepository extends PagingAndSortingRepository<CompteInfo, Long> {
	CompteInfo findByPseudo(@Param("Pseudo") String pseudo);

	CompteInfo findByPseudoAndMotDePasse(String pseudo, String motDePasse);
	CompteInfo findById(long id);

}