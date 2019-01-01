package System.Solaire.com.WebServiceArtifact.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.ISimulationDAO;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.BatterieRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.SimulationRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.UtilisateurRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.SimulationDB;
import System.Solaire.com.WebServiceArtifact.Model.MoisConsomation;
import System.Solaire.com.WebServiceArtifact.Model.PartageListUtilisateur;
import System.Solaire.com.WebServiceArtifact.Model.PossibiliteSimulation;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;
import System.Solaire.com.WebServiceArtifact.Model.SimulationFromServer;
import System.Solaire.com.WebServiceArtifact.Services.ObjectService;
import System.Solaire.com.WebServiceArtifact.Services.SimulationService;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IChartsService;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IPossibiliteSimulationService;
import System.Solaire.com.WebServiceArtifact.Services.Interface.ISimulationService;
import aj.org.objectweb.asm.Type;
import ch.qos.logback.classic.pattern.Util;
import ch.qos.logback.core.pattern.Converter;

@RestController
@RequestMapping("Simulation")
public class SimulationController {
	@Autowired
	ISimulationService simulationService;
	@Autowired
	Simulation simulation;
	@Autowired
	MoisConsomation moisconsomation;
	@Autowired
	IPossibiliteSimulationService PossibiliteSimulation;
	@Autowired
	ISimulationDAO simlationDAO;
	@Autowired
	IChartsService chartsService;
	@Autowired
	SimulationRepository simulationRepository;
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	private IChartsService chartService;
	@PostMapping(value = { "/" })
	public @ResponseBody ResponseEntity<Simulation> simulation(@RequestParam("file") MultipartFile file,
			@RequestParam("puissance_Installee") double puissance_Installee,
			@RequestParam("Nombre_Heur_Production") double Nombre_Heur_Production,
			@RequestParam("capacite_battrie") double capacite_battrie,
			@RequestParam("valeur_décharge_batterie") double valeur_décharge_batterie,
			@RequestParam("dateDebut") String dateDebut,
			@RequestParam("puissance_maximum_Onduleur") double puissance_maximum_Onduleur,
			@RequestParam("seuil_reseau") double seuil_reseau, @RequestParam("maxbatterie") double maxbatterie,
			@RequestParam("minbatterie") double minbatterie, @RequestParam("patbatterie") double patbatterie,
			@RequestParam("maxPuissance") double maxPuissance, @RequestParam("minPuissance") double minPuissance,
			@RequestParam("patPuissance") double patPuissance, @RequestParam("autoProdvise") double autoProdvise,
			@RequestParam("janvier") double janvier, @RequestParam("fevrier") double fevrier,
			@RequestParam("mars") double mars, @RequestParam("avril") double avril, @RequestParam("mai") double mai,
			@RequestParam("juin") double juin, @RequestParam("juillet") double juillet,
			@RequestParam("aout") double aout, @RequestParam("septembre") double septembre,
			@RequestParam("octobre") double octobre, @RequestParam("novembre") double novembre,
			@RequestParam("decembre") double decembre) throws IOException {
		moisconsomation.setJanvier(janvier);
		moisconsomation.setFevrier(fevrier);
		moisconsomation.setMars(mars);
		moisconsomation.setAvril(avril);
		moisconsomation.setMai(mai);
		moisconsomation.setJuin(juin);
		moisconsomation.setJuillet(juillet);
		moisconsomation.setAout(aout);
		moisconsomation.setSeptembre(septembre);
		moisconsomation.setOctobre(octobre);
		moisconsomation.setNovembre(novembre);
		moisconsomation.setDecembre(decembre);
		simulation.setparams(puissance_Installee, Nombre_Heur_Production, capacite_battrie, valeur_décharge_batterie,
				dateDebut, puissance_maximum_Onduleur, moisconsomation, seuil_reseau);
		PossibiliteSimulation possibiliteSimulation = new PossibiliteSimulation(maxbatterie, minbatterie, patbatterie,
				maxPuissance, minPuissance, patPuissance, autoProdvise * 0.01);
		simulation.setPossibiliteSimulation(possibiliteSimulation);
		if (simulation == null)
			return ResponseEntity.noContent().build();
		simulation.setPossibiliteSimulation(PossibiliteSimulation.possibilite(file, Nombre_Heur_Production,
				valeur_décharge_batterie, dateDebut, puissance_maximum_Onduleur, seuil_reseau, maxbatterie, minbatterie,
				patbatterie, maxPuissance, minPuissance, patPuissance, autoProdvise * 0.01));
		return simulationService.Simulation(file, simulation);
	}

	/////////////////////////////////////////////////////////

