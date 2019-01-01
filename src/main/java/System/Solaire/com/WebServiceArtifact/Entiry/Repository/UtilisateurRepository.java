package System.Solaire.com.WebServiceArtifact.Entiry.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;
import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;

@Component
@RepositoryRestResource(collectionResourceRel = "Utilisateur", path = "Utilisateur")
public interface UtilisateurRepository  extends PagingAndSortingRepository<Utilisateur, Long>{
	List<Utilisateur> findBySociete(@Param("societe") long  idSociete);
	List<Utilisateur> findBySociete(@Param("societe") List<Long>  listIntegers);
	Utilisateur findById(@Param("id") long id);
	Utilisateur findByCompte(@Param("compte")String compte);
}
