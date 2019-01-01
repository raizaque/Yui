package System.Solaire.com.WebServiceArtifact.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import System.Solaire.com.WebServiceArtifact.Model.Charts;
import System.Solaire.com.WebServiceArtifact.Model.MoisConsomation;
import System.Solaire.com.WebServiceArtifact.Model.MoisResultList;
import System.Solaire.com.WebServiceArtifact.Model.ResultLigne;
import System.Solaire.com.WebServiceArtifact.Model.ResultListJourWeekendJourSemaine;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IChartsService;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IResultatLigneService;
import System.Solaire.com.WebServiceArtifact.Services.Interface.ISimulationService;

@Service
public class ChartsService implements IChartsService {
	public ChartsService() {

	}

	private boolean inserted = false;
	@Autowired
	ISimulationService simulationService;
	@Autowired
	Charts charts;
	@Autowired
	IResultatLigneService resultLigneService;
	@Override
	public void setChartsResultLigne(Simulation simulation) {
		this.resultLigneSansProductionSansBatterie(simulation);
		this.resultLigneSansBatterie(simulation);
	}

	@Override
	public void setResultLigneMoyen(Simulation simulation) {

		MoisResultList moisResultList = new MoisResultList();
		ResultListJourWeekendJourSemaine janvier = new ResultListJourWeekendJourSemaine();

		janvier.setJourSemaine(new ArrayList<ResultLigne>());
		janvier.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine fevrier = new ResultListJourWeekendJourSemaine();
		fevrier.setJourSemaine(new ArrayList<ResultLigne>());
		fevrier.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine mars = new ResultListJourWeekendJourSemaine();
		mars.setJourSemaine(new ArrayList<ResultLigne>());
		mars.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine avril = new ResultListJourWeekendJourSemaine();
		avril.setJourSemaine(new ArrayList<ResultLigne>());
		avril.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine mai = new ResultListJourWeekendJourSemaine();
		mai.setJourSemaine(new ArrayList<ResultLigne>());
		mai.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine juin = new ResultListJourWeekendJourSemaine();
		juin.setJourSemaine(new ArrayList<ResultLigne>());
		juin.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine juillet = new ResultListJourWeekendJourSemaine();
		juillet.setJourSemaine(new ArrayList<ResultLigne>());
		juillet.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine aout = new ResultListJourWeekendJourSemaine();
		aout.setJourSemaine(new ArrayList<ResultLigne>());
		aout.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine septembre = new ResultListJourWeekendJourSemaine();
		septembre.setJourSemaine(new ArrayList<ResultLigne>());
		septembre.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine octobre = new ResultListJourWeekendJourSemaine();
		octobre.setJourSemaine(new ArrayList<ResultLigne>());
		octobre.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine novembre = new ResultListJourWeekendJourSemaine();
		novembre.setJourSemaine(new ArrayList<ResultLigne>());
		novembre.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine decembre = new ResultListJourWeekendJourSemaine();
		decembre.setJourSemaine(new ArrayList<ResultLigne>());
		decembre.setJourWeekend(new ArrayList<ResultLigne>());
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
				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, janvier.getJourWeekend());

				if (this.inserted == false) {
					janvier.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, janvier.getJourSemaine());

