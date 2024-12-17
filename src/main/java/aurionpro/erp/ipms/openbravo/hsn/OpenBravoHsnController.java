package aurionpro.erp.ipms.openbravo.hsn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@RestController
@RequestMapping(value = "/ipms/obhsn")
public class OpenBravoHsnController {

	@Autowired
    OpenBravoHsnRepository hsnRepo;

    @PostMapping()
    public OpenBravoHsnMaster CreateHsnMaster(@Valid @RequestBody OpenBravoHsnMaster hsnMaster){
        validateRequest(hsnMaster);
		Optional<OpenBravoHsnMaster> tcheck = hsnRepo.findById(hsnMaster.getOpenBravoId());
		if (tcheck.isPresent()){
            throw new RuntimeException("HSN ID already exists.");
        }
        return hsnRepo.save(hsnMaster);
    }
    
    private boolean validateRequest(OpenBravoHsnMaster hsnMaster){
    	List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(hsnMaster.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("HSN entity is invalid", errorlist);
		}
		return true;
    }
}