package System.Solaire.com.WebServiceArtifact.Model;
//////////////////// jour de weekend et jour de semaine
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class MoisResultList  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 133634027918955321L;
	public MoisResultList() {
	}
	@Autowired
	private ResultListJourWeekendJourSemaine janvier;
	@Autowired
	private ResultListJourWeekendJourSemaine  fevrier;
	@Autowired
	private ResultListJourWeekendJourSemaine  mars;
	@Autowired
	private ResultListJourWeekendJourSemaine  avril;
	@Autowired
	private ResultListJourWeekendJourSemaine  mai;
	@Autowired
	private ResultListJourWeekendJourSemaine  juin;
	@Autowired
	private ResultListJourWeekendJourSemaine juillet;
	@Autowired
	private ResultListJourWeekendJourSemaine  aout;
	@Autowired
	private ResultListJourWeekendJourSemaine  septembre;
	@Autowired
	private ResultListJourWeekendJourSemaine  octobre;
	@Autowired
	private ResultListJourWeekendJourSemaine  novembre;
	@Autowired
	private ResultListJourWeekendJourSemaine decembre;
	public ResultListJourWeekendJourSemaine getJanvier() {
		return janvier;
	}
	public void setJanvier(ResultListJourWeekendJourSemaine janvier) {
		this.janvier = janvier;
	}
	public ResultListJourWeekendJourSemaine getFevrier() {
		return fevrier;
	}
	public void setFevrier(ResultListJourWeekendJourSemaine fevrier) {
		this.fevrier = fevrier;
	}
	public ResultListJourWeekendJourSemaine getMars() {
		return mars;
	}
	public void setMars(ResultListJourWeekendJourSemaine mars) {
		this.mars = mars;
	}
	public ResultListJourWeekendJourSemaine getAvril() {
		return avril;
	}
	public void setAvril(ResultListJourWeekendJourSemaine avril) {
		this.avril = avril;
	}
	public ResultListJourWeekendJourSemaine getMai() {
		return mai;
	}
	public void setMai(ResultListJourWeekendJourSemaine mai) {
		this.mai = mai;
	}
	public ResultListJourWeekendJourSemaine getJuin() {
		return juin;
	}
	public void setJuin(ResultListJourWeekendJourSemaine juin) {
		this.juin = juin;
	}
	public ResultListJourWeekendJourSemaine getJuillet() {
		return juillet;
	}
	public void setJuillet(ResultListJourWeekendJourSemaine juillet) {
		this.juillet = juillet;
	}
	public ResultListJourWeekendJourSemaine getAout() {
		return aout;
	}
	public void setAout(ResultListJourWeekendJourSemaine aout) {
		this.aout = aout;
	}
	public ResultListJourWeekendJourSemaine getSeptembre() {
		return septembre;
	}
	public void setSeptembre(ResultListJourWeekendJourSemaine septembre) {
		this.septembre = septembre;
	}
	public ResultListJourWeekendJourSemaine getOctobre() {
		return octobre;
	}
	public void setOctobre(ResultListJourWeekendJourSemaine octobre) {
		this.octobre = octobre;
	}
	public ResultListJourWeekendJourSemaine getNovembre() {
		return novembre;
	}
	public void setNovembre(ResultListJourWeekendJourSemaine novembre) {
		this.novembre = novembre;
	}
	public ResultListJourWeekendJourSemaine getDecembre() {
		return decembre;
	}
	public void setDecembre(ResultListJourWeekendJourSemaine decembre) {
		this.decembre = decembre;
	}
	
	
}
