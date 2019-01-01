package System.Solaire.com.WebServiceArtifact.Services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import System.Solaire.com.WebServiceArtifact.DAO.Interface.IBatterieDAO;
import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Model.MoisConsomation;
import System.Solaire.com.WebServiceArtifact.Model.PossibiliteSimulation;
import System.Solaire.com.WebServiceArtifact.Model.ResultLigne;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IPossibiliteSimulationService;

@Service
public class PossibiliteSimulationService implements IPossibiliteSimulationService {
	////////////////
	@Autowired
	private ResultatLigneService resultatLigneService;
	@Autowired
	private PossibiliteSimulationService possibiliteSimulationService;
	private ArrayList<ResultLigne> resultLignes = new ArrayList<ResultLigne>();
	@Autowired
	private PossibiliteSimulation possibiliteSimulation;
	@Autowired
	IBatterieDAO batterieDAO;
	//////////////////
	@Autowired
	SimulationService simulationService;
	@Override
	public PossibiliteSimulation Calcule(Simulation simulation) {

		return null;
	}

	public PossibiliteSimulation possibilite(MultipartFile file, double nombre_Heur_Production,
			double valeur_décharge_batterie, String dateDebut, double puissance_maximum_Onduleur, double seuil_reseau,
			double maxbatterie, double minbatterie, double patbatterie, double maxPuissance, double minPuissance,
			double patPuissance,double autoProdvise) {
		// recupération et lecture du excel en utilisan la librerie poi
		try {
			FileInputStream excelFile = (FileInputStream) file.getInputStream();
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			DataFormatter dataFormatter = new DataFormatter();
			resultLignes.clear();
			/////////////// file
			//////////// loop
			/// iteration sur l'excel + calcul

			while (rows.hasNext()) {
				ResultLigne resultLigne = new ResultLigne();
				Row currentRow = rows.next();
				Iterator<Cell> cellsInRow = currentRow.cellIterator();
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					String cellAdress = currentCell.getAddress().toString();
					String cellValue = dataFormatter.formatCellValue(currentCell) + "";
					if (cellValue.equals(""))
						cellValue = "0";
					if (cellValue.matches(".*\\d+.*")) {
						switch (cellAdress.substring(0, 1)) {
						case "A":
							resultLigne.setProfil_Helda(Double.parseDouble(cellValue.replace(",", ".")));
							break;
						case "B":
							resultLigne.setConso_wh(Double.parseDouble(cellValue.replace(",", ".")));
							break;
						}
					}
				}
				resultLignes.add(resultLigne);
			}
			List<List<Double>> pacosomation = new ArrayList<List<Double>>();
			List<List<Double>> papproduction = new ArrayList<List<Double>>();
			//////////// todo use database battrie instad of params
			List<Batterie> batteries = (ArrayList<Batterie>) batterieDAO.getBatteries(minbatterie, maxbatterie);
			List <String> autoProductionVise= new ArrayList<String>();
			for (int i = 0; i < batteries.size(); i++) {
				double a = batteries.get(i).getCapacite();
				double b = batteries.get(i).getPuissance();
				ArrayList<Double> pac = new ArrayList<Double>();
				ArrayList<Double> pap = new ArrayList<Double>();
				for (double j = minPuissance; j <= maxPuissance; j = j + patPuissance) {
					Simulation simulation = new Simulation();
					simulation.setparams(j, nombre_Heur_Production, a, valeur_décharge_batterie, dateDebut, b,
							seuil_reseau);
					simulation.setResults(resultLignes);
					resultatLigneService.traitementLigne(simulation);
					pac.add(simulation.getTaux_auto_consomation());
					pap.add(simulation.getTaux_auto_production());
					if(autoProdvise<=simulation.getTaux_auto_production()+0.05 
							&& autoProdvise>=simulation.getTaux_auto_production()-0.05) {
						autoProductionVise.add(batteries.get(i).getNom() +":"+batteries.get(i).getCapacite()
								+":"+batteries.get(i).getPuissance()+":"+j);
					}
				}
				pacosomation.add(pac);
				papproduction.add(pap);
			}
			possibiliteSimulation.setAutoProdviseProche(autoProductionVise);
			possibiliteSimulation.setPossibiliteAutoConsomation(pacosomation);
			possibiliteSimulation.setPossibiliteAutoproduction(papproduction);
			possibiliteSimulation.setMaxbatterie(batteries.get(batteries.size() - 1).getCapacite());
			possibiliteSimulation.setMaxPuissance(maxPuissance);
			possibiliteSimulation.setMinbatterie(batteries.get(0).getCapacite());
			possibiliteSimulation.setMinPuissance(minPuissance);
			possibiliteSimulation.setPatbatterie(batteries.get(1).getCapacite()-batteries.get(0).getCapacite());
			possibiliteSimulation.setPatPuissance(patPuissance);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return possibiliteSimulation;

	}	
	public PossibiliteSimulation possibilitePoderation(Simulation simulation) {
		try {
			List<List<Double>> pacosomation = new ArrayList<List<Double>>();
			List<List<Double>> papproduction = new ArrayList<List<Double>>();
			//////////// todo use database battrie instad of params
			ArrayList<Batterie> batteries=(ArrayList<Batterie>)batterieDAO.getBatteries(simulation.getPossibiliteSimulation().getMinbatterie(), 
					simulation.getPossibiliteSimulation().getMaxbatterie());
			List <String> autoProductionVise= new ArrayList<String>();
			for (int i = 0; i < batteries.size(); i++) {
				double a = batteries.get(i).getCapacite();
				double b = batteries.get(i).getPuissance();
				ArrayList<Double> pac = new ArrayList<Double>();
				ArrayList<Double> pap = new ArrayList<Double>();
				for (double j = simulation.getPossibiliteSimulation().getMinPuissance()
						; j <= simulation.getPossibiliteSimulation().getMaxPuissance()
						; j = j + simulation.getPossibiliteSimulation().getPatPuissance()) {
					Simulation silmulationclone= (Simulation)ObjectService.deepClone(simulation);
					silmulationclone.setCapacite_battrie(batteries.get(i).getCapacite());
					silmulationclone.setValeur_decharge_batterie(batteries.get(i).getCapacite());
					silmulationclone.setPuissance_maximum_Onduleur(batteries.get(i).getPuissance());
					silmulationclone.setPuissance_Installee(j);
					resultatLigneService.traitementLigne(silmulationclone);
					pac.add(silmulationclone.getTaux_auto_consomation());
					pap.add(silmulationclone.getTaux_auto_production());
					if(silmulationclone.getPossibiliteSimulation().getAutoProdvise()<=silmulationclone.getTaux_auto_production()+0.1
							&& silmulationclone.getPossibiliteSimulation().getAutoProdvise()>=silmulationclone.getTaux_auto_production()-0.1) {
						autoProductionVise.add(batteries.get(i).getNom() +":"+batteries.get(i).getCapacite()
								+":"+batteries.get(i).getPuissance()+":"+j);
					}
				}
				pacosomation.add(pac);
				papproduction.add(pap);
			}
			possibiliteSimulation.setAutoProdvise(simulation.getPossibiliteSimulation().getAutoProdvise()*100);
			possibiliteSimulation.setAutoProdviseProche(autoProductionVise);
			possibiliteSimulation.setPossibiliteAutoConsomation(pacosomation);
			possibiliteSimulation.setPossibiliteAutoproduction(papproduction);
			possibiliteSimulation.setMaxbatterie(batteries.get(batteries.size() - 1).getCapacite());
			possibiliteSimulation.setMaxPuissance(simulation.getPossibiliteSimulation().getMaxPuissance());
			possibiliteSimulation.setMinbatterie(batteries.get(0).getCapacite());
			possibiliteSimulation.setMinPuissance(simulation.getPossibiliteSimulation().getMinPuissance());
			possibiliteSimulation.setPatbatterie(batteries.get(1).getCapacite()-batteries.get(0).getCapacite());
			possibiliteSimulation.setPatPuissance(simulation.getPossibiliteSimulation().getPatPuissance());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return possibiliteSimulation;

	}
}
