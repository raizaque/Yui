package System.Solaire.com.WebServiceArtifact.Services.Interface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import System.Solaire.com.WebServiceArtifact.Entitry.Societe;

public interface ISocieteService {

    @Secured ({"ROLE_ADMIN"})
    boolean insertSociete(Societe societe);
    @Secured ({"ROLE_ADMIN"})
	List<Societe> getAll();
    @Secured ({"ROLE_ADMIN"})
	boolean modiferSociete(String listSociete);
    @Secured ({"ROLE_ADMIN"})
	boolean supprimerSociete(Societe societe);
    @Secured ({"ROLE_ADMIN"})
	Societe getSociete(long idSociete);
}
