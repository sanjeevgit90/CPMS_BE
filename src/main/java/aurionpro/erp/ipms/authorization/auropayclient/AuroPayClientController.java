package aurionpro.erp.ipms.authorization.auropayclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/cpms/auropayclient")
public class AuroPayClientController {
	
	@Autowired
    private AuroPayClientService clientService;
	
	 @PostMapping()
	    public AuroPayClient CreateClient(@RequestBody AuroPayClient auroClient){

	            return clientService.createAuroPayClient(auroClient);
	    }

}
