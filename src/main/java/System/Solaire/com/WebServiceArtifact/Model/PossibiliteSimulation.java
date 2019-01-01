package System.Solaire.com.WebServiceArtifact.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PossibiliteSimulation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2315435516914293745L;
	private List<List<Double>> possibiliteAutoConsomation;
	private List<List<Double>> possibiliteAutoproduction;
	private double maxbatterie;
	private double minbatterie;
	private double patbatterie;
	private double maxPuissance;
	private double minPuissance;
	private double patPuissance;
	private double autoProdvise;
	private List <String> autoProdviseProche;
	
	public PossibiliteSimulation() {
	}

	public PossibiliteSimulation(double maxbatterie, double minbatterie, double patbatterie, double maxPuissance,
			double minPuissance, double patPuissance, double autoProdvise) {

		this.maxbatterie = maxbatterie;
		this.minbatterie = minbatterie;
		this.patbatterie = patbatterie;
		this.maxPuissance = maxPuissance;
		this.minPuissance = minPuissance;
		this.patPuissance = patPuissance;
		this.autoProdvise = autoProdvise;
	}
	
	public List <String> getAutoProdviseProche() {
		return autoProdviseProche;
	}

	public void setAutoProdviseProche(List <String>autoProdviseProche) {
		this.autoProdviseProche = autoProdviseProche;
	}

	public List<List<Double>> getPossibiliteAutoConsomation() {
		return possibiliteAutoConsomation;
	}

	public double getAutoProdvise() {
		return autoProdvise;
	}

	public void setAutoProdvise(double autoProdvise) {
		this.autoProdvise = autoProdvise;
	}

	public void setPossibiliteAutoConsomation(List<List<Double>> pacosomation) {
		this.possibiliteAutoConsomation = pacosomation;
	}

	public List<List<Double>> getPossibiliteAutoproduction() {
		return possibiliteAutoproduction;
	}

	public void setPossibiliteAutoproduction(List<List<Double>> possibiliteAutoproduction) {
		this.possibiliteAutoproduction = possibiliteAutoproduction;
	}

	public double getMaxbatterie() {
		return maxbatterie;
	}

	public void setMaxbatterie(double maxbatterie) {
		this.maxbatterie = maxbatterie;
	}

	public double getMinbatterie() {
		return minbatterie;
	}

	public void setMinbatterie(double minbatterie) {
		this.minbatterie = minbatterie;
	}

	public double getPatbatterie() {
		return patbatterie;
	}

	public void setPatbatterie(double patbatterie) {
		this.patbatterie = patbatterie;
	}

	public double getMaxPuissance() {
		return maxPuissance;
	}

	public void setMaxPuissance(double maxPuissance) {
		this.maxPuissance = maxPuissance;
	}

	public double getMinPuissance() {
		return minPuissance;
	}

	public void setMinPuissance(double minPuissance) {
		this.minPuissance = minPuissance;
	}

	public double getPatPuissance() {
		return patPuissance;
	}

	public void setPatPuissance(double patPuissance) {
		this.patPuissance = patPuissance;
	}




}
