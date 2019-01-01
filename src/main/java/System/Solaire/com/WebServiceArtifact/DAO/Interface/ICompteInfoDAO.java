package System.Solaire.com.WebServiceArtifact.DAO.Interface;

import java.util.List;

import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;

public interface ICompteInfoDAO {
	CompteInfo getActiveUser(CompteInfo compteinfo);
	CompteInfo getActiveUser(String usernmae);
	List<CompteInfo> getActiveUsers();
    void addCompte(CompteInfo compteinfo);
    void updateCompte(CompteInfo compteinfo);
    void deleteCompte(long compteinfo);
    CompteInfo getCompteById(CompteInfo compteinfo);
    boolean compteExists(String pseudo);
	CompteInfo getCompteById(long id_Compte);
	CompteInfo getcompteByEmail(String email);
	void modifier(CompteInfo compte);
}