					if (this.inserted == false) {
						janvier.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
				}
				this.inserted = false;
				break;
			case 1:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, fevrier.getJourWeekend());

				if (this.inserted == false) {
					fevrier.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, fevrier.getJourSemaine());

					if (this.inserted == false) {
						fevrier.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					}
				
				this.inserted = false;
				break;
			case 2:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, mars.getJourWeekend());

				if (this.inserted == false) {
					mars.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, mars.getJourSemaine());

					if (this.inserted == false) {
						mars.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					
				}
				this.inserted = false;
				break;
			case 3:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, avril.getJourWeekend());

				if (this.inserted == false) {
					avril.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, avril.getJourSemaine());

					if (this.inserted == false) {
						avril.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					
				}
				this.inserted = false;
				break;
			case 4:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, mai.getJourWeekend());

				if (this.inserted == false) {
					mai.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, mai.getJourSemaine());

					if (this.inserted == false) {
						mai.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					
				}
				this.inserted = false;
				break;
			case 5:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, juin.getJourWeekend());

				if (this.inserted == false) {
					juin.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, juin.getJourSemaine());

					if (this.inserted == false) {
						juin.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					
				}
				this.inserted = false;
				break;
			case 6:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, juillet.getJourWeekend());

				if (this.inserted == false) {
					juillet.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, juillet.getJourSemaine());

					if (this.inserted == false) {
						juillet.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					
				}
				this.inserted = false;
				break;
			case 7:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, aout.getJourWeekend());

				if (this.inserted == false) {
					aout.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, aout.getJourSemaine());

					if (this.inserted == false) {
						aout.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					
				}
				this.inserted = false;
				break;
			case 8:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, septembre.getJourWeekend());

				if (this.inserted == false) {
					septembre.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, septembre.getJourSemaine());

					if (this.inserted == false) {
						septembre.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					
				}
				this.inserted = false;
				break;
			case 9:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, octobre.getJourWeekend());

				if (this.inserted == false) {
					octobre.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {
					this.AjouterMoyAList(item, octobre.getJourSemaine());

					if (this.inserted == false) {
						octobre.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
					
				}
				this.inserted = false;
				break;
			case 10:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, novembre.getJourWeekend());

				if (this.inserted == false) {
					novembre.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {

					this.AjouterMoyAList(item, novembre.getJourSemaine());

					if (this.inserted == false) {
						novembre.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
				}
				this.inserted = false;
				break;
			case 11:

				if(dateSimulation.getDay()==6 ||dateSimulation.getDay()==0) {
				this.AjouterMoyAList(item, decembre.getJourWeekend());

				if (this.inserted == false) {
					decembre.getJourWeekend().add((ResultLigne)ObjectService.deepClone(item));
				}
				}
				else {

					this.AjouterMoyAList(item, decembre.getJourSemaine());

					if (this.inserted == false) {
						decembre.getJourSemaine().add((ResultLigne)ObjectService.deepClone(item));
					}
				}
				this.inserted = false;
				break;
			}
		}
		moisResultList.setJanvier(janvier);
		moisResultList.setFevrier(fevrier);
		moisResultList.setMars(mars);
		moisResultList.setAvril(avril);
		moisResultList.setMai(mai);
		moisResultList.setJuin(juin);
		moisResultList.setJuillet(juillet);
		moisResultList.setAout(aout);
		moisResultList.setSeptembre(septembre);
		moisResultList.setOctobre(octobre);
		moisResultList.setNovembre(novembre);
		moisResultList.setDecembre(decembre);
		
		this.charts.setResultLigneMoyen(moisResultList);
		
		simulation.setCharts(charts);		
		
	}

	private void AjouterMoyAList(ResultLigne item, List<ResultLigne> jourSemaine) {
		for (ResultLigne resultLigne : jourSemaine) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			Date dateitem = null;
			Date dateResultLine = null;
			try {
				dateResultLine = sdf.parse(resultLigne.getDate());
				dateitem = sdf.parse(item.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
/////////////////////mouckilllllllllllllllllllllllllllllla -_-
			if (dateitem.getHours()+0 == dateResultLine.getHours()
					&& dateitem.getMinutes() == dateResultLine.getMinutes()) {
				resultLigne.setparams(0, item.getDate(), (item.getProfil_Helda() + resultLigne.getProfil_Helda()) / 2.0,
						(item.getProduction_Wh() + resultLigne.getProduction_Wh()) / 2.0,
						(item.getConso_wh() + resultLigne.getConso_wh()) / 2.0,
						(item.getNiveau_stockbatterie() + resultLigne.getNiveau_stockbatterie()) / 2.0,
						(item.getBesoin() + resultLigne.getBesoin()) / 2.0,
						(item.getFlux_batterie() + resultLigne.getFlux_batterie()) / 2.0,
						(item.getSoutirage() + resultLigne.getSoutirage()) / 2.0,
						(item.getSurplus_de_production() + resultLigne.getSurplus_de_production()) / 2.0,
						(item.getAchat() + resultLigne.getAchat()) / 2,
						(item.getAutoConsommation() + resultLigne.getAutoConsommation()) / 2.0,
						(item.getEnergie_reinjectee_par_la_batterie_kWh()
								+ resultLigne.getEnergie_reinjectee_par_la_batterie_kWh()) / 2);
				this.inserted = true;
			}
		}
	}

	private void resultLigneSansBatterie(Simulation simulation ) {
		Simulation simulationClone = new Simulation();		
		simulationClone=(Simulation)ObjectService.deepClone(simulation);
		simulationClone.setCapacite_battrie(0);
		simulationClone.setValeur_decharge_batterie(0);
		simulationClone.setPuissance_maximum_Onduleur(0);
		simulationService.SimulationPonderation(simulationClone);
		
		this.charts.setResultLigneSansBatterie(this.ResultLigneMoisJS_W(simulationClone.getResults()));	

		simulation.setCharts(charts);

	}

	public void resultLigneSemaineWS(Simulation simulation) {
		this.charts.setResultLigne(this.ResultLigneMoisJS_W(simulation.getResults()));
		simulation.setCharts((Charts)ObjectService.deepClone(charts));
	}

	private void resultLigneSansProductionSansBatterie(Simulation simulation) {
		Simulation simulationClone = new Simulation();
		simulationClone=(Simulation)ObjectService.deepClone(simulation);
		simulationClone.setCapacite_battrie(0);
		simulationClone.setValeur_decharge_batterie(0);
		simulationClone.setPuissance_maximum_Onduleur(0);
		simulationService.SimulationPonderationSansProduction(simulationClone);
		this.charts.setResultLigneSansProductionSansBatterie(this.ResultLigneMoisJS_W(simulationClone.getResults()));
		simulation.setCharts(charts);
	}

	private MoisResultList ResultLigneMoisJS_W(List<ResultLigne> resultLigne) {
		MoisResultList moisResultList = new MoisResultList();
		ResultListJourWeekendJourSemaine janvier = new ResultListJourWeekendJourSemaine();

		janvier.setJourSemaine(new ArrayList<ResultLigne>());
		janvier.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine fevrier = new ResultListJourWeekendJourSemaine();
		fevrier.setJourSemaine(new ArrayList<ResultLigne>());
		fevrier.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine mars = new ResultListJourWeekendJourSemaine();
		mars.setJourSemaine(new ArrayList<ResultLigne>());
		mars.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine avril = new ResultListJourWeekendJourSemaine();
		avril.setJourSemaine(new ArrayList<ResultLigne>());
		avril.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine mai = new ResultListJourWeekendJourSemaine();
		mai.setJourSemaine(new ArrayList<ResultLigne>());
		mai.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine juin = new ResultListJourWeekendJourSemaine();
		juin.setJourSemaine(new ArrayList<ResultLigne>());
		juin.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine juillet = new ResultListJourWeekendJourSemaine();
		juillet.setJourSemaine(new ArrayList<ResultLigne>());
		juillet.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine aout = new ResultListJourWeekendJourSemaine();
		aout.setJourSemaine(new ArrayList<ResultLigne>());
		aout.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine septembre = new ResultListJourWeekendJourSemaine();
		septembre.setJourSemaine(new ArrayList<ResultLigne>());
		septembre.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine octobre = new ResultListJourWeekendJourSemaine();
		octobre.setJourSemaine(new ArrayList<ResultLigne>());
		octobre.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine novembre = new ResultListJourWeekendJourSemaine();
		novembre.setJourSemaine(new ArrayList<ResultLigne>());
		novembre.setJourWeekend(new ArrayList<ResultLigne>());
		
		ResultListJourWeekendJourSemaine decembre = new ResultListJourWeekendJourSemaine();
		decembre.setJourSemaine(new ArrayList<ResultLigne>());
		decembre.setJourWeekend(new ArrayList<ResultLigne>());
		for (ResultLigne item : resultLigne) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			Date dateSimulation = null;
			try {
				dateSimulation = sdf.parse(item.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (dateSimulation.getDate() >= 15 && dateSimulation.getDate() <= 21) {

				switch (dateSimulation.getMonth()) {
				case 0:
					if (dateSimulation.getDay() == 0) {
						janvier.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						janvier.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 1:
					if (dateSimulation.getDay() == 0) {
						fevrier.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						fevrier.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 2:
					if (dateSimulation.getDay() == 0) {
						mars.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						mars.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 3:
					if (dateSimulation.getDay() == 0) {
						avril.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						avril.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 4:
					if (dateSimulation.getDay() == 0) {
						mai.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						mai.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 5:
					if (dateSimulation.getDay() == 0) {
						juin.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						juin.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 6:
					if (dateSimulation.getDay() == 0) {
						juillet.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						juillet.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 7:
					if (dateSimulation.getDay() == 0) {
						aout.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						aout.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 8:
					if (dateSimulation.getDay() == 0) {
						septembre.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						septembre.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 9:
					if (dateSimulation.getDay() == 0) {
						octobre.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						octobre.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 10:
					if (dateSimulation.getDay() == 0) {
						novembre.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						novembre.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;
				case 11:
					if (dateSimulation.getDay() == 0) {
						decembre.getJourWeekend()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					} else if (dateSimulation.getDay() == 1) {
						decembre.getJourSemaine()
								.add(new ResultLigne(item.num, item.getDate(), item.getProfil_Helda(),
										item.getProduction_Wh(), item.getConso_wh(), item.getNiveau_stockbatterie(),
										item.getBesoin(), item.getFlux_batterie(), item.getSoutirage(),
										item.getSurplus_de_production(), item.getAchat(), item.getAutoConsommation(),
										item.getEnergie_reinjectee_par_la_batterie_kWh()));
					}
					break;

				}
			}
		}
		moisResultList.setJanvier(janvier);
		moisResultList.setFevrier(fevrier);
		moisResultList.setMars(mars);
		moisResultList.setAvril(avril);
		moisResultList.setMai(mai);
		moisResultList.setJuin(juin);
		moisResultList.setJuillet(juillet);
		moisResultList.setAout(aout);
		moisResultList.setSeptembre(septembre);
		moisResultList.setOctobre(octobre);
		moisResultList.setNovembre(novembre);
		moisResultList.setDecembre(decembre);
		List<ResultLigne> janviercourbe= new ArrayList<ResultLigne>();
		return moisResultList;
	}

	@Override
	public MoisResultList setCourbMoyen(Simulation simulation) {
		MoisConsomation UnJourconsomation=new MoisConsomation();
		MoisResultList resultsCourbeMoyen=new MoisResultList();
		resultsCourbeMoyen=(MoisResultList) ObjectService.deepClone(simulation.getCharts().getResultLigne());
		 try {
			//////////////// excel
					FileInputStream excelFile = new FileInputStream(new File("pourcentageProduction.xlsx"));
					Workbook workbook = new XSSFWorkbook(excelFile);
					Sheet sheet = workbook.getSheetAt(0);
					Iterator<Row> rows = sheet.iterator();
					DataFormatter dataFormatter = new DataFormatter();
					///////////////file
					////////////loop
					int i=0;
					while (rows.hasNext()) {
						Row currentRow = rows.next();
						Iterator<Cell> cellsInRow = currentRow.cellIterator();
						while (cellsInRow.hasNext()) {			
							Cell currentCell = cellsInRow.next();
							String cellAdress = currentCell.getAddress().toString();
							String cellValue = dataFormatter.formatCellValue(currentCell) + "";
							if (cellValue.matches(".*\\d+.*")) {
								switch (cellAdress.substring(0, 1)) {
								case "A":
									if(cellAdress.equals("A1")) 
										UnJourconsomation.setJanvier(((Double.parseDouble(cellValue.replace(",", "."))*
												simulation.getPuissance_Installee()*
												simulation.getNombre_Heur_Production())/100)/31);
									else {
										resultsCourbeMoyen.getJanvier().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getJanvier());
										resultsCourbeMoyen.getJanvier().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getJanvier());
									}
									break;
								case "B":
									if(cellAdress.equals("B1"))
										UnJourconsomation.setFevrier(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/28);
									else{
										resultsCourbeMoyen.getFevrier().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getFevrier());

										resultsCourbeMoyen.getFevrier().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getFevrier());
										}
									break;
								case "C":
									if(cellAdress.equals("C1"))
										UnJourconsomation.setMars(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/31);
									else{
										resultsCourbeMoyen.getMars().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getMars());

										resultsCourbeMoyen.getMars().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getMars());
									
									}break;
								case "D":
									if(cellAdress.equals("D1"))
										UnJourconsomation.setAvril(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/30);
									else{
										resultsCourbeMoyen.getAvril().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getAvril());

										resultsCourbeMoyen.getAvril().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getAvril());
									
									}break;
								case "E":
									if(cellAdress.equals("E1"))
										UnJourconsomation.setMai(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/31);
									else{
										resultsCourbeMoyen.getMai().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getMai());

										resultsCourbeMoyen.getMai().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getMai());
									
									}break;
								case "F":
									if(cellAdress.equals("F1"))
										UnJourconsomation.setJuin(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/30);
									else{
										resultsCourbeMoyen.getJuin().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getJuin());

										resultsCourbeMoyen.getJuin().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getJuin());
									
									}break;
								case "G":
									if(cellAdress.equals("G1"))
										UnJourconsomation.setJuillet(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/31);
									else{
										resultsCourbeMoyen.getJuillet().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getJuillet());

										resultsCourbeMoyen.getJuillet().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getJuillet());
									
									}break;
								case "H":
									if(cellAdress.equals("H1"))
										UnJourconsomation.setAout(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/31);
									else{
										resultsCourbeMoyen.getAout().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getAout());

										resultsCourbeMoyen.getAout().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getAout());
									
									}break;
								case "I":
									if(cellAdress.equals("I1"))
										UnJourconsomation.setSeptembre(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/30);
									else{
										resultsCourbeMoyen.getSeptembre().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getSeptembre());

										resultsCourbeMoyen.getSeptembre().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getSeptembre());
									
									}break;
								case "J":
									if(cellAdress.equals("J1"))
										UnJourconsomation.setOctobre(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/31);
									else{
										resultsCourbeMoyen.getOctobre().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getOctobre());

										resultsCourbeMoyen.getOctobre().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getOctobre());
									
									}break;
								case "K":
									if(cellAdress.equals("K1"))
										UnJourconsomation.setNovembre(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/30);
									else{
										resultsCourbeMoyen.getNovembre().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getNovembre());

										resultsCourbeMoyen.getNovembre().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getNovembre());
									
									}break;
								case "L":
									if(cellAdress.equals("L1"))
										UnJourconsomation.setDecembre(((Double.parseDouble(cellValue.replace(",", "."))*
											simulation.getPuissance_Installee()*
											simulation.getNombre_Heur_Production())/100)/31);
									else{
										resultsCourbeMoyen.getDecembre().getJourSemaine().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getDecembre());

										resultsCourbeMoyen.getDecembre().getJourWeekend().get(i).
										setProduction_Wh(((Double.parseDouble(cellValue.replace(",", ".")))/100)*UnJourconsomation.getDecembre());
										i++;
									}break;

								}
							}
						}
					}
					
					workbook.close();
					excelFile.close();

					
					
					Simulation simulationJourWeekendJanvier =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendJanvier.setResults(resultsCourbeMoyen.getJanvier().getJourWeekend());	
					this.resultLigneService.traitementLigneChart(simulationJourWeekendJanvier,simulation.getCharts()
							.getResultLigneMoyen().getJanvier().getJourWeekend().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendJanvier,simulationJourWeekendJanvier
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getJanvier().setJourWeekend(simulationJourWeekendJanvier.getResults());
					

					
					Simulation simulationJourSemainJanvier =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainJanvier.setResults(resultsCourbeMoyen.getJanvier().getJourSemaine());
					
					this.resultLigneService.traitementLigneChart(simulationJourSemainJanvier,simulationJourWeekendJanvier
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getJanvier().setJourSemaine(simulationJourSemainJanvier.getResults());

					///////////////////////////////////////////////////////////////////////////////////
					Simulation simulationJourWeekendFevrie =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendFevrie.setResults(resultsCourbeMoyen.getFevrier().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendFevrie,simulation.getCharts()
							.getResultLigneMoyen().getFevrier().getJourWeekend().get(simulation.getCharts()
									.getResultLigneMoyen().getFevrier().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendFevrie,simulationJourWeekendFevrie
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getFevrier().setJourWeekend(simulationJourWeekendFevrie.getResults());
					
					
					Simulation simulationJourSemainFevrie =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainFevrie.setResults(resultsCourbeMoyen.getFevrier().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainFevrie,simulationJourWeekendFevrie
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getFevrier().setJourSemaine(simulationJourSemainFevrie.getResults());
					
					///////////////////////////////////////////////////////////////////////////////////
					Simulation simulationJourWeekendMars =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendMars.setResults(resultsCourbeMoyen.getMars().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendMars,simulation.getCharts().
							getResultLigneMoyen().getMars().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getMars().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendMars,simulationJourWeekendMars
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getMars().setJourWeekend(simulationJourWeekendMars.getResults());
					
					
					Simulation simulationJourSemainMars =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainMars.setResults(resultsCourbeMoyen.getMars().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainMars,simulationJourWeekendMars
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getMars().setJourSemaine(simulationJourSemainMars.getResults());
					
					///////////////////////////////////////////////////////////////////////////////////
					
					Simulation simulationJourWeekendAvril =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendAvril.setResults(resultsCourbeMoyen.getAvril().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendAvril,simulation.getCharts().
							getResultLigneMoyen().getAvril().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getAvril().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendAvril,simulationJourWeekendAvril
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getAvril().setJourWeekend(simulationJourWeekendAvril.getResults());
					
					
					Simulation simulationJourSemainAvril =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainAvril.setResults(resultsCourbeMoyen.getAvril().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainAvril,simulationJourWeekendAvril
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getAvril().setJourSemaine(simulationJourSemainAvril.getResults());
					///////////////////////////////////////////////////////////////////////////////////
					
					Simulation simulationJourWeekendMai =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendMai.setResults(resultsCourbeMoyen.getMai().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendMai,simulation.getCharts().
							getResultLigneMoyen().getMai().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getMai().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendMai,simulationJourWeekendMai
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getMai().setJourWeekend(simulationJourWeekendMai.getResults());
					
					
					Simulation simulationJourSemainMai =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainMai.setResults(resultsCourbeMoyen.getMai().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainMai,simulationJourWeekendMai
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getMai().setJourSemaine(simulationJourSemainMai.getResults());
					///////////////////////////////////////////////////////////////////////////////////

					
					Simulation simulationJourWeekendJuin =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendJuin.setResults(resultsCourbeMoyen.getJuin().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendJuin,simulation.getCharts().
							getResultLigneMoyen().getJuin().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getJuin().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendJuin,simulationJourWeekendJuin
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getJuin().setJourWeekend(simulationJourWeekendJuin.getResults());
					
					Simulation simulationJourSemainJuin =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainJuin.setResults(resultsCourbeMoyen.getJuin().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainJuin,simulationJourWeekendJuin
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getJuin().setJourSemaine(simulationJourSemainJuin.getResults());
					///////////////////////////////////////////////////////////////////////////////////

					
					Simulation simulationJourWeekendJuillet =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendJuillet.setResults(resultsCourbeMoyen.getJuillet().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendJuillet,simulation.getCharts().
							getResultLigneMoyen().getJuillet().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getJuillet().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendJuillet,simulationJourWeekendJuillet
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getJuillet().setJourWeekend(simulationJourWeekendJuillet.getResults());
					
					Simulation simulationJourSemainJuillet =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainJuillet.setResults(resultsCourbeMoyen.getJuillet().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainJuillet,simulationJourWeekendJuillet
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getJuillet().setJourSemaine(simulationJourSemainJuillet.getResults());
					///////////////////////////////////////////////////////////////////////////////////

					
					Simulation simulationJourWeekendAout =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendAout.setResults(resultsCourbeMoyen.getAout().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendAout,simulation.getCharts().
							getResultLigneMoyen().getAout().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getAout().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendAout,simulationJourWeekendAout
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getAout().setJourWeekend(simulationJourWeekendAout.getResults());
					
					Simulation simulationJourSemainAout =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainAout.setResults(resultsCourbeMoyen.getAout().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainAout,simulationJourWeekendAout
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getAout().setJourSemaine(simulationJourSemainAout.getResults());
					///////////////////////////////////////////////////////////////////////////////////
					Simulation simulationJourWeekendSeptembre =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendSeptembre.setResults(resultsCourbeMoyen.getSeptembre().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendSeptembre,simulation.getCharts().
							getResultLigneMoyen().getSeptembre().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getSeptembre().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendSeptembre,simulationJourWeekendSeptembre
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getSeptembre().setJourWeekend(simulationJourWeekendSeptembre.getResults());
					
					
					Simulation simulationJourSemainSeptembre =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainSeptembre.setResults(resultsCourbeMoyen.getSeptembre().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainSeptembre,simulationJourWeekendSeptembre
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getSeptembre().setJourSemaine(simulationJourSemainSeptembre.getResults());
					
					///////////////////////////////////////////////////////////////////////////////////

					
					Simulation simulationJourWeekendOctobre =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendOctobre.setResults(resultsCourbeMoyen.getOctobre().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendOctobre,simulation.getCharts().
							getResultLigneMoyen().getOctobre().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getOctobre().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendOctobre,simulationJourWeekendOctobre
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getOctobre().setJourWeekend(simulationJourWeekendOctobre.getResults());
					
					Simulation simulationJourSemainOctobre =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainOctobre.setResults(resultsCourbeMoyen.getOctobre().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainOctobre,simulationJourWeekendOctobre
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getOctobre().setJourSemaine(simulationJourSemainOctobre.getResults());
					///////////////////////////////////////////////////////////////////////////////////

					
					Simulation simulationJourWeekendNovembre =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendNovembre.setResults(resultsCourbeMoyen.getNovembre().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendNovembre,simulation.getCharts().
							getResultLigneMoyen().getNovembre ().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getNovembre ().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendNovembre,simulationJourWeekendNovembre
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getNovembre().setJourWeekend(simulationJourWeekendNovembre.getResults());
					
					Simulation simulationJourSemainNovembre =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainNovembre.setResults(resultsCourbeMoyen.getNovembre().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainNovembre,simulationJourWeekendNovembre
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getNovembre().setJourSemaine(simulationJourSemainNovembre.getResults());
					///////////////////////////////////////////////////////////////////////////////////
					
					Simulation simulationJourWeekendDecembre =(Simulation) ObjectService.deepClone(simulation);
					simulationJourWeekendDecembre.setResults(resultsCourbeMoyen.getDecembre().getJourWeekend());
					this.resultLigneService.traitementLigneChart(simulationJourWeekendDecembre,simulation.getCharts().
							getResultLigneMoyen().getDecembre().getJourWeekend().get(simulation.getCharts().
									getResultLigneMoyen().getDecembre().getJourWeekend().size()-1));
					//**//
					this.resultLigneService.traitementLigneChart(simulationJourWeekendDecembre,simulationJourWeekendDecembre
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					//**//
					resultsCourbeMoyen.getDecembre().setJourWeekend(simulationJourWeekendDecembre.getResults());
					
					
					Simulation simulationJourSemainDecembre =(Simulation) ObjectService.deepClone(simulation);
					simulationJourSemainDecembre.setResults(resultsCourbeMoyen.getDecembre().getJourSemaine());
					this.resultLigneService.traitementLigneChart(simulationJourSemainDecembre,simulationJourWeekendDecembre
							.getResults().get(simulation.getCharts()
									.getResultLigneMoyen().getJanvier().getJourWeekend().size()-1));
					resultsCourbeMoyen.getDecembre().setJourSemaine(simulationJourSemainDecembre.getResults());
					///////////////////////////////////////////////////////////////////////////////////

					return resultsCourbeMoyen;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		 return null;
	}
	
}
