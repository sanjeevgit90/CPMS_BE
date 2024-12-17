package aurionpro.erp.ipms.ordermgmt.vendorinvoicepayment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.ordermgmt.prs.Prs;
import aurionpro.erp.ipms.ordermgmt.prs.PrsService;

@RestController
@RequestMapping(value = "/ipms/vendorinvoice")
public class VendorInvoicePaymentController {

	@Autowired
	VendorInvoicePaymentRepository vendorInvoicePaymentRepo;
	
	@Autowired
	PrsService prsService;
	
	@PostMapping()
    public VendorInvoicePayment createVendorPayment(@Valid @RequestBody VendorInvoicePayment request){
		validateRequest(request, "ADD");
        return vendorInvoicePaymentRepo.save(request);
    }

	@PutMapping("/{paymentId}")
    public VendorInvoicePayment updateVendorInvoicePayment(@PathVariable(value="paymentId") long paymentId, @Valid @RequestBody VendorInvoicePayment request){
        if (paymentId!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");
        
        validateRequest(request, "UPDATE");

        Optional<VendorInvoicePayment> paymentTemp=vendorInvoicePaymentRepo.findById(paymentId);

        if (paymentTemp==null) {
        	throw new RuntimeException("Party entity does not exists.");
        }
        return vendorInvoicePaymentRepo.save(request);
    }
	
	@PostMapping("/getallvendorpayments")
    public List<VendorInvoicePayment> getAllVendorPayments(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody VendorInvoicePayment request){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<VendorInvoicePayment> paymentEx = Example.of(request,em);
		
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	    		Pageable paging = PageRequest.of(page, size);
	    		return vendorInvoicePaymentRepo.findAll(paymentEx, paging).getContent();
	    	}
	    	else
	    	{
	    		return vendorInvoicePaymentRepo.findAll(paymentEx);
	    	}
    }
	
	@GetMapping("/{id}")
    public Optional<VendorInvoicePayment> getVendorPaymentById(@PathVariable(value="id") long paymentId){
        return vendorInvoicePaymentRepo.findById(paymentId);
    }
	
	@DeleteMapping("/{id}")
    public void deleteVendorPaymentById(@PathVariable(value="id") long paymentId){
		VendorInvoicePayment paymentTemp=vendorInvoicePaymentRepo.findById(paymentId).get();
        if (paymentTemp==null) {
        	throw new RuntimeException("Vendor payment entity does not exists.");
        }
        vendorInvoicePaymentRepo.deleteById(paymentId);
    }
	
	@GetMapping("/getallvendorpaymentsbyprs/{id}")
    public List<VendorInvoicePayment> getAllVendorPaymentsByPrs(@PathVariable(value="id") long prsId){
		return vendorInvoicePaymentRepo.findByPrsId(prsId);
    }
	
	public float getTotalPaymentsOfPrsById(long prsId, long paymentId) {
		float totalPaymentsForPrs= 0.0f;
		Object obj = vendorInvoicePaymentRepo.getTotalPaymentsOfPrsById(prsId, paymentId);
		if(obj!=null) {
			totalPaymentsForPrs = Float.parseFloat(obj.toString());
		}
		return totalPaymentsForPrs;
	}
	
	private boolean validateRequest(VendorInvoicePayment request, String op) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getPaymentDate()))
			errorlist.add("Payment date cannot be null.");
		if(StringUtils.isEmpty(request.getAmount()))
			errorlist.add("Amount cannot be null.");
		if(StringUtils.isEmpty(request.getPaymentReference()))
			errorlist.add("Payment reference cannot be null.");
		if(StringUtils.isEmpty(request.getRemark()))
			errorlist.add("Remark cannot be null.");
		
		if(!StringUtils.isEmpty(request.getPrsId())) {
			Prs prsObj = prsService.getPrsById(request.getPrsId()).get();
			if(prsObj == null) {
				throw new RuntimeException("PRS entity does not exists.");
			}
			float totalPayments = (op.equalsIgnoreCase("ADD")) ? getTotalPaymentsOfPrsById(request.getPrsId(), 0) : getTotalPaymentsOfPrsById(request.getPrsId(), request.getEntityId());
	   		float balanceAmount = (float) (prsObj.getInvoiceAmount() - totalPayments);
			if(totalPayments+request.getAmount() > prsObj.getInvoiceAmount())
				errorlist.add("Total amount for all vendor payments should not exceed the PRS invoice amount(\u20B9"+ prsObj.getInvoiceAmount()+"), balance amount is \u20B9"+balanceAmount);
		}
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Vendor payment entity is invalid", errorlist);
		}
		return true;
	}
}
