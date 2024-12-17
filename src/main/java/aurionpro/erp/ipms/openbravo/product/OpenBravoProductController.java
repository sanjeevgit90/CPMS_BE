package aurionpro.erp.ipms.openbravo.product;

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
import aurionpro.erp.ipms.openbravo.category.OpenBravoCategoryRepository;
import aurionpro.erp.ipms.openbravo.hsn.OpenBravoHsnRepository;

@RestController
@RequestMapping(value = "/ipms/obproduct")
public class OpenBravoProductController {

	@Autowired
	OpenBravoProductRepository productRepo;
	
	@Autowired
	OpenBravoCategoryRepository openBravoCategoryRepo;
	
	@Autowired
    OpenBravoHsnRepository openBravoHsnRepo;

	@PostMapping()
    public OpenBravoProductMaster createProduct(@Valid @RequestBody OpenBravoProductMaster product){
		validate(product);
		Optional<OpenBravoProductMaster> tcheck = productRepo.findById(product.getOpenBravoId());
		if (tcheck.isPresent()){
            throw new RuntimeException("Product ID already exists.");
        }
		if(product.getProduct().getOrgflag()==null)
			product.getProduct().setOrgflag(1);
		
		//for replacing open bravo id's with our id, name in the request
		String categoryName = openBravoCategoryRepo.getCatgeoryFromOpenBravoId(product.getProduct().getCategory());
		String hsnId = openBravoHsnRepo.getHsnFromOpenBravoId(product.getProduct().getHsncode());
		if(StringUtils.isEmpty(categoryName)) {
			throw new RuntimeException("Category does not exists.");
		}
		if(StringUtils.isEmpty(hsnId)) {
			throw new RuntimeException("Hsn does not exists.");
		}
		product.getProduct().setCategory(categoryName);
		product.getProduct().setHsncode(hsnId);
		
        return productRepo.save(product);
    }
    
    private boolean validate(OpenBravoProductMaster product) {
    	List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(product.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Product entity is invalid", errorlist);
		}
		return true;
	}
}
