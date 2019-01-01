package System.Solaire.com.WebServiceArtifact.Model;

import java.io.Serializable;
import java.util.*;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Simulation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2414257194680634928L;

	public Simulation() {
	}

	private PossibiliteSimulation possibiliteSimulation;
	private double puissance_Installee;
	private double nombre_Heur_Production;
	private double capacite_battrie;
	private double puissance_maximum_Onduleur;
	private double valeur_decharge_batterie;
	private double base_PV = 72;
	private double base_Prod = 1142;
	// private double base_PV=40.4;
	// private double base_Prod=1308;
	private double taux_auto_production;
	private double taux_auto_consomation;
	private String dateDebut;
	private double seuil_reseau = 0;
	private List<ResultLigne> results;
	private MoisConsomation moisConsomation;
	@Autowired
	private Charts charts;

	public void setparams(double puissance_Installee, double nombre_Heur_Production, double capacite_battrie,
			double valeur_decharge_batterie, String dateDebut, double puissance_maximum_Onduleur,
			MoisConsomation moisconsomation, double seuil_reseau) {
		this.puissance_Installee = puissance_Installee * 1000;
		this.nombre_Heur_Production = nombre_Heur_Production;
		this.capacite_battrie = capacite_battrie * 1000;
		this.valeur_decharge_batterie = valeur_decharge_batterie * 1000;
		this.dateDebut = dateDebut;
		this.puissance_maximum_Onduleur = puissance_maximum_Onduleur * 1000;
		this.moisConsomation = moisconsomation;
		this.seuil_reseau = seuil_reseau;
		if (this.capacite_battrie == 0) {
			this.valeur_decharge_batterie = 0;
			this.puissance_maximum_Onduleur = 0;
		}
	}

	public void setparams(double puissance_Installee, double nombre_Heur_Production, double capacite_battrie,
			double valeur_decharge_batterie, String dateDebut, double puissance_maximum_Onduleur, double seuil_reseau) {
		this.puissance_Installee = puissance_Installee * 1000;
		this.nombre_Heur_Production = nombre_Heur_Production;
		this.capacite_battrie = capacite_battrie * 1000;
		this.valeur_decharge_batterie = valeur_decharge_batterie * 1000;
		this.dateDebut = dateDebut;
		this.puissance_maximum_Onduleur = puissance_maximum_Onduleur * 1000;
		this.seuil_reseau = seuil_reseau * 1000;
	}
	public PossibiliteSimulation getPossibiliteSimulation() {
		return possibiliteSimulation;
	}

	public void setPossibiliteSimulation(PossibiliteSimulation possibiliteSimulation) {
		this.possibiliteSimulation = possibiliteSimulation;
	}

	public double getSeuil_reseau() {
		return seuil_reseau;
	}

	public void setSeuil_reseau(double seuil_reseau) {
		this.seuil_reseau = seuil_reseau;
	}

	public MoisConsomation getMoisConsomation() {
		return moisConsomation;
	}

	public void setMoisConsomation(MoisConsomation moisConsomation) {
		this.moisConsomation = moisConsomation;
	}

	public double getBase_PV() {
		return base_PV;
	}

	public double getBase_Prod() {
		return base_Prod;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String string) {
		this.dateDebut = dateDebut;
	}

	public double getPuissance_Installee() {
		return puissance_Installee;
	}

	public void setPuissance_Installee(double puissance_Installee) {
		this.puissance_Installee = puissance_Installee * 1000;
	}

	public double getNombre_Heur_Production() {
		return nombre_Heur_Production;
	}

	public void setNombre_Heur_Production(double nombre_Heur_Production) {
		this.nombre_Heur_Production = nombre_Heur_Production;
	}

	public double getCapacite_battrie() {
		return capacite_battrie;
	}

	public void setCapacite_battrie(double capacite_battrie) {
		this.capacite_battrie = capacite_battrie * 1000;
	}

	public double getPuissance_maximum_Onduleur() {

		return puissance_maximum_Onduleur;
	}

	public void setPuissance_maximum_Onduleur(double puissance_maximum_Onduleur) {
		this.puissance_maximum_Onduleur = puissance_maximum_Onduleur;
	}

	public void setBase_PV(double base_PV) {
		this.base_PV = base_PV;
	}

	public void setBase_Prod(double base_Prod) {
		this.base_Prod = base_Prod;
	}

	public double getValeur_decharge_batterie() {
		return valeur_decharge_batterie;
	}

	public void setValeur_decharge_batterie(double valeur_decharge_batterie) {
		this.valeur_decharge_batterie = valeur_decharge_batterie;
	}

	public List<ResultLigne> getResults() {
		return results;
	}

	public void setResults(List<ResultLigne> list) {
		this.results = list;
	}

	public double getTaux_auto_production() {
		return taux_auto_production;
	}

	public void setTaux_auto_production(double taux_auto_production) {
		this.taux_auto_production = taux_auto_production;
	}

	public double getTaux_auto_consomation() {
		return taux_auto_consomation;
	}

	public void setTaux_auto_consomation(double taux_auto_consomation) {
		this.taux_auto_consomation = taux_auto_consomation;
	}

	public Charts getCharts() {
		return charts;
	}

	public void setCharts(Charts charts) {
		this.charts = charts;
	}

	public void setconsomationfinal(double janvier, double fevrier, double mars, double avril, double mai, double juin,
			double juillet, double aout, double septembre, double octobre, double novembre, double decembre) {
		if (janvier > 0)
			this.getMoisConsomation().setJanvier(janvier);
		if (fevrier > 0)
			this.getMoisConsomation().setFevrier(fevrier);
		if (mars > 0)
			this.getMoisConsomation().setMars(mars);
		if (avril > 0)
			this.getMoisConsomation().setAvril(avril);
		if (mai > 0)
			this.getMoisConsomation().setMai(mai);
		if (juin > 0)
			this.getMoisConsomation().setJuin(juin);
		if (juillet > 0)
			this.getMoisConsomation().setJuillet(juillet);
		if (aout > 0)
			this.getMoisConsomation().setAout(aout);
		if (septembre > 0)
			this.getMoisConsomation().setSeptembre(septembre);
		if (octobre > 0)
			this.getMoisConsomation().setOctobre(octobre);
		if (novembre > 0)
			this.getMoisConsomation().setNovembre(novembre);
		if (decembre > 0)
			this.getMoisConsomation().setDecembre(decembre);

	}

	@Override
	public String toString() {
		return "Simulation [possibiliteSimulation=" + possibiliteSimulation + ", puissance_Installee="
				+ puissance_Installee + ", nombre_Heur_Production=" + nombre_Heur_Production + ", capacite_battrie="
				+ capacite_battrie + ", puissance_maximum_Onduleur=" + puissance_maximum_Onduleur
				+ ", valeur_decharge_batterie=" + valeur_decharge_batterie + ", base_PV=" + base_PV + ", base_Prod="
				+ base_Prod + ", taux_auto_production=" + taux_auto_production + ", taux_auto_consomation="
				+ taux_auto_consomation + ", dateDebut=" + dateDebut + ", seuil_reseau=" + seuil_reseau + ", results="
				+ results + ", moisConsomation=" + moisConsomation + ", charts=" + charts + "]";
	}
	
}
