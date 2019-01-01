package System.Solaire.com.WebServiceArtifact.Services.Interface;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import System.Solaire.com.WebServiceArtifact.Entitry.Batterie;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;

public interface ICompteInfoService {
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    List<CompteInfo> getActiveUsers();
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    CompteInfo getCompteById(int compteInfoID);
    @Secured ({"ROLE_ADMIN"})
    boolean addCompte(CompteInfo compteInfo);
    @Secured ({"ROLE_ADMIN"})
    void updateCompte(CompteInfo compteInfo);
    @Secured ({"ROLE_ADMIN"})
    boolean deleteCompte(int CompteInfoID);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    CompteInfo getCompte(String mail,String motDePasse);
    @Secured ({"ROLE_ADMIN"})
    CompteInfo getCompteByEmail(String email);
    @Secured ({"ROLE_ADMIN"})
	boolean modiferUtilisateurs(String listUtilisateur);
    @Secured ({"ROLE_ADMIN"})
	boolean modifer(CompteInfo compte);
    @Secured ({"ROLE_ADMIN"})
	void deleteCompteByID(long parseLong);
}
