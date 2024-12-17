package aurionpro.erp.ipms.ticketmgmt.ticketreports;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.ticketmgmt.ticketmaster.TicketMasterView;
import aurionpro.erp.ipms.ticketmgmt.ticketmaster.TicketViewRepository;
import aurionpro.erp.ipms.ticketmgmt.trip.TripMaster;
import aurionpro.erp.ipms.ticketmgmt.trip.TripRepository;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;


@Service()
public class TicketReportService {
	
	@Autowired
	TicketViewRepository ticketRepository;
	
	@Autowired
	SLATicketRepository SLARepo;
	
	@Autowired
	TicketAgeingRepository ageRepo;
	
	 @Autowired
	 ProjectUtil projectFilterService;  
	 
	 @Autowired
	 TripRepository tripRepo;
		
    public PageImpl<TicketMasterView> getIncidentReportByFilter(TicketReportRequest tkt, Integer page, Integer size){
    	
        Long userId = MyPrincipal.getMyProfile().getUserProfileId();
		
        TicketReportRequest ticketRequest = castFilterValues(tkt);
		List<TicketMasterView> ticketList= ticketRepository.getTicketByFilter(ticketRequest.getTicketNo(),
				ticketRequest.getAccountName(), ticketRequest.getPriority(), 
				ticketRequest.getState(),ticketRequest.getDistrict(),ticketRequest.getLocation(),ticketRequest.getTicketStatus(),
				ticketRequest.getTicketTitle(),ticketRequest.getAssetid(),
				ticketRequest.getFromDate(), ticketRequest.getToDate(),ticketRequest.getTicketOwner(), userId, "DESC", "createddate");
	
	        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	           Pageable paging = PageRequest.of(page-1, size);
			   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), ticketList.size());
	     		
	           return new PageImpl<TicketMasterView>(ticketList.subList(start, end), paging, ticketList.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<>(ticketList);
	    	}
		  
   }
   
	/*  public PageImpl<TicketMasterView> getIncidentReportByFilter(TicketReportRequest tkt, Integer page, Integer size){
    	
    	TicketMasterView ticket = new TicketMasterView();
    	ticket.setTicketNo(tkt.getTicketNo());
    	ticket.setState(tkt.getState());
    	ticket.setAccountName(tkt.getAccountName());
    	ticket.setPriority(tkt.getPriority());
    	ticket.setDistrict(tkt.getDistrict());
        ticket.setIsdeleted(false);
        ExampleMatcher em=ExampleMatcher.matching()
                           .withIgnoreNullValues()
                           .withIgnoreCase()
                           .withStringMatcher(StringMatcher.CONTAINING);

       Example<TicketMasterView> ticketEx=Example.of(ticket,em);
      
       List<TicketMasterView> ticketTaskList = ticketRepository.findAll(ticketEx);
       Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
       List<Long> id;
       if( tkt.getFromDate()!=null && tkt.getToDate()!= null )
       {
    	   id = ticketRepository.getIncidentTickets(profileId, tkt.getFromDate(),tkt.getToDate());  
       }
       else if( tkt.getFromDate()!=null && tkt.getToDate()== null )
       {
    	   id = ticketRepository.getTicketFromDate(profileId, tkt.getFromDate());  
       }
       else if( tkt.getFromDate()==null && tkt.getToDate()!= null )
       {
    	  id = ticketRepository.getTicketToDate(profileId, tkt.getFromDate());  
       }
       else
       {
    	  id = ticketRepository.getIncidentTickets(profileId); 
       }
		   List<TicketMasterView> filteredProject = ticketTaskList.stream()  
		         	     .filter(f->id.contains(f.getEntityId())).collect(Collectors.toList());
	       
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
			 Pageable paging = PageRequest.of(page-1, size);
	    	   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), filteredProject.size());
	     		
	           return new PageImpl<TicketMasterView>(filteredProject.subList(start, end), paging, filteredProject.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<TicketMasterView>(filteredProject);
	    	}
		  
   } */

	public PageImpl<SLATicketReport> missedSLAResolutionReport(TicketReportRequest tkt, Integer page, Integer size) {

		SLATicketReport ticket = new SLATicketReport();
    	ticket.setTicketNo(tkt.getTicketNo());
    	ticket.setState(tkt.getState());
    	ticket.setAccountName(tkt.getAccountName());
    	ticket.setPriority(tkt.getPriority());
    	ticket.setDistrict(tkt.getDistrict());
        ticket.setIsdeleted(false);

		 ExampleMatcher em=ExampleMatcher.matching()
		                  .withIgnoreNullValues()
		                  .withIgnoreCase()
		                  .withStringMatcher(StringMatcher.CONTAINING);
		
		Example<SLATicketReport> ticketEx=Example.of(ticket,em);
		List<SLATicketReport> ticketTaskList = SLARepo.findAll(ticketEx);
		
		 Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       List<Long> id;
	       
	       if( tkt.getFromDate()!=null && tkt.getToDate()!= null )
	       {
	    	   id = SLARepo.getSLATicket(profileId, tkt.getFromDate(),tkt.getToDate());  
	       }
	       else if( tkt.getFromDate()!=null && tkt.getToDate()== null )
	       {
	    	   id = SLARepo.getSLATicketFromDate(profileId, tkt.getFromDate());  
	       }
	       else if( tkt.getFromDate()==null && tkt.getToDate()!= null )
	       {
	    	  id = SLARepo.getSLATicketToDate(profileId, tkt.getFromDate());  
	       }
	       else
	       {
	    	  id = SLARepo.getSLATicketTickets(profileId); 
	       }
			   List<SLATicketReport> filteredProject = ticketTaskList.stream()  
			         	     .filter(f->id.contains(f.getEntityId())).collect(Collectors.toList());
		       
			 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		    	{
				 Pageable paging = PageRequest.of(page-1, size);
		    	   int start =  (int) paging.getOffset();
		           int end = Math.min((start + paging.getPageSize()), filteredProject.size());
		     		
		           return new PageImpl<SLATicketReport>(filteredProject.subList(start, end), paging, filteredProject.size());
		      
		    	}
		    	else
		    	{
		    		 return new PageImpl<SLATicketReport>(filteredProject);
		    	}
	}

	public PageImpl<TicketMasterView> getEscalationReport(TicketMasterView ticketMasterView, Integer page, Integer size) {
		ticketMasterView.setIsdeleted(false);
		ticketMasterView.setTicketStatus("ESCALATED");
		ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

		Example<TicketMasterView> ticketEx=Example.of(ticketMasterView,em);

		List<TicketMasterView> ticketList =  ticketRepository.findAll(ticketEx);
	    Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       
		   List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
		       
		   List<TicketMasterView> filteredTicket = ticketList.stream()  
		         	     .filter(f->projectId.contains(f.getAccountName())).collect(Collectors.toList());
		         
		   if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
			 Pageable paging = PageRequest.of(page-1, size);
	    	   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), filteredTicket.size());
	     		
	           return new PageImpl<TicketMasterView>(filteredTicket.subList(start, end), paging, filteredTicket.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<TicketMasterView>(filteredTicket);
	    	}
	}

	public PageImpl<TicketAgeing> ticketAgingReport(TicketAgeing request, Integer page, Integer size) {
		request.setIsdeleted(false);
		ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

		Example<TicketAgeing> ticketEx=Example.of(request,em);

		List<TicketAgeing> ticketList = ageRepo.findAll(ticketEx);
	    Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       
		List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
		       
		List<TicketAgeing> filteredProject = ticketList.stream()  
		         	     .filter(f->projectId.contains(f.getAccountName())).collect(Collectors.toList());
		       
	    if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		    {
				 Pageable paging = PageRequest.of(page, size);
		    	   int start =  (int) paging.getOffset();
		           int end = Math.min((start + paging.getPageSize()), filteredProject.size());
		     		
		        return new PageImpl<TicketAgeing>(filteredProject.subList(start, end), paging, filteredProject.size());
		      
		    }
		    else
		    {
		         return new PageImpl<TicketAgeing>(filteredProject);
		    }
	}

	public List<TripMaster> getAllOrphanTickets(TripMaster request, Integer page, Integer size) {
		ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

		Example<TripMaster> ticketEx=Example.of(request,em);

	    if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	   	{
	   		Pageable paging = PageRequest.of(page, size);
	   		return tripRepo.findAll(ticketEx, paging).getContent();
	   	}
	   	else
	   	{
	   		return tripRepo.findAll(ticketEx);
	   	}

	}
	