	@PostMapping(value = { "/ponderation" })
	public @ResponseBody ResponseEntity<Simulation> ponderation(@RequestParam("file") MultipartFile file,
			@RequestParam("puissance_Installee") double puissance_Installee,
			@RequestParam("Nombre_Heur_Production") double Nombre_Heur_Production,
			@RequestParam("capacite_battrie") double capacite_battrie,
			@RequestParam("valeur_décharge_batterie") double valeur_décharge_batterie,
			@RequestParam("dateDebut") String dateDebut,
			@RequestParam("puissance_maximum_Onduleur") double puissance_maximum_Onduleur,
			@RequestParam("seuil_reseau") double seuil_reseau, @RequestParam("maxbatterie") double maxbatterie,
			@RequestParam("minbatterie") double minbatterie, @RequestParam("patbatterie") double patbatterie,
			@RequestParam("maxPuissance") double maxPuissance, @RequestParam("minPuissance") double minPuissance,
			@RequestParam("patPuissance") double patPuissance, @RequestParam("autoProdvise") double autoProdvise,
			@RequestParam("janvier") double janvier, @RequestParam("fevrier") double fevrier,
			@RequestParam("mars") double mars, @RequestParam("avril") double avril, @RequestParam("mai") double mai,
			@RequestParam("juin") double juin, @RequestParam("juillet") double juillet,
			@RequestParam("aout") double aout, @RequestParam("septembre") double septembre,
			@RequestParam("octobre") double octobre, @RequestParam("novembre") double novembre,
			@RequestParam("decembre") double decembre) throws IOException {
		System.out.println("getting data");
		///////////////
		moisconsomation.setJanvier(janvier);
		moisconsomation.setFevrier(fevrier);
		moisconsomation.setMars(mars);
		moisconsomation.setAvril(avril);
		moisconsomation.setMai(mai);
		moisconsomation.setJuin(juin);
		moisconsomation.setJuillet(juillet);
		moisconsomation.setAout(aout);
		moisconsomation.setSeptembre(septembre);
		moisconsomation.setOctobre(octobre);
		moisconsomation.setNovembre(novembre);
		moisconsomation.setDecembre(decembre);
		simulation.setparams(puissance_Installee, Nombre_Heur_Production, capacite_battrie, valeur_décharge_batterie,
				dateDebut, puissance_maximum_Onduleur, moisconsomation, seuil_reseau);
		PossibiliteSimulation possibiliteSimulation = new PossibiliteSimulation(maxbatterie, minbatterie, patbatterie,
				maxPuissance, minPuissance, patPuissance, autoProdvise * 0.01);
				System.out.println(possibiliteSimulation.getAutoProdvise());
		simulation.setPossibiliteSimulation(possibiliteSimulation);
		System.out.println("....");
		simulationService.SetParamsPonderation(file, simulation);
		System.out.println("....");
		simulation.setPossibiliteSimulation(PossibiliteSimulation.possibilitePoderation(simulation));
		System.out.println("....");
		chartsService.setChartsResultLigne(simulation);
		System.out.println("....");
		simulationService.SimulationPonderation(simulation);
		chartService.resultLigneSemaineWS(simulation);
		chartService.setResultLigneMoyen(simulation);
		simulation.getCharts().setResultsCourbeMoyen(
				chartService.setCourbMoyen((Simulation) ObjectService.deepClone(simulation)));
		System.out.println("end of simulation sending data");
		if (simulation == null)
			return ResponseEntity.noContent().build();
		else
			return new ResponseEntity<Simulation>(simulation, HttpStatus.OK);
	}

