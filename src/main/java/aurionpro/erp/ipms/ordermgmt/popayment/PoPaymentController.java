package aurionpro.erp.ipms.ordermgmt.popayment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
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

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderService;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderView;

@RestController
@RequestMapping(value = "/ipms/popayment")
public class PoPaymentController {
	
	@Autowired
	PoPaymentRepository poPaymentRepo;
	
	@Autowired
	PoPaymentViewRepository poPaymentViewRepo;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;

	 //@PreAuthorize("hasAnyAuthority('Pending_Payment_ADD','MOB_Pending_Payment_ADD')")   
	@PostMapping()
    public PoPayment saveData(@Valid @RequestBody PoPayment request){
 		validateRequest(request, "SAVE");
        return poPaymentRepo.save(request);
    }
	
	 //@PreAuthorize("hasAnyAuthority('Pending_Payment_EDIT','MOB_Pending_Payment_EDIT')")   
	@PutMapping("/{id}")
    public PoPayment updateData(@PathVariable(value="id") long id, @Valid @RequestBody PoPayment request){
		if (id!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");

        validateRequest(request, "UPDATE");
        
        Optional<PoPayment> paymentTemp=poPaymentRepo.findById(id);
        if (paymentTemp==null) {
        	throw new EntityNotFoundException("Entity does not exists.");
        }
        return poPaymentRepo.save(request);
    }
	
	@GetMapping("/{id}")
    public Optional<PoPayment> getPoPaymentById(@PathVariable(value = "id") long id) {
    	Optional<PoPayment> paymentObj = poPaymentRepo.findById(id);
    	if (paymentObj == null) {
    		throw new EntityNotFoundException("Entity does not exists.");
    	}
		return paymentObj;
    }
	
	//@PreAuthorize("hasAnyAuthority('Pending_Payment_DELETE','MOB_Pending_Payment_DELETE')")   
	@DeleteMapping("/{id}")
    public void deletePoPaymentById(@PathVariable(value = "id") long id){
		Optional<PoPayment> paymentObj = poPaymentRepo.findById(id);
        if(paymentObj == null) {
            throw new EntityNotFoundException("Entity deos not exists.");
        }
        poPaymentRepo.deleteById(id);
    }
	
	//@PreAuthorize("hasAnyAuthority('Pending_Payment_VIEW','MOB_Pending_Payment_VIEW')")   
	@PostMapping("/searchpopayment")
    public List<PoPayment> searchPoPayment(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody PoPayment request){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PoPayment> paymentEx = Example.of(request,em);
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		return poPaymentRepo.findAll(paymentEx, paging).getContent();
    	}
    	else
    	{
    		return poPaymentRepo.findAll(paymentEx);
    	}
	
    }
	
	@GetMapping("/getpopaymentsbypoid/{poId}")
    public List<PoPayment> getPoPaymentsByPoId(@PathVariable(value = "poId") long poId) {
    	List<PoPayment> paymentList = poPaymentRepo.findByPurchaseOrderNo(poId);
		return paymentList;
    }
	
	@PostMapping("/getpendingpaymentreport")
    public PageImpl<PoPaymentView> getPendingPaymentReport(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody PoPaymentView request){
		
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PoPaymentView> paymentEx = Example.of(request,em);
		List<PoPaymentView> reportList =  poPaymentViewRepo.findAll(paymentEx);
		if(request.getToDate()!=null && request.getToDate()>0) {
			reportList = reportList.stream().filter(p -> p.getScheduleDate() <= request.getToDate()).collect(Collectors.toList());
		}
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		    {
		    	Pageable paging = PageRequest.of(page-1, size);
		    	int start =  (int) paging.getOffset();
		    	int end = Math.min((start + paging.getPageSize()), reportList.size());
		    	return new PageImpl<PoPaymentView>(reportList.subList(start, end), paging, reportList.size());
		    }
		    else
		    {
		    	return new PageImpl<PoPaymentView>(reportList);
		    }
    }
	
	@PostMapping("/getmonthwisepaymentreport")
    public PageImpl<PoPaymentView> getMonthWisePaymentReport(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody PoPaymentView request){
		if(StringUtils.isEmpty(request.getMonth()) && StringUtils.isEmpty(request.getYear())) {
			throw new RuntimeException("Please select month and year.");
		}
		
		List<PoPaymentView> reportList = poPaymentViewRepo.getMonthWisePaymentReport(request.getMonth(), request.getYear());
		
		//for pagination in mobile app
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
			Pageable paging = PageRequest.of(page-1, size);
			int start =  (int) paging.getOffset();
			int end = Math.min((start + paging.getPageSize()), reportList.size());
			return new PageImpl<PoPaymentView>(reportList.subList(start, end), paging, reportList.size());
    	}
    	else
    	{
    		 return new PageImpl<PoPaymentView>(reportList);
    	}
    }
	
	private void validateRequest(@Valid PoPayment request, String op) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getScheduleDate()))
			errorlist.add("Schedule date should not be empty/null.");
		if(StringUtils.isEmpty(request.getAmount()))
			errorlist.add("Amount should not be empty/null.");
		if(StringUtils.isEmpty(request.getCashBackReceived()))
			request.setCashBackReceived(0.0);
		if(StringUtils.isEmpty(request.getInterestPaid()))
			request.setInterestPaid(0.0);

		//for checking all po payment schedule amount with grand total of po.
		double allPoPaymentScheduleTotal = 0.0f;
		List<PoPayment> paymentList = poPaymentRepo.findByPurchaseOrderNo(request.getPurchaseOrderNo());
		if(paymentList != null){
			for(PoPayment p : paymentList){
				if(op.equalsIgnoreCase("UPDATE")) {
					if(p.getEntityId().equals(request.getEntityId()))
						continue;
				}
				allPoPaymentScheduleTotal += (p.getAmount() + p.getCashBackReceived() - p.getInterestPaid());
			}
		}
		double currentScheduleTotal = (request.getAmount() + request.getCashBackReceived() - request.getInterestPaid());
		double validTotal = allPoPaymentScheduleTotal + currentScheduleTotal;

		PurchaseOrderView po = purchaseOrderService.getPurchaseOrderInfo(request.getPurchaseOrderNo());
		if(validTotal>po.getGrandTotal()){
			errorlist.add("The total of all payment schedules for this PO is exceeding the grand total of PO.");
		}
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Payment entity is invalid", errorlist);
		}
	}
}
