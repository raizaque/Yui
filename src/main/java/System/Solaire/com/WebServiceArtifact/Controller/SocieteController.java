package System.Solaire.com.WebServiceArtifact.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import System.Solaire.com.WebServiceArtifact.Entiry.Repository.SocieteRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.UtilisateurRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;
import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;
import System.Solaire.com.WebServiceArtifact.Services.Interface.ISocieteService;

@Controller
@RequestMapping("Societe")
public class SocieteController {
	@Autowired 
	SocieteRepository societeRepository;
	@Autowired
	ISocieteService societeService;
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Societe>> getAllSocietes(){
		List<Societe> Societes=this.societeService.getAll();
		if(Societes!=null) {
			return new ResponseEntity<List<Societe>>(Societes, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Societe> getSociete(@PathVariable(value="id")long idSociete){
		Societe Societe=this.societeService.getSociete(idSociete);
		if(Societe!=null) {
			return new ResponseEntity<Societe>(Societe, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Ajouter")
	public @ResponseBody ResponseEntity<Boolean> ajouterSociete(@RequestParam(value="nom")String nom,@RequestParam (value="adresse") String adresse){
		Societe societe = new Societe(nom,adresse);
		if(this.societeService.insertSociete(societe)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/modifier")
	public @ResponseBody ResponseEntity<Boolean> modifierSociete(@RequestBody String listSociete ){
		if(this.societeService.modiferSociete(listSociete)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/supprimer")
	public @ResponseBody ResponseEntity<Boolean> supprierSociete(@RequestParam(value="id")long id){
		Societe societe = new Societe(id);
		if(this.societeService.supprimerSociete(societe)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
	}
}
