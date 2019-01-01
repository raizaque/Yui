package System.Solaire.com.WebServiceArtifact.DAO.Interface;

import java.util.List;

import System.Solaire.com.WebServiceArtifact.Entitry.Societe;

public interface ISocieteDAO {

	List<Societe> getall();

	boolean insert(Societe societe);

	boolean supprimer(Societe societe);

	Societe getSociete(long idSociete);

}
