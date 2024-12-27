package aurionpro.erp.ipms.authorization.auropayclient;

import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.authentication.JwtUtil;
import aurionpro.erp.ipms.utility.StringUtil;


@RestController()
@RequestMapping(value = "/cpms/client")
@Transactional()
public class ClientLoginController {
	
	@Autowired
    private ClientLoginRepository clientRepo;

    @Autowired
    private AuroPayClientService auroClientService;

    @Autowired
    private ClientLoginService clientService;
    
    @Autowired
    private AuroPayClientRepository auroClientRepo;
    
    @Autowired
	private JwtUtil jwtUtil;
    
    
    @Autowired
    PasswordEncoder passEncoder;
    
    
//    @PostMapping()
//    public ClientLogin creatClientLogin(@RequestBody AuroPayClientRequest client){
//
//        Optional<ClientLogin> acTemp=clientRepo.findById(client.getEmailId());
//
//        if (acTemp.isPresent()){
//            throw new EntityExistsException("Client already exists");
//        }
//
//        String randomPassword=StringUtil.randomPasswordString(8); //generate random Password
//
//        ClientLogin acCreate=new ClientLogin();
//        acCreate.setClientname(client.getEmailId());
//        String pass=passEncoder.encode("admin#123");
//        acCreate.setPassword(pass);
//        acCreate.setFirstAttempt(true);
//        if(client.getAccountExpiryDate()==0){
//        	acCreate.setAccountExpiryDate(Long.valueOf("4102338600000")); // set to 31/12/2099
//        }
//        clientRepo.save(acCreate);
//
//        AuroPayClient clientProfile= new AuroPayClient(client);
//        auroClientService.createAuroPayClient(clientProfile);
//
//        return acCreate;
//    }
    
	@PostMapping("/login")
	public ClientLoginResponse login(@RequestBody ClientLoginRequest loginRequest) {

//		String password = passEncoder.encode(loginRequest.getPassword());
		UserDetails userDetails;
		Authentication authentication;
		try {
			userDetails = loadUserAndVerifyLogin(loginRequest.getClientname(), loginRequest.getPassword());
			authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
		} catch (BadCredentialsException | AccessDeniedException e) {
			throw new BadCredentialsException("user.not.found");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtil.generateToken(authentication, userDetails);
		if (StringUtils.isEmpty(token)) {
			throw new InternalError();
		}
		ClientLoginResponse loginResponse = new ClientLoginResponse(token, userDetails.getUsername());
		return loginResponse;
	}

	private UserPrincipal loadUserAndVerifyLogin(String clientname, String password) {
 		AuroPayClient client = auroClientRepo.findByEmailIdAndStatusAndIsDeleted(clientname, 1, false);
		if (client == null) {
			throw new BadCredentialsException("user not found");
		}
		if(!passEncoder.matches(password, client.getPassword())) {
			throw new BadCredentialsException("Invalid user not found");
			
		}
		return new UserPrincipal(client.getEntityId(), client.getClientName(), client.getEmailId(),
				client.getPassword());
	}
}


