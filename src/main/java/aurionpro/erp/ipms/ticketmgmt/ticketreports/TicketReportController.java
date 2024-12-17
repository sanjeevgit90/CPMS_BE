package aurionpro.erp.ipms.ticketmgmt.ticketreports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.ticketmgmt.ticketmaster.TicketMasterView;
import aurionpro.erp.ipms.ticketmgmt.trip.TripMaster;

@RestController
@RequestMapping(value = "/ipms/ticketReport")
public class TicketReportController {

	@Autowired
	TicketReportService reportService;

	
	@PostMapping("/getIncidentReportByFilter")
	public PageImpl<TicketMasterView> getIncidentReportByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TicketReportRequest ticketMasterView) {
		return reportService.getIncidentReportByFilter(ticketMasterView, page, size);
	}
	
	@PostMapping("/missedSLAResolutionReport")
	public PageImpl<SLATicketReport> missedSLAResolutionReport(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TicketReportRequest ticketMasterView) {
		return reportService.missedSLAResolutionReport(ticketMasterView, page, size);
	}
	
	@PostMapping("/ticketAgingReport")
	public PageImpl<TicketAgeing> ticketAgingReport(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TicketAgeing request) {
		return reportService.ticketAgingReport(request, page, size);
	}
	
	@PostMapping("/getAllOrphanTickets")
	public List<TripMaster> getAllOrphanTickets(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TripMaster ticketMasterView) {
		return reportService.getAllOrphanTickets(ticketMasterView, page, size);
	}
	
	@PostMapping("/getEscalationReport")
	public PageImpl<TicketMasterView> getEscalationReport(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TicketMasterView ticketMasterView) {
		return reportService.getEscalationReport(ticketMasterView, page, size);
	}
	
	@PostMapping("/getTicketMisReport")
	public PageImpl<TicketMasterView> getTicketMisReport(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TicketReportRequest ticketMasterView) {
		return reportService.getTicketMisReport(ticketMasterView, page, size);
	}


}
