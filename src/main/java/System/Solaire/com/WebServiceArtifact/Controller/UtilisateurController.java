package System.Solaire.com.WebServiceArtifact.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import System.Solaire.com.WebServiceArtifact.Entiry.Repository.CompteInfoRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.UtilisateurRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;
import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;
import System.Solaire.com.WebServiceArtifact.Model.UtilisateurFromServeur;
import System.Solaire.com.WebServiceArtifact.Services.Interface.ICompteInfoService;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IUtilisateurService;

@Controller
@RequestMapping("utilisateurs")
public class UtilisateurController {
	@Autowired 
	UtilisateurRepository utilisateurRepository;
	@Autowired
	IUtilisateurService utilisateurservice;
	@Autowired
	ICompteInfoService compteinfoService;
	@GetMapping("/all")
	public ResponseEntity<List<UtilisateurFromServeur>> getAllUtilisateurs(){
		List<UtilisateurFromServeur> Listutilisateur=this.utilisateurservice.findall();
		if(Listutilisateur!=null) {
			return new ResponseEntity<List<UtilisateurFromServeur>>(Listutilisateur, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	@GetMapping("{idSociete}/all")
	public ResponseEntity<List<Utilisateur>> getAllSocieteUtilisateurs(@PathVariable(value="idSociete")long idSociete){
		List<Utilisateur> Listutilisateur=(ArrayList)this.utilisateurRepository.findBySociete(idSociete);
		if(Listutilisateur!=null) {
			return new ResponseEntity<List<Utilisateur>>(Listutilisateur, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	@GetMapping("{idSociete}")
	public ResponseEntity<List<Utilisateur>> getAllMSocieteUtilisateurs(@PathVariable(value="idSociete")String idSociete){
		List<String>  idsocieteString=Arrays.asList(idSociete.split(","));
		List<Utilisateur> Listutilisateur=new ArrayList<Utilisateur>();
		idsocieteString.forEach((id)->{
			this.utilisateurRepository.findBySociete(Long.parseLong(id)).forEach((item)->{
				Listutilisateur.add(item);
			});
			  
		});
		if(Listutilisateur!=null) {
			return new ResponseEntity<List<Utilisateur>>(Listutilisateur, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Ajouter")
	public @ResponseBody ResponseEntity<Boolean> ajouterUtilisateur(
			@RequestParam(value="photo")String photo,@RequestParam(value="nom")String nom,
			@RequestParam(value="prenom")String prenom,@RequestParam(value="role")String role
			,@RequestParam(value="societe")String societe, @RequestParam(value="adress")String adress,
			@RequestParam(value="email")String email,@RequestParam(value="motDePasse")String motDePasse,
			@RequestParam(value="dateFinLicence")String dateFinLicence){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		CompteInfo compte= new CompteInfo(email,encoder.encode(motDePasse),dateFinLicence,role);
		boolean added=this.compteinfoService.addCompte(compte);
		if(!added)
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		Utilisateur user =new Utilisateur( nom,  prenom,  adress,  photo,  societe,
				this.compteinfoService.getCompteByEmail(email).getId()+"");
		added=this.utilisateurservice.addUtilisateur(user);
		if(!added) {
			this.compteinfoService.deleteCompte(Integer.parseInt(user.getCompte()));
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);

	}
	@PutMapping("/modifier")
	public @ResponseBody ResponseEntity<Boolean> modifierUtilisateur(@RequestBody String listUtilisateur ){
		if(this.utilisateurservice.modiferUtilisateurs(listUtilisateur)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
	}	
	@DeleteMapping("/supprimer")
	public @ResponseBody ResponseEntity<Boolean> supprimerutilisateur(@RequestParam(value="id")String id ){
		if(this.utilisateurservice.supprimerUtilisateurs(Long.parseLong(id))) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/selectUser/{idcompte}")
	public @ResponseBody ResponseEntity<Utilisateur> getUtilisateur(@PathVariable(value="idcompte")String idcompte ){
		Utilisateur user= this.utilisateurservice.selectByIdCompte(idcompte);
		if(user!=null) {
			return new ResponseEntity<Utilisateur>(user,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
		}
	}	
	@PostMapping("/updateuser")
	public @ResponseBody ResponseEntity<Utilisateur> mAJUtilisateur(@RequestParam(value="idcompte") String idcompte,
			@RequestParam(value="role") String role,@RequestParam(value="pseudo") String pseudo,
			@RequestParam(value="mot_de_passe") String mot_de_passe,@RequestParam(value="token") String token,
			@RequestParam(value="datefin") String datefin,@RequestParam(value="iduser") String iduser,
			@RequestParam(value="nom") String nom,@RequestParam(value="adress") String adress,
			@RequestParam(value="prenom") String prenom,
		    @RequestParam(value="compte") String compte,@RequestParam(value="photo") String photo,
		    @RequestParam(value="societe") String societe){

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			CompteInfo compte1=new CompteInfo(Long.parseLong(idcompte),role,pseudo,encoder.encode(mot_de_passe),token,datefin);
			this.compteinfoService.updateCompte(compte1);
			Utilisateur utilisateur= new Utilisateur(Long.parseLong(iduser), nom, prenom, adress, compte, photo, Long.parseLong(societe));
			this.utilisateurservice.addUtilisateur(utilisateur);
			return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
		
	}
}
