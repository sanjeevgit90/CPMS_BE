package aurionpro.erp.ipms.utility.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")

public class EmailSendingController {
	
	@Autowired
	EmailRepository mailRepo;

 
	 @PostMapping("/saveEmail")
	 public Email saveEmail(@RequestBody Email email) {
	     return mailRepo.save(email);

	}
}
