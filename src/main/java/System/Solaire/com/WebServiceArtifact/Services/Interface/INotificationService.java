package System.Solaire.com.WebServiceArtifact.Services.Interface;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import System.Solaire.com.WebServiceArtifact.Model.NotificationModel;

public interface INotificationService {

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    List<NotificationModel> getAllNotification(long IdUtilisateur);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    boolean deletNotification(long IdUtilisateur);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	boolean updateSetVuFalse(long idUtilisateur);
}
