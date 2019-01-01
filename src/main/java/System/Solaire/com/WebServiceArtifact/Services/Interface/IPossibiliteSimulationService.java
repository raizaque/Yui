package System.Solaire.com.WebServiceArtifact.Services.Interface;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

import System.Solaire.com.WebServiceArtifact.Model.MoisConsomation;
import System.Solaire.com.WebServiceArtifact.Model.PossibiliteSimulation;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;

public interface IPossibiliteSimulationService {

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    PossibiliteSimulation Calcule(Simulation simulation);

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	PossibiliteSimulation possibilite(MultipartFile file, double nombre_Heur_Production,
			 double valeur_décharge_batterie, String dateDebut, double puissance_maximum_Onduleur,
			double seuil_reseau, double maxbatterie, double minbatterie, double patbatterie, double maxPuissance, double minPuissance, double patPuissance,double autoProdVise);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    PossibiliteSimulation possibilitePoderation(Simulation simulation);
    }
