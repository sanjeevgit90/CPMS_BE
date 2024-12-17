package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import aurionpro.erp.ipms.ticketmgmt.ticketreports.TicketReportRequest;
import aurionpro.erp.ipms.ticketmgmt.vendor.VendorRepository;
import aurionpro.erp.ipms.ticketmgmt.vendor.VendorView;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/ticket")
public class TicketController {

	@Autowired
	TicketRepository ticketRepo;

	@Autowired
	TicketService ticketService;
		
	@Autowired
	VendorRepository vendorRepo;


	@GetMapping()
	public Iterable<TicketMaster> GetTicket() {
		return ticketRepo.findAll();
	}
	
    @PreAuthorize("hasAnyAuthority('Ticket_Master_ADD','MOB_Ticketmaster_ADD')")   
	@PostMapping()
	public TicketMaster createTicketMaster(@Valid @RequestBody TicketMaster ticketMaster) {
		validate();
		return ticketService.save(ticketMaster);
	}
    
    @PreAuthorize("hasAnyAuthority('Ticket_Master_ADD','MOB_Ticketmaster_ADD')")   
   	@PostMapping("saveAsDraft")
   	public TicketMaster saveAsDraft(@Valid @RequestBody TicketMaster ticketMaster) {
   		validate();
   		return ticketService.saveAsDraft(ticketMaster);
   	}
	
    @PreAuthorize("hasAnyAuthority('Ticket_Master_EDIT','MOB_Ticketmaster_EDIT')")   
	 @PutMapping("{entityId}")
	    public TicketMaster updateTicket(@Valid @RequestBody TicketMaster ticketMaster){
	    	 validate();
			 return ticketService.updateTicket(ticketMaster);
	    }
    
    @PreAuthorize("hasAnyAuthority('Ticket_Master_EDIT','MOB_Ticketmaster_EDIT')")   
	 @PutMapping("updateTicketData/{entityId}")
	    public TicketMaster updateTicketData(@Valid @RequestBody TicketMaster ticketMaster){
	    	 validate();
			 return ticketService.updateTicketData(ticketMaster);
	    }
	    
    @PreAuthorize("hasAnyAuthority('Pending_Tickets_EDIT','MOB_pendingTicket_EDIT')")   
	@PostMapping("/processworkflow")
	public TicketTaskHistory processTask(@RequestParam(name = "assignName", required = false) Long assignName,
			@RequestBody TicketTaskRequest ticketTaskRequest) {
		return ticketService.processWorkflow(ticketTaskRequest, assignName);
	}

	private void validate() {

	}

	 @PreAuthorize("hasAnyAuthority('Ticket_Master_VIEW','MOB_Ticketmaster_VIEW')")   
	@PostMapping("/getTicketByFilter")
	public PageImpl<TicketMasterView> getTicketByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,
    		@RequestParam(name = "sort", required = true) String sort,
    		@RequestParam(name = "column", required = true) String column,
    		@RequestBody TicketReportRequest ticketMasterView) {

		return ticketService.getTicketByFilter(ticketMasterView, page,size, sort, column);

	}
	 
	// @PreAuthorize("hasAnyAuthority('Ticket_Master_VIEW','MOB_Ticketmaster_VIEW')")   
		@GetMapping("/getTicketByFilterWithoutBodyRequest")
		public Page<TicketMasterView> getTicketByFilterWithoutBodyRequest(@RequestParam(name = "pageNumber", required = false) Integer page,
	    		@RequestParam(name = "pageSize", required = false) Integer size,
	    		@RequestParam(name = "sortOrder", required = true) String sort,
	    		@RequestParam(name = "column", required = true) String column,
	    		@RequestParam(name = "filter", required = true) String filter) {

			return ticketService.getTicketByFilterWithoutBodyRequest(filter, page,size, sort, column);

		}
	
	 @PreAuthorize("hasAnyAuthority('Pending_Tickets_VIEW','MOB_pendingTicket_VIEW')")   
	@PostMapping("/ticketTaskByFilter")
	public PageImpl<TicketTaskView> getTicketTaskByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody TicketTaskView ticketTaskView) {
      
		return ticketService.getTicketTaskByFilter(ticketTaskView, page,size);

	}
	 
	 @PreAuthorize("hasAnyAuthority('Pending_Tickets_VIEW','MOB_pendingTicket_VIEW')")   
	@PostMapping("/ticketHistoryByFilter")
	public PageImpl<TicketTaskView> ticketHistoryByFilter(@RequestParam(name = "page", required = false) Integer page,
	    		@RequestParam(name = "size", required = false) Integer size,@RequestBody TicketTaskView ticketTaskView) {
	      
			return ticketService.getTicketHistoryByFilter(ticketTaskView, page,size);

	}
	
	// @PreAuthorize("hasAuthority('Pending_Tickets_VIEW')")
	@GetMapping("/{entityId}")
    public Optional<TicketTaskHistory> findTaskById(@PathVariable(value = "entityId") Long entityId) 
    {
    	return ticketService.findById(entityId);
    }
  
	// @PreAuthorize("hasAuthority('Ticket_Master_VIEW')")
	@GetMapping("/tic/{entityId}")
    public Optional<TicketMaster> findTicketById(@PathVariable(value = "entityId") Long entityId) 
    {
    	return ticketService.findTicketById(entityId);
    }
	
	@GetMapping("/ticketDataById/{entityId}")
    public Optional<TicketMaster> ticketDataById(@PathVariable(value = "entityId") Long entityId) 
    {
    	return ticketService.ticketDataById(entityId);
    }
	
	@GetMapping("/ticketView/{entityId}")
    public Optional<TicketMasterView> findTicketViewById(@PathVariable(value = "entityId") Long entityId) 
    {
    	return ticketService.findTicketViewById(entityId);
    }

	//@PreAuthorize("hasAnyAuthority('Pending_Tickets_VIEW','MOB_pendingTicket_VIEW')")   
	@GetMapping("/task/{ticketNo}")
	public List<TicketTaskHistory> getHistoryTicketList(@PathVariable(value = "ticketNo") String ticketNo) {
		return ticketService.getHistoryTicketList(ticketNo);
	}
	
	@Autowired
    FileUploadService uploadService;

	
	@PostMapping("/ticketAttachment")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file){
        String ticket="ticket";
        return uploadService.UploadSingleFile(ticket, file);
    }
	
	@GetMapping("/getVendorList/{id}")
	public List<VendorView> getVendorList(@PathVariable(value = "id") Long id) {
        return vendorRepo.getVendortList(id);
		//return ticketService.getTicketByFilter(ticketTaskRequest);	
	}
	
	@GetMapping("/getAllVendorList")
	public List<SelectionList> getVendorList() {
        return vendorRepo.getAllVendortList();
		//return ticketService.getTicketByFilter(ticketTaskRequest);	
	}
	
	@PostMapping("/captureImg")
    public FileResponse uploadPaymentAdvice(@RequestParam("file") MultipartFile file){
        String subFolder="ticktaskupload";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	@GetMapping("/reopenTicketByid/{id}")
	public TicketMaster reopenTicketById(@PathVariable(value = "id") Long id) {
        return ticketService.reopenTicketById(id);
		//return ticketService.getTicketByFilter(ticketTaskRequest);	
	}


}
