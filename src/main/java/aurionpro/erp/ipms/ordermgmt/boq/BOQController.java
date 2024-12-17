package aurionpro.erp.ipms.ordermgmt.boq;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.ordermgmt.boqproduct.BOQProductChild;

@RestController
@RequestMapping(value = "/ipms/boq")
public class BOQController {

	@Autowired
	BOQService boqService;
	
	 @PreAuthorize("hasAuthority('BOQ_Master_ADD')")
	@PostMapping()
	public BOQ saveBOQ(@Valid @RequestBody BOQ request) {
		return boqService.saveBOQ(request);
	};
		
	 @PreAuthorize("hasAuthority('BOQ_Master_VIEW')")
		@PostMapping("/getAllBOQs")
	public List<BOQ> getAllBOQs(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody BOQ request) {
		return boqService.getAllBOQs(request, page,size);
	};

	//@GetMapping("/getBOQSingleRecord/{id}")
	@GetMapping("/{id}")
	public Optional<BOQ> getSingleBOQRecord(@PathVariable(value="id") long id) {
		return boqService.getSingleBOQRecord(id);
	};

	 @PreAuthorize("hasAuthority('BOQ_Master_EDIT')")
	@PutMapping("/editBOQ/{id}")
	public BOQ editBOQ(@PathVariable(value="id") long id, @Valid @RequestBody BOQ request) {
		return boqService.editBOQ(id, request);
	};
	
	@GetMapping("/boqselectionlist")
    public List<SelectionList> getSelectionBoqList(){
        return boqService.getSelectionBoqList();
    }
	
//	@GetMapping("/getProductDropDown")
//	Response getProductDropDown();
	
	@PostMapping("/saveBOQProduct")
	public BOQProductChild saveBOQProductChild(@Valid @RequestBody BOQProductChild request) {
		return boqService.saveBOQProductChild(request);
	};
	
	@PutMapping("/editBOQProduct/{id}")
	public BOQProductChild editBOQProductChild(@PathVariable(value="id") long id, @Valid @RequestBody BOQProductChild request) {
		return boqService.editBOQProductChild(id, request);
	};
	
	@GetMapping("/deleteBOQProductById/{id}")
	public void deleteBOQProductById(@PathVariable(value="id") long id) {
		boqService.deleteBOQProductById(id);
	};
	
	@DeleteMapping("/deleteBOQProductChild")
	public void deleteBOQProductChild(@RequestBody BOQProductChild request) {
		boqService.deleteBOQProductChild(request);
	};
	
	@GetMapping("/getBOQAuditTrail/{id}")
	public Optional<BOQAuditTrail> getBOQProductAuditTrail(@PathVariable(value="id") long id) {
		return boqService.getBOQProductAuditTrail(id);
	};
}
