package aurionpro.erp.ipms.ordermgmt.rcproductdetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import aurionpro.erp.ipms.assetmgmt.productmaster.ProductMaster;
import aurionpro.erp.ipms.assetmgmt.productmaster.ProductMasterRepository;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractMaster;
import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractRepository;
import aurionpro.erp.ipms.vendor.addressmaster.AddressMasterRepository;

@Service
public class RcProductDetailsService {

	@Autowired
	RcProductDetailsRepository productDetailsRepository;
	
	@Autowired
    ProductMasterRepository productRepo;
	
	@Autowired
	RateContractRepository rateContractRepo;
	
	@Autowired
	RcProductDetailsViewRepository productDetailsViewRepo;
	
	@Autowired
	AddressMasterRepository addressMasterRepo;
	
	public RcProductDetails addProductDetail(RcProductDetails productDetailsRequest){
		validateRequest(productDetailsRequest);
		
		//for saving historic columns
		ProductMaster product = productRepo.findById(productDetailsRequest.getProductName()).get();
		productDetailsRequest.setBaseUomData(product.getBaseuom());
		String productNameWithDesc;
		productNameWithDesc = product.getProductname();
		if(productDetailsRequest.getDescription()!=null || product.getDescription()!=null) {
			if(productDetailsRequest.getDescription()!=null && product.getDescription()==null)
				productNameWithDesc += " ("+productDetailsRequest.getDescription()+")";
			if(productDetailsRequest.getDescription()==null && product.getDescription()!=null)
				productNameWithDesc += " ("+product.getDescription()+")";
			if(productDetailsRequest.getDescription()!=null && product.getDescription()!=null)
				productNameWithDesc += " ("+product.getDescription()+" - "+productDetailsRequest.getDescription()+")";
		}
		productDetailsRequest.setProductNameWithDesc(productNameWithDesc);
		//for saving historic columns
		
		RcProductDetails obj1 = productDetailsRepository.save(productDetailsRequest);
				
        return obj1;
    }
	
    public RcProductDetails updateProductDetail(@PathVariable(value="id") long id, @RequestBody RcProductDetails productDetailsRequest){
        if (id!=productDetailsRequest.getEntityId())
        	throw new RuntimeException("Request mismatch");
        
        validateRequest(productDetailsRequest);

        Optional<RcProductDetails> orderTemp=productDetailsRepository.findById(id);

        if (orderTemp==null) {
        	throw new RuntimeException("Product detail entity does not exists.");
        }
        
        //for saving historic columns
		ProductMaster product = productRepo.findById(productDetailsRequest.getProductName()).get();
		productDetailsRequest.setBaseUomData(product.getBaseuom());
		String productNameWithDesc;
		productNameWithDesc = product.getProductname();
		if(productDetailsRequest.getDescription()!=null || product.getDescription()!=null) {
			if(productDetailsRequest.getDescription()!=null && product.getDescription()==null)
				productNameWithDesc += " ("+productDetailsRequest.getDescription()+")";
			if(productDetailsRequest.getDescription()==null && product.getDescription()!=null)
				productNameWithDesc += " ("+product.getDescription()+")";
			if(productDetailsRequest.getDescription()!=null && product.getDescription()!=null)
				productNameWithDesc += " ("+product.getDescription()+" - "+productDetailsRequest.getDescription()+")";
		}
		productDetailsRequest.setProductNameWithDesc(productNameWithDesc);
		//for saving historic columns
		
		RcProductDetails obj1 = productDetailsRepository.save(productDetailsRequest);

        return obj1;
    }
	
    public List<RcProductDetails> getAllProductDetails(){
        return productDetailsRepository.findAll();
    }
	
    public Optional<RcProductDetails> getProductDetailById(@PathVariable(value="id") long id){
        return productDetailsRepository.findById(id);
    }
	
    public void deleteProductDetailById(@PathVariable(value="id") long id){
		productDetailsRepository.deleteById(id);
    }
    
    public void deleteProductListByRc(RateContractMaster rc){
		productDetailsRepository.deleteProductListByRc(rc);
    }
	
