package aurionpro.erp.ipms.ordermgmt.prs;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
@RequestMapping(value = "/ipms/prs")
public class PrsController {

	@Autowired
	PrsService prsService;
	
	@PreAuthorize("hasAnyAuthority('PRS_Master_ADD','MOB_PRS_Master_ADD')")   
	@PostMapping("/addprs")
    public Prs savePrs(@Valid @RequestBody Prs prsRequest){
        return prsService.savePrs(prsRequest);
    }
	
	@PreAuthorize("hasAnyAuthority('PRS_Master_EDIT','MOB_PRS_Master_EDIT')")   
	@PutMapping("/updateprs/{prsId}")
    public Prs updatePrs(@PathVariable(value="prsId") long prsId, @Valid @RequestBody Prs prsRequest){
        return prsService.updatePrs(prsId, prsRequest);
    }
	
	@GetMapping("/getprsbyid/{id}")
    public Optional<Prs> getPrsById(@PathVariable(value="id") long id){
        return prsService.getPrsById(id);
    }
	
	@PreAuthorize("hasAnyAuthority('PRS_Master_DELETE','MOB_PRS_Master_DELETE')")   
	@GetMapping("/deactiveprsbyid/{id}")
    public Prs deactivePrsById(@PathVariable(value="id") long id){
		return prsService.deactivePrsById(id);
    }
	
	@PreAuthorize("hasAnyAuthority('PRS_Master_VIEW','MOB_PRS_Master_VIEW')")   
	@PostMapping("/getallprsbyfilter")
	public PageImpl<PrsView> getAllPrsByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody PrsView prsRequest){
		return prsService.getAllPrsByFilter(prsRequest, page, size);
	}
	
	@GetMapping("/prsselectionlist")
    public List<SelectionList> getSelectionPrsList(){
        return prsService.getSelectionPrsList();
    }
	
	@GetMapping("/getPrsView/{id}")
    public Optional<PrsView> getPrsView(@PathVariable(value="id") long id){
        return prsService.getPrsView(id);
    }
	
	@PostMapping("/getprsreport")
	public PageImpl<PrsView> getPrsReport(@RequestBody PrsView prsRequest, @RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size){
		return prsService.getPrsReport(prsRequest, page, size);
	}
	
	@Autowired
    FileUploadService uploadService;

   // @PreAuthorize("hasAuthority('Invoice_Collection_ADD')")
    @PostMapping("/uploadInvoiceFile")
    public FileResponse uploadInvoiceFile(@RequestParam("file") MultipartFile file){
        String subFolder="prs/invoiceFile";
        return uploadService.UploadSingleFile(subFolder, file);
    }
    @PostMapping("/uploadAttachedBill")
    public FileResponse uploadAttachedBill(@RequestParam("file") MultipartFile file){
        String subFolder="prs/attachedBill";
        return uploadService.UploadSingleFile(subFolder, file);
    }
    @PostMapping("/uploadAttachments")
    public FileResponse uploadAttachments(@RequestParam("file") MultipartFile file){
        String subFolder="prs/attachments";
        return uploadService.UploadSingleFile(subFolder, file);
    }
}
