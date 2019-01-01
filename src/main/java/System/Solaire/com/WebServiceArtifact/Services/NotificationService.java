package System.Solaire.com.WebServiceArtifact.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import System.Solaire.com.WebServiceArtifact.Entiry.Repository.NotificationRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.StatusRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Notification;
import System.Solaire.com.WebServiceArtifact.Model.NotificationModel;
import System.Solaire.com.WebServiceArtifact.Services.Interface.INotificationService;
@Service
public class NotificationService implements INotificationService{
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private StatusRepository statusRepository;
	public NotificationService() {
		
	}
	@Override
	public List<NotificationModel> getAllNotification(long IdUtilisateur) {
		 List<NotificationModel> notification =new  ArrayList<NotificationModel>();
		 ArrayList<Notification> noti =(ArrayList<Notification>) this.notificationRepository.findTop10ByIdUtilisateurOrderByIdDesc(IdUtilisateur);
		 noti.forEach((item)->{ 
			 notification.add(new NotificationModel(item.getId(),item.getIdUtilisateur(),item.isSeen(),item.getTypeObjet(),item.getNObject()));
		 });
		 notification.forEach((item)->{
			 statusRepository.findByIdObjectAndTypeObjet(item.getIdNotification(), "notification").forEach((status)->{
				 item.setDescription(status.getDescription());
			 });
		 });
		return notification;
	}

	@Override
	public boolean deletNotification(long idNotification) {
		this.notificationRepository.deleteById(idNotification);
		this.statusRepository.deleteByIdObjectAndTypeObjet(idNotification, "notification");
		return true;
	}
	@Override
	public boolean updateSetVuFalse(long idUtilisateur) {
		this.notificationRepository.findAllByIdUtilisateur(idUtilisateur).forEach((item)->{

			item.setSeen(true);
			this.notificationRepository.save(item);
		});;
		return true;
	}
	

}
