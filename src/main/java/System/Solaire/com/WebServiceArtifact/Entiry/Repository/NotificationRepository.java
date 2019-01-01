package System.Solaire.com.WebServiceArtifact.Entiry.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import System.Solaire.com.WebServiceArtifact.Entitry.Notification;
import System.Solaire.com.WebServiceArtifact.Model.NotificationModel;

@Component
@RepositoryRestResource(collectionResourceRel = "Notification", path = "Notification")
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {
	public Notification findFirstByOrderByIdDesc();
	public Notification findByOrderByIdDesc();
	public List<Notification> findAllByIdUtilisateur(long idUtilisateur);
	public List<Notification> findTop10ByIdUtilisateur(long idUtilisateur);
	public List<Notification> findTop10ByIdUtilisateurOrderByIdDesc(long idUtilisateur);
	public List<Notification> findByNObjectAndTypeobjet(long idObject,String typeobjet);
}
