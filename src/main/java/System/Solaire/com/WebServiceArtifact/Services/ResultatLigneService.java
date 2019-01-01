package System.Solaire.com.WebServiceArtifact.Services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import System.Solaire.com.WebServiceArtifact.Model.ResultLigne;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IResultatLigneService;

@Service
public class ResultatLigneService implements IResultatLigneService {
	private double sommeProduction;
	private double sommeAutoConsomation;
	private double sommeConsomation;

	@SuppressWarnings("deprecation")
	@Override
	public void traitementLigne(Simulation simulation) {

		try {
			List<ResultLigne> results=new ArrayList<ResultLigne>();
			 results = (ArrayList<ResultLigne>) simulation.getResults();
			/////////////////////////
			for (int i = 0; i < results.size(); i++) {
				results.get(i).calculerProduction_Wh(simulation.getPuissance_Installee(),
						simulation.getNombre_Heur_Production(), simulation.getBase_PV(), simulation.getBase_Prod());
				results.get(i).calculerBesoin();
				if (i == 0)
					results.get(i).setNiveau_stockbatterie(0);
				else if (simulation.getCapacite_battrie() == 0)

					results.get(i).setNiveau_stockbatterie(0);
				else
					results.get(i).calculerNiveau_stockbatterie(results.get(i - 1).getNiveau_stockbatterie(),
							results.get(i - 1).getFlux_batterie(), simulation.getCapacite_battrie());

				results.get(i).calculerFlux_batterie(simulation.getPuissance_maximum_Onduleur(),
						simulation.getValeur_decharge_batterie(), simulation.getSeuil_reseau());
				results.get(i).calculerAutoConsommation(simulation.getCapacite_battrie());
				results.get(i).calculerSoutirage();
				results.get(i).calculerSurplus_de_production(simulation.getCapacite_battrie());
				results.get(i).calculerAchat();
				if (i == 0)
					results.get(i).setEnergie_reinjectee_par_la_batterie_kWh(results.get(i).getFlux_batterie());
				else
					results.get(i).calculerEnergie_reinjectee_par_la_batterie_kWh(
							results.get(i - 1).getEnergie_reinjectee_par_la_batterie_kWh());

				results.get(i).num = i;
			}
			sommeProduction = 0;
			sommeAutoConsomation = 0;
			sommeConsomation = 0;
			simulation.getResults().forEach((result) -> {
				sommeProduction = sommeProduction + result.getProduction_Wh();
				sommeConsomation = sommeConsomation + result.getConso_wh();
				sommeAutoConsomation = sommeAutoConsomation + result.getAutoConsommation();
			});
			if (sommeProduction == 0) {
				simulation.setTaux_auto_consomation(0);
			} else {
				simulation.setTaux_auto_consomation(sommeAutoConsomation / sommeProduction);
			}
			if (sommeConsomation == 0) {
				simulation.setTaux_auto_production(0);
			} else {
				simulation.setTaux_auto_production(
						(simulation.getTaux_auto_consomation() * sommeProduction) / sommeConsomation);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void traitementLigneChart(Simulation simulation, ResultLigne resultLigne) {

		try {
			List<ResultLigne> results=new ArrayList<ResultLigne>();
			 results = (ArrayList<ResultLigne>) simulation.getResults();
			/////////////////////////
			for (int i = 0; i < results.size(); i++) {
				results.get(i).calculerBesoin();
				if (i == 0)
					{
					results.get(i).setNiveau_stockbatterie(resultLigne.getNiveau_stockbatterie());}
				else if (simulation.getCapacite_battrie() == 0)
					results.get(i).setNiveau_stockbatterie(0);
				else
					results.get(i).calculerNiveau_stockbatterie(results.get(i - 1).getNiveau_stockbatterie(),
							results.get(i - 1).getFlux_batterie(), simulation.getCapacite_battrie());

				results.get(i).calculerFlux_batterie(simulation.getPuissance_maximum_Onduleur(),
						simulation.getValeur_decharge_batterie(), simulation.getSeuil_reseau());
				results.get(i).calculerAutoConsommation(simulation.getCapacite_battrie());
				results.get(i).calculerSoutirage();
				results.get(i).calculerSurplus_de_production(simulation.getCapacite_battrie());
				results.get(i).calculerAchat();
				if (i == 0)
					results.get(i).setEnergie_reinjectee_par_la_batterie_kWh(results.get(i).getFlux_batterie());
				else
					results.get(i).calculerEnergie_reinjectee_par_la_batterie_kWh(
							results.get(i - 1).getEnergie_reinjectee_par_la_batterie_kWh());

				results.get(i).num = i;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void traitementLigneSansProduction(Simulation simulation) {
		List<ResultLigne> results=new ArrayList<ResultLigne>();
		 results = (ArrayList<ResultLigne>) simulation.getResults();
		for (int i = 0; i < results.size(); i++) {
			results.get(i).setProduction_Wh(0);;
			results.get(i).calculerBesoin();
			if (i == 0)
				results.get(i).setNiveau_stockbatterie(0);
			else if (simulation.getCapacite_battrie() == 0)

				results.get(i).setNiveau_stockbatterie(0);
			else
				results.get(i).calculerNiveau_stockbatterie(results.get(i - 1).getNiveau_stockbatterie(),
						results.get(i - 1).getFlux_batterie(), simulation.getCapacite_battrie());

			results.get(i).calculerFlux_batterie(simulation.getPuissance_maximum_Onduleur(),
					simulation.getValeur_decharge_batterie(), simulation.getSeuil_reseau());
			results.get(i).calculerAutoConsommation(simulation.getCapacite_battrie());
			results.get(i).calculerSoutirage();
			results.get(i).calculerSurplus_de_production(simulation.getCapacite_battrie());
			results.get(i).calculerAchat();
			if (i == 0)
				results.get(i).setEnergie_reinjectee_par_la_batterie_kWh(results.get(i).getFlux_batterie());
			else
				results.get(i).calculerEnergie_reinjectee_par_la_batterie_kWh(
						results.get(i - 1).getEnergie_reinjectee_par_la_batterie_kWh());

			results.get(i).num = i;
		}
		
	}
}
