package aurionpro.erp.ipms.ordermgmt.ratecontract;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Lob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/ratecontract")
public class RateContractController {

	@Autowired
	RateContractService rateContractService;
	
	@Autowired
    FileUploadService uploadService;
	
	 @PreAuthorize("hasAnyAuthority('Rate_Contract_ADD','MOB_RateContract_ADD')")   
	@PostMapping("/addratecontract")
    public RateContractMaster createRateContract(@RequestBody RateContractMaster rateContractRequest){
        return rateContractService.createRateContract(rateContractRequest);
    }
	
	 @PreAuthorize("hasAnyAuthority('Rate_Contract_EDIT','MOB_RateContract_EDIT')")   
		@PutMapping("/{orderId}")
    public RateContractMaster updateRateContract(@PathVariable(value="orderId") long orderId, @RequestBody RateContractMaster rateContractRequest){
        return rateContractService.updateRateContract(orderId, rateContractRequest);
    }
	
	 @PreAuthorize("hasAnyAuthority('Rate_Contract_VIEW','MOB_RateContract_VIEW')")   
	@GetMapping("/getallratecontracts")
    public List<RateContractMaster> getAllRateContracts(){
        return rateContractService.getAllRateContracts();
    }
	
	@GetMapping("/getorderbyid/{id}")
    public Optional<RateContractMaster> getOrderById(@PathVariable(value="id") long id){
        return rateContractService.getOrderById(id);
    }
	
	@Lob
	@GetMapping("/getorderbyidview/{id}")
    public Optional<RateContractView> getOrderByIdView(@PathVariable(value="id") long id){
        return rateContractService.getOrderByIdView(id);
    }
	
	@PreAuthorize("hasAnyAuthority('Rate_Contract_DELETE','MOB_RateContract_DELETE')")   
	@GetMapping("/deleteorderbyid/{id}")
    public void deleteOrderById(@PathVariable(value="id") long id){
		rateContractService.deleteOrderById(id);
    }
	
	@PreAuthorize("hasAnyAuthority('Rate_Contract_VIEW','MOB_RateContract_VIEW')")   
	@PostMapping("/getallrcbyfilter")
	public PageImpl<RateContractView> getAllRcByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody RateContractView poRequest){
		return rateContractService.getAllRcByFilter(poRequest, page, size);
	}
	
	@GetMapping("/getratecontractinfo/{id}")
	public RateContractView getRateContractInfo(@PathVariable(value="id") long id){
		return rateContractService.getRateContractInfo(id);
	}
	
	@GetMapping("/rcselectionlist")
    public List<SelectionList> getSelectionRcList(){
        return rateContractService.selectionRcList();
    }
	
	@GetMapping("/rcapprovedselectionlist")
    public List<SelectionList> getSelectionApprovedRcList(){
        return rateContractService.selectionApprovedRcList();
    }
	
	@PostMapping("/updategrandtotal")
	public RateContractMaster updateGrandTotal(@RequestBody RateContractMaster poRequest){
		return rateContractService.updateGrandTotal(poRequest);
	}
	
	@PostMapping("/generateduplicateorder")
	public RateContractMaster generateDuplicateOrder(@RequestBody RateContractView request){
		return rateContractService.generateDuplicateOrder(request);
	}
	
	@PostMapping("/uploadrcattachments/{folderName}")
    public FileResponse uploadPoAttachments(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
        String subFolder = "Rate Contract/Attachments/" + folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PostMapping("/uploadsignedcopy/{folderName}")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
    	String subFolder = "Rate Contract/Signed Copies/" + folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PostMapping("/uploadannexure/{folderName}")
    public FileResponse uploadAnnexure(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
    	String subFolder = "Rate Contract/Annexures/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	//---------------for history details table
	@PostMapping("/addratecontracthistorydetails")
    public RateContractHistoryDetails createRcHistoryDetails(@RequestBody RateContractMaster request){
		return rateContractService.saveRcHistoryDetails(request, "SAVE");
    }
	
	@PutMapping("/updateratecontracthistorydetails/{orderId}")
    public RateContractHistoryDetails updateRcHistoryDetails(@PathVariable(value="orderId") long orderId, @RequestBody RateContractHistoryDetails request){
        return rateContractService.updateRcHistoryDetails(orderId, request);
    }
	
	@GetMapping("/getorderhistorybyid/{id}")
    public Optional<RateContractHistoryDetails> getOrderHistoryById(@PathVariable(value="id") long id){
        return rateContractService.getOrderHistoryById(id);
    }
	
	@GetMapping("/deleteorderhistorybyid/{id}")
    public void deleteOrderHistoryById(@PathVariable(value="id") long id){
		rateContractService.deleteOrderHistoryById(id);
    }
	
	public boolean validated() {
		return true;
	}
	//-----------------for history details table
}