/* 	public PageImpl<TicketMasterView> getTicketMisReport(TicketReportRequest tkt, Integer page, Integer size) {
		TicketMasterView ticket = new TicketMasterView();
		
		ticket.setTicketNo(tkt.getTicketNo());
    	ticket.setState(tkt.getState());
    	ticket.setAccountName(tkt.getAccountName());
    	ticket.setPriority(tkt.getPriority());
    	ticket.setDistrict(tkt.getDistrict());
    	ticket.setLocation(tkt.getLocation());
    	ticket.setTicketStatus(tkt.getTicketStatus());
        ticket.setIsdeleted(false);
        ExampleMatcher em=ExampleMatcher.matching()
                           .withIgnoreNullValues()
                           .withIgnoreCase()
                           .withStringMatcher(StringMatcher.CONTAINING);

       Example<TicketMasterView> ticketEx=Example.of(ticket,em);
      
       List<TicketMasterView> ticketTaskList = ticketRepository.findAll(ticketEx);
       Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
       List<Long> id;
       if( tkt.getFromDate()!=null && tkt.getToDate()!= null )
       {
    	   id = ticketRepository.getIncidentTickets(profileId, tkt.getFromDate(),tkt.getToDate());  
       }
       else if( tkt.getFromDate()!=null && tkt.getToDate()== null )
       {
    	   id = ticketRepository.getTicketFromDate(profileId, tkt.getFromDate());  
       }
       else if( tkt.getFromDate()==null && tkt.getToDate()!= null )
       {
    	  id = ticketRepository.getTicketToDate(profileId, tkt.getFromDate());  
       }
       else
       {
    	  id = ticketRepository.getIncidentTickets(profileId); 
       }
		   List<TicketMasterView> filteredProject = ticketTaskList.stream()  
		         	     .filter(f->id.contains(f.getEntityId())).collect(Collectors.toList());
	       
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
			 Pageable paging = PageRequest.of(page-1, size);
	    	   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), filteredProject.size());
	     		
	           return new PageImpl<TicketMasterView>(filteredProject.subList(start, end), paging, filteredProject.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<TicketMasterView>(filteredProject);
	    	}
	}*/

	public PageImpl<TicketMasterView> getTicketMisReport(TicketReportRequest tkt, Integer page, Integer size) {
		Long userId = MyPrincipal.getMyProfile().getUserProfileId();
		
        TicketReportRequest ticketRequest = castFilterValues(tkt);
		List<TicketMasterView> ticketList= ticketRepository.getTicketByFilter(ticketRequest.getTicketNo(),
				ticketRequest.getAccountName(), ticketRequest.getPriority(), 
				ticketRequest.getState(),ticketRequest.getDistrict(),ticketRequest.getLocation(),ticketRequest.getTicketStatus(),
				ticketRequest.getTicketTitle(),ticketRequest.getAssetid(),
				ticketRequest.getFromDate(), ticketRequest.getToDate(),ticketRequest.getTicketOwner(), userId ,"DESC", "createddate");
	
	        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	           Pageable paging = PageRequest.of(page-1, size);
			   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), ticketList.size());
	     		
	           return new PageImpl<TicketMasterView>(ticketList.subList(start, end), paging, ticketList.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<>(ticketList);
	    	}
	}
	
	 private TicketReportRequest castFilterValues(TicketReportRequest tkt) {
		 TicketReportRequest ticketRequest = new TicketReportRequest();
			long value = 0;
			if (tkt.getTicketNo() == null)
			{
				ticketRequest.setTicketNo("");
			}
			else
			{
				ticketRequest.setTicketNo(tkt.getTicketNo());
			}
			
			if (tkt.getAccountName() == null)
			{
				ticketRequest.setAccountName(value);
			}
			else
			{
				ticketRequest.setAccountName(tkt.getAccountName());
			}
			
			if (tkt.getPriority() == null)
			{
				ticketRequest.setPriority("");
			}
			else
			{
				ticketRequest.setPriority(tkt.getPriority());
			}
			if (tkt.getState() == null)
			{
				ticketRequest.setState("");
			}
			else
			{
				ticketRequest.setState(tkt.getState());
			}
			
			if (tkt.getDistrict() == null)
			{
				ticketRequest.setDistrict("");
			}
			else
			{
				ticketRequest.setDistrict(tkt.getDistrict());
			}
			
			if (tkt.getLocation() == null)
			{
				ticketRequest.setLocation("");
			}
			else
			{
				ticketRequest.setLocation(tkt.getLocation());
			}
			if (tkt.getTicketStatus() == null)
			{
				ticketRequest.setTicketStatus("");
			}
			else
			{
				ticketRequest.setTicketStatus(tkt.getTicketStatus());
			}
			
			if (tkt.getTicketTitle() == null)
			{
				ticketRequest.setTicketTitle("");
			}
			else
			{
				ticketRequest.setTicketTitle(tkt.getTicketTitle());
			}
			
			if (tkt.getAssetid() == null)
			{
				ticketRequest.setAssetid(value);
			}
			else
			{
				ticketRequest.setAssetid(tkt.getAssetid());
			}
			if (tkt.getFromDate() == null)
			{
				ticketRequest.setFromDate(value);
			}
			else
			{
				ticketRequest.setFromDate(tkt.getFromDate());
			}
			if (tkt.getToDate() == null)
			{
				ticketRequest.setToDate(value);
			}
			else
			{
				ticketRequest.setToDate(tkt.getToDate());
			}
			if (tkt.getTicketOwner() == null)
			{
				ticketRequest.setTicketOwner(value);
			}
			else
			{
				ticketRequest.setTicketOwner(tkt.getTicketOwner());
			}
				
			return ticketRequest;
		}
	
}
