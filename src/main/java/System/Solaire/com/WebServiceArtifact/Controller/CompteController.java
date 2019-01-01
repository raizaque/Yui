package System.Solaire.com.WebServiceArtifact.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import System.Solaire.com.WebServiceArtifact.Entiry.Repository.CompteInfoRepository;
import System.Solaire.com.WebServiceArtifact.Entitry.CompteInfo;
@Controller
@RequestMapping("compte")
public class CompteController {
	@Autowired
	CompteInfoRepository compteInfoRepository;
	@GetMapping(value= {"/comptes"})
	public ResponseEntity<List<CompteInfo>> getUsers(){
		List<CompteInfo> list = (List<CompteInfo>) compteInfoRepository.findAll();
		return new ResponseEntity<List<CompteInfo>>(list, HttpStatus.OK);
	}
	@PostMapping(value="/connect")
	public ResponseEntity<CompteInfo> getUsers(@RequestParam("mail")String pseudo,
			@RequestParam("mot_de_passe")String mot_de_passe){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		CompteInfo compte= this.compteInfoRepository.findByPseudo(pseudo);
		
		if(passwordEncoder.matches(mot_de_passe, compte.getMotDePasse())) {
			return new ResponseEntity<CompteInfo>(compte,HttpStatus.OK);}
		return new ResponseEntity<CompteInfo>(compte,HttpStatus.NOT_FOUND);
	}
}
