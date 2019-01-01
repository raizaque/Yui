package System.Solaire.com.WebServiceArtifact.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Model.NotificationModel;
import System.Solaire.com.WebServiceArtifact.Services.Interface.INotificationService;

@RestController
@RequestMapping("Notification")
public class NotificationController {
	@Autowired
	INotificationService notificationservice;
	@GetMapping(value= {"/{idUtilisateur}"})
	public ResponseEntity<List<NotificationModel>> getNotification(@PathVariable("idUtilisateur")long  idUtilisateur){
		List<NotificationModel> notification= this.notificationservice.getAllNotification(idUtilisateur);
		if(notification.size()!=0) {
			return new ResponseEntity<List<NotificationModel>>(notification, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping(value= {"/{idUtilisateur}"})
	public ResponseEntity<Boolean> deletNotification(@PathVariable("idUtilisateur")long  idUtilisateur){
		boolean notification= this.notificationservice.deletNotification(idUtilisateur);
		if(notification) {
			return new ResponseEntity<Boolean>(notification, HttpStatus.OK);}
		else {
			return new ResponseEntity<Boolean>(notification,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value= {"Update/{idUtilisateur}"})
	public ResponseEntity<Boolean> UpdateNotification(@PathVariable("idUtilisateur")long  idUtilisateur){
		boolean notification= this.notificationservice.updateSetVuFalse(idUtilisateur);
		if(notification) {
			return new ResponseEntity<Boolean>(notification, HttpStatus.OK);}
		else {
			return new ResponseEntity<Boolean>(notification,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
