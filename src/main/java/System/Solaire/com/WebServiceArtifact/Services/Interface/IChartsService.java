package System.Solaire.com.WebServiceArtifact.Services.Interface;

import org.springframework.security.access.annotation.Secured;

import System.Solaire.com.WebServiceArtifact.Model.MoisResultList;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;

public interface IChartsService {

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	void setChartsResultLigne(Simulation simulation);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"}) 
    void resultLigneSemaineWS(Simulation simulation);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"}) 
    void setResultLigneMoyen(Simulation simulation);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"}) 
	MoisResultList setCourbMoyen(Simulation simulation);
	
}
