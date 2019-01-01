package System.Solaire.com.WebServiceArtifact.Model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class MoisConsomation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4923350599756479848L;
	private double janvier;
	private double fevrier;
	private double mars;
	private double avril;
	private double mai;
	private double juin;
	private double juillet;
	private double aout;
	private double septembre;
	private double octobre;
	private double novembre;
	private double decembre;
	public MoisConsomation() {
		super();
	}
	public double getJanvier() {
		return janvier;
	}
	public void setJanvier(double janvier) {
		this.janvier = janvier;
	}
	public double getFevrier() {
		return fevrier;
	}
	public void setFevrier(double fevrier) {
		this.fevrier = fevrier;
	}
	public double getMars() {
		return mars;
	}
	public void setMars(double mars) {
		this.mars = mars;
	}
	public double getAvril() {
		return avril;
	}
	public void setAvril(double avril) {
		this.avril = avril;
	}
	public double getMai() {
		return mai;
	}
	public void setMai(double mai) {
		this.mai = mai;
	}
	public double getJuin() {
		return juin;
	}
	public void setJuin(double juin) {
		this.juin = juin;
	}
	public double getJuillet() {
		return juillet;
	}
	public void setJuillet(double juillet) {
		this.juillet = juillet;
	}
	public double getAout() {
		return aout;
	}
	public void setAout(double aout) {
		this.aout = aout;
	}
	public double getSeptembre() {
		return septembre;
	}
	public void setSeptembre(double septembre) {
		this.septembre = septembre;
	}
	public double getOctobre() {
		return octobre;
	}
	public void setOctobre(double octobre) {
		this.octobre = octobre;
	}
	public double getNovembre() {
		return novembre;
	}
	public void setNovembre(double novembre) {
		this.novembre = novembre;
	}
	public double getDecembre() {
		return decembre;
	}
	public void setDecembre(double decembre) {
		this.decembre = decembre;
	}
	@Override
	public String toString() {
		return "MoisConsomation [janvier=" + janvier + ", fevrier=" + fevrier + ", mars=" + mars + ", avril=" + avril
				+ ", mai=" + mai + ", juin=" + juin + ", juillet=" + juillet + ", aout=" + aout + ", septembre="
				+ septembre + ", octobre=" + octobre + ", novembre=" + novembre + ", decembre=" + decembre + "]";
	}
	
}
