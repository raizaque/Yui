package System.Solaire.com.WebServiceArtifact.Model;

import java.io.Serializable;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
@Component
public class Charts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8277539087191189598L;
	public Charts() {}
	@Autowired
	private MoisResultList ResultLigneSansProductionSansBatterie; 
	@Autowired
	private MoisResultList ResultLigne; 
	@Autowired
	private MoisResultList ResultLigneSansBatterie; 
	@Autowired
	private MoisResultList ResultLigneMoyen;
	@Autowired
	private MoisResultList ResultsCourbeMoyen;
	

	public MoisResultList getResultsCourbeMoyen() {
		return ResultsCourbeMoyen;
	}
	public void setResultsCourbeMoyen(MoisResultList resultsCourbeMoyen) {
		ResultsCourbeMoyen = resultsCourbeMoyen;
	}
	public MoisResultList getResultLigneSansProductionSansBatterie() {
		return ResultLigneSansProductionSansBatterie;
	}
	public void setResultLigneSansProductionSansBatterie(MoisResultList resultLigneSansProductionSansBatterie) {
		ResultLigneSansProductionSansBatterie = resultLigneSansProductionSansBatterie;
	}
	public MoisResultList getResultLigne() {
		return ResultLigne;
	}
	public void setResultLigne(MoisResultList resultLigne) {
		ResultLigne = resultLigne;
	}
	public MoisResultList getResultLigneSansBatterie() {
		return ResultLigneSansBatterie;
	}
	public void setResultLigneSansBatterie(MoisResultList resultLigneSansBatterie) {
		ResultLigneSansBatterie = resultLigneSansBatterie;
	}
	public MoisResultList getResultLigneMoyen() {
		return ResultLigneMoyen;
	}
	public void setResultLigneMoyen(MoisResultList resultLigneMoyen) {
		ResultLigneMoyen = resultLigneMoyen;
	}
}
