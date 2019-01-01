package System.Solaire.com.WebServiceArtifact.Config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import System.Solaire.com.WebServiceArtifact.DAO.Interface.ICompteInfoDAO;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.CompteInfoRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
@Service
public class MyAppUserDetailsService  implements UserDetailsService{
	@Autowired
	private CompteInfoRepository compteInfoRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CompteInfo activeUserInfo = (CompteInfo) compteInfoRepository.findByPseudo(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
		UserDetails userDetails = (UserDetails)new User(activeUserInfo.getPseudo(),
				activeUserInfo.getMotDePasse(), Arrays.asList(authority));
		return userDetails;
	}

}
