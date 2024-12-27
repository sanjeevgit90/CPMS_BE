package aurionpro.erp.ipms.authorization.auropayclient;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class AuroPayClientService {
	
	    @Autowired
	    private AuroPayClientRepository clientRepo;
	    

	public AuroPayClient createAuroPayClient(AuroPayClient auroClient) {
		 List<AuroPayClient> client= clientRepo.findByEmailId(auroClient.getEmailId());

	        if(client.size()>0)
	        {
	            throw new EntityExistsException("The Client  already exists");
	        }
	        else{
	            validate();
	            return clientRepo.save(auroClient);
	        }
	}
	
	 private void validate() {
	    }
	 
		public UserDetails loadUserByUsername(String username) {
			 AuroPayClient client= clientRepo.findByEmailIdAndStatusAndIsDeleted(username, 1, false);
			 if (client == null) {
				 throw new RuntimeException("Client not exists");
			 }
			 return  new UserPrincipal(client.getEntityId(), client.getClientName(), client.getEmailId(),
						client.getPassword());

		}


}
