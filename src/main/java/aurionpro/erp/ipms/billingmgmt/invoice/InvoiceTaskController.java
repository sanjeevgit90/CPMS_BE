package aurionpro.erp.ipms.billingmgmt.invoice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ipms/invoicetask")
public class InvoiceTaskController {

    @Autowired
    private InvoiceService invoiceService;
    
	
    @PreAuthorize("hasAuthority('Invoice_Task_ADD')")
    @PostMapping("/taskByFilters")
    public Iterable<InvoiceTaskView> taskByFilters(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody InvoiceTaskView proj){
	      return invoiceService.taskByFilters(proj, page, size);
    }

    
    @PreAuthorize("hasAuthority('Invoice_Task_VIEW')")
     @GetMapping("/{id}")
    public Optional<InvoiceTaskMaster> gettaskById(@PathVariable(value = "id") Long id){
    	return invoiceService.taskByid(id);
    }

    @PreAuthorize("hasAuthority('Invoice_Task_EDIT')")
     @PutMapping()
    public InvoiceMaster processInvoice(@RequestBody InvoiceRequest proj){       
       return invoiceService.processInvoice(proj);
    }

    @PreAuthorize("hasAuthority('Invoice_Task_VIEW')")
    @PostMapping("/getAllHistoryTasks")
    public List<InvoiceTaskView> getAllHistoryTasks(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody InvoiceTaskView request){
        return invoiceService.getAllHistoryTasks(request, page, size);
    }
    
    @PreAuthorize("hasAuthority('Invoice_Task_VIEW')")
    @GetMapping("/getHistoryById/{id}")
    public List<InvoiceTaskView> getHistoryById(@PathVariable(value = "id") long id){
        return invoiceService.getHistoryById(id);
    }
}