package System.Solaire.com.WebServiceArtifact.Services.Interface;

import java.text.ParseException;

import org.springframework.security.access.annotation.Secured;

import System.Solaire.com.WebServiceArtifact.Model.ResultLigne;
import System.Solaire.com.WebServiceArtifact.Model.Simulation;

public interface IResultatLigneService {
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    void traitementLigne(Simulation simulation);

    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    void traitementLigneChart(Simulation simulation, ResultLigne resultLigne);
    ;}