	/////////////////////////////////////
	@PostMapping(value = "/{idUtilisateur}/{nomFichier}/{list}/{description}")
	public @ResponseBody ResponseEntity<Boolean> insertSimulation(@RequestBody String simulation,
			@PathVariable("idUtilisateur") int idUtilisateur, @PathVariable("nomFichier") String nomFichier,
			@PathVariable("list") String checkedItems,
			@PathVariable("description") String description) {
		
		SimulationDB simulationDB = new SimulationDB();
		ObjectMapper mapper = new ObjectMapper();
		Simulation objSimulation = new Simulation();
		try {
			objSimulation = mapper.readValue(simulation, Simulation.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			simulationDB.setSimulation(ObjectService.serializeObject(objSimulation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<PartageListUtilisateur> obj=new ArrayList<PartageListUtilisateur>();
		try {
			obj = mapper.readValue("["+checkedItems+"]", mapper.getTypeFactory().constructCollectionType(List.class, PartageListUtilisateur.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		simulationDB.setiUtilisateur(idUtilisateur);
		simulationDB.setNomFichier(nomFichier);
		simulationDB.setUtilisateurActuel("aucune personne");
		boolean status = simulationService.enregistre(simulationDB, checkedItems, description);
		if (status == false)
			return new ResponseEntity<Boolean>(true, HttpStatus.EXPECTATION_FAILED);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);		

	}

	@GetMapping(value = "{idUtilisateur}/getSimulation")
	public @ResponseBody ResponseEntity<List<SimulationFromServer>> getSimulationsParUtilisateur(
			@PathVariable(name = "idUtilisateur") long idUtilisateur) {
		List<SimulationFromServer> simulationDB = this.simulationService.selectSimulation(idUtilisateur);
		if (simulationDB == null)
			return ResponseEntity.noContent().build();
		return new ResponseEntity<List<SimulationFromServer>>(simulationDB, HttpStatus.CREATED);
	}

	@GetMapping(value = "{idUtilisateur}/getSimulationPartager")
	public @ResponseBody ResponseEntity<List<SimulationFromServer>> getSimulationsPartager(
			@PathVariable(name = "idUtilisateur") long idUtilisateur) {
		List<SimulationFromServer> simulationDB=this.simulationService.selectSimulationPartager(idUtilisateur);
		if (simulationDB==null)
			return ResponseEntity.noContent().build();
		return new ResponseEntity<List<SimulationFromServer>>(simulationDB, HttpStatus.CREATED);
	}

	@GetMapping(value = "{idSimulation}/{idUtilisateur}")
	public @ResponseBody ResponseEntity<List<Map<String, Object>>> getSimulations(
			@PathVariable(name = "idSimulation") long idSimulation,
			@PathVariable(name = "idUtilisateur") long idUtilisateur) {
		List<SimulationDB> simulationDB = (List<SimulationDB>) simulationRepository.findById(idSimulation);
		simulationDB.get(0).setUtilisateurActuel(this.utilisateurRepository.findById(idUtilisateur).toString());
		if (simulationDB == null)
			return ResponseEntity.noContent().build();
		return new ResponseEntity<List<Map<String, Object>>>(SimulationService.setArraySimulation(simulationDB),
				HttpStatus.CREATED);
	}

	@PutMapping(value = "/{idUtilisateur}/{nomFichier}/{list}/{description}/{idSimulation}")
	public @ResponseBody ResponseEntity<Boolean> MiseAjourSimulation(@RequestBody String simulation,
			@PathVariable("idUtilisateur") int idUtilisateur, @PathVariable("nomFichier") String nomFichier,
			@PathVariable("list") String checkedItems,
			@PathVariable("description") String description,
			@PathVariable("idSimulation") long idSimulation) {
		boolean supprimer = simulationService.supprimer(idSimulation);
		if (supprimer == false)
			return new ResponseEntity<Boolean>(supprimer, HttpStatus.EXPECTATION_FAILED);
		SimulationDB simulationDB = new SimulationDB();
		Gson gson = new Gson(); // Or use new GsonBuilder().create();
		Simulation target = gson.fromJson(simulation, Simulation.class);
		
		try {
			simulationDB.setSimulation(ObjectService.serializeObject(target));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		simulationDB.setiUtilisateur(idUtilisateur);
		simulationDB.setNomFichier(nomFichier);
		simulationDB.setUtilisateurActuel("aucune personne");

		boolean status = simulationService.enregistre(simulationDB, checkedItems, description);
		if (status == false)
			return new ResponseEntity<Boolean>(status, HttpStatus.EXPECTATION_FAILED);

		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete/{idUtilisateur}/{idSimulation}")
	public @ResponseBody ResponseEntity<Boolean> DeleteSimulation(@PathVariable("idSimulation") long idSimulation
			,@PathVariable("idUtilisateur") long idUtilisateur) {
		boolean supprimer = simulationService.supprimer(idSimulation);
		if (supprimer == false)
			return new ResponseEntity<Boolean>(supprimer, HttpStatus.EXPECTATION_FAILED);
		return new ResponseEntity<Boolean>(supprimer, HttpStatus.OK);
	}
	@GetMapping(value = "listPartager/{idUtilisateur}/{idSimulation}")
	public @ResponseBody ResponseEntity<List<PartageListUtilisateur>>  selectListPartager(@PathVariable("idSimulation") long idSimulation
			,@PathVariable("idUtilisateur") long idUtilisateur) {
		List<PartageListUtilisateur> listPartage = simulationService.getListPartage(idSimulation,idUtilisateur);
		if (listPartage.size()==0)
			return new ResponseEntity< List<PartageListUtilisateur> >(listPartage, HttpStatus.EXPECTATION_FAILED);
		return new ResponseEntity<List< PartageListUtilisateur> >(listPartage, HttpStatus.OK);
	}
	@PutMapping(value = "listPartager/{idSimulation}/{idUtilisateur}")
	public @ResponseBody ResponseEntity<Boolean>  misAJourListPartage(@RequestBody List<PartageListUtilisateur> checkedItems
			,@PathVariable("idUtilisateur") long idUtilisateur,@PathVariable("idSimulation")long idSimulation) {
		
		boolean updated =this.simulationService.updatePartager(checkedItems,idUtilisateur,idSimulation);

		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	@PostMapping(value = "/comparer/{idUtilisateur}")
	public @ResponseBody ResponseEntity<List<Simulation>> Comparer(@RequestBody String simulations,
			@PathVariable("idUtilisateur") int idUtilisateur) {
			List<Simulation> obj=this.simulationService.compare( simulations, idUtilisateur);
			return new ResponseEntity<List<Simulation>>(obj, HttpStatus.OK);
	}

}
