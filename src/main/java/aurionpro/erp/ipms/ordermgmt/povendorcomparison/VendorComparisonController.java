package aurionpro.erp.ipms.ordermgmt.povendorcomparison;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/vendorcomparison")
public class VendorComparisonController {

	@Autowired
	VendorComparisonRepository vendorComparisonRepo;
	
	@Autowired
    FileUploadService uploadService;

	@PostMapping()
    public VendorComparison saveData(@Valid @RequestBody VendorComparison request){
 		validateRequest(request);
        return vendorComparisonRepo.save(request);
    }
	
	@PutMapping("/{id}")
    public VendorComparison updateData(@PathVariable(value="id") long id, @Valid @RequestBody VendorComparison request){
		if (id!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");

        validateRequest(request);
        
        Optional<VendorComparison> tempObj=vendorComparisonRepo.findById(id);
        if (tempObj==null) {
        	throw new EntityNotFoundException("Entity does not exists.");
        }
        return vendorComparisonRepo.save(request);
    }
	
	@GetMapping("/{id}")
    public Optional<VendorComparison> getVendorComparisonById(@PathVariable(value = "id") long id) {
    	Optional<VendorComparison> obj = vendorComparisonRepo.findById(id);
    	if (obj == null) {
    		throw new EntityNotFoundException("Entity does not exists.");
    	}
		return obj;
    }
	
	@DeleteMapping("/{id}")
    public void deleteVendorComparisonById(@PathVariable(value = "id") long id){
		Optional<VendorComparison> obj = vendorComparisonRepo.findById(id);
        if(obj == null) {
            throw new EntityNotFoundException("Entity deos not exists.");
        }
        vendorComparisonRepo.deleteById(id);
    }
	
	@PostMapping("/searchvendorcomparison")
    public List<VendorComparison> searchVendorComparison(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody VendorComparison request){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<VendorComparison> objEx = Example.of(request,em);
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		return vendorComparisonRepo.findAll(objEx, paging).getContent();
    	}
    	else
    	{
    		return vendorComparisonRepo.findAll(objEx);
    	}
	
    }
	
	@GetMapping("/getvendorcomparisonsbypoid/{poId}")
    public List<VendorComparison> getVendorComparisonsByPoId(@PathVariable(value = "poId") long poId) {
    	List<VendorComparison> objList = vendorComparisonRepo.findByPurchaseOrderNo(poId);
		return objList;
    }
	
	@PostMapping("/uploadattachments/{folderName}")
    public FileResponse uploadAnnexure(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
    	String subFolder = "Vendor Comparison/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	private void validateRequest(@Valid VendorComparison request) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getCustomerName()))
			errorlist.add("Customer name should not be empty/null.");
		if(StringUtils.isEmpty(request.getAmount()))
			errorlist.add("Amount should not be empty/null.");
		if(StringUtils.isEmpty(request.getDeliveryTime()))
			errorlist.add("Delivery date should not be empty/null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Vendor comparison entity is invalid", errorlist);
		}
	}
}
