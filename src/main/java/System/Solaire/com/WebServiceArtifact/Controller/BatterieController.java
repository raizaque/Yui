package System.Solaire.com.WebServiceArtifact.Controller;


import java.util.ArrayList;

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

import System.Solaire.com.WebServiceArtifact.DAO.Interface.IBatterieDAO;
import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Model.SimulationFromServer;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IBatterieService;

import java.util.List;

@Controller
@RequestMapping("batteries")
public class BatterieController {
	@Autowired
	IBatterieDAO iBatterieDAO;
	@Autowired
	IBatterieService batterieService;
	@GetMapping(value= {"/{capacite}"})
	public ResponseEntity<Batterie> getBattrie(@PathVariable("capacite")double capacite){
		Batterie batterie = iBatterieDAO.getBatterieByCapacite(capacite);
		if(batterie!=null) {
			return new ResponseEntity<Batterie>(batterie, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value= {"/all"})
	public ResponseEntity<List<Batterie>> getBattries(){
		List<Batterie> batterie=new ArrayList<Batterie>();
		batterie= batterieService.getAll();
		if(batterie!=null) {
			return new ResponseEntity<List<Batterie>>(batterie, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value= {"/all/{societeid}"})
	public ResponseEntity<List<Batterie>> getBattriesBySociete(@PathVariable(value="societeid")long societeid){
		List<Batterie> batterie=new ArrayList<Batterie>();
		batterie= iBatterieDAO.getBatteries(societeid);
		if(batterie!=null) {
			return new ResponseEntity<List<Batterie>>(batterie, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value= {"/{id}"})
	public ResponseEntity<Batterie> getBattrie(@PathVariable(value="id")long id){
		Batterie batterie= this.batterieService.getBatterie(id);
		if(batterie!=null) {
			return new ResponseEntity<Batterie>(batterie, HttpStatus.OK);}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Ajouter")
	public ResponseEntity<Boolean> ajouterBatterie(@RequestParam(value="nom")String nom,@RequestParam (value="capacite") double capacite
			,@RequestParam (value="puissance") double puissance,@RequestParam(value="societe") String societe){
		Batterie batterie = new Batterie(nom,capacite,puissance,societe);
		if(this.batterieService.insertBatterie(batterie)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/modifier")
	public ResponseEntity<Boolean> modifierBatterie(@RequestBody String listBatteries){
		System.out.println(listBatteries);
		if(this.batterieService.modiferBatterie(listBatteries)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/supprimer")
	public ResponseEntity<Boolean> supprierBatterie(@RequestParam(value="id")long id){
		Batterie batterie = new Batterie(id);
		if(this.batterieService.supprimerBatterie(batterie)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		}
	}
}
