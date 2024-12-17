package aurionpro.erp.ipms.billingmgmt.invoice;

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
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PreAuthorize("hasAuthority('Invoice_ADD')")
    @PostMapping()
    public InvoiceMaster createInvoice(@Valid @RequestBody InvoiceMaster invoice){
    		 validate();
 			 return invoiceService.createInvoice(invoice);
    }
    
    @PreAuthorize("hasAuthority('Invoice_ADD')")
    @GetMapping("/submitInvoice/{id}")
    public InvoiceMaster submitInvoice(@PathVariable(value = "id") Long id){
    		 validate();
 			 return invoiceService.submitInvoice(id);
    }
    
    @PreAuthorize("hasAuthority('Invoice_EDIT')")
    @PutMapping()
    public InvoiceMaster updateInvoice(@Valid @RequestBody InvoiceMaster invoice){
    	 validate();
		 return invoiceService.updateInvoice(invoice);
    }
    
  //  @PreAuthorize("hasAuthority('Invoice_VIEW')")
    @GetMapping("/{id}")
    public Optional<InvoiceMaster> findInvoiceById(@PathVariable(value = "id") Long id) 
    {
    	return invoiceService.findInvoiceById(id);
    }
    
    @PreAuthorize("hasAuthority('Invoice_DELETE')")
    @DeleteMapping("/{id}")
    public InvoiceMaster deleteInvoice(@PathVariable(value = "id") Long id){
           return invoiceService.deleteInvoice(id);
    }
  
    @PreAuthorize("hasAuthority('Invoice_VIEW')")
    @PostMapping("/invoiceByFilter/{projectid}")
    public List<InvoiceView> searchInvoice(@PathVariable(value = "projectid") Long projectid,
    		@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody InvoiceView invoice){
    		invoice.setProjectid(projectid);
    		return invoiceService.searchInvoice(invoice, page,size);
    }
       
    private void validate() {
    }
    
    @GetMapping("/getInvoiceListFromProject/{projectid}")
    public List<SelectionList> getInvoiceListFromProject(@PathVariable(value = "projectid") Long projectid){
        return invoiceService.getInvoiceListFromProject(projectid);
    } 
    
    @GetMapping("/getActiveInvoiceFromProject/{projectid}")
    public List<SelectionList> getActiveInvoiceFromProject(@PathVariable(value = "projectid") Long projectid){
        return invoiceService.getActiveInvoiceFromProject(projectid);
    } 
    
    @PreAuthorize("hasAuthority('Invoice_VIEW')")
    @GetMapping("/invoiceFromProject/{projectid}")
    public List<InvoiceMaster> invoiceFromProject(@PathVariable(value = "projectid") Long projectid){
        return invoiceService.invoiceFromProject(projectid);
    } 
    @Autowired
    FileUploadService uploadService;

    @PreAuthorize("hasAuthority('Invoice_Collection_ADD')")
    @PostMapping("/uploadInvoiceexcel")
    public FileResponse uploadInvoiceexcel(@RequestParam("file") MultipartFile file){
        String subFolder="invoice/excel";
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PreAuthorize("hasAuthority('Invoice_Collection_ADD')")
    @PostMapping("/uploadInvoicesupportingdoc")
    public FileResponse uploadInvoicesupportingdoc(@RequestParam("file") MultipartFile file){
        String subFolder="invoice/SupportingDoc";
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PreAuthorize("hasAuthority('Invoice_Collection_ADD')")
    @PostMapping("/uploadAccountexcel")
    public FileResponse uploadAccountexcel(@RequestParam("file") MultipartFile file){
        String subFolder="invoice/Accountexcel";
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PreAuthorize("hasAuthority('Invoice_Collection_ADD')")
    @PostMapping("/uploadInvoiceSignedExcel")
    public FileResponse uploadInvoiceSignedExcel(@RequestParam("file") MultipartFile file){
        String subFolder="invoice/InvoiceSignedExcel";
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
}