package aurionpro.erp.ipms.openbravo.currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.openbravo.organisation.OpenBravoOrganisationRepository;

@RestController
@RequestMapping(value = "/ipms/obcurrency")
public class OpenBravoCurrencyController {

	@Autowired
	OpenBravoCurrencyRepository currencyRepository;
	
	@Autowired
	OpenBravoOrganisationRepository openBravoOrganisationRepo;

	@PostMapping()
	public OpenBravoCurrencyMaster createCurrencyMaster(@Valid @RequestBody OpenBravoCurrencyMaster currencyMaster) {
		Optional<OpenBravoCurrencyMaster> currencyTemp = currencyRepository.findById(currencyMaster.getOpenBravoId());

		if (currencyTemp.isPresent()) {
			throw new EntityExistsException("Currency ID already exists");
		} else {
			validate(currencyMaster);
			//for replacing open bravo id's with our orgid in the request
			if(!StringUtils.isEmpty(currencyMaster.getCurrency().getOrganisationId())){
				if(!currencyMaster.getCurrency().getOrganisationId().equalsIgnoreCase("0")) {
					Long orgId = openBravoOrganisationRepo.getOrgIdFromOpenBravoId(currencyMaster.getCurrency().getOrganisationId());
					if(StringUtils.isEmpty(orgId)) {
						throw new RuntimeException("Organisation does not exists.");
					}
					currencyMaster.getCurrency().setOrganisationId(orgId.toString());
				}
			}
			
			return currencyRepository.save(currencyMaster);
		}
	}

	private boolean validate(OpenBravoCurrencyMaster currencyMaster) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(currencyMaster.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Currency entity is invalid", errorlist);
		}
		return true;
	}
}
