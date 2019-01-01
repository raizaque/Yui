package System.Solaire.com.WebServiceArtifact.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ResultListJourWeekendJourSemaine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 885176880671628620L;
	public ResultListJourWeekendJourSemaine() {
	}
	private List<ResultLigne> JourWeekend;
	private List<ResultLigne> JourSemaine;
	public List<ResultLigne> getJourWeekend() {
		return JourWeekend;
	}
	public void setJourWeekend(List<ResultLigne> jourWeekend) {
		JourWeekend = jourWeekend;
	}
	public List<ResultLigne> getJourSemaine() {
		return JourSemaine;
	}
	public void setJourSemaine(List<ResultLigne> jourSemaine) {
		JourSemaine = jourSemaine;
	}
	
}