	private boolean validateRequest(RcProductDetails request) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getProductName()))
			errorlist.add("Product cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Product details entity is invalid", errorlist);
		}
        return true;
    }

	public List<RcProductDetails> getProductDetailsByRcId(RateContractMaster rc) {
		return productDetailsRepository.findByRateContract(rc);
	}
	
	public List<RcProductDetailsView> searchProductDetailsByRcId(RateContractMaster rc) {
		return productDetailsViewRepo.findByRateContract(rc.getEntityId());
	}

	public RcProductDetails updateStates(RcProductDetails productDetailsRequest) {
		List<RcProductDetails> l = productDetailsRepository.findByRateContract(productDetailsRequest.getRateContract());
		for(RcProductDetails p : l)
		{
			p.setAmount(p.getRate()*p.getQuantity());
			p.setTotalDiscount(p.getDiscountAmount()+(p.getDiscount()/100*p.getAmount()));
			p.setTotalAmount(p.getAmount()-p.getTotalDiscount()); 
			if(productDetailsRequest.getIsHistoricData().equalsIgnoreCase("NO"))
			{
				if(productDetailsRequest.getApplyGstFlag().equalsIgnoreCase("YES"))
				{
					if(productDetailsRequest.getStateFlag()) {
						p.setCgstAmount((p.getCgst()/100)*p.getTotalAmount());
						p.setSgstAmount((p.getSgst()/100)*p.getTotalAmount());
						p.setIgstAmount(0.0);
						p.setFinalAmount(p.getTotalAmount()+(((p.getCgst()+p.getSgst())/100)*p.getTotalAmount()));
					} else {
						p.setCgstAmount(0.0);
						p.setSgstAmount(0.0);
						p.setIgstAmount((p.getIgst()/100)*p.getTotalAmount());
						p.setFinalAmount(p.getTotalAmount()+((p.getIgst()/100)*p.getTotalAmount()));
					}
				}
				else
				{
					productDetailsRequest.setCgstAmount(0.0);
					productDetailsRequest.setSgstAmount(0.0);
					productDetailsRequest.setIgstAmount(0.0);
					productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount());
				}
			}
			else if(productDetailsRequest.getIsHistoricData().equalsIgnoreCase("YES"))
			{
				double i = 0.0;
				productDetailsRequest.setCgstAmount(i);
				productDetailsRequest.setSgstAmount(i);
				productDetailsRequest.setIgstAmount(i);
				productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+productDetailsRequest.getServiceTax()+productDetailsRequest.getVat());
			}
			productDetailsRepository.save(productDetailsRequest);
		}
		return null;
	}

	public void checkAllProductCalculations(RateContractMaster rc) {
		List<RcProductDetailsView> prodDetails = productDetailsViewRepo.findByRateContract(rc.getEntityId());
		if(prodDetails.isEmpty() || prodDetails == null)
			return;
		
		if(rc.getIsHistoricData().equalsIgnoreCase("YES"))
			return;

		RcProductDetails prodReq = null;
		boolean isSameState = false;
		isSameState = addressMasterRepo.compareStateOfAddress(rc.getSupplierDetails(), rc.getBillToAddress());
		
		for(RcProductDetailsView p : prodDetails) {
			prodReq = new RcProductDetails();
			prodReq.setEntityId(p.getEntityId());
			if(rc.getCurrency().equalsIgnoreCase("INR")){
				if(rc.getIsHistoricData().equalsIgnoreCase("NO")){
					if(!rc.getBillFromGstNo().equalsIgnoreCase("NA")){
						if(isSameState)
						{
							prodReq.setCgstAmount((p.getCgst()/100)*(p.getTotalAmount()));
							prodReq.setSgstAmount((p.getSgst()/100)*(p.getTotalAmount()));
							prodReq.setIgstAmount(0.0);
							prodReq.setFinalAmount(p.getTotalAmount()+((p.getCgst()+p.getSgst())/100*p.getTotalAmount()));
						}
						else
						{
							prodReq.setIgstAmount((p.getIgst()/100)*(p.getTotalAmount()));
							prodReq.setCgstAmount(0.0);
							prodReq.setSgstAmount(0.0);
							prodReq.setFinalAmount(p.getTotalAmount()+((p.getIgst())/100*p.getTotalAmount()));	
						}
					}
					else {
						prodReq.setCgstAmount(0.0);
						prodReq.setSgstAmount(0.0);
						prodReq.setIgstAmount(0.0);
						prodReq.setFinalAmount(p.getTotalAmount());
					}
				}
				//this is not needed as the final amount will be same for every product as service tax and vat amount remain same.
				/*else if(po.getIsHistoricData().equalsIgnoreCase("YES")){
					double i = 0.0;
					prodReq.setCgstAmount(i);
					prodReq.setSgstAmount(i);
					prodReq.setIgstAmount(i);
					prodReq.setFinalAmount(p.getToatalAmount()+p.getServiceTax()+p.getVat());
				}*/
			}
			else {
				prodReq.setIgstAmount((p.getIgst()/100)*(p.getTotalAmount()));
				prodReq.setCgstAmount(0.0);
				prodReq.setSgstAmount(0.0);
				prodReq.setFinalAmount(p.getTotalAmount()+p.getIgstAmount());
			}
			updateAllProductCalculations(prodReq);
		}
	}
	
	public RcProductDetails updateAllProductCalculations(RcProductDetails request) {
		RcProductDetails entity = productDetailsRepository.findById(request.getEntityId()).get();
		entity.setCgstAmount(request.getCgstAmount());
		entity.setSgstAmount(request.getSgstAmount());
		entity.setIgstAmount(request.getIgstAmount());
		entity.setFinalAmount(request.getFinalAmount());
		productDetailsRepository.save(entity);
		return entity;
	}
}
