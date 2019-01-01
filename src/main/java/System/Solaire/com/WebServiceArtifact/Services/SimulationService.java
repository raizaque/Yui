package System.Solaire.com.WebServiceArtifact.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import System.Solaire.com.WebServiceArtifact.Entiry.Repository.BatterieRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.NotificationRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.PartageRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.SimulationRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.SocieteRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.StatusRepository;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.UtilisateurRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Notification;
import System.Solaire.com.WebServiceArtifact.Entitry.Partage;
import System.Solaire.com.WebServiceArtifact.Entitry.SimulationDB;
import System.Solaire.com.WebServiceArtifact.Entitry.StatusBD;
import System.Solaire.com.WebServiceArtifact.Entitry.Utilisateur;
import System.Solaire.com.WebServiceArtifact.Model.PartageListUtilisateur;
import System.Solaire.com.WebServiceArtifact.Model.ResultLigne;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;
import System.Solaire.com.WebServiceArtifact.Model.SimulationFromServer;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IChartsService;
import System.Solaire.com.WebServiceArtifact.Services.Interface.ISimulationService;
@Service
public class SimulationService implements ISimulationService {
	private ResultatLigneService resultatLigneService = new ResultatLigneService();
	private PossibiliteSimulationService possibiliteSimulationService = new PossibiliteSimulationService();
	private ArrayList<ResultLigne> resultLignes = new ArrayList<ResultLigne>();
	private double sommeProduction;
	private double sommeAutoConsomation;
	private double sommeConsomation;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private PartageRepository partageRepository;
	@Autowired
	private SimulationRepository simulationRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private SocieteRepository societeRepository;
	@Autowired
	private IChartsService chartService;
	@Autowired
	private BatterieRepository batterieRepository;
	private boolean poderation = false;

	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<Simulation> Simulation(MultipartFile file, Simulation simulation) {
		/*
		 * try { // recupération et lecture du excel en utilisan la librerie poi
		 * FileInputStream excelFile = (FileInputStream) file.getInputStream(); Workbook
		 * workbook = new XSSFWorkbook(excelFile); Sheet sheet = workbook.getSheetAt(0);
		 * Iterator<Row> rows = sheet.iterator(); DataFormatter dataFormatter = new
		 * DataFormatter(); resultLignes.clear(); /////////////// file //////////// loop
		 * /// iteration sur l'excel + calcul try {
		 * 
		 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); Date
		 * datedebut = sdf.parse(simulation.getDateDebut()+ " 00:00:00"); Calendar cl =
		 * Calendar.getInstance(); cl.setTime(datedebut); while (rows.hasNext()) {
		 * ResultLigne resultLigne = new ResultLigne(); Row currentRow = rows.next();
		 * Iterator<Cell> cellsInRow = currentRow.cellIterator(); while
		 * (cellsInRow.hasNext()) { Cell currentCell = cellsInRow.next(); String
		 * cellAdress = currentCell.getAddress().toString(); String cellValue =
		 * dataFormatter.formatCellValue(currentCell) + ""; if (cellValue.equals(""))
		 * cellValue = "0"; if (cellValue.matches(".*\\d+.*")) { switch
		 * (cellAdress.substring(0, 1)) { case "A":
		 * resultLigne.setProfil_Helda(Double.parseDouble(cellValue.replace(",", ".")));
		 * break; case "B":
		 * resultLigne.setConso_wh(Double.parseDouble(cellValue.replace(",", ".")));
		 * break; } } Date setdate2 = cl.getTime(); cl.add(Calendar.MINUTE, 60);
		 * resultLigne.setDate(new
		 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(setdate2).toString()); }
		 * resultLignes.add(resultLigne); } simulation.setResults(resultLignes); } catch
		 * (Exception e) { e.printStackTrace(); }
		 * resultatLigneService.traitementLigne(simulation); workbook.close();
		 * excelFile.close();
		 * 
		 * getPorduction(simulation); return new ResponseEntity<Simulation>(simulation,
		 * HttpStatus.OK); } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void SimulationPonderation(Simulation simulation) {
		try {
			try {
				resultatLigneService.traitementLigne(simulation);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void SimulationPonderationSansProduction(Simulation simulation) {
		try {
			resultatLigneService.traitementLigneSansProduction(simulation);
			// getPorduction(simulation);
			chartService.resultLigneSemaineWS(simulation);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getPorduction(Simulation simulation) {

		double productioJanvier = 0;
		double productioFevrier = 0;
		double productioMars = 0;
		double productioAvril = 0;
		double productioMai = 0;
		double productioJuin = 0;
		double productioetJuillet = 0;
		double productioAout = 0;
		double productioSeptembre = 0;
		double productioOctobre = 0;
		double productioNovembre = 0;
		double productioDecembre = 0;
		////////////
		double autoconsomationJanvier = 0;
		double autoconsomationFevrier = 0;
		double autoconsomationMars = 0;
		double autoconsomationAvril = 0;
		double autoconsomationMai = 0;
		double autoconsomationJuin = 0;
		double autoconsomationetJuillet = 0;
		double autoconsomationAout = 0;
		double autoconsomationSeptembre = 0;
		double autoconsomationOctobre = 0;
		double autoconsomationNovembre = 0;
		double autoconsomationDecembre = 0;

		for (ResultLigne item : simulation.getResults()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			Date dateSimulation = null;

			try {
				dateSimulation = sdf.parse(item.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (dateSimulation.getMonth()) {
			case 0:
				autoconsomationJanvier = autoconsomationJanvier + item.getAutoConsommation();
				productioJanvier = productioJanvier + item.getProduction_Wh();
				break;
			case 1:
				autoconsomationFevrier = autoconsomationFevrier + item.getAutoConsommation();
				productioFevrier = productioFevrier + item.getProduction_Wh();
				break;
			case 2:
				autoconsomationMars = autoconsomationMars + item.getAutoConsommation();
				productioMars = productioMars + item.getProduction_Wh();
				break;
			case 3:
				autoconsomationAvril = autoconsomationAvril + item.getAutoConsommation();
				productioAvril = productioAvril + item.getProduction_Wh();
				break;
			case 4:
				autoconsomationMai = autoconsomationMai + item.getAutoConsommation();
				productioMai = productioMai + item.getProduction_Wh();
				break;
			case 5:
				autoconsomationJuin = autoconsomationJuin + item.getAutoConsommation();
				productioJuin = productioJuin + item.getProduction_Wh();
				break;
			case 6:
				autoconsomationetJuillet = autoconsomationetJuillet + item.getAutoConsommation();
				productioetJuillet = productioetJuillet + item.getProduction_Wh();
				break;
			case 7:
				autoconsomationAout = autoconsomationAout + item.getAutoConsommation();
				productioAout = productioAout + item.getProduction_Wh();
				break;
			case 8:
				autoconsomationSeptembre = autoconsomationSeptembre + item.getAutoConsommation();
				productioSeptembre = productioSeptembre + item.getProduction_Wh();
				break;
			case 9:
				autoconsomationOctobre = autoconsomationOctobre + item.getAutoConsommation();
				productioOctobre = productioOctobre + item.getProduction_Wh();
				break;
			case 10:

				autoconsomationNovembre = autoconsomationNovembre + item.getAutoConsommation();

				productioNovembre = productioNovembre + item.getProduction_Wh();
				break;
			case 11:
				autoconsomationDecembre = autoconsomationDecembre + item.getAutoConsommation();
				productioDecembre = productioDecembre + item.getProduction_Wh();
				break;

			}
		}
		simulation.getMoisConsomation().setJanvier(productioJanvier / 1000);
		simulation.getMoisConsomation().setFevrier(productioFevrier / 1000);
		simulation.getMoisConsomation().setMars(productioMars / 1000);
		simulation.getMoisConsomation().setAvril(productioAvril / 1000);
		simulation.getMoisConsomation().setMai(productioMai / 1000);
		simulation.getMoisConsomation().setJuin(productioJuin / 1000);
		simulation.getMoisConsomation().setJuillet(productioetJuillet / 1000);
		simulation.getMoisConsomation().setAout(productioAout / 1000);
		simulation.getMoisConsomation().setSeptembre(productioSeptembre / 1000);
		simulation.getMoisConsomation().setOctobre(productioOctobre / 1000);
		simulation.getMoisConsomation().setNovembre(productioNovembre / 1000);
		simulation.getMoisConsomation().setDecembre(productioDecembre / 1000);
	}

	private Simulation adjustConsomation(Simulation simulation) {
		double consomationJanvier = 0;
		double consomationFevrier = 0;
		double consomationMars = 0;
		double consomationAvril = 0;
		double consomationMai = 0;
		double consomationJuin = 0;
		double consomationetJuillet = 0;
		double consomationAout = 0;
		double consomationSeptembre = 0;
		double consomationOctobre = 0;
		double consomationNovembre = 0;
		double consomationDecembre = 0;
		///////////////////////////////////////////
		double ratioErrorJanvier = 1;
		double ratioErrorFevrier = 1;
		double ratioErrorMars = 1;
		double ratioErrorAvril = 1;
		double ratioErrorMai = 1;
		double ratioErrorJuin = 1;
		double ratioErroretJuillet = 1;
		double ratioErrorAout = 1;
		double ratioErrorSeptembre = 1;
		double ratioErrorOctobre = 1;
		double ratioErrorNovembre = 1;
		double ratioErrorDecembre = 1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date dateSimulation = null;
		for (ResultLigne item : simulation.getResults()) {
			try {
				dateSimulation = sdf.parse(item.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (dateSimulation.getMonth()) {
			case 0:
				consomationJanvier = consomationJanvier + item.getConso_wh();
				break;
			case 1:
				consomationFevrier = consomationFevrier + item.getConso_wh();
				break;
			case 2:
				consomationMars = consomationMars + item.getConso_wh();
				break;
			case 3:
				consomationAvril = consomationAvril + item.getConso_wh();
				break;
			case 4:
				consomationMai = consomationMai + item.getConso_wh();
				break;
			case 5:
				consomationJuin = consomationJuin + item.getConso_wh();
				break;
			case 6:
				consomationetJuillet = consomationetJuillet + item.getConso_wh();
				break;
			case 7:
				consomationAout = consomationAout + item.getConso_wh();
				break;
			case 8:
				consomationSeptembre = consomationSeptembre + item.getConso_wh();
				break;
			case 9:
				consomationOctobre = consomationOctobre + item.getConso_wh();
				break;
			case 10:

				consomationNovembre = consomationNovembre + item.getConso_wh();
				break;
			case 11:
				consomationDecembre = consomationDecembre + item.getConso_wh();
				break;

			}
		}
		if (simulation.getMoisConsomation().getJanvier() > 0)
			ratioErrorJanvier = simulation.getMoisConsomation().getJanvier() / (consomationJanvier / 1000);
		else
			simulation.getMoisConsomation().setJanvier(consomationJanvier / 1000);
		if (simulation.getMoisConsomation().getFevrier() > 0)
			ratioErrorFevrier = simulation.getMoisConsomation().getFevrier() / (consomationFevrier / 1000);
		else
			simulation.getMoisConsomation().setFevrier(consomationFevrier / 1000);
		if (simulation.getMoisConsomation().getMars() > 0)
			ratioErrorMars = simulation.getMoisConsomation().getMars() / (consomationMars / 1000);
		else
			simulation.getMoisConsomation().setMars(consomationMars / 1000);
		if (simulation.getMoisConsomation().getAvril() > 0)
			ratioErrorAvril = simulation.getMoisConsomation().getAvril() / (consomationAvril / 1000);
		else
			simulation.getMoisConsomation().setAvril(consomationAvril / 1000);
		if (simulation.getMoisConsomation().getMai() > 0)
			ratioErrorMai = simulation.getMoisConsomation().getMai() / (consomationMai / 1000);
		else
			simulation.getMoisConsomation().setMai(consomationMai / 1000);
		if (simulation.getMoisConsomation().getJuin() > 0)
			ratioErrorJuin = simulation.getMoisConsomation().getJuin() / (consomationJuin / 1000);
		else
			simulation.getMoisConsomation().setJuin(consomationJuin / 1000);
		if (simulation.getMoisConsomation().getJuillet() > 0)
			ratioErroretJuillet = simulation.getMoisConsomation().getJuillet() / (consomationetJuillet / 1000);
		else
			simulation.getMoisConsomation().setJuillet(consomationetJuillet / 1000);
		if (simulation.getMoisConsomation().getAout() > 0)
			ratioErrorAout = simulation.getMoisConsomation().getAout() / (consomationAout / 1000);
		else
			simulation.getMoisConsomation().setAout(consomationAout / 1000);
		if (simulation.getMoisConsomation().getSeptembre() > 0)
			ratioErrorSeptembre = simulation.getMoisConsomation().getSeptembre() / (consomationSeptembre / 1000);
		else
			simulation.getMoisConsomation().setSeptembre(consomationSeptembre / 1000);
		if (simulation.getMoisConsomation().getOctobre() > 0)
			ratioErrorOctobre = simulation.getMoisConsomation().getOctobre() / (consomationOctobre / 1000);
		else
			simulation.getMoisConsomation().setOctobre(consomationOctobre / 1000);
		if (simulation.getMoisConsomation().getNovembre() > 0)
			ratioErrorNovembre = simulation.getMoisConsomation().getNovembre() / (consomationNovembre / 1000);
		else
			simulation.getMoisConsomation().setNovembre(consomationNovembre / 1000);
		if (simulation.getMoisConsomation().getDecembre() > 0)
			ratioErrorDecembre = simulation.getMoisConsomation().getDecembre() / (consomationDecembre / 1000);
		else
			simulation.getMoisConsomation().setDecembre(consomationDecembre / 1000);

		for (ResultLigne item : simulation.getResults()) {
			try {
				dateSimulation = sdf.parse(item.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (dateSimulation.getMonth()) {
			case 0:
				item.setConso_wh(item.getConso_wh() * ratioErrorJanvier);
				consomationJanvier = consomationJanvier + item.getConso_wh();
				break;
			case 1:
				item.setConso_wh(item.getConso_wh() * ratioErrorFevrier);
				consomationFevrier = consomationFevrier + item.getConso_wh();
				break;
			case 2:
				item.setConso_wh(item.getConso_wh() * ratioErrorMars);
				consomationMars = consomationMars + item.getConso_wh();
				break;
			case 3:
				item.setConso_wh(item.getConso_wh() * ratioErrorAvril);
				consomationAvril = consomationAvril + item.getConso_wh();
				break;
			case 4:
				item.setConso_wh(item.getConso_wh() * ratioErrorMai);
				consomationMai = consomationMai + item.getConso_wh();
				break;
			case 5:
				item.setConso_wh(item.getConso_wh() * ratioErrorJuin);
				consomationJuin = consomationJuin + item.getConso_wh();
				break;
			case 6:
				item.setConso_wh(item.getConso_wh() * ratioErroretJuillet);
				consomationetJuillet = consomationetJuillet + item.getConso_wh();
				break;
			case 7:
				item.setConso_wh(item.getConso_wh() * ratioErrorAout);
				consomationAout = consomationAout + item.getConso_wh();
				break;
			case 8:
				item.setConso_wh(item.getConso_wh() * ratioErrorSeptembre);
				consomationSeptembre = consomationSeptembre + item.getConso_wh();
				break;
			case 9:
				item.setConso_wh(item.getConso_wh() * ratioErrorOctobre);
				consomationOctobre = consomationOctobre + item.getConso_wh();
				break;
			case 10:
				item.setConso_wh(item.getConso_wh() * ratioErrorNovembre);
				consomationNovembre = consomationNovembre + item.getConso_wh();
				break;
			case 11:
				item.setConso_wh(item.getConso_wh() * ratioErrorDecembre);
				consomationDecembre = consomationDecembre + item.getConso_wh();
				break;
			}
		}
		return simulation;
	}

	public void finalizeconsuption(ArrayList<ArrayList<ResultLigne>> remplissageAutoconsomation, Simulation simulation)
			throws ParseException {
		SimpleDateFormat sdf0 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = sdf0.parse(remplissageAutoconsomation.get(0).get(0).getDate());

		for (ResultLigne item : simulation.getResults()) {
			if (item.getConso_wh() == 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
				Date dateSimulation = sdf.parse(item.getDate());
				switch (dateSimulation.getMonth()) {
				case 0:
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());

							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 1:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 2:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 3:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 4:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 5:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 6:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 7:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 8:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 9:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 10:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;
				case 11:

					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("mon")) {
						for (ResultLigne mon : remplissageAutoconsomation.get(0)) {
							Date datesunday = sdf.parse(mon.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(mon.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("tue")) {
						for (ResultLigne tue : remplissageAutoconsomation.get(1)) {
							Date datesunday = sdf.parse(tue.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(tue.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("wed")) {
						for (ResultLigne wed : remplissageAutoconsomation.get(2)) {
							Date datesunday = sdf.parse(wed.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(wed.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("thu")) {
						for (ResultLigne thu : remplissageAutoconsomation.get(3)) {
							Date datesunday = sdf.parse(thu.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(thu.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("fri")) {
						for (ResultLigne fri : remplissageAutoconsomation.get(4)) {
							Date datesunday = sdf.parse(fri.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(fri.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sat")) {
						for (ResultLigne sat : remplissageAutoconsomation.get(5)) {
							Date datesunday = sdf.parse(sat.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sat.getConso_wh());
							}
						}
					}
					if (dateSimulation.toString().substring(0, 3).toLowerCase().equals("sun")) {
						for (ResultLigne sun : remplissageAutoconsomation.get(6)) {
							Date datesunday = sdf.parse(sun.getDate());
							if (datesunday.getHours() == dateSimulation.getHours()
									&& datesunday.getMinutes() == dateSimulation.getMinutes()) {
								item.setConso_wh(sun.getConso_wh());
							}
						}
					}
					break;

				}
			}
		}
		this.adjustConsomation(simulation);
	}

	public ArrayList<ArrayList<ResultLigne>> remplissageAutoconsomation(Simulation simulation) {
		ArrayList<ArrayList<ResultLigne>> days = new ArrayList<ArrayList<ResultLigne>>();
		ArrayList<ResultLigne> mon = new ArrayList<ResultLigne>();
		ArrayList<ResultLigne> tue = new ArrayList<ResultLigne>();
		ArrayList<ResultLigne> wed = new ArrayList<ResultLigne>();
		ArrayList<ResultLigne> thu = new ArrayList<ResultLigne>();
		ArrayList<ResultLigne> fri = new ArrayList<ResultLigne>();
		ArrayList<ResultLigne> sat = new ArrayList<ResultLigne>();
		ArrayList<ResultLigne> sun = new ArrayList<ResultLigne>();
		for (ResultLigne item : simulation.getResults()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			Date date = null;
			try {
				date = sdf.parse(item.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (item.getConso_wh() != 0) {

				if (date.toString().substring(0, 3).equals("Mon") && mon.size() < 144) {
					mon.add(item);
				}
				if (date.toString().substring(0, 3).equals("Tue") && tue.size() < 144) {
					tue.add(item);
				}
				if (date.toString().substring(0, 3).equals("Wed") && wed.size() < 144) {
					wed.add(item);
				}
				if (date.toString().substring(0, 3).equals("Thu") && thu.size() < 144) {
					thu.add(item);
				}
				if (date.toString().substring(0, 3).equals("Fri") && fri.size() < 144) {
					fri.add(item);
				}
				if (date.toString().substring(0, 3).equals("Sat") && sat.size() < 144) {
					sat.add(item);
				}
				if (date.toString().substring(0, 3).equals("Sun") && sun.size() < 144) {
					sun.add(item);
				}
			}
		}
		days.add(mon);
		days.add(tue);
		days.add(wed);
		days.add(thu);
		days.add(fri);
		days.add(sat);
		days.add(sun);

		return days;
	}

	private void setconsumptioninput(Simulation simulation, MultipartFile file) throws IOException {
		// recupération et lecture du excel en utilisan la librerie poi
		FileInputStream excelFile = (FileInputStream) file.getInputStream();
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		DataFormatter dataFormatter = new DataFormatter();
		resultLignes.clear();
		/////////////// file
		//////////// loop
		/// iteration sur l'excel + calcul
		double total = 0;
		try {
			int i = 0;
			while (rows.hasNext()) {
				ResultLigne resultLigne = new ResultLigne();
				Row currentRow = rows.next();
				Iterator<Cell> cellsInRow = currentRow.cellIterator();
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					String cellAdress = currentCell.getAddress().toString();
					String cellValue = dataFormatter.formatCellValue(currentCell) + "";
					if (cellValue.matches(".*\\d+.*")) {
						switch (cellAdress.substring(0, 1)) {
						case "A":
							SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
							Date datefile = sdf.parse(cellValue);
							resultLigne
									.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(datefile).toString());
							break;
						case "B":
							resultLigne.setConso_wh(Double.parseDouble(cellValue.replace(",", ".")) / 6);
							total = total + resultLigne.getConso_wh();
							i++;
							break;

						}
					}
				}
				addResultLignes(resultLigne, simulation);
			}
			if (i < 52560) {
				this.poderation = true;
			} else {
				this.poderation = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
		workbook.close();
		excelFile.close();
	}

	private void setConsomationMois(Simulation simulation) {
		// TODO Auto-generated method stub

	}

	private void addResultLignes(ResultLigne resultLigne, Simulation simulation) {

		for (ResultLigne item : simulation.getResults()) {
			if (resultLigne.getDate().substring(0, 5).equals(item.getDate().substring(0, 5))
					&& resultLigne.getDate().substring(10, 16).equals(item.getDate().substring(10, 16))) {
				item.setConso_wh(resultLigne.getConso_wh());
				item.setDate(resultLigne.getDate());
			}
		}
	}

	private Simulation setHeldaandDate(Simulation simulation) throws IOException, ParseException {
		FileInputStream excelFile = new FileInputStream(new File("profilTELEC.xlsx"));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		DataFormatter dataFormatter = new DataFormatter();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date datedebut = sdf.parse("01/01" + simulation.getDateDebut().substring(5, 10) + " 00:00:00");
		Calendar cl = Calendar.getInstance();
		cl.setTime(datedebut);
		//////////////////////////////////////////////
		int i = 0;
		ArrayList<ResultLigne> resultLignes = new ArrayList<ResultLigne>();
		while (rows.hasNext()) {
			Row currentRow = rows.next();
			Iterator<Cell> cellsInRow = currentRow.cellIterator();
			while (cellsInRow.hasNext()) {
				ResultLigne resultLigne = new ResultLigne();
				Cell currentCell = cellsInRow.next();
				String cellValue = dataFormatter.formatCellValue(currentCell);
				resultLigne.setProfil_Helda(Double.parseDouble(cellValue.replace(",", ".")));
				resultLigne.num = i++;
				Date setdate2 = cl.getTime();
				cl.add(Calendar.MINUTE, 10);
				resultLigne.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(setdate2).toString());
				resultLignes.add(resultLigne);
			}
		}
		workbook.close();
		excelFile.close();
		simulation.setResults(resultLignes);

		return simulation;
		// TODO Auto-generated method stub
	}

	public static List<Map<String, Object>> setArraySimulation(List<SimulationDB> simulationDB) {
		List<Map<String, Object>> simulations = new ArrayList<Map<String, Object>>();
		for (SimulationDB item : simulationDB) {
			Map<String, Object> hm = new HashMap<>();
			Simulation simulation = new Simulation();
			try {
				hm.put("id", item.getId());
				hm.put("simulation", (Simulation) ObjectService.deserializeBytes(item.getSimulation()));
				hm.put("iUtilisateur", item.getiUtilisateur());
				hm.put("NomProjet", item.getNomFichier());
				hm.put("NomCompletUtilisateurActuel", item.getUtilisateurActuel());
				simulations.add(hm);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return simulations;
	}

	@Override
	public void SetParamsPonderation(MultipartFile file, Simulation simulation) {
		if (simulation.getResults() != null)
			simulation.getResults().clear();
		try {
			setconsumptioninput(setHeldaandDate(simulation), file);
			if (this.poderation == true)
				finalizeconsuption(remplissageAutoconsomation(simulation), simulation);
			getConsomation(simulation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getConsomation(Simulation simulation) {
		double consomationJanvier = 0;
		double consomationFevrier = 0;
		double consomationMars = 0;
		double consomationAvril = 0;
		double consomationMai = 0;
		double consomationJuin = 0;
		double consomationetJuillet = 0;
		double consomationAout = 0;
		double consomationSeptembre = 0;
		double consomationOctobre = 0;
		double consomationNovembre = 0;
		double consomationDecembre = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date dateSimulation = null;
		for (ResultLigne item : simulation.getResults()) {
			try {
				dateSimulation = sdf.parse(item.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (dateSimulation.getMonth()) {
			case 0:
				consomationJanvier = consomationJanvier + item.getConso_wh();
				break;
			case 1:
				consomationFevrier = consomationFevrier + item.getConso_wh();
				break;
			case 2:
				consomationMars = consomationMars + item.getConso_wh();
				break;
			case 3:
				consomationAvril = consomationAvril + item.getConso_wh();
				break;
			case 4:
				consomationMai = consomationMai + item.getConso_wh();
				break;
			case 5:
				consomationJuin = consomationJuin + item.getConso_wh();
				break;
			case 6:
				consomationetJuillet = consomationetJuillet + item.getConso_wh();
				break;
			case 7:
				consomationAout = consomationAout + item.getConso_wh();
				break;
			case 8:
				consomationSeptembre = consomationSeptembre + item.getConso_wh();
				break;
			case 9:
				consomationOctobre = consomationOctobre + item.getConso_wh();
				break;
			case 10:

				consomationNovembre = consomationNovembre + item.getConso_wh();
				break;
			case 11:
				consomationDecembre = consomationDecembre + item.getConso_wh();
				break;

			}
		}

		simulation.getMoisConsomation().setJanvier(consomationJanvier / 1000);
		simulation.getMoisConsomation().setFevrier(consomationFevrier / 1000);
		simulation.getMoisConsomation().setMars(consomationMars / 1000);
		simulation.getMoisConsomation().setAvril(consomationAvril / 1000);
		simulation.getMoisConsomation().setMai(consomationMai / 1000);
		simulation.getMoisConsomation().setJuin(consomationJuin / 1000);
		simulation.getMoisConsomation().setJuillet(consomationetJuillet / 1000);
		simulation.getMoisConsomation().setAout(consomationAout / 1000);
		simulation.getMoisConsomation().setSeptembre(consomationSeptembre / 1000);
		simulation.getMoisConsomation().setOctobre(consomationOctobre / 1000);
		simulation.getMoisConsomation().setNovembre(consomationNovembre / 1000);
		simulation.getMoisConsomation().setDecembre(consomationDecembre / 1000);
	}

	public boolean enregistre(SimulationDB simulationDB, String checkedItems, String description) {
		this.simulationRepository.save(simulationDB);
		simulationDB.setId(simulationRepository.findFirstByOrderByIdDesc().getId());
		Notification notification = new Notification();
		notification.setIdUtilisateur(simulationDB.getiUtilisateur());
		notification.setNObject(notification.getId());
		notification.setTypeobjet("simulation");
		this.notificationRepository.save(notification);
		StatusBD status = new StatusBD();
		status.setDescription("Le projet " + simulationDB.getNomFichier() + " est crée");
		status.setIdObject(notificationRepository.findFirstByOrderByIdDesc().getId());
		status.setTypeObjet("notification");
		this.statusRepository.save(status);
		ObjectMapper mapper = new ObjectMapper();
		List<PartageListUtilisateur> obj = new ArrayList<PartageListUtilisateur>();
		try {
			obj = mapper.readValue("[" + checkedItems + "]",
					mapper.getTypeFactory().constructCollectionType(List.class, PartageListUtilisateur.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.updateListPartager(obj, simulationDB.getiUtilisateur(), simulationDB.getId());

		StatusBD statusSimulationDescription = new StatusBD();
		statusSimulationDescription.setDescription(description);
		statusSimulationDescription.setIdObject(simulationDB.getId());
		statusSimulationDescription.setTypeObjet("simulation");
		this.statusRepository.save(statusSimulationDescription);
		return true;
	}

	public boolean supprimer(long id) {
		List<SimulationDB> simulation = simulationRepository.findById(id);
		if (simulation == null)
			return false;
		else
			simulation.forEach((item) -> {
				simulationRepository.delete(item);
				partageRepository.findByIdUtilisateursource(item.getiUtilisateur()).forEach((partage) -> {
					partageRepository.delete(partage);
				});
				statusRepository.findByIdObject(item.getId()).forEach((status) -> {
					statusRepository.delete(status);
				});
				notificationRepository.findByNObjectAndTypeobjet(item.getId(), "simulation");
			});

		return true;
	}

	public List<PartageListUtilisateur> getListPartage(long idSimulation, long idsourceUtilisateur) {
		List<PartageListUtilisateur> listPartageUtilisateur = new ArrayList<PartageListUtilisateur>();
		this.utilisateurRepository.findAll().forEach((item) -> {
			listPartageUtilisateur.add(new PartageListUtilisateur("utilisateur_" + item.getId(),
					item.getPrenom() + " " + item.getNom(), "societe_" + item.getSociete(), false));
		});
		this.societeRepository.findAll().forEach((item) -> {
			listPartageUtilisateur
					.add(new PartageListUtilisateur("societe_" + item.getId(), item.getNom(), null, false));

		});
		this.partageRepository.findByIdUtilisateursource(idsourceUtilisateur).forEach((item) -> {
			listPartageUtilisateur.forEach((itemlitst) -> {
				if (itemlitst.getId().replaceAll("utilisateur_", "")
						.equals(String.valueOf(item.getUtilisateurRecepteur()))) {
					itemlitst.setSelected(true);
				}
			});
		});
		return listPartageUtilisateur;
	}

	@Override
	public boolean updateListPartager(List<PartageListUtilisateur> checkedItems, long idUtilisateur,
			long idSimulation) {
		try {
			if (checkedItems.get(0) == null || checkedItems == null) {

				StatusBD statusSimulation = new StatusBD();
				statusSimulation.setDescription("privé");
				statusSimulation.setIdObject(idSimulation);
				statusSimulation.setTypeObjet("simulationStatus");
				this.statusRepository.save(statusSimulation);
			} else {

				StatusBD statusSimulation = new StatusBD();
				statusSimulation.setDescription("partagé");
				statusSimulation.setIdObject(idSimulation);
				statusSimulation.setTypeObjet("simulationStatus");
				this.statusRepository.save(statusSimulation);

				checkedItems.forEach((item) -> {
					this.partageRepository.save(new Partage(idSimulation, idUtilisateur,
							Long.parseLong(item.getId().replaceAll("utilisateur_", ""))));
					Notification notificationPartage = new Notification();
					notificationPartage.setIdUtilisateur(Long.parseLong(item.getId().replaceAll("utilisateur_", "")));
					notificationPartage.setNObject(idSimulation);
					notificationPartage.setTypeobjet("simulation");
					this.notificationRepository.save(notificationPartage);
					StatusBD statusPartage = new StatusBD();
					Utilisateur utilisatuer = utilisateurRepository.findById(idUtilisateur);
					statusPartage.setDescription("L'Utilisateur" + utilisatuer.getPrenom() + " " + utilisatuer.getNom()
							+ " a partager le projet "
							+ this.simulationRepository.findFirstByOrderByIdDesc().getNomFichier());
					statusPartage.setIdObject(notificationRepository.findFirstByOrderByIdDesc().getId());
					statusPartage.setTypeObjet("notification");
					this.statusRepository.save(statusPartage);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePartager(List<PartageListUtilisateur> checkedItems, long idUtilisateur, long idSimulation) {
		Partage partage = new Partage();
		partage.setIdSimulation(idSimulation);
		partage.setIdUtilisateursource(idUtilisateur);
		this.partageRepository.findAll().forEach((item) -> {
			if (idSimulation == item.getIdSimulation() && idUtilisateur == item.getIdUtilisateursource()) {
				this.partageRepository.delete(item);
			}
		});

		if (checkedItems.size() == 0 || checkedItems == null) {

			StatusBD statusSimulation = new StatusBD();
			statusSimulation.setDescription("privé");
			statusSimulation.setIdObject(idSimulation);
			statusSimulation.setTypeObjet("simulationStatus");
			if (this.statusRepository.findByIdObjectAndTypeObjet(idSimulation, "simulationStatus").size() != 0)
				statusSimulation.setId(this.statusRepository
						.findByIdObjectAndTypeObjet(idSimulation, "simulationStatus").get(0).getId());
			this.statusRepository.save(statusSimulation);

		} else {
			StatusBD statusSimulation = new StatusBD();
			statusSimulation.setDescription("partagé");
			statusSimulation.setIdObject(idSimulation);
			statusSimulation.setTypeObjet("simulationStatus");
			if (this.statusRepository.findByIdObjectAndTypeObjet(idSimulation, "simulationStatus").size() != 0) {
				statusSimulation.setId(this.statusRepository
						.findByIdObjectAndTypeObjet(idSimulation, "simulationStatus").get(0).getId());
				this.partageRepository.findAll().forEach((item) -> {
					if (item.getIdUtilisateursource() == idUtilisateur && item.getIdSimulation() == idSimulation)
						this.partageRepository.delete(item);
				});
			}
			this.statusRepository.save(statusSimulation);

			checkedItems.forEach((item) -> {
				this.partageRepository.save(new Partage(idSimulation, idUtilisateur,
						Long.parseLong(item.getId().replaceAll("utilisateur_", ""))));
				Notification notificationPartage = new Notification();
				notificationPartage.setIdUtilisateur(Long.parseLong(item.getId().replaceAll("utilisateur_", "")));
				notificationPartage.setNObject(idSimulation);
				notificationPartage.setTypeobjet("simulation");
				this.notificationRepository.save(notificationPartage);
				StatusBD statusPartage = new StatusBD();
				Utilisateur utilisatuer = utilisateurRepository.findById(idUtilisateur);
				statusPartage.setDescription("L'Utilisateur" + utilisatuer.getPrenom() + " " + utilisatuer.getNom()
						+ " a partager le projet "
						+ this.simulationRepository.findFirstByOrderByIdDesc().getNomFichier());
				statusPartage.setIdObject(notificationRepository.findFirstByOrderByIdDesc().getId());
				statusPartage.setTypeObjet("notification");
				this.statusRepository.save(statusPartage);
			});
		}

		return true;
	}

	@Override
	public List<SimulationFromServer> selectSimulationPartager(long idUtilisateur) {
		List<SimulationFromServer> listsimulations = new ArrayList<SimulationFromServer>();

		this.partageRepository.findByUtilisateurRecepteur(idUtilisateur).forEach((item) -> {

			SimulationFromServer simul = new SimulationFromServer();
			this.simulationRepository.findById(item.getIdSimulation()).forEach((simulation) -> {
				simulation.setSimulation(null);
				simul.setSimulation(simulation);
				simul.setUtilisateur(this.utilisateurRepository.findById(simulation.getiUtilisateur()));
				simul.getUtilisateur().setPhoto(null);
				simul.setSociete(this.societeRepository.findById(simul.getUtilisateur().getSociete()));
				simul.setDescription(this.statusRepository
						.findByIdObjectAndTypeObjet(simul.getSimulation().getId(), "simulation").get(0));
				simul.setStatus(this.statusRepository
						.findByIdObjectAndTypeObjet(simul.getSimulation().getId(), "simulationStatus").get(0));
				if (simul.getStatus().getDescription().equals("partagé"))
					listsimulations.add(simul);
			});
		});
		return listsimulations;
	}

	@Override
	public List<SimulationFromServer> selectSimulation(long idUtilisateur) {
		List<SimulationFromServer> listsimulations = new ArrayList<SimulationFromServer>();
		this.simulationRepository.findByIUtilisateur(idUtilisateur).forEach((simulation)->{
			SimulationFromServer simul = new SimulationFromServer();
			simulation.setSimulation(null);
			simul.setSimulation(simulation);
			simul.setUtilisateur(this.utilisateurRepository.findById(simulation.getiUtilisateur()));
			simul.getUtilisateur().setPhoto(null);
			simul.setSociete(this.societeRepository.findById(simul.getUtilisateur().getSociete()));
			simul.setDescription(this.statusRepository
					.findByIdObjectAndTypeObjet(simul.getSimulation().getId(), "simulation").get(0));
			simul.setStatus(this.statusRepository
					.findByIdObjectAndTypeObjet(simul.getSimulation().getId(), "simulationStatus").get(0));
			listsimulations.add(simul);
		});
		return listsimulations;
	}

	@Override
	public List<Simulation> compare(String simulations, int idUtilisateur) {

		ObjectMapper mapper = new ObjectMapper();
		List<Simulation> obj = new ArrayList<Simulation>();
		try {
			obj = mapper.readValue(simulations,
					mapper.getTypeFactory().constructCollectionType(List.class, Simulation.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.forEach((item)->{
			item.setparams(item.getPuissance_Installee(), item.getNombre_Heur_Production(), item.getCapacite_battrie(), 
					this.batterieRepository.findByCapacite(item.getCapacite_battrie()).get(0).getPuissance(),
					item.getDateDebut(), this.batterieRepository.findByCapacite(item.getCapacite_battrie()).get(0).getPuissance()
					, item.getMoisConsomation(), item.getSeuil_reseau());
			
			this.SimulationPonderation(item);
			chartService.resultLigneSemaineWS(item);
			List<ResultLigne> ligne=new ArrayList<ResultLigne>();
			item.setResults(ligne);
		});
		return obj;
	}

}
