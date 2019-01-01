package System.Solaire.com.WebServiceArtifact.Entiry.Repository;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import System.Solaire.com.WebServiceArtifact.Entitry.StatusBD;

@Component
@RepositoryRestResource(collectionResourceRel = "Status", path = "Status")
public interface StatusRepository extends PagingAndSortingRepository<StatusBD, Long>{
	List<StatusBD> findByIdObject(long idObject);
	List<StatusBD> findByIdObjectAndTypeObjet(long idObject,String typeObjet);
	boolean deleteByIdObjectAndTypeObjet(long idNotification, String string);
}
