package System.Solaire.com.WebServiceArtifact.Model;

import java.io.Serializable;
import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class ResultLigne implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 975829605014726234L;
	public ResultLigne() {
		
	}
	public int num;
	private String date;
	private double profil_Helda;
	private double production_Wh;
	private double Conso_wh;
	private double niveau_stockbatterie;
	private double besoin;
	private double flux_batterie;
	private double soutirage;
	private double surplus_de_production;
	private double achat;
	private double autoConsommation;
	private double energie_reinjectee_par_la_batterie_kWh;
	public double getProfil_Helda() {
		return profil_Helda;
	}

	public void setProfil_Helda(double profil_Helda) {
		this.profil_Helda = profil_Helda;
	}

	@Override
	public String toString() {
		return "ResultLigne [number=" + num + ", date=" + date + ", profil_Helda=" + profil_Helda
				+ ", production_Wh=" + production_Wh + ", Conso_wh=" + Conso_wh + ", niveau_stockbatterie="
				+ niveau_stockbatterie + ", besoin=" + besoin + ", flux_batterie=" + flux_batterie + ", soutirage="
				+ soutirage + ", surplus_de_production=" + surplus_de_production + ", achat=" + achat
				+ ", autoConsommation=" + autoConsommation + ", energie_reinjectee_par_la_batterie_kWh="
				+ energie_reinjectee_par_la_batterie_kWh + "]";
	}

	public String getDate() {
		return date;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getProduction_Wh() {
		return production_Wh;
	}

	public void setProduction_Wh(double production_Wh) {
		this.production_Wh = production_Wh;
	}

	public double getConso_wh() {
		return Conso_wh;
	}

	public void setConso_wh(double conso_wh) {
		Conso_wh = conso_wh;
	}

	public double getNiveau_stockbatterie() {
		return niveau_stockbatterie;
	}

	public void setNiveau_stockbatterie(double niveau_stockbatterie) {
		this.niveau_stockbatterie = niveau_stockbatterie;
	}

	public double getBesoin() {
		return besoin;
	}

	public void setBesoin(double besoin) {
		this.besoin = besoin;
	}

	public double getFlux_batterie() {
		return flux_batterie;
	}

	public void setFlux_batterie(double flux_batterie) {
		this.flux_batterie = flux_batterie;
	}

	public double getSoutirage() {
		return soutirage;
	}

	public void setSoutirage(double soutirage) {
		this.soutirage = soutirage;
	}

	public double getSurplus_de_production() {
		return surplus_de_production;
	}

	public void setSurplus_de_production(double surplus_de_production) {
		this.surplus_de_production = surplus_de_production;
	}

	public double getAchat() {
		return achat;
	}

	public void setAchat(double achat) {
		this.achat = achat;
	}

	public double getAutoConsommation() {
		return autoConsommation;
	}

	public void setAutoConsommation(double autoConsommation) {
		this.autoConsommation = autoConsommation;
	}

	public double getEnergie_reinjectee_par_la_batterie_kWh() {
		return energie_reinjectee_par_la_batterie_kWh;
	}

	public void setEnergie_reinjectee_par_la_batterie_kWh(double energie_reinjectee_par_la_batterie_kWh) {
		this.energie_reinjectee_par_la_batterie_kWh = energie_reinjectee_par_la_batterie_kWh;
	}

	public void calculerProduction_Wh(double puissance_Installee, double nombre_Heur_Production, double base_PV,
			double base_Prod) {
		this.production_Wh = this.profil_Helda * (1 / base_PV) * (1 / base_Prod)
				* (nombre_Heur_Production * puissance_Installee);
	}

	public void calculerNiveau_stockbatterie(double niveau_stockbatterie, double flux_batterie,
			double capacite_battrie) {
		if (niveau_stockbatterie - flux_batterie > capacite_battrie)
			this.niveau_stockbatterie = capacite_battrie;
		else {
			if (niveau_stockbatterie - flux_batterie > 0)
				this.niveau_stockbatterie = niveau_stockbatterie - flux_batterie;
			else
				this.niveau_stockbatterie = 0;
		}
	}

	public void calculerBesoin() {
		this.besoin = this.Conso_wh - this.production_Wh;

	}

	//////////////////////////////////// sous fonction de calculerFlux_battrie
	private void calculeStockBesion() {
		if (this.niveau_stockbatterie > this.besoin)
			this.flux_batterie = this.besoin;
		else
			this.flux_batterie = this.niveau_stockbatterie;
	}

	private void calculeResultatOnduleur(double puissance_maximum_Onduleur) {
		if (this.flux_batterie < (-puissance_maximum_Onduleur))
			this.flux_batterie = -puissance_maximum_Onduleur;
		else
			calculeStockBesion();
	}

	private void calculeResultatDecharge(double valeur_decharge_batterie, double puissance_maximum_Onduleur) {
		if (this.flux_batterie > valeur_decharge_batterie)
			this.flux_batterie = valeur_decharge_batterie;
		else {
			calculeStockBesion();
			calculeResultatOnduleur(puissance_maximum_Onduleur);
		}
	}

	public void calculerFlux(double valeur_decharge_batterie, double puissance_maximum_Onduleur) {
		calculeStockBesion();
		calculeResultatOnduleur(puissance_maximum_Onduleur);
		calculeResultatDecharge(valeur_decharge_batterie, puissance_maximum_Onduleur);
	}

	public void calculerFlux_batterie(double puissance_maximum_Onduleur, double valeur_decharge_batterie,
			double seuil_reseaux) {

				calculerFlux(valeur_decharge_batterie, puissance_maximum_Onduleur);
			
	}

	public void calculerSoutirage() {
		if (this.Conso_wh - this.autoConsommation > 0)
			this.soutirage = this.Conso_wh - this.autoConsommation;
		else
			this.soutirage = 0;

	}

	public void calculerSurplus_de_production(double capacite_battrie) {
		if (capacite_battrie == 0) {
			if (this.production_Wh >= this.Conso_wh)
				this.surplus_de_production = this.production_Wh - this.Conso_wh;
			else
				this.surplus_de_production = 0;
		} else {
			if (this.niveau_stockbatterie >= capacite_battrie) {
				if (this.production_Wh - this.Conso_wh > 0)
					this.surplus_de_production = this.production_Wh - this.Conso_wh;
				else
					this.surplus_de_production = 0;
			} else if (this.flux_batterie < 0)
				this.surplus_de_production = this.production_Wh - this.Conso_wh + this.flux_batterie;
			else
				this.surplus_de_production = 0;
		}

	}
	public void calculerSurplus_de_productionChart(double capacite_battrie) {
		if(this.niveau_stockbatterie>=capacite_battrie)
			if(this.production_Wh>this.achat) {
				this.surplus_de_production=this.production_Wh-this.achat;
				System.out.println(this.surplus_de_production);}
			else
				this.surplus_de_production=0;
		else
			this.surplus_de_production=0;
	}

	public void calculerAchat() {
		this.achat = this.soutirage;
	}

	public void calculerAutoConsommation(double capacite_battrie) {
		if (capacite_battrie == 0) {
			if (this.production_Wh >= this.Conso_wh)
				this.autoConsommation = this.Conso_wh;
			else
				this.autoConsommation = this.production_Wh;
		} else {
			if (this.production_Wh >= this.Conso_wh)
				this.autoConsommation = this.Conso_wh;
			else
				this.autoConsommation = this.production_Wh + this.flux_batterie;
		}
	}

	public void calculerEnergie_reinjectee_par_la_batterie_kWh(double energie_reinjectee_par_la_batterie_kWh) {
		if (this.flux_batterie > 0)
			this.energie_reinjectee_par_la_batterie_kWh = energie_reinjectee_par_la_batterie_kWh + this.flux_batterie;
		else
			this.energie_reinjectee_par_la_batterie_kWh = this.flux_batterie;
		if (this.energie_reinjectee_par_la_batterie_kWh < 0)
			this.energie_reinjectee_par_la_batterie_kWh = 0;
	}

	public void setparams(int num, String date, double profil_Helda, double production_Wh, double Conso_wh,
			double niveau_stockbatterie, double besoin, double flux_batterie, double soutirage,
			double surplus_de_production, double achat, double autoConsommation,
			double energie_reinjectee_par_la_batterie_kWh) {
		this.num = num;
		this.date = date;
		this.profil_Helda = profil_Helda;
		this.production_Wh = production_Wh;
		this.Conso_wh = Conso_wh;
		this.niveau_stockbatterie = niveau_stockbatterie;
		this.besoin = besoin;
		this.flux_batterie = flux_batterie;
		this.soutirage = soutirage;
		this.surplus_de_production = surplus_de_production;
		this.achat = achat;
		this.autoConsommation = autoConsommation;
		this.energie_reinjectee_par_la_batterie_kWh = energie_reinjectee_par_la_batterie_kWh;

	}
	public ResultLigne(int num, String date, double profil_Helda, double production_Wh, double Conso_wh,
			double niveau_stockbatterie, double besoin, double flux_batterie, double soutirage,
			double surplus_de_production, double achat, double autoConsommation,
			double energie_reinjectee_par_la_batterie_kWh) {
		this.num = num;
		this.date = date;
		this.profil_Helda = profil_Helda;
		this.production_Wh = production_Wh;
		this.Conso_wh = Conso_wh;
		this.niveau_stockbatterie = niveau_stockbatterie;
		this.besoin = besoin;
		this.flux_batterie = flux_batterie;
		this.soutirage = soutirage;
		this.surplus_de_production = surplus_de_production;
		this.achat = achat;
		this.autoConsommation = autoConsommation;
		this.energie_reinjectee_par_la_batterie_kWh = energie_reinjectee_par_la_batterie_kWh;
	}

}
