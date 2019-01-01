package System.Solaire.com.WebServiceArtifact.Services.Interface;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;

public interface IBatterieService {

	boolean insertBatterie(Batterie batterie);

	boolean modiferBatterie(String batteries);

	boolean supprimerBatterie(Batterie batterie);

	Batterie getBatterie(long id);

	List<Batterie> getAll();
}
