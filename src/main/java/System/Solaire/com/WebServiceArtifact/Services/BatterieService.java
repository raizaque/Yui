package System.Solaire.com.WebServiceArtifact.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import System.Solaire.com.WebServiceArtifact.DAO.BatterieDAO;
import System.Solaire.com.WebServiceArtifact.DAO.Interface.IBatterieDAO;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.BatterieRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Entitry.Societe;
import System.Solaire.com.WebServiceArtifact.Services.Interface.IBatterieService;

@Service
public class BatterieService implements IBatterieService{
	@Autowired
	IBatterieDAO batterieDAO;
	@Override
	public boolean insertBatterie(Batterie batterie) {
		if(this.batterieDAO.insert(batterie)) {
			return true;
		};
		return false;
	}
	boolean updateall=true;
	@Override
	public boolean modiferBatterie(String batterie) {
		updateall=true;
		ObjectMapper mapper = new ObjectMapper();
		List<Batterie> obj = new ArrayList<Batterie>();
		System.out.println(batterie);
		try {
			obj = mapper.readValue(batterie,
					mapper.getTypeFactory().constructCollectionType(List.class, Batterie.class));
			obj.forEach((object)->{
				System.out.println(object.getCapacite());
				boolean updated=this.batterieDAO.insert(object);
				if(updated==false) {
					updateall=false;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return updateall;
	}
	@Override
	public boolean supprimerBatterie(Batterie batterie) {
		if(this.batterieDAO.supprimer(batterie)) {
			return true;
		};
		return false;
	}
	@Override
	public Batterie getBatterie(long id) {
		Batterie batterie= batterieDAO.getBatterie(id);
		return batterie;
	}
	@Override
	public List<Batterie> getAll() {
		List <Batterie> batteries =new ArrayList<Batterie>();
		batteries=this.batterieDAO.getAll();
		batteries.remove(0);
		return batteries;
	}
	
}
