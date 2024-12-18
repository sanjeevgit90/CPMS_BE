package aurionpro.erp.ipms.authorization.auropayclient;

import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    PasswordEncoder passEncoder;
    
    
    @PostMapping()
    public ClientLogin creatClientLogin(@RequestBody AuroPayClientRequest client){

        Optional<ClientLogin> acTemp=clientRepo.findById(client.getEmailId());

        if (acTemp.isPresent()){
            throw new EntityExistsException("Client already exists");
        }

        String randomPassword=StringUtil.randomPasswordString(8); //generate random Password

        ClientLogin acCreate=new ClientLogin();
        acCreate.setClientname(client.getEmailId());
        String pass=passEncoder.encode("admin#123");
        acCreate.setPassword(pass);
        acCreate.setFirstAttempt(true);
        if(client.getAccountExpiryDate()==0){
        	acCreate.setAccountExpiryDate(Long.valueOf("4102338600000")); // set to 31/12/2099
        }
        clientRepo.save(acCreate);

        AuroPayClient clientProfile= new AuroPayClient(client);
        auroClientService.createAuroPayClient(clientProfile);

//        String msgBody="Dear User, <br><br> Thankyou for registring with us. <br>Your Password: " + randomPassword + "<br><br> Regards,<br>IPMS Team";
//        emailService.sendEmail(uProfile.getUserName(),"Registration Successful", msgBody, "");

        return acCreate;
    }

}
